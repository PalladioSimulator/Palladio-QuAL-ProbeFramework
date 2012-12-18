package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MeasurementMetadata implements IMeasurementMetadata {

    private Map<String, String> map;

    public MeasurementMetadata() {
        this(false);
    }

    private MeasurementMetadata(boolean immutable) {
        if (immutable) {
            map = Collections.emptyMap();
        } else {
            map = new HashMap<String, String>();
        }
    }
    
    public static IMeasurementMetadata emptyMetadata() {
        return new MeasurementMetadata(true);
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    public boolean equals(Object o) {
        return map.equals(o);
    }

    public String get(Object key) {
        return map.get(key);
    }

    public int hashCode() {
        return map.hashCode();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public String put(String key, String value) {
        return map.put(key, value);
    }

    public void putAll(Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    public String remove(Object key) {
        return map.remove(key);
    }

    public int size() {
        return map.size();
    }

    public Collection<String> values() {
        return map.values();
    }

}
