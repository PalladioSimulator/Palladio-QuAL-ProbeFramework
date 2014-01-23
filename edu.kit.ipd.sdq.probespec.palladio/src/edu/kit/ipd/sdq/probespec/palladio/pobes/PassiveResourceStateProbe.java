package edu.kit.ipd.sdq.probespec.palladio.pobes;

import edu.kit.ipd.sdq.probespec.framework.probes.AbstractProbe;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.MetricType;

public class PassiveResourceStateProbe extends AbstractProbe<Integer> {

    public PassiveResourceStateProbe(String name) {
        super(name);
        initMetadata();
    }

    public PassiveResourceStateProbe(String name, boolean persistent) {
        super(name, persistent);
        initMetadata();
    }

    public PassiveResourceStateProbe(String id, String name) {
        super(id, name);
        initMetadata();
    }

    public PassiveResourceStateProbe(String id, String name, boolean persistent) {
        super(id, name, persistent);
        initMetadata();
    }

    @Override
    public Class<Integer> getGenericClass() {
        return Integer.class;
    }

    private void initMetadata() {
        getMetadata().put("MetricType", MetricType.STATE);
    }

}
