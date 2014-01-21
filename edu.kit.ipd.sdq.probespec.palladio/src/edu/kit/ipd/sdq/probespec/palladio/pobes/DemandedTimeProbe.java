package edu.kit.ipd.sdq.probespec.palladio.pobes;

import edu.kit.ipd.sdq.probespec.framework.probes.AbstractProbe;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.MetricType;

public class DemandedTimeProbe extends AbstractProbe<Double> {

    public DemandedTimeProbe(String name) {
        super(name);
        initMetadata();
    }

    public DemandedTimeProbe(String name, boolean persistent) {
        super(name, persistent);
        initMetadata();
    }

    public DemandedTimeProbe(String id, String name) {
        super(id, name);
        initMetadata();
    }

    public DemandedTimeProbe(String id, String name, boolean persistent) {
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
