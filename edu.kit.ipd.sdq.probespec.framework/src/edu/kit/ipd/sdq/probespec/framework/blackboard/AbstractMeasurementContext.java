package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AbstractMeasurementContext implements IMeasurementContext {

    private String id;

    private IMeasurementContext parent;

    private List<IMeasurementContext> children;

    public AbstractMeasurementContext(String id) {
        this(id, null);
    }

    public AbstractMeasurementContext(String id, AbstractMeasurementContext parent) {
        this.id = id;
        this.parent = parent;

        if (parent != null) {
            parent.addChildren(this);
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public IMeasurementContext getRoot() {
        IMeasurementContext context = this;
        while (context.getParent() != null) {
            context = context.getParent();
        }
        return context;
    }
    
    @Override
    public boolean isRoot() {
        return this == getRoot();
    }

    @Override
    public IMeasurementContext getParent() {
        return parent;
    }

    @Override
    public List<IMeasurementContext> getChildren() {
        if (children == null) {
            children = new ArrayList<IMeasurementContext>();
        }
        return Collections.unmodifiableList(children);
    }
    
    private void addChildren(AbstractMeasurementContext context) {
        if (children == null) {
            children = new ArrayList<IMeasurementContext>();
        }
        children.add(context);
    }

    @Override
    public String toString() {
        return "MeasurementContext [id=" + id + ", parent=" + parent + "]";
    }
    
}
