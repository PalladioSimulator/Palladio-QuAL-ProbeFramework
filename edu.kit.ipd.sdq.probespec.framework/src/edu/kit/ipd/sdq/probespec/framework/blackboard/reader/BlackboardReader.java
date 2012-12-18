package edu.kit.ipd.sdq.probespec.framework.blackboard.reader;

import java.util.List;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.SimpleBlackboardRegion;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;

public class BlackboardReader<V, T> implements IBlackboardReader<V, T> {

    private Probe<V> probe;

    private SimpleBlackboardRegion<V, T> blackboardRegion;

    public BlackboardReader(Probe<V> probe, SimpleBlackboardRegion<V, T> blackboardRegion) {
        this.probe = probe;
        this.blackboardRegion = blackboardRegion;
    }

    @Override
    public Measurement<V, T> getLatestMeasurement() {
        return blackboardRegion.getLatestMeasurement(probe);
    }

    @Override
    public Measurement<V, T> getLatestMeasurement(IMeasurementContext... contexts) {
        return blackboardRegion.getLatestMeasurement(probe, contexts);
    }
    
    @Override
    public Measurement<V, T> getLatestMeasurement(ILookupStrategy lookupStrategy, IMeasurementContext... contexts) {
        for (List<IMeasurementContext> c : lookupStrategy.lookup(contexts)) {
            Measurement<V, T> mm = getLatestMeasurement(c.toArray(new IMeasurementContext[c.size()]));
            if (mm != null) {
                return mm;
            }
        }
        return null;
    }

    @Override
    public Probe<V> getProbe() {
        return probe;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

}
