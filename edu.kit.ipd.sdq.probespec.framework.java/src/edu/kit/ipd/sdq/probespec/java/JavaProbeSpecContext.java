package edu.kit.ipd.sdq.probespec.java;

import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ITimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementMetadata;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.writer.IBlackboardWriter;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.BinaryCalculatorBinding;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.IBinaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.IUnaryCalculator;
import edu.kit.ipd.sdq.probespec.framework.calculators.unary.UnaryCalculatorBinding;

public class JavaProbeSpecContext extends ProbeSpecContext<Long> implements IBlackboard<Long> {

    private static final ITimestampGenerator<Long> TIMESTAMP_GENERFATOR = new JavaTimestampBuilder();

    public JavaProbeSpecContext() {
        super(TIMESTAMP_GENERFATOR);
    }

    public JavaProbeSpecContext(BlackboardType blackboardType) {
        super(TIMESTAMP_GENERFATOR, blackboardType);
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe) {
        getBlackboard().addMeasurement(value, probe);
    }
    
    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementMetadata metadata) {
        getBlackboard().addMeasurement(value, probe, metadata);
    }
    
    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementContext... contexts) {
        getBlackboard().addMeasurement(value, probe, contexts);
    }

    @Override
    public <V> void addMeasurement(V value, Probe<V> probe, IMeasurementMetadata metadata, IMeasurementContext... contexts) {
        getBlackboard().addMeasurement(value, probe, metadata, contexts);
    }
    
    @Override
    public void deleteMeasurements(IMeasurementContext context) {
        getBlackboard().deleteMeasurements(context);
    }

    @Override
    public <V> void deleteMeasurement(Probe<V> probe, IMeasurementContext... contexts) {
        getBlackboard().deleteMeasurement(probe, contexts);
    }

    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, Long> l, Probe<V> probe) {
        getBlackboard().addMeasurementListener(l, probe);
    }

    @Override
    public <V> void addMeasurementListener(IBlackboardListener<V, Long> l) {
        getBlackboard().addMeasurementListener(l);
    }
    
    @Override
    public <V> void removeMeasurementListener(IBlackboardListener<V, Long> l) {
        getBlackboard().removeMeasurementListener(l);
    }
    
    @Override
    public <V> IBlackboardReader<V, Long> getReader(Probe<V> probe) {
    	return getBlackboard().getReader(probe);
    }
    
    @Override
    public <V> IBlackboardWriter<V> getWriter(Probe<V> probe) {
        return getBlackboard().getWriter(probe);
    }

    public <IN, OUT> UnaryCalculatorBinding<IN, OUT, Long> addCalculator(IUnaryCalculator<IN, OUT, Long> calculator) {
        return getCalculatorRegistry().add(calculator);
    }

    public <IN1, IN2, OUT> BinaryCalculatorBinding<IN1, IN2, OUT, Long> addCalculator(
            IBinaryCalculator<IN1, IN2, OUT, Long> calculator) {
        return getCalculatorRegistry().add(calculator);
    }

}
