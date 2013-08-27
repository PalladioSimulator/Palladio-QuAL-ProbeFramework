package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.BlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.BinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.binding.BinaryUnboundCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.UnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.binding.UnaryUnboundCalculator;

public interface ProbeManager<T> {

    public <IN, OUT> UnaryUnboundCalculator<IN, OUT> installCalculator(UnaryCalculator<IN, OUT, T> calculator);

    public <IN1, IN2, OUT> BinaryUnboundCalculator<IN1, IN2, OUT> installCalculator(
            BinaryCalculator<IN1, IN2, OUT, T> calculator);

    public <V> BlackboardReader<V, T> getReader(Probe<V> probe);

    public <V> BlackboardWriter<V> getWriter(Probe<V> probe);

    public <V> void addMeasurementListener(MeasurementListener<V, T> l, Probe<V> probe);

    public <V> void addMeasurementListener(MeasurementListener<V, T> l);

    public <V> void removeMeasurementListener(MeasurementListener<V, T> l);

}