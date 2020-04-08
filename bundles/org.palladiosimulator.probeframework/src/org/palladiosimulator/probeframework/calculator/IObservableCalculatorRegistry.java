package org.palladiosimulator.probeframework.calculator;

import java.util.Collection;

import org.palladiosimulator.commons.designpatterns.IAbstractObservable;

/**
 * This interface allows to access the Calculators which are currently
 * registered within a <code>ProbeFrameworkContext</code>.
 * 
 * It was introduces to prevent the casting of CalculatorFactory to the
 * registering delegate implementation which has taken root.
 * 
 * @author Sebastian Krach
 *
 */
public interface IObservableCalculatorRegistry
        extends ICalculatorRegistryAccess, IAbstractObservable<CalculatorRegistryListener> {

    /**
     * Returns all currently registered observers.
     * 
     * @return
     */
    public Collection<CalculatorRegistryListener> getObservers();

}
