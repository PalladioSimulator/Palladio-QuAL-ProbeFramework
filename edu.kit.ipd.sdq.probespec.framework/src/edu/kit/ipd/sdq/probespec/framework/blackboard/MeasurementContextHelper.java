package edu.kit.ipd.sdq.probespec.framework.blackboard;

public class MeasurementContextHelper {

    public enum LookupStrategy {
        SAME_CONTEXT,
        SAME_OR_PARENT_CONTEXT
    }
    
    public static IMeasurementContext[] parentContext(Class<? extends IMeasurementContext> clazz,
            IMeasurementContext... contexts) {
        IMeasurementContext[] result = new IMeasurementContext[contexts.length];
        for (int i = 0; i < contexts.length; i++) {
            IMeasurementContext c = contexts[i];
            if (clazz.isInstance(c)) {
                result[i] = c.getParent();
            } else {
                result[i] = c;
            }
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends IMeasurementContext> T filter(Class<T> clazz, IMeasurementContext... contexts) {
        for(IMeasurementContext c : contexts) {
            if (clazz.isInstance(c)) {
                return (T) c;
            }
        }
        return null;
    }

}
