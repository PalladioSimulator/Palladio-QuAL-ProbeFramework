package edu.kit.ipd.sdq.probespec.palladio;

import java.util.Set;

import javax.management.RuntimeErrorException;

import probespec.PBasicProbe;
import probespec.PCalculationProbe;
import probespec.PMeasurableEntity;
import probespec.PProbe;
import probespec.ProbeSpecRepository;
import probespec.calculatortype.CalculatorTypeRepository;
import probespec.probetype.ProbeTypeRepository;
import edu.kit.ipd.sdq.probespec.framework.Calculator;
import edu.kit.ipd.sdq.probespec.framework.MeasurableEntity;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorRegistry;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding.BinaryCalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding.UnaryCalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.probes.ProbeRegistry;

public class ConfigurationReader<T> {

    private ProbeSpecRepository probeSpecRepository;
    private ProbeTypeRepository probeTypeRepository;
    private CalculatorTypeRepository calculatorTypeRepository;
    private ProbeRegistry<T> probeRegistry;
    private CalculatorRegistry<T> calculatorRegistry;

    public ConfigurationReader(ProbeSpecRepository probeSpecRepository, ProbeTypeRepository probeTypeRepository,
            CalculatorTypeRepository calculatorTypeRepository, ProbeRegistry<T> probeRegistry,
            CalculatorRegistry<T> calculatorRegistry) {
        this.probeSpecRepository = probeSpecRepository;
        this.probeTypeRepository = probeTypeRepository;
        this.calculatorTypeRepository = calculatorTypeRepository;
        this.probeRegistry = probeRegistry;
        this.calculatorRegistry = calculatorRegistry;
    }

    public void adjustProbes() {
        for (PProbe pp : probeSpecRepository.getProbes()) {
            Probe<?> p = null;
            if (PBasicProbe.class.isInstance(pp)) {
                PBasicProbe pbp = (PBasicProbe) pp;
                MeasurableEntity entity = getEntity(pbp.getMeasuringPoint().getEntity().getId());
                String measuringPoint = pbp.getMeasuringPoint().getName();
                p = findBasicProbe(entity, measuringPoint, pp.getType().getJavaClass());
            } else if (PCalculationProbe.class.isInstance(pp)) {
                PCalculationProbe pcp = (PCalculationProbe) pp;
                p = findCalculationProbe(pcp.getCalculator());
            } else {
                throw new RuntimeException("Encountered unexpected probe type.");
            }
            p.setActive(pp.isActive());
            p.setPersistent(pp.isPersistent());
            p.setName(pp.getName());
        }
    }

    private Probe<?> findBasicProbe(MeasurableEntity entity, String measuringPoint, String javaClass) {
        Set<Probe<?>> probes = probeRegistry.getProbes(entity, measuringPoint);
        for (Probe<?> p : probes) {
            if (p.getClass().getName().equals(javaClass)) {
                return p;
            }
        }

        // TODO better throw exception?
        return null;
    }

    @SuppressWarnings("unchecked")
    private Probe<?> findCalculationProbe(Calculator<?> calculator) {
        for (CalculatorBinding b : calculatorRegistry.getCalculatorBindings()) {
            if (UnaryCalculatorBinding.class.isInstance(b)) {
                UnaryCalculatorBinding<?, ?, Double> ub = (UnaryCalculatorBinding<?, ?, Double>) b;
                if (ub.getCalculator().equals(calculator)) {
                    return ((UnaryCalculatorBinding<?, ?, Double>) b).getOutProbe();
                }
            } else if (BinaryCalculatorBinding.class.isInstance(b)) {
                BinaryCalculatorBinding<?, ?, ?, Double> bb = (BinaryCalculatorBinding<?, ?, ?, Double>) b;
                if (bb.getCalculator().equals(calculator)) {
                    return ((BinaryCalculatorBinding<?, ?, ?, Double>) b).getOutProbe();
                }
            } else {
                throw new RuntimeException("Encountered unexpected calculator binding type.");
            }
        }
        return null;
    }

    private MeasurableEntity getEntity(String id) {
        for (MeasurableEntity e : probeRegistry.getEntities()) {
            if (e.getMeasurableEntityId().equals(id)) {
                return e;
            }
        }
        // TODO better throw exception?
        return null;
    }

}
