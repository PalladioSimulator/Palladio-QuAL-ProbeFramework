package edu.kit.ipd.sdq.probespec.framework.probes;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;


public class SimpleProbeRegistryRegion<V> implements IProbeRegistryRegion<V> {

    /** maps (entity, mountPoint)-pairs to probes */
    private Multimap<KeyPair<Object, Object>, Probe<V>> probeMap;
    
    public SimpleProbeRegistryRegion() {
        probeMap = HashMultimap.create();
    }
    
    @Override
    public Probe<V> getProbe(Object entity, Class<? extends Probe<?>> probeType) {
        KeyPair<Object, Object> key = new KeyPair<Object, Object>(entity, null);
        return getProbe(key, probeType);
    }
    
    @Override
    public Probe<V> getProbe(Object entity, Object mountPoint, Class<? extends Probe<?>> probeType) {
        KeyPair<Object, Object> key = new KeyPair<Object, Object>(entity, mountPoint);
        return getProbe(key, probeType);
    }
    
    private Probe<V> getProbe(KeyPair<Object, Object> key, Class<? extends Probe<?>> clazz) {
        for(Probe<V> p : probeMap.get(key)) {
            if (p.getClass().equals(clazz)) {
                return p;
            }
        }
        // TODO: log warning?
        return null;
    }

    @Override
    public void mountProbe(Probe<V> probe, Object entity) {
        KeyPair<Object, Object> key = new KeyPair<Object, Object>(entity, null);
        probeMap.put(key, probe);
    }
    
    @Override
    public void mountProbe(Probe<V> probe, Object entity, Object mountPoint) {
        KeyPair<Object, Object> key = new KeyPair<Object, Object>(entity, mountPoint);
        probeMap.put(key, probe);
    }
    
    private static class KeyPair<K1, K2> {
        
        private K1 entity;
        
        private K2 mountPoint;
        
        public KeyPair(K1 entity, K2 mountPoint) {
            this.entity = entity;
            this.mountPoint = mountPoint;
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
