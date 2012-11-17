package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.framework.ITimestampGenerator;

public class JavaTimestampBuilder implements ITimestampGenerator<Long> {

    @Override
    public Long now() {
        return System.nanoTime();
    }

}
