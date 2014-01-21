package edu.kit.ipd.sdq.probespec.framework.test.util;

import edu.kit.ipd.sdq.probespec.framework.probes.AbstractProbe;

public class DoubleProbe extends AbstractProbe<Double> {

    public DoubleProbe(String name, boolean _transient) {
        super(name, _transient);
    }

    public DoubleProbe(String id, String name, boolean _transient) {
        super(id, name, _transient);
    }

    public DoubleProbe(String id, String name) {
        super(id, name);
    }

    public DoubleProbe(String name) {
        super(name);
    }

    @Override
    public Class<Double> getGenericClass() {
        return Double.class;
    }

}
