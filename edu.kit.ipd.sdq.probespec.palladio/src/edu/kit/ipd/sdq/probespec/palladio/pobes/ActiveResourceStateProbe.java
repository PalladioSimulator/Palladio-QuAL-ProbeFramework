package edu.kit.ipd.sdq.probespec.palladio.pobes;

import edu.kit.ipd.sdq.probespec.framework.probes.AbstractProbe;
import edu.kit.ipd.sdq.probespec.palladio.sensorframework.MetricType;

public class ActiveResourceStateProbe extends AbstractProbe<Integer> {

    public ActiveResourceStateProbe(String name) {
        super(name);
        initMetadata();
    }

    public ActiveResourceStateProbe(String name, boolean persistent) {
        super(name, persistent);
        initMetadata();
    }

    public ActiveResourceStateProbe(String id, String name) {
        super(id, name);
        initMetadata();
    }

    public ActiveResourceStateProbe(String id, String name, boolean persistent) {
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
