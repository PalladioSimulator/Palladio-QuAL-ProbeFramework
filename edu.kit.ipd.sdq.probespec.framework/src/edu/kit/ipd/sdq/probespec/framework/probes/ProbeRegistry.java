package edu.kit.ipd.sdq.probespec.framework.probes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.Probe;


public class ProbeRegistry<T> {

    private static final Logger logger = Logger.getLogger(ProbeRegistry.class);

    /**
     * allows looking up the region for a given probe type (represented by its class)
     */
    @SuppressWarnings("rawtypes")
    private Map<Class<? extends Probe>, ProbeRegistryRegion<?>> probeTypeToRegionMap;

    private Map<Class<?>, ProbeRegistryRegion<?>> regions;

    private Set<String> probeIds;

    @SuppressWarnings("rawtypes")
    public ProbeRegistry() {
        this.probeTypeToRegionMap = new HashMap<Class<? extends Probe>, ProbeRegistryRegion<?>>();
        this.regions = new HashMap<Class<?>, ProbeRegistryRegion<?>>();
        this.probeIds = new HashSet<String>();
    }

    public <V> void mountProbe(Probe<V> probe, Object entity) {
        mountProbe(probe, entity, null); // TODO: use DEFAULT_MOUNT_POINT or the like
    }

    public <V> void mountProbe(Probe<V> probe, Object entity, Object mountPoint) {
        // ensure uniqueness of probe IDs
        if (probeIds.contains(probe.getId())) {
            logger.error("Cannot register probe with ID \"" + probe.getId() + "\" because this ID is already in use. "
                    + "Make sure that probes have unique IDs and that probes are registered only once.");
        }
        probeIds.add(probe.getId());

        // register probe
        createOrFindRegion(probe.getGenericClass()).mountProbe(probe, entity, mountPoint);
        probeTypeToRegionMap.put(probe.getClass(), createOrFindRegion(probe.getGenericClass()));
    }

    public <V> Probe<V> getProbe(Object entity, Class<? extends Probe<V>> probeType) {
        return findRegionForProbeType(probeType).getProbe(entity, probeType);
    }
    
    public <V> Probe<V> getProbe(Object entity, Object mountPoint, Class<? extends Probe<V>> probeType) {
        return findRegionForProbeType(probeType).getProbe(entity, mountPoint, probeType);
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

}
