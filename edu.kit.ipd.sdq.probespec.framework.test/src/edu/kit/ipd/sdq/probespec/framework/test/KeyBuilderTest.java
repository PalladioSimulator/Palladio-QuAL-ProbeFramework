package edu.kit.ipd.sdq.probespec.framework.test;

import junit.framework.Assert;

import org.junit.Test;

import edu.kit.ipd.sdq.probespec.framework.blackboard.KeyBuilder;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.probes.AbstractProbe;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.AssemblyContext;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.UsageContext;

public class KeyBuilderTest {

    @Test
    public void testCreateKey() {
        IntegerProbe probe = new IntegerProbe("testProbe", "testProbe");

        MeasurementContext assCtx = new AssemblyContext("AssCtx1");
        MeasurementContext usgCtx = new UsageContext("UsgCtx1");

        KeyBuilder builder = new KeyBuilder();

        Assert.assertEquals("testProbe", builder.createKey(probe));
        Assert.assertEquals("testProbe::AssCtx1::UsgCtx1", builder.createKey(probe, assCtx, usgCtx));
        Assert.assertEquals("testProbe::AssCtx1::UsgCtx1", builder.createKey(probe, usgCtx, assCtx));

    }

    private static class IntegerProbe extends AbstractProbe<Integer> {

        public IntegerProbe(String id, String name) {
            super(id, name);
        }

        @Override
        public Class<Integer> getGenericClass() {
            return Integer.class;
        }

    }

}
