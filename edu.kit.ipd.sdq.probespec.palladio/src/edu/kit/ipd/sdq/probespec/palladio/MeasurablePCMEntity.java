package edu.kit.ipd.sdq.probespec.palladio;

import de.uka.ipd.sdq.pcm.core.entity.Entity;
import edu.kit.ipd.sdq.probespec.framework.MeasurableEntity;

public class MeasurablePCMEntity implements MeasurableEntity {

    private Entity pcmEntity;

    public MeasurablePCMEntity(Entity pcmEntity) {
        this.pcmEntity = pcmEntity;
    }

    @Override
    public String getMeasurableEntityId() {
        return pcmEntity.getId();
    }

    @Override
    public String getMeasurableEntityName() {
        return pcmEntity.getEntityName();
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
        MeasurablePCMEntity other = (MeasurablePCMEntity) obj;
        if (getMeasurableEntityId() == null) {
            if (other.getMeasurableEntityId() != null)
                return false;
        } else if (!getMeasurableEntityId().equals(other.getMeasurableEntityId()))
            return false;
        return true;
    }

}
