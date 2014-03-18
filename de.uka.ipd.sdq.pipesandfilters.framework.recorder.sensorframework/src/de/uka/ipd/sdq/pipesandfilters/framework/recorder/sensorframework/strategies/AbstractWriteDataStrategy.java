package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import de.uka.ipd.sdq.pipesandfilters.framework.MetaDataInit;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.SensorHelper;
import de.uka.ipd.sdq.probespec.framework.measurements.BasicMeasurement;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet;
import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.Sensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

public abstract class AbstractWriteDataStrategy implements IWriteDataStrategy {

    protected IDAOFactory daoFactory;

    protected Experiment experiment;

    protected ExperimentRun run;

    protected Sensor sensor;

    public AbstractWriteDataStrategy(final IDAOFactory daoFactory,
            final Experiment experiment, final ExperimentRun run) {
        this.daoFactory = daoFactory;
        this.experiment = experiment;
        this.run = run;
    }

    @Override
    public void initialise(final MetaDataInit metaData) {
        final String sensorId = metaData.getMetricDescriptions().getTextualDescription();
        sensor = SensorHelper.createOrReuseTimeSensor(daoFactory, experiment,
                sensorId);
    }

    @SuppressWarnings("unchecked")
    protected <V,Q extends Quantity> Measure<V,Q> getMeasureHelper(final Measurement data, final int i) {
        final MeasurementSet measurementSet = (MeasurementSet) data;
        final Measurement subMeasurement = measurementSet.getSubsumedMeasurements().get(0);
        final BasicMeasurement<V, Q> basicMeasurement = (BasicMeasurement<V, Q>) subMeasurement;
        return basicMeasurement.getMeasure();
    }

}
