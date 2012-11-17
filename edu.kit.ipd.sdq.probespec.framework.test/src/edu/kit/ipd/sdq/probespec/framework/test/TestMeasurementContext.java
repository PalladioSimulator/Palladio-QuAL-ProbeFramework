package edu.kit.ipd.sdq.probespec.framework.test;

import edu.kit.ipd.sdq.probespec.DerivedIntegerProbe;
import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.framework.ProbeFactory;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IBlackboard;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.concurrent.ThreadManager;
import edu.kit.ipd.sdq.probespec.java.DifferenceCalculator;
import edu.kit.ipd.sdq.probespec.java.JavaTimestampBuilder;

public class TestMeasurementContext {

    // @Test
    public void testOne() {
        // initialise logging
        LoggingUtils.configureLogging();

        // ProbeSpecContext<Long> ctx = new ProbeSpecContext<Long>(new SimpleTimestampBuilder());
        ThreadManager m = new ThreadManager();
        IBlackboard<Long> bb = BlackboardFactory.createBlackboard(BlackboardType.SIMPLE,
                new JavaTimestampBuilder(), m);
        ProbeSpecContext<Long> ctx = new ProbeSpecContext<Long>(bb);

        // create two basic probes
        IntegerProbe startProbe = ProbeFactory.createIntegerProbe("startProbe");
        IntegerProbe stopProbe = ProbeFactory.createIntegerProbe("stopProbeOne");

        // create a derived probe to calculate the difference between the start and stop probe
        DerivedIntegerProbe differenceProbe = ProbeFactory.createDerivedIntegerProbe("differenceProbe");

        // bind derived probe
        ctx.getCalculatorRegistry().bind(new DifferenceCalculator(differenceProbe), startProbe, stopProbe);

        // create artificial measurement data
        IMeasurementContext assCtx = new AssemblyContext("MyAssemblyContext");
        IMeasurementContext usgCtx = new UsageContext("User1");

        IntegerProbe[] probes = new IntegerProbe[1000000];
        for (int i = 0; i < 1000000; i++) {
            probes[i] = ProbeFactory.createIntegerProbe("probe" + i);
        }

        // -------------------
        long t1 = System.nanoTime();

        for (int i = 0; i < 1000000; i++) {
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

        long d1 = (t2 - t1) / (1000 * 1000);
        long d2 = (t3 - t2) / (1000 * 1000);
        long d3 = (t4 - t3) / (1000 * 1000);
        long d4 = (t5 - t4) / (1000 * 1000);

        System.out.println("D1: " + d1 + " (added 1.000.000 measurements)");
        System.out.println("D2: " + d2 + " (delete 1.000.000 measurements)");
        System.out.println("D3: " + d3 + " (garbage collection; leaving behind dangling weak references)");
        System.out.println("D4: " + d4 + " (removal of dangling weak references)");

        // Assert.assertEquals(new Integer(40),
        // ctx.getBlackboard().getLatestMeasurement(differenceProbe));

        // System.out.println(ctx.getBlackboard().getLatestMeasurement(thirdProbe));

        m.stopThreads();
    }

    public static void main(String[] args) {
        TestMeasurementContext c = new TestMeasurementContext();
        c.testOne();
    }

}
