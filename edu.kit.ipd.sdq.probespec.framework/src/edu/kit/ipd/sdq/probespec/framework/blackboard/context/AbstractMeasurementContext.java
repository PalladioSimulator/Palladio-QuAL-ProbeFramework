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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractMeasurementContext other = (AbstractMeasurementContext) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
