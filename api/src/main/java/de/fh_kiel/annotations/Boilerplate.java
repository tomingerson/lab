package de.fh_kiel.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * This annotation is used as marker to generate boilerplate code on annotated stubs. Boilerplate
 * includes getters and setters for member variables as well as implementation of hashcode, equals
 * and toString.
 *
 * @author Created by tom on 16.10.2016.
 */
@Documented
@Target(ElementType.TYPE)
public @interface Boilerplate {
}
