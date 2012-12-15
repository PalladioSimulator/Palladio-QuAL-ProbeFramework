package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.DerivedProbe;
import edu.kit.ipd.sdq.probespec.framework.calculators.AbstractCalculator;

public abstract class AbstractBinaryCalculator<IN1, IN2, OUT, T> extends AbstractCalculator<OUT> implements
        IBinaryCalculator<IN1, IN2, OUT, T> {

    private Class<IN1> in1Class;

    private Class<IN2> in2Class;

    private Class<OUT> outClass;

    public AbstractBinaryCalculator(DerivedProbe<OUT> outputProbe, Class<IN1> in1Class, Class<IN2> in2Class,
            Class<OUT> outClass) {
        super(outputProbe);
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

}
