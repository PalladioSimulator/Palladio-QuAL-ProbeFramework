package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.framework.TimestampGenerator;

public class JavaTimestampBuilder implements TimestampGenerator<Long> {

    @Override
    public Long now() {
        return System.nanoTime();
    }

}
