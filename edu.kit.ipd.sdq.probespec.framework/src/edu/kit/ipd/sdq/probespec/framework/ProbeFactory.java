package edu.kit.ipd.sdq.probespec.framework;

import edu.kit.ipd.sdq.probespec.DerivedIntegerProbe;
import edu.kit.ipd.sdq.probespec.DoubleProbe;
import edu.kit.ipd.sdq.probespec.IntegerProbe;
import edu.kit.ipd.sdq.probespec.LongProbe;
import edu.kit.ipd.sdq.probespec.ObjectProbe;
import edu.kit.ipd.sdq.probespec.probespecFactory;

public class ProbeFactory {

    public ObjectProbe createObjectProbe(String id) {
        return createObjectProbe(id, id);
    }

    public ObjectProbe createObjectProbe(String id, String name) {
        ObjectProbe p = probespecFactory.eINSTANCE.createObjectProbe();
        p.setId(id);
        p.setName(name);
        return p;
    }

    public IntegerProbe createIntegerProbe(String id) {
        return createIntegerProbe(id, id);
    }

    public IntegerProbe createIntegerProbe(String id, String name) {
        IntegerProbe p = probespecFactory.eINSTANCE.createIntegerProbe();
        p.setId(id);
        p.setName(name);
        return p;
    }

    public LongProbe createLongProbe(String id) {
        return createLongProbe(id, id);
    }

    public LongProbe createLongProbe(String id, String name) {
        LongProbe p = probespecFactory.eINSTANCE.createLongProbe();
        p.setId(id);
        p.setName(name);
        return p;
    }

    public DoubleProbe createDoubleProbe(String id) {
        return createDoubleProbe(id, id);
    }

    public DoubleProbe createDoubleProbe(String id, String name) {
        DoubleProbe p = probespecFactory.eINSTANCE.createDoubleProbe();
        p.setId(id);
        p.setName(name);

        return p;
    }

    public <I> DerivedIntegerProbe createDerivedIntegerProbe(String id) {
        return createDerivedIntegerProbe(id, id);
    }

    public <I> DerivedIntegerProbe createDerivedIntegerProbe(String id, String name) {
        DerivedIntegerProbe p = probespecFactory.eINSTANCE.createDerivedIntegerProbe();
        p.setId(id);
        p.setName(name);

        return p;
    }

}
