package edu.kit.ipd.sdq.probespec.framework;


public interface IProbeRegistryRegion<V> {
    
    public Probe<V> getProbe(Object annotatedEntity, Class<? extends Probe<?>> probeType);
    
    public void registerProbe(Probe<V> probe, Object annotatedEntity);

}
