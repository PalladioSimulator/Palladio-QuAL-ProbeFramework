package edu.kit.ipd.sdq.probespec.framework.test.util;

import edu.kit.ipd.sdq.probespec.framework.AbstractProbe;

public class IntegerProbe extends AbstractProbe<Integer>  {
    
    public IntegerProbe(String name, boolean _transient) {
        super(name, _transient);
    }

    public IntegerProbe(String id, String name, boolean _transient) {
        super(id, name, _transient);
    }

    public IntegerProbe(String id, String name) {
        super(id, name);
    }

    public IntegerProbe(String name) {
        super(name);
    }

    @Override
    public Class<Integer> getGenericClass() {
        return Integer.class;
    }
    
}
