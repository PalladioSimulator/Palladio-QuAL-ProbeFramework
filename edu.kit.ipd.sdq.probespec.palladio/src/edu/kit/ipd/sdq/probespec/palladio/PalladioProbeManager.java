package edu.kit.ipd.sdq.probespec.palladio;

import edu.kit.ipd.sdq.probespec.framework.AbstractProbeManager;
import edu.kit.ipd.sdq.probespec.framework.Probe;
import edu.kit.ipd.sdq.probespec.framework.blackboard.BlackboardType;
import edu.kit.ipd.sdq.probespec.framework.probes.ProbeStateListener;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.SensorFrameworkAdapter;

public class PalladioProbeManager extends AbstractProbeManager<Double> {

    private SensorFrameworkAdapter sfa;

    public PalladioProbeManager(PalladioTimestampBuilder timestampBuilder) {
        super(timestampBuilder);
    }

    public PalladioProbeManager(PalladioTimestampBuilder timestampBuilder, SensorFrameworkAdapter sfa) {
        super(timestampBuilder);
        this.sfa = sfa;
    }

    public PalladioProbeManager(PalladioTimestampBuilder timestampBuilder, BlackboardType blackboardType) {
        super(timestampBuilder, blackboardType);
    }

    @Override
    public <V> void mountProbe(final Probe<V> probe, Object entity, Object mountPoint) {
        super.mountProbe(probe, entity, mountPoint);
        if (!probe.isTransient()) {
            sfa.addProbe(probe);
        }
        probe.addProbeStateListener(new ProbeStateListener() {

            @Override
            public void isTransient(boolean _transient) {
                if (_transient) {
                    sfa.removeProbe(probe);
                } else {
                    sfa.addProbe(probe);
                }
            }

            @Override
            public void isActive(boolean active) {
                // nothing to do; no measurements appear for deactivated probes
            }
        });
    }

    @Override
    public <V> void mountProbe(final Probe<V> probe, Object entity) {
        super.mountProbe(probe, entity);
        if (!probe.isTransient()) {
            sfa.addProbe(probe);
        }
        probe.addProbeStateListener(new ProbeStateListener() {

            @Override
            public void isTransient(boolean _transient) {
                if (_transient) {
                    sfa.removeProbe(probe);
                } else {
                    sfa.addProbe(probe);
                }
            }

            @Override
            public void isActive(boolean active) {
                // nothing to do; no measurements appear for deactivated probes
            }
        });
    }

}
