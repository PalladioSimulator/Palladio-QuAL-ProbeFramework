package edu.kit.ipd.sdq.probespec.framework.calculators.unary;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.IBlackboardListener;

public interface IUnaryCalculator<IN, OUT> {

    public OUT calculate(IN p);

    public void setBoundedProbe(Probe<OUT> probe);

    public IBlackboardListener<IN> getListener();

    public Class<IN> getInClass();

    public Class<OUT> getOutClass();

}
