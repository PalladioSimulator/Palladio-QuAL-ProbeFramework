package edu.kit.ipd.sdq.probespec.pcm.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.kit.ipd.sdq.probespec.DerivedDoubleProbe;
import edu.kit.ipd.sdq.probespec.DoubleProbe;
import edu.kit.ipd.sdq.probespec.Probe;
import edu.kit.ipd.sdq.probespec.framework.ITimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.ProbeFactory;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.MeasurementContextHelper;
import edu.kit.ipd.sdq.probespec.framework.blackboard.listener.IBlackboardListener;
import edu.kit.ipd.sdq.probespec.framework.test.util.LoggingUtils;
import edu.kit.ipd.sdq.probespec.pcm.ResponseTimeCalculator;
import edu.kit.ipd.sdq.probespec.pcm.contexts.UsageContext;

public class TestResponseTimeCalculator {

    @BeforeClass
    public static void setup() {
        // initialise logging
        LoggingUtils.configureLogging();
    }

    @Test
    public void testResponseTimeCalculator() {
        ProbeSpecContext<Double> ps = new ProbeSpecContext<Double>(new MockupTimestampGenerator(),
                BlackboardType.SIMPLE);
        
        ps.getBlackboard().addMeasurementListener(new PrintMeasurementsListener());

        DoubleProbe startProbe = ProbeFactory.createDoubleProbe("startProbe");
        DoubleProbe stopProbe = ProbeFactory.createDoubleProbe("stopProbe");

        DerivedDoubleProbe responseTimeProbe = ProbeFactory.createDerivedDoubleProbe("responseTimeProbe");
        ps.getCalculatorRegistry().add(new ResponseTimeCalculator(responseTimeProbe)).bind(startProbe, stopProbe);
        IBlackboardReader<Double, Double> responseTimeReader = ps.getBlackboard().getReader(responseTimeProbe);

        IMeasurementContext rootCtx = new UsageContext("root");
        IMeasurementContext childCtx = new UsageContext("child", (UsageContext) rootCtx);
        IMeasurementContext childOfChildCtx = new UsageContext("childOfChild", (UsageContext) childCtx);

        // ////////////////////////////////////
        ps.getBlackboard().addMeasurement(10.0, startProbe, rootCtx);
        // ////////////////////////////////////

        ps.getBlackboard().addMeasurement(12.0, stopProbe, rootCtx);
        Assert.assertEquals(new Double(2.0), responseTimeReader.getLatestMeasurement().getValue());

        ps.getBlackboard().addMeasurement(14.0, stopProbe, childCtx);
        Assert.assertEquals(new Double(4.0), responseTimeReader.getLatestMeasurement().getValue());

        ps.getBlackboard().addMeasurement(16.0, stopProbe, childOfChildCtx);
        Assert.assertEquals(new Double(6.0), responseTimeReader.getLatestMeasurement().getValue());

        // ////////////////////////////////////
        ps.getBlackboard().addMeasurement(20.0, startProbe, childCtx);
        // ////////////////////////////////////

        ps.getBlackboard().addMeasurement(22.0, stopProbe, rootCtx);
        Assert.assertEquals(new Double(12.0), responseTimeReader.getLatestMeasurement().getValue());

        ps.getBlackboard().addMeasurement(24.0, stopProbe, childCtx);
        Assert.assertEquals(new Double(4.0), responseTimeReader.getLatestMeasurement().getValue());

        ps.getBlackboard().addMeasurement(26.0, stopProbe, childOfChildCtx);
        Assert.assertEquals(new Double(6.0), responseTimeReader.getLatestMeasurement().getValue());

        // ////////////////////////////////////
        ps.getBlackboard().addMeasurement(30.0, startProbe, childOfChildCtx);
        // ////////////////////////////////////

        ps.getBlackboard().addMeasurement(32.0, stopProbe, rootCtx);
        Assert.assertEquals(new Double(22.0), responseTimeReader.getLatestMeasurement().getValue());

        ps.getBlackboard().addMeasurement(34.0, stopProbe, childCtx);
        Assert.assertEquals(new Double(14.0), responseTimeReader.getLatestMeasurement().getValue());

        ps.getBlackboard().addMeasurement(36.0, stopProbe, childOfChildCtx);
        Assert.assertEquals(new Double(6.0), responseTimeReader.getLatestMeasurement().getValue());

        ps.shutdown();
    }

    private class MockupTimestampGenerator implements ITimestampGenerator<Double> {

        @Override
        public Double now() {
            return Double.valueOf(System.nanoTime());
        }

    }

    private static class PrintMeasurementsListener implements IBlackboardListener<Double, Double> {

        @Override
        public void measurementArrived(Measurement<Double, Double> measurement, Probe<Double> probe,
                IMeasurementContext... contexts) {
            System.out.println("Encountered " + measurement + " for probe " + probe.getName() + " in " + MeasurementContextHelper.filter(UsageContext.class, contexts));
        }

        @Override
        public Class<Double> getGenericType() {
            return Double.class;
        }

    }

}
