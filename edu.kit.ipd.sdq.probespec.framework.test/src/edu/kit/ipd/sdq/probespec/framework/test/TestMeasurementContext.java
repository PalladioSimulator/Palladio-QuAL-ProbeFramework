package edu.kit.ipd.sdq.probespec.framework.test;

import edu.kit.ipd.sdq.probespec.DerivedIntegerProbe;
import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.framework.ProbeFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.java.DifferenceCalculator;
import edu.kit.ipd.sdq.probespec.java.JavaProbeSpecContext;

public class TestMeasurementContext {

    // @Test
    public void testOne() {
        // initialise logging
        LoggingUtils.configureLogging();

        // initialize ProbeSpec
        JavaProbeSpecContext ctx = new JavaProbeSpecContext(BlackboardType.SIMPLE);

        // create two basic probes
        IntegerProbe startProbe = ProbeFactory.createIntegerProbe("startProbe");
        IntegerProbe stopProbe = ProbeFactory.createIntegerProbe("stopProbeOne");

        // create a derived probe to calculate the difference between the start and stop probe
        DerivedIntegerProbe differenceProbe = ProbeFactory.createDerivedIntegerProbe("differenceProbe");

        // bind derived probe
        ctx.addCalculator(new DifferenceCalculator(differenceProbe)).bind(startProbe, stopProbe);

        // create artificial measurement data
        IMeasurementContext assCtx = new AssemblyContext("MyAssemblyContext");
        IMeasurementContext usgCtx = new UsageContext("User1");

        // -------------------
        long t0 = System.nanoTime();

        IntegerProbe[] probes = new IntegerProbe[100000];
        for (int i = 0; i < 100000; i++) {
            probes[i] = ProbeFactory.createIntegerProbe("probe" + i);
        }

        // -------------------
        long t1 = System.nanoTime();

        for (int i = 0; i < 100000; i++) {
            ctx.getBlackboard().addMeasurement(i, probes[i], assCtx, usgCtx);
        }
        ctx.getBlackboard().addMeasurement(40, startProbe);
        ctx.getBlackboard().addMeasurement(50, stopProbe);

        ctx.getBlackboard().addMeasurement(40, startProbe, assCtx, usgCtx);

        // Assert.assertEquals(new Integer(12), ctx.getBlackboard().getLatestMeasurement(startProbe,
        // usgCtx, assCtx));

        // -------------------
        long t2 = System.nanoTime();

        ctx.getBlackboard().deleteMeasurements(assCtx);

        // -------------------
        long t3 = System.nanoTime();

        // ((ConcurrentBlackboard) ctx.getBlackboard()).synchronise();
        System.gc();

        // -------------------
        long t4 = System.nanoTime();

        ctx.getBlackboard().deleteMeasurements(assCtx);

        long t5 = System.nanoTime();

        long d0 = (t1 - t0) / (1000 * 1000);
        long d1 = (t2 - t1) / (1000 * 1000);
        long d2 = (t3 - t2) / (1000 * 1000);
        long d3 = (t4 - t3) / (1000 * 1000);
        long d4 = (t5 - t4) / (1000 * 1000);

        System.out.println("D0: " + d0 + " (created probes)");
        System.out.println("D1: " + d1 + " (added measurements)");
        System.out.println("D2: " + d2 + " (deleted measurements)");
        System.out.println("D3: " + d3 + " (garbage collection; leaving behind dangling weak references)");
        System.out.println("D4: " + d4 + " (removal of dangling weak references)");

        // Assert.assertEquals(new Integer(40),
        // ctx.getBlackboard().getLatestMeasurement(differenceProbe));

        // System.out.println(ctx.getBlackboard().getLatestMeasurement(thirdProbe));

        ctx.shutdown();
    }

    public static void main(String[] args) {
        TestMeasurementContext c = new TestMeasurementContext();
        c.testOne();
    }

}
