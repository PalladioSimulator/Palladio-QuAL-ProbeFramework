package edu.kit.ipd.sdq.probespec.framework.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.AbstractMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContextHelper;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.AssemblyContext;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.UsageContext;

public class MeasurementContextHelperTest {

    private AssemblyContext assCtxRoot;
    private AssemblyContext assCtxChild;

    private UsageContext usgCtxRoot;
    private UsageContext usgCtxChild;

    @Before
    public void before() {
        assCtxRoot = new AssemblyContext("Assembly Context Root");
        assCtxChild = new AssemblyContext("Assembly Context Child", assCtxRoot);
        usgCtxRoot = new UsageContext("Usage Context Root");
        usgCtxChild = new UsageContext("Usage Context Child", usgCtxRoot);
    }

    @Test
    public void testParentContext_noContexts() {
        MeasurementContext[] results = MeasurementContextHelper.parentContext(AssemblyContext.class);
        Assert.assertArrayEquals(new MeasurementContext[] {}, results);
    }

    @Test
    public void testParentContext_unknownContext() {
        MeasurementContext[] results = MeasurementContextHelper.parentContext(DummyContext.class, assCtxChild,
                usgCtxChild);
        Assert.assertArrayEquals(new MeasurementContext[] { assCtxChild, usgCtxChild }, results);
    }

    @Test
    public void testParentContext() {
        MeasurementContext[] results = MeasurementContextHelper.parentContext(AssemblyContext.class, assCtxChild,
                usgCtxChild);
        Assert.assertArrayEquals(new MeasurementContext[] { assCtxRoot, usgCtxChild }, results);

        results = MeasurementContextHelper.parentContext(UsageContext.class, assCtxChild, usgCtxChild);
        Assert.assertArrayEquals(new MeasurementContext[] { assCtxChild, usgCtxRoot }, results);
    }

    @Test
    public void testFilter() {
        MeasurementContext result = MeasurementContextHelper.filter(AssemblyContext.class, assCtxChild, usgCtxChild);
        Assert.assertEquals(result, assCtxChild);

        result = MeasurementContextHelper.filter(UsageContext.class, assCtxChild, usgCtxChild);
        Assert.assertEquals(result, usgCtxChild);
    }

    @Test
    public void testFilter_unknownContext() {
        MeasurementContext result = MeasurementContextHelper.filter(DummyContext.class, assCtxChild, usgCtxChild);
        Assert.assertNull(result);
    }

    @Test
    public void testFilter_noContexts() {
        MeasurementContext result = MeasurementContextHelper.filter(DummyContext.class);
        Assert.assertNull(result);
    }

    private static class DummyContext extends AbstractMeasurementContext {

        public DummyContext() {
            super("Dummy Context");
        }

    }

}
