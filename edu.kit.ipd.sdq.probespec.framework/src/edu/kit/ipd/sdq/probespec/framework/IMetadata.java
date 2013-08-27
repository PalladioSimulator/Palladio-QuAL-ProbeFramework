package edu.kit.ipd.sdq.probespec.framework;

import java.util.Map;


public interface IMetadata extends Map<Object, Object> {

    public static final IMetadata EMPTY_METADATA = Metadata.emptyMetadata();
    
    // intentionally left blank
    
}
