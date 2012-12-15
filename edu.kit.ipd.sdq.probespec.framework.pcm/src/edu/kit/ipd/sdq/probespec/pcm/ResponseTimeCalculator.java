package edu.kit.ipd.sdq.probespec.pcm;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.AbstractBinaryCalculator;

public class ResponseTimeCalculator extends AbstractBinaryCalculator<Double, Double, Double, Double> {

    public ResponseTimeCalculator(DerivedProbe<Double> outputProbe) {
        super(outputProbe, Double.class, Double.class, Double.class);
    }

    @Override
    public void setupBlackboardAccess(IBlackboardReader<Double, Double> proxy1, IBlackboardReader<Double, Double> proxy2) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public Double calculate(Probe<?> probe, IMeasurementContext... contexts) {
        // TODO Auto-generated method stub
        return null;
    }

}
