package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;

public class Measurements {

    public static Measurement<Integer> create(Integer value) {
        return new Measurement<Integer>(System.nanoTime(), value);
    }
    
    public static Measurement<Long> create(Long value) {
        return new Measurement<Long>(System.nanoTime(), value);
    }
    
    public static Measurement<Double> create(Double value) {
        return new Measurement<Double>(System.nanoTime(), value);
    }
    
}
