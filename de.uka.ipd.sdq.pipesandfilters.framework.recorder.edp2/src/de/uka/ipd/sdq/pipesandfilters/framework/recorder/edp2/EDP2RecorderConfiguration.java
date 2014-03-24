package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2;

import java.io.Serializable;
import java.util.Map;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.AbstractRecorderConfiguration;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

public class EDP2RecorderConfiguration extends AbstractRecorderConfiguration implements IRecorderConfiguration, Serializable {

    private static final long serialVersionUID = 1L;

    private String repositoryID;

    public static final String REPOSITORY_ID = "EDP2RepositoryID";
    public static final String PERSISTENCE_RECORDER_ID = "EDP2RecorderID";

    @Override
    public void setConfiguration(final Map<String,Object> configuration) {
        super.setConfiguration(configuration);
        this.repositoryID = (String) configuration.get(
                REPOSITORY_ID);
    }

    public String getRepositoryID() {
        return this.repositoryID;
    }

    public String getModelElementID() {
        // TODO!
        return null;
    }

}
