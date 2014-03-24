package de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.uka.ipd.sdq.pipesandfilters.framework.recorder.IRawWriteStrategy;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.Recorder;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.launch.IRecorderConfiguration;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies.AbstractWriteDataStrategy;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies.DemandedTimeWriteDataStrategy;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies.ExecutionResultWriteDataStrategy;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies.OverallUtilisationWriteDataStrategy;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies.ResponseTimeWriteDataStrategy;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies.UtilisationWriteDataStrategy;
import de.uka.ipd.sdq.pipesandfilters.framework.recorder.sensorframework.strategies.WaitingTimeWriteDataStrategy;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.sensorframework.SensorFrameworkDataset;
import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * Recorder for the SensorFramework.
 *
 * @author pmerkle, Sebastian Lehrig
 */
public class SensorFrameworkRecorder extends Recorder implements IRawWriteStrategy {

    private static Logger logger = Logger.getLogger(SensorFrameworkRecorder.class.getName());

    private SensorFrameworkRecorderConfiguration recorderConfiguration;

    private IDAOFactory daoFactory;

    /**
     * All instances of this class uses the same IDAOFactory. Hence it is
     * sufficient when a single writer flushes the data. Several subsequent
     * flushes would be redundant. This variable is used to coordinate the flush
     * between several instances of this writer. It is false when one instance
     * has written data that has not yet been flushed; true else.
     */
    private static boolean flushed;

    private Experiment experiment;

    private ExperimentRun run;

    private AbstractWriteDataStrategy writeDataStrategy;

    @Override
    public void initialize(final IRecorderConfiguration myRecorderConfiguration) {
        if (myRecorderConfiguration instanceof SensorFrameworkRecorderConfiguration) {
            this.recorderConfiguration = (SensorFrameworkRecorderConfiguration) myRecorderConfiguration;
        } else {
            throw new IllegalArgumentException("Expected meta data of type "
                    + SensorFrameworkRecorderConfiguration.class.getSimpleName()
                    + " but was " + myRecorderConfiguration.getClass().getSimpleName());
        }

        if (!this.recorderConfiguration.isRemoteRun()) {
            initialiseNewSensorframework();
        } else {
            initialiseTempSensorframework();
        }

        // Create sensor
        // TODO Remove hard coded metric names "Response Time", ... Use Enum
        // instead!?
        if (recorderConfiguration.getRecorderAcceptedMetric().getName().equals("Response Time")) {
            writeDataStrategy = new ResponseTimeWriteDataStrategy(daoFactory,
                    experiment, run);
        } else if (recorderConfiguration.getRecorderAcceptedMetric().getName().equals("Waiting Time")) {
            writeDataStrategy = new WaitingTimeWriteDataStrategy(daoFactory,
                    experiment, run);
        } else if (recorderConfiguration.getRecorderAcceptedMetric().getName().equals("Hold Time")) {
            writeDataStrategy = new WaitingTimeWriteDataStrategy(daoFactory,
                    experiment, run);
        } else if (recorderConfiguration.getRecorderAcceptedMetric().getName().equals("Demand")) {
            writeDataStrategy = new DemandedTimeWriteDataStrategy(daoFactory,
                    experiment, run);
        } else if (recorderConfiguration.getRecorderAcceptedMetric().getName().equals("State")) {
            writeDataStrategy = new UtilisationWriteDataStrategy(daoFactory,
                    experiment, run);
        } else if (recorderConfiguration.getRecorderAcceptedMetric().getName().equals("Overall Utilisation")) {
            writeDataStrategy = new OverallUtilisationWriteDataStrategy(
                    daoFactory, experiment, run);
        } else if (recorderConfiguration.getRecorderAcceptedMetric().getName().equals("Execution Time")) {
            writeDataStrategy = new ExecutionResultWriteDataStrategy(
                    daoFactory, experiment, run);
        }
        writeDataStrategy.initialise(recorderConfiguration);

        flushed = false;
    }

    @Override
    public void writeData(final Measurement data) {
        if (!flushed) {
            writeDataStrategy.writeData(data);
        } else {
            if(logger.isEnabledFor(Level.WARN)) {
                logger.warn("Tried to write data, but the pipe has been flushed already");
            }
        }
    }

    @Override
    public synchronized void flush() {
        if (!flushed) {
            flushed = true;
            if(logger.isDebugEnabled()) {
                logger.debug("Flushing SensorFramework data store");
            }
            daoFactory.store();
            //do not execute daoFactory.finalizeAndClose() ! This will flush all lists for file-based lists, e.g. experiments, from memory. This should not be done on any DAO requested via the singleton as the lists are not reloaded on next access.
        }
        run = null;
        experiment = null;
    }

    private void initialiseNewSensorframework() {
        // Obtain DAOFactory
        daoFactory = SensorFrameworkDataset.singleton().getDataSourceByID(
                recorderConfiguration.getDatasourceID());
        if (daoFactory == null) {
            throw new DatasourceConfigurationInvalidException();
        }

        // Find an existing or create a new Experiment
        if (daoFactory.createExperimentDAO().findByExperimentName(
                recorderConfiguration.getExperimentName()).size() == 1) {
            experiment = daoFactory.createExperimentDAO().findByExperimentName(
                    recorderConfiguration.getExperimentName()).iterator().next();
        } else {
            experiment = daoFactory.createExperimentDAO().addExperiment(
                    recorderConfiguration.getExperimentName());
        }

        // Find an existing or create a new ExperimentRun
        for (final ExperimentRun r : experiment.getExperimentRuns()) {
            if (r.getExperimentDateTime().equals(
                    recorderConfiguration.getExperimentRunName())) {
                run = r;
            }
        }
        if (run == null) {
            run = experiment.addExperimentRun(recorderConfiguration.getExperimentRunName());
        }

        // run = new SimuComExperimentRunDecorator(this, experiment
        // .addExperimentRun("Run " + new Date()));
    }

    // TODO Initialization for R-OSGI
    private void initialiseTempSensorframework() {
        // // TODO!!!!
        // this.daoFactory = new FileDAOFactory("C:/temp/test");
        // experiment = daoFactory.createExperimentDAO().addExperiment(
        // this.getConfig().getNameExperimentRun());
        // run = new SimuComExperimentRunDecorator(this, experiment
        // .addExperimentRun("Run " + new Date()));
    }

}
