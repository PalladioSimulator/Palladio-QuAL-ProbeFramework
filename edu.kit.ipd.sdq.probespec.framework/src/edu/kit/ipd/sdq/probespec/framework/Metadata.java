package edu.kit.ipd.sdq.probespec.framework;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Metadata implements IMetadata {

    private Map<String, String> map;

    public Metadata() {
        this(false);
    }

    private Metadata(boolean immutable) {
        if (immutable) {
            map = Collections.emptyMap();
        } else {
            map = new HashMap<String, String>();
        }
    }
    
    public static IMetadata emptyMetadata() {
        return new Metadata(true);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return map.equals(o);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public String put(String key, String value) {
        return map.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    @Override
    public String remove(Object key) {
        return map.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Collection<String> values() {
        return map.values();
    }

}
