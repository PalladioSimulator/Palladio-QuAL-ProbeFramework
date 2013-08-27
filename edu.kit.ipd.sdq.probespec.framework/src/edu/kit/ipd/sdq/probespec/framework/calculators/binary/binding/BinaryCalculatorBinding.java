package edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding;

import org.apache.log4j.Logger;

import edu.kit.ipd.sdq.probespec.framework.Measurement;
import edu.kit.ipd.sdq.probespec.framework.MeasurementListener;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Blackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.calculators.CalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.BinaryCalculator;

public class BinaryCalculatorBinding<IN1, IN2, OUT, T> implements CalculatorBinding,
        BinaryUnboundCalculator<IN1, IN2, OUT>, BinaryPartiallyBoundCalculator<OUT>, BinaryBoundCalculator {

    private Logger logger = Logger.getLogger(BinaryCalculatorBinding.class);

    private Blackboard<T> blackboard;

    private MeasurementListener<IN1, T> in1Listener;

    private MeasurementListener<IN2, T> in2Listener;

    private BinaryCalculator<IN1, IN2, OUT, T> calculator;

    private boolean isInputBound;

    private boolean isOutputBound;

    public BinaryCalculatorBinding(BinaryCalculator<IN1, IN2, OUT, T> calculator, Blackboard<T> blackboard) {
        this.blackboard = blackboard;
        this.calculator = calculator;
        this.in1Listener = new Input1Listener();
        this.in2Listener = new Input2Listener();
    }

    public BinaryBoundCalculator bindOutput(Probe<OUT> outProbe) {
        // setup blackboard writer
        BlackboardWriter<OUT> writer = blackboard.getWriter(outProbe);
        calculator.setupBlackboardWriter(writer);

        isOutputBound = true;

        return this;
    }

    public BinaryPartiallyBoundCalculator<OUT> bindInput(Probe<IN1> in1Probe, Probe<IN2> in2Probe) {
        if (in1Probe == null || in2Probe == null) {
            throw new IllegalArgumentException("Cannot bind calculator to probes because at least one is null.");
        }

        // setup binding
        blackboard.addMeasurementListener(in1Listener, in1Probe);
        blackboard.addMeasurementListener(in2Listener, in2Probe);
        calculator.setupBinding(in1Probe, in2Probe);

        // setup blackboard reader
        BlackboardReader<IN1, T> reader1 = blackboard.getReader(in1Probe);
        BlackboardReader<IN2, T> reader2 = blackboard.getReader(in2Probe);
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

    private class Input1Listener implements MeasurementListener<IN1, T> {

        @Override
        public void measurementArrived(Measurement<IN1, T> measurement, Probe<IN1> probe,
                MeasurementContext... contexts) {
            calculator.calculate(probe, contexts);
        }

        @Override
        public Class<IN1> getGenericType() {
            return calculator.getIn1Class();
        }

    }

    private class Input2Listener implements MeasurementListener<IN2, T> {

        @Override
        public void measurementArrived(Measurement<IN2, T> measurement, Probe<IN2> probe,
                MeasurementContext... contexts) {
            calculator.calculate(probe, contexts);
        }

        @Override
        public Class<IN2> getGenericType() {
            return calculator.getIn2Class();
        }

    }

}
