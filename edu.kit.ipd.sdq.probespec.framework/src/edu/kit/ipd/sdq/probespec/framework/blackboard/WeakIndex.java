package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class WeakIndex {

    private Multimap<String, WeakReference<String>> index;

    public WeakIndex() {
        index = HashMultimap.create();
    }

    public void add(String key, String value) {
        index.put(key, new WeakReference<String>(value));
    }

    public void removeAll(String key) {
        index.removeAll(key);
    }

    public <T> int removeMeasurements(String key, Map<String, Measurement<T>> measurements) {
        int deletionCounter = 0;
        for (Iterator<String> it = iterator(key); it.hasNext();) {
            String k = it.next();

            // weakly referenced object might be gc'ed already, thus check for null first
            if (k != null) {
                measurements.remove(k);
                ++deletionCounter;
            }
        }
        return deletionCounter;
    }

    public int size(String key) {
        return index.get(key).size();
    }

    public Iterator<String> iterator(final String key) {
        return new Iterator<String>() {
            private Iterator<WeakReference<String>> it = index.get(key).iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public String next() {
                return it.next().get();
            }

            @Override
            public void remove() {
                it.remove();
            }
        };
    }

    private Iterator<String> iterator() {
        return new Iterator<String>() {
            private Iterator<WeakReference<String>> it = index.values().iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public String next() {
                return it.next().get();
            }

            @Override
            public void remove() {
                it.remove();
            }
        };
    }

    public int clean() {
        int deletionCount = 0;
        for (Iterator<String> it = iterator(); it.hasNext();) {
            String key = it.next();
            if (key == null) {
                // remove dangling weak reference, whose referenced object has been gc'ed already
                it.remove();
                ++deletionCount;
            }
        }
        return deletionCount;
    }

}
