package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.calculators.ICalculatorBinding;

public class BinaryCalculatorBinding<IN1, IN2, OUT, T> implements ICalculatorBinding {

    private Logger logger = Logger.getLogger(BinaryCalculatorBinding.class);

    private IBlackboard<T> blackboard;

    private IBlackboardListener<IN1, T> in1Listener;

    private IBlackboardListener<IN2, T> in2Listener;

    private IBinaryCalculator<IN1, IN2, OUT, T> calculator;

    private boolean isBound;

    public BinaryCalculatorBinding(IBinaryCalculator<IN1, IN2, OUT, T> calculator, IBlackboard<T> blackboard) {
        this.blackboard = blackboard;
        this.calculator = calculator;
        this.in1Listener = new Input1Listener();
        this.in2Listener = new Input2Listener();
    }

    public void bind(Probe<IN1> in1Probe, Probe<IN2> in2Probe) {
        blackboard.addMeasurementListener(in1Listener, in1Probe);
        IBlackboardReader<IN1, T> reader1 = blackboard.getReader(in1Probe);
        blackboard.addMeasurementListener(in2Listener, in2Probe);
        IBlackboardReader<IN2, T> reader2 = blackboard.getReader(in2Probe);

        calculator.setupBlackboardAccess(reader1, reader2);
        calculator.setupBinding(in1Probe, in2Probe);

        isBound = true;
    }

    @Override
    public boolean isBound() {
        return isBound;
    }

    @Override
    public void unbind() {
        if (isBound) {
            blackboard.removeMeasurementListener(in1Listener);
            blackboard.removeMeasurementListener(in2Listener);
            isBound = false;
        } else {
            logger.warn("Tried to unbind a calculator which has not been bound before: " + calculator);
        }
    }

    @Override
    public String toString() {
        return calculator.toString();
    }

    private class Input1Listener implements IBlackboardListener<IN1, T> {

        @Override
        public void measurementArrived(Measurement<IN1, T> measurement, Probe<IN1> probe,
                IMeasurementContext... contexts) {
            OUT result = calculator.calculate(probe, contexts);
            if (result != null) {
                blackboard.addMeasurement(result, calculator.getOutputProbe());
            }
        }

        @Override
        public Class<IN1> getGenericType() {
            return calculator.getIn1Class();
        }

    }

    private class Input2Listener implements IBlackboardListener<IN2, T> {

        @Override
        public void measurementArrived(Measurement<IN2, T> measurement, Probe<IN2> probe,
                IMeasurementContext... contexts) {
            OUT result = calculator.calculate(probe, contexts);
            if (result != null) {
                blackboard.addMeasurement(result, calculator.getOutputProbe());
            }
        }

        @Override
        public Class<IN2> getGenericType() {
            return calculator.getIn2Class();
        }

    }

}
