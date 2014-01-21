package edu.kit.ipd.sdq.probespec.framework.probes;

import java.util.Set;

import edu.kit.ipd.sdq.probespec.framework.Calculator;
import edu.kit.ipd.sdq.probespec.framework.MeasurableEntity;
import edu.kit.ipd.sdq.probespec.framework.Probe;

public interface ProbeRegistryRegion<V> {

    Probe<V> getProbe(MeasurableEntity entity, Object mountPoint, Class<? extends Probe<?>> probeType);
    
    Probe<V> getProbe(MeasurableEntity entity, Class<? extends Probe<?>> probeType);
    
    Probe<V> getCalculatedProbe(Calculator<V> calculator);
    
    Set<Probe<V>> getProbes(MeasurableEntity entity, Object mountPoint);
    
    Set<Probe<V>> getProbes();
    
    Set<Probe<?>> getCalculatedProbes();

    Set<MeasurableEntity> getEntities();
    
    Set<Object> getMeasuringPointsForEntity(MeasurableEntity entity);

    void mountProbe(Probe<V> probe, MeasurableEntity entity);

    void mountProbe(Probe<V> probe, MeasurableEntity entity, Object mountPoint);
    
    void mountCalculatedProbe(Probe<V> probe, Calculator<V> calculator);

}
