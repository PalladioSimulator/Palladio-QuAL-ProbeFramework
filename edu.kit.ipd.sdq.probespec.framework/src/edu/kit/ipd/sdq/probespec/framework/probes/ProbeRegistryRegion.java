package edu.kit.ipd.sdq.probespec.framework.probes;

import edu.kit.ipd.sdq.probespec.framework.Probe;


public interface ProbeRegistryRegion<V> {
    
    public Probe<V> getProbe(Object entity, Class<? extends Probe<?>> probeType);
    
    public Probe<V> getProbe(Object entity, Object mountPoint, Class<? extends Probe<?>> probeType);
    
    public void mountProbe(Probe<V> probe, Object entity);
    
    public void mountProbe(Probe<V> probe, Object entity, Object mountPoint);

}
