package edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.calculators.ICalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public class BinaryCalculatorBinding<IN1, IN2, OUT, T> implements ICalculatorBinding,
        IBinaryUnboundCalculator<IN1, IN2, OUT>, IBinaryPartiallyBoundCalculator<OUT>, IBinaryBoundCalculator {

    private Logger logger = Logger.getLogger(BinaryCalculatorBinding.class);

    private IBlackboard<T> blackboard;

    private IBlackboardListener<IN1, T> in1Listener;

    private IBlackboardListener<IN2, T> in2Listener;

    private IBinaryCalculator<IN1, IN2, OUT, T> calculator;

    private boolean isInputBound;

    private boolean isOutputBound;

    public BinaryCalculatorBinding(IBinaryCalculator<IN1, IN2, OUT, T> calculator, IBlackboard<T> blackboard) {
        this.blackboard = blackboard;
        this.calculator = calculator;
        this.in1Listener = new Input1Listener();
        this.in2Listener = new Input2Listener();
    }

    public IBinaryBoundCalculator bindOutput(Probe<OUT> outProbe) {
        // setup blackboard writer
        IBlackboardWriter<OUT> writer = blackboard.getWriter(outProbe);
        calculator.setupBlackboardWriter(writer);

        isOutputBound = true;

        return this;
    }

    public IBinaryPartiallyBoundCalculator<OUT> bindInput(Probe<IN1> in1Probe, Probe<IN2> in2Probe) {
        if (in1Probe == null || in2Probe == null) {
            throw new IllegalArgumentException("Cannot bind calculator to probes because at least one is null.");
        }

        // setup binding
        blackboard.addMeasurementListener(in1Listener, in1Probe);
        blackboard.addMeasurementListener(in2Listener, in2Probe);
        calculator.setupBinding(in1Probe, in2Probe);

        // setup blackboard reader
        IBlackboardReader<IN1, T> reader1 = blackboard.getReader(in1Probe);
        IBlackboardReader<IN2, T> reader2 = blackboard.getReader(in2Probe);
        calculator.setupBlackboardReader(reader1, reader2);

        isInputBound = true;

        return this;
    }

    @Override
    public boolean isBound() {
        return isInputBound && isOutputBound;
    }

    @Override
    public void unbind() {
        if (isInputBound) {
            blackboard.removeMeasurementListener(in1Listener);
            blackboard.removeMeasurementListener(in2Listener);
            isInputBound = false;
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
            calculator.calculate(probe, contexts);
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
            calculator.calculate(probe, contexts);
        }

        @Override
        public Class<IN2> getGenericType() {
            return calculator.getIn2Class();
        }

    }

}
