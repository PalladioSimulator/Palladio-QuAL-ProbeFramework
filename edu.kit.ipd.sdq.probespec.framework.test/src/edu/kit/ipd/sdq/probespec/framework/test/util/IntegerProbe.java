package edu.kit.ipd.sdq.probespec.framework.test.util;

import edu.kit.ipd.sdq.probespec.framework.AbstractProbe;

public class IntegerProbe extends AbstractProbe<Integer>  {
    
    public IntegerProbe(String name) {
        super(name, name);
    }

    @Override
    public Class<Integer> getGenericClass() {
        return Integer.class;
    }
    
}
