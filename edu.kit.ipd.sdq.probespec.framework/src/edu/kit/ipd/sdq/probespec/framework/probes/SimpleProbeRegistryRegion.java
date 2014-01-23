package edu.kit.ipd.sdq.probespec.framework.probes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import edu.kit.ipd.sdq.probespec.framework.Calculator;
import edu.kit.ipd.sdq.probespec.framework.Probe;

public class SimpleProbeRegistryRegion<V> implements ProbeRegistryRegion<V> {

    private static final Logger logger = Logger.getLogger(SimpleProbeRegistryRegion.class);

    public static String DEFAULT_MOUNT_POINT = "DEFAULT";

    /** maps (entity, mountPoint)-pairs to probes */
    private Multimap<KeyPair<Object, Object>, Probe<V>> entityAndMeasuringPointMap;

    private Map<Calculator<V>, Probe<V>> calculatorsMap;

    private Set<String> probeIds;

    public SimpleProbeRegistryRegion() {
        entityAndMeasuringPointMap = HashMultimap.create();
        calculatorsMap = new HashMap<Calculator<V>, Probe<V>>();
        probeIds = new HashSet<String>();
    }

    @Override
    public Probe<V> getProbe(Object entity, Object mountPoint, Class<? extends Probe<?>> probeType) {
        KeyPair<Object, Object> key = new KeyPair<Object, Object>(entity, mountPoint);
        return getProbe(key, probeType);
    }

    @Override
    public Probe<V> getProbe(Object entity, Class<? extends Probe<?>> probeType) {
        return getProbe(entity, DEFAULT_MOUNT_POINT, probeType);
    }

    @Override
    public Set<Probe<V>> getProbes(Object entity, Object mountPoint) {
        Set<Probe<V>> probes = new HashSet<Probe<V>>();
        KeyPair<Object, Object> key = new KeyPair<Object, Object>(entity, mountPoint);
        probes.addAll(entityAndMeasuringPointMap.get(key));
        return probes;
    }

    @Override
    public Set<Probe<V>> getProbes() {
        Set<Probe<V>> probes = new HashSet<Probe<V>>();
        probes.addAll(entityAndMeasuringPointMap.values());
        probes.addAll(calculatorsMap.values());
        return probes;
    }

    // TODO inline?
    private Probe<V> getProbe(KeyPair<Object, Object> key, Class<? extends Probe<?>> clazz) {
        for (Probe<V> p : entityAndMeasuringPointMap.get(key)) {
            if (p.getClass().equals(clazz)) {
                return p;
            }
        }
        // TODO: log warning?
        return null;
    }

    @Override
    public Set<Object> getEntities() {
        Set<Object> entities = new HashSet<Object>();
        for (KeyPair<Object, Object> keyPair : entityAndMeasuringPointMap.keySet()) {
            entities.add(keyPair.getEntity());
        }
        return entities;
    }

    @Override
    public Set<Object> getMeasuringPointsForEntity(Object entity) {
        Set<Object> measuringPoints = new HashSet<Object>();
        for (KeyPair<Object, Object> keyPair : entityAndMeasuringPointMap.keySet()) {
            Object e = keyPair.getEntity();
            if (e.equals(entity)) {
                measuringPoints.add(keyPair.getMountPoint());
            }
        }
        return measuringPoints;
    }

    @Override
    public void mountProbe(Probe<V> probe, Object entity) {
        KeyPair<Object, Object> key = new KeyPair<Object, Object>(entity, DEFAULT_MOUNT_POINT);
        entityAndMeasuringPointMap.put(key, probe);
    }

    @Override
    public void mountProbe(Probe<V> probe, Object entity, Object mountPoint) {
        // ensure uniqueness of probe IDs
        if (probeIds.contains(probe.getId())) {
            logger.error("Cannot register probe with ID \"" + probe.getId() + "\" because this ID is already in use. "
                    + "Make sure that probes have unique IDs and that probes are registered only once.");
        }
        probeIds.add(probe.getId());

        // prevent installation of two probes of the same type for the same entity
        @SuppressWarnings("unchecked")
        Class<? extends Probe<V>> clazz = (Class<? extends Probe<V>>) probe.getClass();
        if (getProbe(entity, mountPoint, clazz) != null) {
            logger.error("Skipping probe mounting: there is already a probe of type " + clazz.getSimpleName()
                    + " mounted at mount point " + mountPoint + " of entity " + entity);
            return;
        }

        KeyPair<Object, Object> key = new KeyPair<Object, Object>(entity, mountPoint);
        entityAndMeasuringPointMap.put(key, probe);
    }

    private static class KeyPair<K1, K2> {

        private K1 entity;

        private K2 mountPoint;

        public KeyPair(K1 entity, K2 mountPoint) {
            this.entity = entity;
            this.mountPoint = mountPoint;
        }

        public K1 getEntity() {
            return entity;
        }

        public K2 getMountPoint() {
            return mountPoint;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((entity == null) ? 0 : entity.hashCode());
            result = prime * result + ((mountPoint == null) ? 0 : mountPoint.hashCode());
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
            KeyPair<?, ?> other = (KeyPair<?, ?>) obj;
            if (entity == null) {
                if (other.entity != null)
                    return false;
            } else if (!entity.equals(other.entity))
                return false;
            if (mountPoint == null) {
                if (other.mountPoint != null)
                    return false;
            } else if (!mountPoint.equals(other.mountPoint))
                return false;
            return true;
        }

    }

}
