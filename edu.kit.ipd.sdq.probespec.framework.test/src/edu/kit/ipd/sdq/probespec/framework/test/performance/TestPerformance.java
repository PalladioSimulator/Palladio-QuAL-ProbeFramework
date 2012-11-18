package edu.kit.ipd.sdq.probespec.framework.test.performance;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import edu.kit.ipd.sdq.probespec.DerivedIntegerProbe;
import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.framework.ProbeFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.AssemblyContext;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.UsageContext;
import edu.kit.ipd.sdq.probespec.framework.test.util.LoggingUtils;
import edu.kit.ipd.sdq.probespec.java.DifferenceCalculator;
import edu.kit.ipd.sdq.probespec.java.JavaProbeSpecContext;

public class TestPerformance {

    private JavaProbeSpecContext ps;

    private IntegerProbe startProbe;
    private IntegerProbe stopProbe;

    @Rule
    public TestName name = new TestName();

    @BeforeClass
    public static void beforeClass() {
        // initialise logging
         LoggingUtils.configureLogging();
    }

    @Before
    public void before() {
        // initialize ProbeSpec
        ps = new JavaProbeSpecContext(BlackboardType.SIMPLE);
    }

    @Test
    public void test1000x1() {
        testTwoBasicOneDerived(1000, 1);
    }

    @Test
    public void test10000x1() {
        testTwoBasicOneDerived(10000, 1);
    }

    @Test
    public void test100000x1() {
        testTwoBasicOneDerived(100000, 1);
    }

    @Test
    public void test1000000x1() {
        testTwoBasicOneDerived(1000000, 1);
    }
    
    @Test
    public void test100000x10() {
        testTwoBasicOneDerived(1000, 10);
    }

    @Test
    public void test10000x100() {
        testTwoBasicOneDerived(10000, 100);
    }

    @Test
    public void test10000x1000() {
        testTwoBasicOneDerived(100000, 1000);
    }

    @Test
    public void test10000x10000() {
        testTwoBasicOneDerived(10000, 10000);
    }

    private void testTwoBasicOneDerived(int numberOfMeasurements, int numberOfderivedProbes) {
        System.out.println("---------------------------------------------------");
        System.out.println(name.getMethodName());
        System.out.println("...................................................");

        // create two basic probes
        startProbe = ProbeFactory.createIntegerProbe("startProbe");
        stopProbe = ProbeFactory.createIntegerProbe("stopProbeOne");

        DerivedIntegerProbe[] derivedProbes = new DerivedIntegerProbe[numberOfderivedProbes];
        for (int i = 0; i < numberOfderivedProbes; i++) {
            // create a derived probe to calculate the difference between the start and stop probe
            DerivedIntegerProbe differenceProbe = ProbeFactory.createDerivedIntegerProbe("differenceProbe");
            derivedProbes[i] = differenceProbe;
            
            // bind derived probe
            ps.addCalculator(new DifferenceCalculator(differenceProbe)).bind(startProbe, stopProbe);
        }

        // create artificial measurement data
        IMeasurementContext assCtx = new AssemblyContext("MyAssemblyContext");
        IMeasurementContext usgCtx = new UsageContext("User1");

        long t0 = System.nanoTime();
        for (int i = 0; i < numberOfMeasurements; i++) {
            ps.addMeasurement(i, startProbe);
            ps.addMeasurement(i + 20, stopProbe);
        }

        // -------------------
        long t1 = System.nanoTime();

        long d0 = (t1 - t0) / (1000 * 1000);

        System.out.println("D0: " + d0 + " (added measurements)");
    }

    // public void test2() {
    // ps.getBlackboard().deleteMeasurements(assCtx);
    //
    // // -------------------
    // long t3 = System.nanoTime();
    //
    // // ((ConcurrentBlackboard) ctx.getBlackboard()).synchronise();
    // System.gc();
    //
    // // -------------------
    // long t4 = System.nanoTime();
    //
    // ps.getBlackboard().deleteMeasurements(assCtx);
    //
    // long t5 = System.nanoTime();
    //
    // long d0 = (t1 - t0) / (1000 * 1000);
    // long d1 = (t2 - t1) / (1000 * 1000);
    // long d2 = (t3 - t2) / (1000 * 1000);
    // long d3 = (t4 - t3) / (1000 * 1000);
    // long d4 = (t5 - t4) / (1000 * 1000);
    //
    // System.out.println("D0: " + d0 + " (created probes)");
    // System.out.println("D1: " + d1 + " (added measurements)");
    // System.out.println("D2: " + d2 + " (deleted measurements)");
    // System.out.println("D3: " + d3 +
    // " (garbage collection; leaving behind dangling weak references)");
    // System.out.println("D4: " + d4 + " (removal of dangling weak references)");
    // }

    @After
    public void after() {
        ps.shutdown();
    }

}
