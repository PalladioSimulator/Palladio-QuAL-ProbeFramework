package edu.kit.ipd.sdq.probespec.palladio;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import probespec.CalculatorParameter;
import probespec.PBasicProbe;
import probespec.PBinaryCalculator;
import probespec.PCalculationProbe;
import probespec.PCalculator;
import probespec.PMeasurableEntity;
import probespec.PMeasuringPoint;
import probespec.PProbe;
import probespec.PUnaryCalculator;
import probespec.ProbeSpecRepository;
import probespec.ProbespecFactory;
import probespec.calculatortype.CalculatorType;
import probespec.calculatortype.CalculatorTypeRepository;
import probespec.probetype.ProbeType;
import probespec.probetype.ProbeTypeRepository;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import edu.kit.ipd.sdq.probespec.framework.MeasurableEntity;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorRegistry;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding.BinaryCalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding.UnaryCalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.probes.ProbeRegistry;

public class ConfigurationWriter<T> {

    private ProbeTypeRepository probeTypeRepository;
    private CalculatorTypeRepository calculatorTypeRepository;
    private ProbeRegistry<T> probeRegistry;
    private CalculatorRegistry<T> calculatorRegistry;

    private BiMap<MeasurableEntity, PMeasurableEntity> entityMap = HashBiMap.create();
    private BiMap<KeyPair, PMeasuringPoint> measuringPointEntityMap = HashBiMap.create();
    private BiMap<Probe<?>, PProbe> probeMap = HashBiMap.create();
    private BiMap<CalculatorBinding, PCalculator> calculatorMap = HashBiMap.create();

    public ConfigurationWriter(ProbeTypeRepository probeTypeRepository,
            CalculatorTypeRepository calculatorTypeRepository, ProbeRegistry<T> probeRegistry,
            CalculatorRegistry<T> calculatorRegistry) {
        this.probeTypeRepository = probeTypeRepository;
        this.calculatorTypeRepository = calculatorTypeRepository;
        this.probeRegistry = probeRegistry;
        this.calculatorRegistry = calculatorRegistry;
    }

    public ProbeSpecRepository createConfiguration() {
        // repository
        ProbeSpecRepository repository = ProbespecFactory.eINSTANCE.createProbeSpecRepository();

        // entities + measuring points
        createEntityMap();
        Set<PMeasurableEntity> entities = entityMap.values();
        repository.getEntities().addAll(entities);

        // probes
        Set<PProbe> probes = new HashSet<PProbe>();
        for (PMeasurableEntity pe : entities) {
            for (PMeasuringPoint pp : pe.getMeasuringPoints()) {
                MeasurableEntity e = entityMap.inverse().get(pe);
                Object measuringPoint = measuringPointEntityMap.inverse().get(pp).measuringPoint;
                probes.addAll(createProbes(e, measuringPoint));
            }
        }
        repository.getProbes().addAll(probes);
        repository.getProbes().addAll(createCalculatedProbes());

        // calculators
        createCalculatorMap();
        repository.getCalculators().addAll(calculatorMap.values());

        return repository;
    }

    private void createEntityMap() {
        Set<MeasurableEntity> entities = probeRegistry.getEntities();
        for (MeasurableEntity e : entities) {
            PMeasurableEntity pe = ProbespecFactory.eINSTANCE.createPMeasurableEntity();
            pe.setId(e.getMeasurableEntityId());
            pe.setName(e.getMeasurableEntityName());
            Set<PMeasuringPoint> measuringPoints = createMeasuringPointsForEntity(e);
            pe.getMeasuringPoints().addAll(measuringPoints);
            entityMap.put(e, pe);
        }
    }

    private void createCalculatorMap() {
        Set<CalculatorBinding> calculators = calculatorRegistry.getCalculatorBindings();
        for (CalculatorBinding b : calculators) {
            if (UnaryCalculatorBinding.class.isInstance(b)) {
                @SuppressWarnings("unchecked")
                UnaryCalculatorBinding<?, ?, Double> ub = (UnaryCalculatorBinding<?, ?, Double>) b;
                PUnaryCalculator pc = ProbespecFactory.eINSTANCE.createPUnaryCalculator();
                pc.setInputProbe(probeMap.get(ub.getInProbe()));
                pc.setOutputProbe((PCalculationProbe) probeMap.get(ub.getOutProbe()));
                pc.setType(getCalculatorType(ub.getCalculator().getClass()));
                pc.getParameters().addAll(createParameters(ub.getCalculator().getParameters()));
                calculatorMap.put(b, pc);
            } else if (BinaryCalculatorBinding.class.isInstance(b)) {
                @SuppressWarnings("unchecked")
                BinaryCalculatorBinding<?, ?, ?, Double> ub = (BinaryCalculatorBinding<?, ?, ?, Double>) b;
                PBinaryCalculator pc = ProbespecFactory.eINSTANCE.createPBinaryCalculator();
                pc.setFirstInputProbe(probeMap.get(ub.getIn1Probe()));
                pc.setSecondInputProbe(probeMap.get(ub.getIn2Probe()));
                pc.setOutputProbe((PCalculationProbe) probeMap.get(ub.getOutProbe()));
                pc.setType(getCalculatorType(ub.getCalculator().getClass()));
                pc.getParameters().addAll(createParameters(ub.getCalculator().getParameters()));
                calculatorMap.put(b, pc);
            }
        }
    }

