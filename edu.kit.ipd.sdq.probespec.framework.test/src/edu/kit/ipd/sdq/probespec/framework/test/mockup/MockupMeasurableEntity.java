package edu.kit.ipd.sdq.probespec.framework.test.mockup;

import java.util.concurrent.atomic.AtomicInteger;

import edu.kit.ipd.sdq.probespec.framework.MeasurableEntity;

public class MockupMeasurableEntity implements MeasurableEntity {

    private String entityName;
    
    private static final AtomicInteger idGenerator = new AtomicInteger();
    
    private Integer id;

    public MockupMeasurableEntity(String entityName) {
        this.entityName = entityName;
        this.id = idGenerator.incrementAndGet();
    }

    @Override
    public String getMeasurableEntityId() {
        return id.toString();
    }

    @Override
    public String getMeasurableEntityName() {
        return entityName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMeasurableEntityId() == null) ? 0 : getMeasurableEntityId().hashCode());
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
        MockupMeasurableEntity other = (MockupMeasurableEntity) obj;
        if (getMeasurableEntityId() == null) {
            if (other.getMeasurableEntityId() != null)
                return false;
        } else if (!getMeasurableEntityId().equals(other.getMeasurableEntityId()))
            return false;
        return true;
    }

}
