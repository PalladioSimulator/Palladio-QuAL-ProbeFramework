package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.lang.ref.WeakReference;
import java.util.Iterator;

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

    public Iterator<String> iterator() {
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
