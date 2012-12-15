package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;

public class DifferenceCalculator extends AbstractBinaryCalculator<Integer, Integer, Integer, Long> {

    public static int count;

    private IBlackboardReader<Integer, Long> in1Reader;
    private IBlackboardReader<Integer, Long> in2Reader;
    
    public DifferenceCalculator(DerivedProbe<Integer> outputProbe) {
        // Provide generic class parameters to super class. This is necessary because generic
        // type parameters are "erased" with compilation, thus making them unavailable at runtime.
        //
        // /////////////// IN1-class //// IN2-class ///// OUT-class
        super(outputProbe, Integer.class, Integer.class, Integer.class);
    }
    
    @Override
    public void setupBlackboardAccess(IBlackboardReader<Integer, Long> in1Reader, IBlackboardReader<Integer, Long> in2Reader) {
        this.in1Reader = in1Reader;
        this.in2Reader = in2Reader;
    }

    @Override
    public Integer calculate(Probe<?> probe, IMeasurementContext... contexts) {
        // TODO
        
        count++;

        Measurement<Integer, Long> mm1 = in1Reader.getLatestMeasurement();
        Measurement<Integer, Long> mm2 = in2Reader.getLatestMeasurement();

        if (mm1 != null && mm2 != null) {
            return Math.abs(mm1.getValue() - mm2.getValue());
        } else {
            return null;
        }
    }

}
