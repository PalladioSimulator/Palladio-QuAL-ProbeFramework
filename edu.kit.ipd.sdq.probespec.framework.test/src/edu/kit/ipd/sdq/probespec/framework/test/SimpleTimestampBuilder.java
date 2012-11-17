package edu.kit.ipd.sdq.probespec.framework.test;

import edu.kit.ipd.sdq.probespec.framework.ITimestampBuilder;

public class SimpleTimestampBuilder implements ITimestampBuilder<Long> {

    @Override
    public Long now() {
        return System.nanoTime();
    }

}
