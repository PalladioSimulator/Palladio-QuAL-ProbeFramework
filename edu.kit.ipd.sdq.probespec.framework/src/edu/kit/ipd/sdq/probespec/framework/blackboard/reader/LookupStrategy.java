package edu.kit.ipd.sdq.probespec.framework.blackboard.reader;

import java.util.List;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;

public interface LookupStrategy {

    Iterable<List<MeasurementContext>> lookup(MeasurementContext... contexts);

}
