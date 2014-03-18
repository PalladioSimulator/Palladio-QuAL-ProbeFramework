package de.uka.ipd.sdq.pipesandfilters.framework.recorder.edp2.launch;

import java.io.Serializable;
import java.util.Map;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

public class EDP2Config implements IRecorderConfiguration, Serializable {

    private static final long serialVersionUID = 1L;
    
    private String repositoryID;
    
    public static final String REPOSITORY_ID = "EDP2RepositoryID";
    public static final String PERSISTENCE_RECORDER_ID = "EDP2RecorderID";
    
    public void setConfiguration(Map<String,Object> configuration) {
        this.repositoryID = (String) configuration.get(
                REPOSITORY_ID); 
    }
    
    public String getRepositoryID() {
        return this.repositoryID;
    }

}