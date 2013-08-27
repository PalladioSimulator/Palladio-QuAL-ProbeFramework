package edu.kit.ipd.sdq.probespec.framework.test.performance;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.AssemblyContext;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.UsageContext;
import edu.kit.ipd.sdq.probespec.framework.test.util.IntegerProbe;
import edu.kit.ipd.sdq.probespec.framework.test.util.LoggingUtils;
import edu.kit.ipd.sdq.probespec.java.DifferenceCalculator;
import edu.kit.ipd.sdq.probespec.java.JavaProbeManager;

public class PerformanceTest {

    private JavaProbeManager ps;

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
        ps = new JavaProbeManager(BlackboardType.SIMPLE);
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
        startProbe = new IntegerProbe("startProbe");
        stopProbe = new IntegerProbe("stopProbeOne");
        
        ps.mountProbe(startProbe, "AnEntity");
        ps.mountProbe(stopProbe, "AnEntity");

        IntegerProbe[] derivedProbes = new IntegerProbe[numberOfderivedProbes];
        for (int i = 0; i < numberOfderivedProbes; i++) {
            // create a derived probe to calculate the difference between the start and stop probe
            IntegerProbe differenceProbe = new IntegerProbe("differenceProbe");
            ps.mountProbe(differenceProbe, "AnEntity");
            derivedProbes[i] = differenceProbe;
            
            // bind derived probe
            ps.installCalculator(new DifferenceCalculator()).bindInput(startProbe, stopProbe).bindOutput(differenceProbe);
        }

        // create artificial measurement data
        MeasurementContext assCtx = new AssemblyContext("MyAssemblyContext");
        MeasurementContext usgCtx = new UsageContext("User1");

        long t0 = System.nanoTime();
        for (int i = 0; i < numberOfMeasurements; i++) {
            startProbe.addMeasurement(i);
            stopProbe.addMeasurement(i + 20);
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
