package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.AbstractRecorderConfiguration;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;

public class SensorFrameworkRecorderConfiguration extends AbstractRecorderConfiguration implements IRecorderConfiguration, Serializable {

    private static final long serialVersionUID = 1L;

    private long datasourceID;
    private boolean isRemoteRun;

    public static final String DATASOURCE_ID = "datasourceID";
    public static final String PERSISTENCE_RECORDER_ID = "SensorFramework";

    @Override
    public void setConfiguration(final Map<String,Object> configuration) {
        super.setConfiguration(configuration);
        this.datasourceID = getValue(configuration, DATASOURCE_ID, Integer.class);
    }

    public long getDatasourceID() {
        return this.datasourceID;
    }

    public boolean isRemoteRun() {
        return isRemoteRun;
    }

    public Map<Integer, String> getExecutionResultTypes() {
        return Collections.EMPTY_MAP;
    }

}
