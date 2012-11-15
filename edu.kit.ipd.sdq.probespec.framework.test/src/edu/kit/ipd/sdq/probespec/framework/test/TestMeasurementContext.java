package edu.kit.ipd.sdq.probespec.framework.test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import edu.kit.ipd.sdq.probespec.DerivedIntegerProbe;
import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.framework.ProbeFactory;
import edu.kit.ipd.sdq.probespec.framework.ProbeSpecContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.calculators.binary.example.DifferenceCalculator;

public class TestMeasurementContext {

    @Test
    public void testOne() {
        // configure log4j
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.DEBUG);
        
        ProbeSpecContext ctx = new ProbeSpecContext();

        // create two basic probes
        IntegerProbe startProbe = ProbeFactory.createIntegerProbe("startProbe");
        IntegerProbe stopProbe = ProbeFactory.createIntegerProbe("stopProbeOne");

        // create a derived probe to calculate the difference between the start and stop probe
        DerivedIntegerProbe differenceProbe = ProbeFactory.createDerivedIntegerProbe("differenceProbe");

        // bind derived probe
        ctx.getBindingContext().bind(new DifferenceCalculator(differenceProbe), startProbe, stopProbe);

        // create artificial measurement data
        IMeasurementContext assCtx = new AssemblyContext("MyAssemblyContext");
        IMeasurementContext usgCtx = new UsageContext("User1");

        //-------------------
        long t1 = System.nanoTime();

        for (int i = 0; i < 1000000; i++) {
            IntegerProbe p = ProbeFactory.createIntegerProbe("probe" + i);
            ctx.getBlackboard().addMeasurement(i, p, assCtx, usgCtx);
        }
        ctx.getBlackboard().addMeasurement(40, startProbe);
        ctx.getBlackboard().addMeasurement(50, stopProbe);
        
        ctx.getBlackboard().addMeasurement(40, startProbe, assCtx, usgCtx);

        // Assert.assertEquals(new Integer(12), ctx.getBlackboard().getLatestMeasurement(startProbe,
        // usgCtx, assCtx));
        
        //-------------------
        long t2 = System.nanoTime();
        
        ctx.getBlackboard().deleteMeasurements(assCtx);

        //-------------------
        long t3 = System.nanoTime();
        
        System.gc();
        
        //-------------------
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

    }

}
