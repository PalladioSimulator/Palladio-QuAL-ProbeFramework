package edu.kit.ipd.sdq.probespec.framework.test;

import junit.framework.Assert;

import org.junit.Test;

import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.framework.ProbeFactory;
import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.KeyBuilder;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.AssemblyContext;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.UsageContext;

public class TestKeyBuilder {

    @Test
    public void testCreateKey() {
        IntegerProbe probe = ProbeFactory.createIntegerProbe("testProbe");
        
        IMeasurementContext assCtx = new AssemblyContext("AssCtx1");
        IMeasurementContext usgCtx = new UsageContext("UsgCtx1");
        
        KeyBuilder builder = new KeyBuilder();
        
        Assert.assertEquals("testProbe", builder.createKey(probe));
        Assert.assertEquals("testProbe::AssCtx1::UsgCtx1", builder.createKey(probe, assCtx, usgCtx));
        Assert.assertEquals("testProbe::AssCtx1::UsgCtx1", builder.createKey(probe, usgCtx, assCtx));
        
    }
    
}
