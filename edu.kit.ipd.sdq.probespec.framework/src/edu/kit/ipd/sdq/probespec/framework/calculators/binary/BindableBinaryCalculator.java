package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;

public class BindableBinaryCalculator<IN1, IN2, OUT, U> {

    private IBlackboardListener<IN1, U> in1Listener;

    private IBlackboardListener<IN2, U> in2Listener;
    
    private IBinaryCalculator<IN1, IN2, OUT, U> calculator;
    
    public BindableBinaryCalculator(IBinaryCalculator<IN1, IN2, OUT, U> calculator) {
        this.calculator = calculator;
        this.in1Listener = new Input1Listener();
        this.in2Listener = new Input2Listener();
    }
    
    public void bind(IBlackboard<U> blackboard, Probe<IN1> sourceProbe1, Probe<IN2> sourceProbe2) {
        blackboard.addMeasurementListener(in1Listener, sourceProbe1);
        blackboard.addMeasurementListener(in2Listener, sourceProbe2);
    }
    
    public void unbind(IBlackboard<U> blackboard) {
        blackboard.removeMeasurementListener(in1Listener);
        blackboard.removeMeasurementListener(in2Listener);
    }
    
    private class Input1Listener implements IBlackboardListener<IN1, U> {

        @Override
        public void measurementArrived(IBlackboard<U> blackboard, Measurement<IN1, U> measurement, Probe<IN1> probe, IMeasurementContext... contexts) {
            calculator.firstMeasurementArrived(measurement, probe, blackboard);
        }
        
        @Override
        public Class<IN1> getGenericType() {
            return calculator.getIn1Class();
        }

    }

    private class Input2Listener implements IBlackboardListener<IN2, U> {

        @Override
        public void measurementArrived(IBlackboard<U> blackboard, Measurement<IN2, U> measurement, Probe<IN2> probe, IMeasurementContext... contexts) {
            calculator.secondMeasurementArrived(measurement, probe, blackboard);
        }
        
        @Override
        public Class<IN2> getGenericType() {
            return calculator.getIn2Class();
        }

    }

}
