package edu.kit.ipd.sdq.probespec.framework.blackboard;

/**
 * Static helper methods for instances of {@link IMeasurementContext}.
 * 
 * @author Philipp Merkle
 * @since 2.0
 * 
 */
public class MeasurementContextHelper {

    /**
     * Returns the passed {@code contexts} where each context of the specified {@code type} is
     * replaced with its parent context using {@link IMeasurementContext#getParent()}. If the
     * contexts parameter does not contain an entry of the given type, this method simply returns
     * the {@code contexts} parameter.
     * 
     * @param type
     * @param contexts
     * @return
     */
    public static IMeasurementContext[] parentContext(Class<? extends IMeasurementContext> type,
            IMeasurementContext... contexts) {
        assert (type != null);
        IMeasurementContext[] result = new IMeasurementContext[contexts.length];
        for (int i = 0; i < contexts.length; i++) {
            IMeasurementContext c = contexts[i];
            if (type.isInstance(c)) {
                result[i] = c.getParent();
            } else {
                result[i] = c;
            }
        }
        return result;
    }

    /**
     * Returns from the passed {@code contexts} the one which is of the specified {@code type}. If
     * there is no such context, this method returns {@code null}. If there are multiple contexts of
     * the specified type, the first occurrence is returned.
     * 
     * @param type
     * @param contexts
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends IMeasurementContext> T filter(Class<T> type, IMeasurementContext... contexts) {
        assert (type != null);
        for (IMeasurementContext c : contexts) {
            if (type.isInstance(c)) {
                return (T) c;
            }
        }
        return null;
    }

}
