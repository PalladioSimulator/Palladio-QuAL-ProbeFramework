package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.calculators.AbstractCalculator;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public abstract class AbstractBinaryCalculator<IN1, IN2, OUT, T> extends AbstractCalculator<OUT> implements
        BinaryCalculator<IN1, IN2, OUT, T> {

    private Class<IN1> in1Class;

    private Class<IN2> in2Class;

    private Class<OUT> outClass;

    protected BlackboardReader<IN1, T> in1Reader;

    protected BlackboardReader<IN2, T> in2Reader;

    protected Probe<IN1> in1Probe;

    protected Probe<IN2> in2Probe;
    
    public AbstractBinaryCalculator(Class<IN1> in1Class, Class<IN2> in2Class,
            Class<OUT> outClass) {
        this.in1Class = in1Class;
        this.in2Class = in2Class;
        this.outClass = outClass;
    }

    @Override
    public Class<IN1> getIn1Class() {
        return in1Class;
    }

    @Override
    public Class<IN2> getIn2Class() {
        return in2Class;
    }

    @Override
    public Class<OUT> getOutClass() {
        return outClass;
    }

    @Override
    public void setupBlackboardReader(BlackboardReader<IN1, T> in1Reader, BlackboardReader<IN2, T> in2Reader) {
        this.in1Reader = in1Reader;
        this.in2Reader = in2Reader;
    }

    @Override
    public void setupBinding(Probe<IN1> in1Probe, Probe<IN2> in2Probe) {
        this.in1Probe = in1Probe;
        this.in2Probe = in2Probe;
    }

}
