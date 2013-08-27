package edu.kit.ipd.sdq.probespec.palladio.sensorframework;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.sensorframework.SensorFrameworkDataset;
import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;
import edu.kit.ipd.sdq.probespec.framework.Measurement;
import edu.kit.ipd.sdq.probespec.framework.MeasurementListener;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.strategies.ISensorWrapper;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.strategies.StateSensorWrapper;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.strategies.TimeSpanSensorWrapper;

/**
 * Write Strategy for the SensorFramework.
 * 
 * @author pmerkle
 * 
 */
public class SensorFrameworkAdapter {

    private static Logger logger = Logger.getLogger(SensorFrameworkAdapter.class);

    private IDAOFactory daoFactory;

    private Experiment experiment;

    private ExperimentRun run;

    private String experimentName;

    private String experimentRunName;

    private boolean flushed;

    private Map<Probe<?>, MeasurementListener<?, Double>> listenerMap;

    public SensorFrameworkAdapter(long dataSourceId, String experimentName, String experimentRunName) {
        this.experimentName = experimentName + " (PS2)";
        this.experimentRunName = experimentRunName;
        this.listenerMap = new HashMap<Probe<?>, MeasurementListener<?, Double>>();

        initialiseNewSensorframework(dataSourceId);
    }

    public <V> void addProbe(final Probe<V> probe) {
        ISensorWrapper s = null;
        MetricType metricType = (MetricType)probe.getMetadata().get("MetricType");
        if (metricType == null) {
            logger.warn("Encountered probe without 'metric type' metadata"); // TODO improve message
        } else if (metricType.equals(MetricType.TIMESPAN)) {
            s = new TimeSpanSensorWrapper(daoFactory, experiment, run);
        } else if (metricType.equals(MetricType.STATE)) {
            s = new StateSensorWrapper(daoFactory, experiment, run);
        }
        s.initialise(probe);

        final ISensorWrapper sensor = s;
        MeasurementListener<V, Double> l = new MeasurementListener<V, Double>() {

            @Override
            public void measurementArrived(Measurement<V, Double> measurement, Probe<V> probe,
                    MeasurementContext... contexts) {
                sensor.addMeasurement((Measurement<?, Double>) measurement, probe, contexts);
            }

            @Override
            public Class<V> getGenericType() {
                return probe.getGenericClass();
            }

        };
        probe.addMeasurementListener(l);

        // put listener into map to be able to remove the listener later on
        listenerMap.put(probe, l);
    }

    public void removeProbe(final Probe<?> probe) {
        MeasurementListener<?, Double> l = listenerMap.get(probe);
        if (l != null) {
            probe.removeMeasurementListener(l);
        } else {
            logger.warn("Tried to remove measurement listener from probe " + probe
                    + " but no corresponding listener could be found");
        }
    }

    public synchronized void flush() {
        if (!flushed) {
            flushed = true;
            if (logger.isDebugEnabled())
                logger.debug("Flushing SensorFramework data store");
            daoFactory.store();
            // do not execute daoFactory.finalizeAndClose() ! This will flush all lists for
            // file-based lists, e.g. experiments, from memory. This should not be done on any DAO
            // requested via the singleton as the lists are not reloaded on next access.
        }
        run = null;
        experiment = null;
    }

    private void initialiseNewSensorframework(long dataSourceId) {
        // Obtain DAOFactory
        daoFactory = SensorFrameworkDataset.singleton().getDataSourceByID(dataSourceId);
        if (daoFactory == null)
            throw new DatasourceConfigurationInvalidException();

        // Find an existing or create a new Experiment
        if (daoFactory.createExperimentDAO().findByExperimentName(experimentName).size() == 1) {
            experiment = daoFactory.createExperimentDAO().findByExperimentName(experimentName).iterator().next();
        } else {
            experiment = daoFactory.createExperimentDAO().addExperiment(experimentName);
        }

        // Find an existing or create a new ExperimentRun
        for (ExperimentRun r : experiment.getExperimentRuns()) {
            if (r.getExperimentDateTime().equals(experimentRunName)) {
                run = r;
            }
        }
        if (run == null)
            run = experiment.addExperimentRun(experimentRunName);
    }

}
