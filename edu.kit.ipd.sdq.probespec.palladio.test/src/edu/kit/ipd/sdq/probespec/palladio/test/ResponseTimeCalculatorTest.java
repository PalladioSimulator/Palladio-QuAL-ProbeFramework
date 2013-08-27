package edu.kit.ipd.sdq.probespec.palladio.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.kit.ipd.sdq.probespec.framework.MeasurementListener;
import edu.kit.ipd.sdq.probespec.framework.TimestampGenerator;
import edu.kit.ipd.sdq.probespec.framework.AbstractProbeManager;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.Measurement;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContextHelper;
import edu.kit.ipd.sdq.probespec.framework.blackboard.reader.BlackboardReader;
import edu.kit.ipd.sdq.probespec.framework.probes.Probe;
import edu.kit.ipd.sdq.probespec.framework.test.util.DoubleProbe;
import edu.kit.ipd.sdq.probespec.framework.test.util.LoggingUtils;
import edu.kit.ipd.sdq.probespec.pcm.ResponseTimeCalculator2;
import edu.kit.ipd.sdq.probespec.pcm.contexts.UsageContext;

public class ResponseTimeCalculatorTest {

    @BeforeClass
    public static void setup() {
        // initialise logging
        LoggingUtils.configureLogging();
    }

    @Test
    public void testResponseTimeCalculator() {
        AbstractProbeManager<Double> ps = new AbstractProbeManager<Double>(new MockupTimestampGenerator(),
                BlackboardType.SIMPLE);

        ps.getBlackboard().addMeasurementListener(new PrintMeasurementsListener());

        DoubleProbe startProbe = ProbeFactory.createDoubleProbe("startProbe");
        DoubleProbe stopProbe = ProbeFactory.createDoubleProbe("stopProbe");

        DerivedDoubleProbe responseTimeProbe = ProbeFactory.createDerivedDoubleProbe("responseTimeProbe");
        ps.getCalculatorRegistry().add(new ResponseTimeCalculator2()).bindInput(startProbe, stopProbe).bindOutput(responseTimeProbe);
        BlackboardReader<Double, Double> responseTimeReader = ps.getBlackboard().getReader(responseTimeProbe);

        MeasurementContext rootCtx = new UsageContext("root");
        MeasurementContext childCtx = new UsageContext("child", (UsageContext) rootCtx);
        MeasurementContext childOfChildCtx = new UsageContext("childOfChild", (UsageContext) childCtx);

        // ////////////////////////////////////
        ps.getBlackboard().addMeasurement(10.0, startProbe, rootCtx);
        // ////////////////////////////////////

        ps.getBlackboard().addMeasurement(12.0, stopProbe, rootCtx);
        Assert.assertEquals(new Double(2.0), responseTimeReader.getLatestMeasurement(rootCtx).getValue());

        ps.getBlackboard().addMeasurement(14.0, stopProbe, childCtx);
        Assert.assertEquals(new Double(4.0), responseTimeReader.getLatestMeasurement(childCtx).getValue());

        ps.getBlackboard().addMeasurement(16.0, stopProbe, childOfChildCtx);
        Assert.assertEquals(new Double(6.0), responseTimeReader.getLatestMeasurement(childOfChildCtx).getValue());

        // ////////////////////////////////////
        ps.getBlackboard().addMeasurement(20.0, startProbe, childCtx);
        // ////////////////////////////////////

        ps.getBlackboard().addMeasurement(22.0, stopProbe, rootCtx);
        Assert.assertEquals(new Double(12.0), responseTimeReader.getLatestMeasurement(rootCtx).getValue());

        ps.getBlackboard().addMeasurement(24.0, stopProbe, childCtx);
        Assert.assertEquals(new Double(4.0), responseTimeReader.getLatestMeasurement(childCtx).getValue());

        ps.getBlackboard().addMeasurement(26.0, stopProbe, childOfChildCtx);
        Assert.assertEquals(new Double(6.0), responseTimeReader.getLatestMeasurement(childOfChildCtx).getValue());

        // ////////////////////////////////////
        ps.getBlackboard().addMeasurement(30.0, startProbe, childOfChildCtx);
        // ////////////////////////////////////

        ps.getBlackboard().addMeasurement(32.0, stopProbe, rootCtx);
        Assert.assertEquals(new Double(22.0), responseTimeReader.getLatestMeasurement(rootCtx).getValue());

        ps.getBlackboard().addMeasurement(34.0, stopProbe, childCtx);
        Assert.assertEquals(new Double(14.0), responseTimeReader.getLatestMeasurement(childCtx).getValue());

        ps.getBlackboard().addMeasurement(36.0, stopProbe, childOfChildCtx);
        Assert.assertEquals(new Double(6.0), responseTimeReader.getLatestMeasurement(childOfChildCtx).getValue());

        ps.shutdown();
    }

    private class MockupTimestampGenerator implements TimestampGenerator<Double> {

        @Override
        public Double now() {
            return Double.valueOf(System.nanoTime());
        }

    }

    private static class PrintMeasurementsListener implements MeasurementListener<Double, Double> {

        @Override
        public void measurementArrived(Measurement<Double, Double> measurement, Probe<Double> probe,
                MeasurementContext... contexts) {
            System.out.println("Encountered " + measurement + " for probe " + probe.getName() + " in "
                    + MeasurementContextHelper.filter(UsageContext.class, contexts));
        }

        @Override
        public Class<Double> getGenericType() {
            return Double.class;
        }

    }

}
