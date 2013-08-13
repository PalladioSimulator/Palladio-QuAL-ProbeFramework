package edu.kit.ipd.sdq.probespec.framework;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ProbeRegistry<T> {

    private static final Logger logger = Logger.getLogger(ProbeRegistry.class);

    /**
     * allows looking up the region for a given probe type (represented by its class) 
     */
    @SuppressWarnings("rawtypes")
    private Map<Class<? extends Probe>, IProbeRegistryRegion<?>> probeTypeToRegionMap;
    
    private Map<Class<?>, IProbeRegistryRegion<?>> regions;

    @SuppressWarnings("rawtypes")
    public ProbeRegistry() {
        this.probeTypeToRegionMap = new HashMap<Class<? extends Probe>, IProbeRegistryRegion<?>>();
        this.regions = new HashMap<Class<?>, IProbeRegistryRegion<?>>();
    }
    
    public <V> void registerProbe(Probe<V> probe, Object annotatedEntity) {
        createOrFindRegion(probe.getGenericClass()).registerProbe(probe, annotatedEntity);
        probeTypeToRegionMap.put(probe.getClass(), createOrFindRegion(probe.getGenericClass()));
    }
    
    
    public <V> Probe<V> getProbe(Object annotatedEntity, Class<? extends Probe<V>> probeType) {
        return findRegionForProbeType(probeType).getProbe(annotatedEntity, probeType);
    }
    
    private <V> IProbeRegistryRegion<V> findRegionForProbeType(Class<? extends Probe<V>> probeType) {
        @SuppressWarnings("unchecked")
        IProbeRegistryRegion<V> r = (IProbeRegistryRegion<V>) probeTypeToRegionMap.get(probeType);
        return r;
    }
    
    private <V> IProbeRegistryRegion<V> createOrFindRegion(Class<V> clazz) {
        @SuppressWarnings("unchecked")
        IProbeRegistryRegion<V> r = (IProbeRegistryRegion<V>) this.regions.get(clazz);
        if (r == null) {
            r = new SimpleProbeRegistryRegion<V>();
            this.regions.put(clazz, r);
            logger.debug("Created probe registry region for " + clazz);
        }
        return r;
    }

}
