package edu.kit.ipd.sdq.probespec.framework.test;

import junit.framework.Assert;

import org.junit.Test;

import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.framework.ProbeFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.KeyBuilder;

public class TestKeyBuilder {

    @Test
    public void testX() {
        IntegerProbe probe = ProbeFactory.createIntegerProbe("testProbe");
        
        IMeasurementContext assCtx = new AssemblyContext("AssCtx1");
        IMeasurementContext usgCtx = new UsageContext("UsgCtx1");
        
        KeyBuilder builder = new KeyBuilder();
//        builder.addContext(assCtx.getClass());
//        builder.addContext(usgCtx.getClass());
        
        Assert.assertEquals("testProbe::AssCtx1::UsgCtx1", builder.createKey(probe, assCtx, usgCtx));
        Assert.assertEquals("testProbe::AssCtx1::UsgCtx1", builder.createKey(probe, usgCtx, assCtx));
        
    }
    
}
