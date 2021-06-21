package com.ejp.golf.league;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Provides a fluent API for constructing objects.
 * @param <O> The object's type.
 *
 * Example usage:
 *  Pojo pojo = new Pojo().build(p -> p
 *          .set(p::setString, "value")
 *          .set(p::setInt, 1));
 *
 * @author Eric Pederson
 */
public interface Buildable<O> {

    /**
     * Return this instance.
     *
     * @return this.
     */
    O instance();

    /**
     * Accepts a value and returns itself.
     *
     * @param <V> The value's type.
     * @param setter The method that sets the value.
     * @param value The value to be set.
     * @return itself.
     */
    default <V> O set(Consumer<V> setter, V value)
    {
        setter.accept(value);
        return instance();
    }

    /**
     * Performs an internal operation and returns itself.
     *
     * @return itself.
     */
    default O act(Runnable action)
    {
        action.run();
        return instance();
    }



    /**
     * Builds an object.
     *
     * @param instructions on how to build the object.
     * @return the built object.
     * @throws RuntimeException when there is an error in the {@code instructions}
     */
    default O build(Function<O, O> instructions) throws RuntimeException
    {
        return Optional.of(instance())
                .map(instructions)
                .orElseThrow(() -> new RuntimeException("Unable to build object: " + instance()));
    }

}
