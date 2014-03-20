package de.uka.ipd.sdq.pipesandfilters.framework.recorder;

import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;

public class ConsoleWriteStrategy implements IRawWriteStrategy {

    @Override
    public void flush() {
    }

    @Override
    public void initialize(final MetaDataInit metaData) {
    }

    @Override
    public void writeData(final Measurement data) {
        System.out.println(data);
    }

}
