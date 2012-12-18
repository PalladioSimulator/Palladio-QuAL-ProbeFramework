package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.Map;

public interface IMeasurementMetadata extends Map<String, String> {

    public static final IMeasurementMetadata EMPTY_METADATA = MeasurementMetadata.emptyMetadata();
    
    // intentionally left blank
    
}
