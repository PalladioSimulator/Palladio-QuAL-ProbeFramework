package edu.kit.ipd.sdq.probespec.framework;

import java.util.Map;


public interface Metadata extends Map<Object, Object> {

    public static final Metadata EMPTY_METADATA = HashMapMetadata.emptyMetadata();
    
    // intentionally left blank
    
}
