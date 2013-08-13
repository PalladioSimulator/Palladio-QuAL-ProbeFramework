package edu.kit.ipd.sdq.probespec.framework.test.util;

import edu.kit.ipd.sdq.probespec.framework.AbstractProbe;

public class DoubleProbe extends AbstractProbe<Double>  {
    
    public DoubleProbe(String name) {
        super(name, name);
    }

    @Override
    public Class<Double> getGenericClass() {
        return Double.class;
    }
    
}
