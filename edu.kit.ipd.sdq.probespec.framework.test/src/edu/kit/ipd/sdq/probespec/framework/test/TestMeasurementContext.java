package edu.kit.ipd.sdq.probespec.framework.test;

import junit.framework.Assert;

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
        IMeasurementContext allocCtx = new AssemblyContext("MyAllocationContext");
        IMeasurementContext usgCtx = new UsageContext();
        
        ctx.getBlackboard().addMeasurement(10, startProbe, assCtx, usgCtx);
        ctx.getBlackboard().addMeasurement(50, stopProbe);

        Assert.assertEquals(new Integer(40), ctx.getBlackboard().getLatestMeasurement(differenceProbe));
        
        // System.out.println(ctx.getBlackboard().getLatestMeasurement(thirdProbe));

    }
    
    private class AssemblyContext implements IMeasurementContext {
        
        private String assemblyName;
        
        public AssemblyContext(String name) {
            this.assemblyName = name;
        }
        
    }
    
    private class UsageContext implements IMeasurementContext {
        
    }

}
