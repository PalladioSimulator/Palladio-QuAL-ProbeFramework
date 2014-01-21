package edu.kit.ipd.sdq.probespec.framework.blackboard.reader;

import java.util.List;

import edu.kit.ipd.sdq.probespec.framework.Measurement;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.SimpleBlackboardRegion;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;

public class BlackboardReaderImpl<V, T> implements BlackboardReader<V, T> {

    private Probe<V> probe;

    private SimpleBlackboardRegion<V, T> blackboardRegion;

    public BlackboardReaderImpl(Probe<V> probe, SimpleBlackboardRegion<V, T> blackboardRegion) {
        this.probe = probe;
        this.blackboardRegion = blackboardRegion;
    }

    @Override
    public Measurement<V, T> getLatestMeasurement() {
        return blackboardRegion.getLatestMeasurement(probe);
    }

    @Override
    public Measurement<V, T> getLatestMeasurement(MeasurementContext... contexts) {
        return blackboardRegion.getLatestMeasurement(probe, contexts);
    }
    
    @Override
    public Measurement<V, T> getLatestMeasurement(LookupStrategy lookupStrategy, MeasurementContext... contexts) {
        for (List<MeasurementContext> c : lookupStrategy.lookup(contexts)) {
            Measurement<V, T> mm = getLatestMeasurement(c.toArray(new MeasurementContext[c.size()]));
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