    private Set<CalculatorParameter> createParameters(Map<String, String> parameters) {
        Set<CalculatorParameter> result = new HashSet<CalculatorParameter>();
        for (Entry<String, String> e : parameters.entrySet()) {
            String parameterName = e.getKey();
            String parameterValue = e.getValue();
            CalculatorParameter param = ProbespecFactory.eINSTANCE.createCalculatorParameter();
            param.setName(parameterName);
            param.setValue(parameterValue);
            result.add(param);
        }
        return result;
    }

    private Set<PMeasuringPoint> createMeasuringPointsForEntity(MeasurableEntity entity) {
        Set<Object> measuringPoints = probeRegistry.getMeasuringPointsForEntity(entity);
        Set<PMeasuringPoint> result = new HashSet<PMeasuringPoint>();
        for (Object o : measuringPoints) {
            PMeasuringPoint p = ProbespecFactory.eINSTANCE.createPMeasuringPoint();
            p.setId(entity.getMeasurableEntityId() + "." + o.toString()); // TODO
            p.setName(o.toString()); // TODO
            result.add(p);
            measuringPointEntityMap.put(new KeyPair(entity, o), p);
        }
        return result;
    }

    private Set<PProbe> createProbes(MeasurableEntity entity, Object measuringPoint) {
        Set<Probe<?>> probes = probeRegistry.getProbes(entity, measuringPoint);
        Set<PProbe> result = new HashSet<PProbe>();
        for (Probe<?> p : probes) {
            PBasicProbe pp = ProbespecFactory.eINSTANCE.createPBasicProbe();
            pp.setActive(p.isActive());
            pp.setId(p.getId());
            pp.setMeasuringPoint(measuringPointEntityMap.get(new KeyPair(entity, measuringPoint)));
            pp.setName(p.getName());
            pp.setPersistent(p.isPersistent());
            pp.setType(getProbeType(p.getClass()));
            result.add(pp);
            probeMap.put(p, pp);
        }
        return result;
    }

    private Set<PProbe> createCalculatedProbes() {
        Set<Probe<?>> probes = probeRegistry.getCalculatedProbes();
        Set<PProbe> result = new HashSet<PProbe>();
        for (Probe<?> p : probes) {
            PCalculationProbe pp = ProbespecFactory.eINSTANCE.createPCalculationProbe();
            pp.setActive(p.isActive());
            pp.setId(p.getId());
            pp.setName(p.getName());
            pp.setPersistent(p.isPersistent());
            pp.setType(getProbeType(p.getClass()));
            result.add(pp);
            probeMap.put(p, pp);
        }
        return result;
    }

    private ProbeType getProbeType(Class<?> clazz) {
        for (ProbeType p : probeTypeRepository.getProbeTypes()) {
            if (p.getJavaClass().equals(clazz.getName())) {
                return p;
            }
        }
        return null; // TODO better throw exception!?
    }

    private CalculatorType getCalculatorType(Class<?> clazz) {
        for (CalculatorType c : calculatorTypeRepository.getCalculatorTypes()) {
            if (c.getJavaClass().equals(clazz.getName())) {
                return c;
            }
        }
        return null; // TODO better throw exception!?
    }

    private class KeyPair {

        MeasurableEntity entity;

        Object measuringPoint;

        public KeyPair(MeasurableEntity entity, Object measuringPoint) {
            this.entity = entity;
            this.measuringPoint = measuringPoint;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((entity == null) ? 0 : entity.hashCode());
            result = prime * result + ((measuringPoint == null) ? 0 : measuringPoint.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            @SuppressWarnings("unchecked")
            KeyPair other = (KeyPair) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (entity == null) {
                if (other.entity != null)
                    return false;
            } else if (!entity.equals(other.entity))
                return false;
            if (measuringPoint == null) {
                if (other.measuringPoint != null)
                    return false;
            } else if (!measuringPoint.equals(other.measuringPoint))
                return false;
            return true;
        }

        private ConfigurationWriter<?> getOuterType() {
            return ConfigurationWriter.this;
        }

    }

}
