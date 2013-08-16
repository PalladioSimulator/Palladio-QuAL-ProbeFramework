package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.framework.ITimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.ProbeManager;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;

public class JavaProbeManager extends ProbeManager<Long> {

    private static final ITimestampGenerator<Long> TIMESTAMP_GENERFATOR = new JavaTimestampBuilder();

    public JavaProbeManager() {
        super(TIMESTAMP_GENERFATOR);
    }

    public JavaProbeManager(BlackboardType blackboardType) {
        super(TIMESTAMP_GENERFATOR, blackboardType);
    }

}
