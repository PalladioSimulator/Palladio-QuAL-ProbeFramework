package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding.IBinaryUnboundCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding.IUnaryUnboundCalculator;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;

public interface IProbeManager<T> {

    public <IN, OUT> IUnaryUnboundCalculator<IN, OUT> installCalculator(IUnaryCalculator<IN, OUT, T> calculator);

    public <IN1, IN2, OUT> IBinaryUnboundCalculator<IN1, IN2, OUT> installCalculator(
            IBinaryCalculator<IN1, IN2, OUT, T> calculator);

    public <V> IBlackboardReader<V, T> getReader(Probe<V> probe);

    public <V> IBlackboardWriter<V> getWriter(Probe<V> probe);

    public <V> void addMeasurementListener(IBlackboardListener<V, T> l, Probe<V> probe);

    public <V> void addMeasurementListener(IBlackboardListener<V, T> l);

    public <V> void removeMeasurementListener(IBlackboardListener<V, T> l);

}