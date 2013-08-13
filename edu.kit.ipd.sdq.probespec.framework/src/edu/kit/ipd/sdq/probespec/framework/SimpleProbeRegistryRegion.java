package edu.kit.ipd.sdq.probespec.framework;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class SimpleProbeRegistryRegion<V> implements IProbeRegistryRegion<V> {

    private Multimap<Object, Probe<V>> probeMap;
    
    public SimpleProbeRegistryRegion() {
        probeMap = HashMultimap.create();
    }
    
    @Override
    public Probe<V> getProbe(Object annotatedEntity, Class<? extends Probe<?>> clazz) {
        for(Probe<V> p : probeMap.get(annotatedEntity)) {
            if (p.getClass().equals(clazz)) {
                return p;
            }
        }
        // TODO
        return null;
    }

    @Override
    public void registerProbe(Probe<V> probe, Object annotatedEntity) {
        probeMap.put(annotatedEntity, probe);
    }


}
