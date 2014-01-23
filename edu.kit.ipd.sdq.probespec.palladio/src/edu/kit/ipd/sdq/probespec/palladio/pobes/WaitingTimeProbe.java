package edu.kit.ipd.sdq.probespec.palladio.pobes;

import edu.kit.ipd.sdq.probespec.framework.probes.AbstractProbe;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.MetricType;

public class WaitingTimeProbe extends AbstractProbe<Double> {

    public WaitingTimeProbe(String name) {
        super(name);
        initMetadata();
    }

    public WaitingTimeProbe(String name, boolean persistent) {
        super(name, persistent);
        initMetadata();
    }

    public WaitingTimeProbe(String id, String name) {
        super(id, name);
        initMetadata();
    }

    public WaitingTimeProbe(String id, String name, boolean persistent) {
        super(id, name, persistent);
        initMetadata();
    }

    @Override
    public Class<Double> getGenericClass() {
        return Double.class;
    }

    private void initMetadata() {
        getMetadata().put("MetricType", MetricType.TIMESPAN);
    }

}
