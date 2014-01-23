package edu.kit.ipd.sdq.probespec.palladio.pobes;

import edu.kit.ipd.sdq.probespec.framework.probes.AbstractProbe;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.MetricType;

public class ResponseTimeProbe extends AbstractProbe<Double> {

    public ResponseTimeProbe(String name) {
        super(name);
        initMetadata();
    }

    public ResponseTimeProbe(String name, boolean persistent) {
        super(name, persistent);
        initMetadata();
    }

    public ResponseTimeProbe(String id, String name) {
        super(id, name);
        initMetadata();
    }

    public ResponseTimeProbe(String id, String name, boolean persistemt) {
        super(id, name, persistemt);
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
