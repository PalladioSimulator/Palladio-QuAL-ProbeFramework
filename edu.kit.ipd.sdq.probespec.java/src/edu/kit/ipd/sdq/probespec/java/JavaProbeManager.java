package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.framework.TimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.AbstractProbeManager;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;

public class JavaProbeManager extends AbstractProbeManager<Long> {

    private static final TimestampGenerator<Long> TIMESTAMP_GENERFATOR = new JavaTimestampBuilder();

    public JavaProbeManager() {
        super(TIMESTAMP_GENERFATOR);
    }

    public JavaProbeManager(BlackboardType blackboardType) {
        super(TIMESTAMP_GENERFATOR, blackboardType);
    }

}
