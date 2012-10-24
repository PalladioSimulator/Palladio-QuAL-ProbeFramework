package edu.kit.ipd.sdq.probespec.framework.calculators.binary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.IBlackboardListener;

public interface IBinaryCalculator<IN1, IN2, OUT> {

    public OUT calculate(IN1 firstProbe, IN2 secondProbe);

    public void setBoundedProbe(Probe<OUT> probe);

    public IBlackboardListener<IN1> getFirstListener();

    public IBlackboardListener<IN2> getSecondListener();

    public Class<IN1> getIn1Class();

    public Class<IN2> getIn2Class();

    public Class<OUT> getOutClass();

}
