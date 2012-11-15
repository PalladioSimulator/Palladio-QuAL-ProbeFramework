package edu.kit.ipd.sdq.probespec.framework.blackboard.index;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;

public class WeakIndex {

    private Class<? extends IMeasurementContext> contextType;

    private Multimap<String, WeakReference<String>> index;

    public WeakIndex(Class<? extends IMeasurementContext> contextType) {
        this.contextType = contextType;
        index = HashMultimap.create();
    }

    public void add(String value, String key) {
        index.put(key, new WeakReference<String>(value));
    }

    public void removeAll(String key) {
        index.removeAll(key);
    }

    public List<String> values(String key) {
        List<String> values = new ArrayList<String>();
        for (WeakReference<String> ref : index.get(key)) {
            String v = ref.get();
            if (v != null) {
                values.add(v);
            }
        }
        return values;
    }

    public int size(String key) {
        return index.get(key).size();
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
