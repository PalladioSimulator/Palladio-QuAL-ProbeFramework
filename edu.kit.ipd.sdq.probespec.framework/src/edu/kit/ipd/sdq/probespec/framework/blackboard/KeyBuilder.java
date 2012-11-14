package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.HashMap;
import java.util.Map;

import edu.kit.ipd.sdq.probespec.Probe;

public class KeyBuilder {

    private static final String DEFAULT_SEPARATOR = "::";

    private Map<Class<? extends IMeasurementContext>, Integer> numberedContexts;

    public KeyBuilder() {
        numberedContexts = new HashMap<Class<? extends IMeasurementContext>, Integer>();
    }
    
    public String createKey(Probe<?> probe, IMeasurementContext... contexts) {
        maintainNumberedContexts(contexts);
        
        String[] partialKeys = new String[numberedContexts.size()];
        for (IMeasurementContext c : contexts) {
            int idx = numberedContexts.get(c.getClass());
            partialKeys[idx] = c.getId();
        }

        StringBuilder compositeKey = new StringBuilder();
        compositeKey.append(probe.getId());
        for (int i = 0; i < partialKeys.length; i++) {
            if (partialKeys[i] != null) {
                compositeKey.append(DEFAULT_SEPARATOR);
                compositeKey.append(partialKeys[i]);
            }
        }

        return compositeKey.toString();
    }
    
    private void maintainNumberedContexts(IMeasurementContext... contexts) {
        for(IMeasurementContext c : contexts) {
            if(!numberedContexts.containsKey(c.getClass())) {
                addContext(c.getClass());
            }
        }
    }
    
    private void addContext(Class<? extends IMeasurementContext> context) {
        numberedContexts.put(context, numberedContexts.size());
    }

}
