package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies;

import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;

public interface IWriteDataStrategy {

    public void initialise(MetaDataInit metaData);

    public void writeData(Measurement data);

}
