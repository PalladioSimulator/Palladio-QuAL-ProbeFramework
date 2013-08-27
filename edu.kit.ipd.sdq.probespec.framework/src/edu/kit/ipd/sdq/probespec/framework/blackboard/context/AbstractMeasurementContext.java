package edu.kit.ipd.sdq.probespec.framework.blackboard.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AbstractMeasurementContext implements MeasurementContext {

    private String id;

    private MeasurementContext parent;

    private List<MeasurementContext> children;

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
    public MeasurementContext getRoot() {
        MeasurementContext context = this;
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
    public MeasurementContext getParent() {
        return parent;
    }

    @Override
    public List<MeasurementContext> getChildren() {
        if (children == null) {
            children = new ArrayList<MeasurementContext>();
        }
        return Collections.unmodifiableList(children);
    }
    
    private void addChildren(AbstractMeasurementContext context) {
        if (children == null) {
            children = new ArrayList<MeasurementContext>();
        }
        children.add(context);
    }

    @Override
    public String toString() {
        return "MeasurementContext [id=" + id + ", parent=" + parent + "]";
    }
    
}
