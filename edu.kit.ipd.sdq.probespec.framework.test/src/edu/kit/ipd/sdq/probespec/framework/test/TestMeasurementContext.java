package edu.kit.ipd.sdq.probespec.framework.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import edu.kit.ipd.sdq.probespec.framework.blackboard.IMeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.test.mockup.AssemblyContext;

public class TestMeasurementContext {

    private AssemblyContext root;
    private AssemblyContext child1;
    private AssemblyContext child2;
    private AssemblyContext child3;
    private AssemblyContext childOfChild2;

    @Rule
    public TestName name = new TestName();

    @Before
    public void before() {
        root = new AssemblyContext("1");
        
        child1 = new AssemblyContext("1.1", root);
        child2 = new AssemblyContext("1.2", root);
        child3 = new AssemblyContext("1.3", root);
        
        childOfChild2 = new AssemblyContext("1.2.1", child2);
    }

    @Test
    public void testGetChildren() {
        // test if root has exactly 3 children: child1, child2, child3
        List<IMeasurementContext> childsOfRoot = root.getChildren();
        Assert.assertEquals(3, childsOfRoot.size());
        Assert.assertTrue(childsOfRoot.contains(child1));
        Assert.assertTrue(childsOfRoot.contains(child2));
        Assert.assertTrue(childsOfRoot.contains(child3));

        // test if child1 has no children
        List<IMeasurementContext> childsOfChild1 = child1.getChildren();
        Assert.assertEquals(0, childsOfChild1.size());

        // test if child2 has exactly 1 children: childOfChild2
        List<IMeasurementContext> childsOfChild2 = child2.getChildren();
        Assert.assertEquals(1, childsOfChild2.size());
        Assert.assertTrue(childsOfChild2.contains(childOfChild2));
    }
    
    @Test
    public void testGetParent() {
        Assert.assertNull(root.getParent());
        
        Assert.assertEquals(root, child1.getParent());
        Assert.assertEquals(root, child2.getParent());
        Assert.assertEquals(root, child3.getParent());
        
        Assert.assertEquals(child2, childOfChild2.getParent());
    }
    
    @Test
    public void testGetRoot() {
        Assert.assertEquals(root, root.getRoot());
        
        Assert.assertEquals(root, child1.getRoot());
        Assert.assertEquals(root, child2.getRoot());
        Assert.assertEquals(root, child3.getRoot());
        
        Assert.assertEquals(root, childOfChild2.getRoot());
    }
    
    @Test
    public void testIsRoot() {
        Assert.assertTrue(root.isRoot());
        Assert.assertFalse(child1.isRoot());
        Assert.assertFalse(child2.isRoot());
        Assert.assertFalse(child3.isRoot());
        Assert.assertFalse(childOfChild2.isRoot());
    }

}
