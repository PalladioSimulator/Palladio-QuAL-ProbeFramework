package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardListener;

public class BinaryCalculatorBinding<IN1, IN2, OUT> {

    private IBlackboardListener<IN1> listener1;

    private IBlackboardListener<IN2> listener2;
    
    private IBinaryCalculator<IN1, IN2, OUT> calculator;
    
    public BinaryCalculatorBinding(IBinaryCalculator<IN1, IN2, OUT> calculator) {
        this.calculator = calculator;
        this.listener1 = new Probe1Listener();
        this.listener2 = new Probe2Listener();
    }
    
    public void bindInput(IBlackboard blackboard, Probe<IN1> sourceProbe1, Probe<IN2> sourceProbe2) {
        blackboard.addMeasurementListener(getFirstListener(), sourceProbe1);
        blackboard.addMeasurementListener(getSecondListener(), sourceProbe2);
    }
    
    private IBlackboardListener<IN1> getFirstListener() {
        return listener1;
    }
    
    private IBlackboardListener<IN2> getSecondListener() {
        return listener2;
    }
    
    private class Probe1Listener implements IBlackboardListener<IN1> {

        @Override
        public Class<IN1> getGenericType() {
            return calculator.getIn1Class();
        }

        @Override
        public void measurementArrived(IN1 measurement, Probe<IN1> probe, IBlackboard blackboard) {
            calculator.firstMeasurementArrived(measurement, probe, blackboard);
        }

    }

    private class Probe2Listener implements IBlackboardListener<IN2> {

        @Override
        public Class<IN2> getGenericType() {
            return calculator.getIn2Class();
        }

        @Override
        public void measurementArrived(IN2 measurement, Probe<IN2> probe, IBlackboard blackboard) {
            calculator.secondMeasurementArrived(measurement, probe, blackboard);
        }

    }

}
