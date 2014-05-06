package org.palladiosimulator.probeframework.test.requestcontext;

import junit.framework.TestCase;

import org.palladiosimulator.probeframework.measurement.RequestContext;

/**
 * JUnit tests for request contexts of the Probe Framework. The tests check whether request contexts
 * correctly cope with their <code>requestContextId</code> (constructor parameter), which uniquely
 * identifies contexts but can also dynamically be extended by strings.
 * 
 * @author Sebastian Lehrig, Steffen Becker
 */
public class RequestContextTest extends TestCase {

    /**
     * Test case for {@link RequestContext} that tests whether request contexts are uniquely
     * identified by their <code>requestContextId</code>.
     */
    public void testEquals() {
        RequestContext ctx1 = new RequestContext("1");
        RequestContext ctx2 = new RequestContext("2");
        RequestContext ctx3 = new RequestContext("1");

        assertFalse(ctx1.equals(ctx2));
        assertFalse(ctx2.equals(ctx1));

        assertFalse(ctx2.equals(ctx3));
        assertFalse(ctx3.equals(ctx2));

        assertTrue(ctx1.equals(ctx3));
        assertTrue(ctx3.equals(ctx1));
    }

    /**
     * Test case for {@link RequestContext} that tests whether the <code>requestContextId</code> of
     * request contexts without parents can be extended by a String.
     */
    public void testAppendWithoutParent() {
        RequestContext sourceContext = new RequestContext("aContext");
        RequestContext extendedContext = sourceContext.append("_anAddition");

        String extendedContextId = "aContext" + "_anAddition";
        assertEquals(extendedContextId, extendedContext.getRequestContextId());
        assertEquals(new RequestContext(extendedContextId), extendedContext);
    }

    /**
     * Test case for {@link RequestContext} that tests whether the <code>requestContextId</code> of
     * request contexts with parents can be extended by a String.
     */
    public void testAppendWithParent() {
        RequestContext parentContext = new RequestContext("aParentContext");

        RequestContext sourceContext = new RequestContext("aContext", parentContext);
        RequestContext extendedContext = sourceContext.append("_anAddition");

        String extendedContextId = "aContext" + "_anAddition";
        assertEquals(extendedContextId, extendedContext.getRequestContextId());
        assertEquals(new RequestContext(extendedContextId, parentContext), extendedContext);
    }

}
