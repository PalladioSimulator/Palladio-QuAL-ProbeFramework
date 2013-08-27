package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public class KeyBuilder {

    private static final String DEFAULT_SEPARATOR = "::";

    private Map<Class<? extends MeasurementContext>, Integer> numberedContexts;

    public KeyBuilder() {
        numberedContexts = new HashMap<Class<? extends MeasurementContext>, Integer>();
    }

    public String createKey(Probe<?> probe, MeasurementContext... contexts) {
        maintainNumberedContexts(contexts);

        String[] partialKeys = new String[numberedContexts.size()];
        for (MeasurementContext c : contexts) {
            int idx = numberedContexts.get(c.getClass());
            partialKeys[idx] = c.getId();
        }

        StringBuilder compositeKey = new StringBuilder();
        compositeKey.append(probe.getId());
        for (int i = 0; i < partialKeys.length; i++) {
            compositeKey.append(DEFAULT_SEPARATOR);
            compositeKey.append(partialKeys[i]);
        }

        return compositeKey.toString();
    }

    private void maintainNumberedContexts(MeasurementContext... contexts) {
        for (MeasurementContext c : contexts) {
            if (!numberedContexts.containsKey(c.getClass())) {
                addContext(c.getClass());
            }
        }
    }

    private void addContext(Class<? extends MeasurementContext> context) {
        numberedContexts.put(context, numberedContexts.size());
    }

}
