package edu.kit.ipd.sdq.probespec.framework.blackboard.reader;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.SimpleBlackboardRegion;

public class BlackboardReaderSupport<V, T> {

    private Map<Probe<?>, Integer> openReaderCounter;

    public BlackboardReaderSupport() {
        openReaderCounter = new HashMap<Probe<?>, Integer>();
    }

    public BlackboardReader<V, T> getReader(Probe<V> probe, SimpleBlackboardRegion<V, T> region) {
        if (!openReaderCounter.containsKey(probe)) {
            openReaderCounter.put(probe, new Integer(0));
        }

        // adjust counter
        Integer adjustedCount = openReaderCounter.get(probe) + 1;
        openReaderCounter.put(probe, adjustedCount);

        return new BlackboardReaderImpl<V, T>(probe, region);
    }

    public boolean hasConsumers(Probe<V> probe) {
        return openReaderCounter.containsKey(probe) && openReaderCounter.get(probe) > 0;
    }

    protected void close(BlackboardReader<V, T> reader) {
        Probe<V> probe = reader.getProbe();
        assert (openReaderCounter.containsKey(reader.getProbe()));

        // adjust counter
        Integer adjustedCount = openReaderCounter.get(probe) - 1;
        openReaderCounter.put(probe, adjustedCount);
    }

}
