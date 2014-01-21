package edu.kit.ipd.sdq.probespec.framework.blackboard.reader;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContext;
import edu.kit.ipd.sdq.probespec.framework.blackboard.context.MeasurementContextHelper;

public class SameOrParentContextLookupStrategy implements LookupStrategy {

    private Class<? extends MeasurementContext> clazz;

    public SameOrParentContextLookupStrategy(Class<? extends MeasurementContext> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Iterable<List<MeasurementContext>> lookup(MeasurementContext... contexts) {
        return new ContextIterable(contexts);
    }

    private class ContextIterable implements Iterable<List<MeasurementContext>> {

        private MeasurementContext[] contexts;

        public ContextIterable(MeasurementContext... contexts) {
            this.contexts = contexts;
        }

        @Override
        public Iterator<List<MeasurementContext>> iterator() {
            if (contexts.length == 0) {
                Iterator<List<MeasurementContext>> it = Collections.emptyIterator();
                return it;
            } else {
                return new ContextIterator(contexts);
            }
        }

    }

    private class ContextIterator implements Iterator<List<MeasurementContext>> {

        private MeasurementContext[] currentContexts;

        private MeasurementContext[] nextContexts;

        public ContextIterator(MeasurementContext... contexts) {
            // Note: currentContexts has been intentionally left null
            this.nextContexts = contexts;
        }

        @Override
        public boolean hasNext() {
            return !Arrays.deepEquals(currentContexts, nextContexts);
        }

        @Override
        public List<MeasurementContext> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iteration has no more elements");
            }
            currentContexts = nextContexts;
            nextContexts = MeasurementContextHelper.parentContext(clazz, nextContexts);
            return Arrays.asList(currentContexts);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
