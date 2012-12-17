package edu.kit.ipd.sdq.probespec.framework.blackboard;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SameOrParentContextLookupStrategy implements ILookupStrategy {

    private Class<? extends IMeasurementContext> clazz;

    public SameOrParentContextLookupStrategy(Class<? extends IMeasurementContext> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Iterable<List<IMeasurementContext>> lookup(IMeasurementContext... contexts) {
        return new ContextIterable(contexts);
    }

    private class ContextIterable implements Iterable<List<IMeasurementContext>> {

        private IMeasurementContext[] contexts;

        public ContextIterable(IMeasurementContext... contexts) {
            this.contexts = contexts;
        }

        @Override
        public Iterator<List<IMeasurementContext>> iterator() {
            if (contexts.length == 0) {
                Iterator<List<IMeasurementContext>> it = Collections.emptyIterator();
                return it;
            } else {
                return new ContextIterator(contexts);
            }
        }
        
    }

    private class ContextIterator implements Iterator<List<IMeasurementContext>> {

        private IMeasurementContext[] currentContexts;
        
        private IMeasurementContext[] nextContexts;

        public ContextIterator(IMeasurementContext... contexts) {
            // Note: currentContexts has been intentionally left null
            this.nextContexts = contexts;
        }

        @Override
        public boolean hasNext() {
            return currentContexts != nextContexts;
        }

        @Override
        public List<IMeasurementContext> next() {
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
