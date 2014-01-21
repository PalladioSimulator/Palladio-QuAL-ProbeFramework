package edu.kit.ipd.sdq.probespec.framework.blackboard.index;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;

public class WeakIndex {

    private Class<? extends MeasurementContext> contextType;

    private Multimap<String, WeakReference<String>> index;

    public WeakIndex(Class<? extends MeasurementContext> contextType) {
        this.contextType = contextType;
        index = HashMultimap.create();
    }

    public void add(String value, String key) {
        index.put(key, new WeakReference<String>(value));
    }

    public void removeAll(String key) {
        index.removeAll(key);
    }

    public Collection<WeakReference<String>> values(String key) {
        return index.get(key);
    }

    public int size(String key) {
        return index.get(key).size();
    }

    public int size() {
        return index.size();
    }

    public int clean() {
        int deletionCount = 0;
        for (Iterator<WeakReference<String>> it = index.values().iterator(); it.hasNext();) {
            WeakReference<String> key = it.next();
            if (key.get() == null) {
                // remove dangling weak reference, whose referenced object has been gc'ed already
                it.remove();
                ++deletionCount;
            }
        }
        return deletionCount;
    }

    @Override
    public String toString() {
        return "WeakIndex [contextType=" + contextType + "]";
    }

}
