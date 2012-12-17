package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;

public class BlackboardReaderSupport<V, T> {

    private Map<Probe<?>, Integer> openReaderCounter;

    public BlackboardReaderSupport() {
        openReaderCounter = new HashMap<Probe<?>, Integer>();
    }

    public IBlackboardReader<V, T> getReader(Probe<V> probe, SimpleBlackboardRegion<V, T> region) {
        if (!openReaderCounter.containsKey(probe)) {
            openReaderCounter.put(probe, new Integer(0));
        }

        // adjust counter
        Integer adjustedCount = openReaderCounter.get(probe) + 1;
        openReaderCounter.put(probe, adjustedCount);

        return new BlackboardReader<V, T>(probe, region);
    }

    public boolean hasConsumers(Probe<V> probe) {
        return openReaderCounter.containsKey(probe) && openReaderCounter.get(probe) > 0;
    }

    protected void close(IBlackboardReader<V, T> reader) {
        Probe<V> probe = reader.getProbe();
        assert (openReaderCounter.containsKey(reader.getProbe()));

        // adjust counter
        Integer adjustedCount = openReaderCounter.get(probe) - 1;
        openReaderCounter.put(probe, adjustedCount);
    }

}
