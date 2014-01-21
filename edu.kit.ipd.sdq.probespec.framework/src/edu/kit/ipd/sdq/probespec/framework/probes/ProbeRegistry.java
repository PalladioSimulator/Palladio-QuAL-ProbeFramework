package edu.kit.ipd.sdq.probespec.framework.probes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.Calculator;
import edu.kit.ipd.sdq.probespec.framework.MeasurableEntity;
import edu.kit.ipd.sdq.probespec.framework.Probe;

public class ProbeRegistry<T> {

    private static final Logger logger = Logger.getLogger(ProbeRegistry.class);

    /**
     * allows looking up the region for a given probe type (represented by its class)
     */
    @SuppressWarnings("rawtypes")
    private Map<Class<? extends Probe>, ProbeRegistryRegion<?>> probeTypeToRegionMap;

    private Map<Class<?>, ProbeRegistryRegion<?>> regions;

    @SuppressWarnings("rawtypes")
    public ProbeRegistry() {
        this.probeTypeToRegionMap = new HashMap<Class<? extends Probe>, ProbeRegistryRegion<?>>();
        this.regions = new HashMap<Class<?>, ProbeRegistryRegion<?>>();
    }

    public <V> void mountProbe(Probe<V> probe, MeasurableEntity entity) {
        createOrFindRegion(probe.getGenericClass()).mountProbe(probe, entity);
        probeTypeToRegionMap.put(probe.getClass(), createOrFindRegion(probe.getGenericClass()));
    }

    public <V> void mountProbe(Probe<V> probe, MeasurableEntity entity, Object mountPoint) {
        createOrFindRegion(probe.getGenericClass()).mountProbe(probe, entity, mountPoint);
        probeTypeToRegionMap.put(probe.getClass(), createOrFindRegion(probe.getGenericClass()));
    }

    public <V> void mountCalculatedProbe(Probe<V> probe, Calculator<V> calculator) {
        createOrFindRegion(probe.getGenericClass()).mountCalculatedProbe(probe, calculator);
        probeTypeToRegionMap.put(probe.getClass(), createOrFindRegion(probe.getGenericClass()));
    }

    public <V> Probe<V> getProbe(MeasurableEntity entity, Class<? extends Probe<V>> probeType) {
        return findRegionForProbeType(probeType).getProbe(entity, probeType);
    }

    public <V> Probe<V> getProbe(MeasurableEntity entity, Object mountPoint, Class<? extends Probe<V>> probeType) {
        return findRegionForProbeType(probeType).getProbe(entity, mountPoint, probeType);
    }

    public Set<Probe<?>> getProbes(MeasurableEntity entity, Object mountPoint) {
        Set<Probe<?>> probes = new HashSet<Probe<?>>();
        for (ProbeRegistryRegion<?> r : regions.values()) {
            probes.addAll(r.getProbes(entity, mountPoint));
        }
        return probes;
    }

    public Set<Probe<?>> getProbes() {
        Set<Probe<?>> probes = new HashSet<Probe<?>>();
        for (ProbeRegistryRegion<?> r : regions.values()) {
            probes.addAll(r.getProbes());
        }
        return probes;
    }

    public Set<Probe<?>> getCalculatedProbes() {
        Set<Probe<?>> probes = new HashSet<Probe<?>>();
        for (ProbeRegistryRegion<?> r : regions.values()) {
            probes.addAll(r.getCalculatedProbes());
        }
        return probes;
    }

    public Set<MeasurableEntity> getEntities() {
        Set<MeasurableEntity> entities = new HashSet<MeasurableEntity>();
        for (ProbeRegistryRegion<?> region : regions.values()) {
            entities.addAll(region.getEntities());
        }
        return entities;
    }

    public Set<Object> getMeasuringPointsForEntity(MeasurableEntity entity) {
        Set<Object> measuringPoints = new HashSet<Object>();
        for (ProbeRegistryRegion<?> region : regions.values()) {
            measuringPoints.addAll(region.getMeasuringPointsForEntity(entity));
        }
        return measuringPoints;
    }

    private <V> ProbeRegistryRegion<V> findRegionForProbeType(Class<? extends Probe<V>> probeType) {
        @SuppressWarnings("unchecked")
        ProbeRegistryRegion<V> r = (ProbeRegistryRegion<V>) probeTypeToRegionMap.get(probeType);
        return r;
    }

    private <V> ProbeRegistryRegion<V> createOrFindRegion(Class<V> clazz) {
        @SuppressWarnings("unchecked")
        ProbeRegistryRegion<V> r = (ProbeRegistryRegion<V>) this.regions.get(clazz);
        if (r == null) {
            r = new SimpleProbeRegistryRegion<V>();
            this.regions.put(clazz, r);
            logger.debug("Created probe registry region for " + clazz);
        }
        return r;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ProbeRegistryRegion<?> region : regions.values()) {
            result.append(region.toString() + "\n");
        }
        return result.toString();
    }

}
