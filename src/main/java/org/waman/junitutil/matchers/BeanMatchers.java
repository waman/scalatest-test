package org.waman.junitutil.matchers;

import org.hamcrest.Matcher;

import org.hamcrest.beans.HasProperty;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.beans.SamePropertyValuesAs;

public class BeanMatchers {

    public static <T>
    Matcher<T> hasProperty(String propertyName){
        return HasProperty.hasProperty(propertyName);
    }

    public static <T>
    Matcher<T> hasProperty(String propertyName, Matcher<?> valueMatcher){
        return HasPropertyWithValue.hasProperty(propertyName, valueMatcher);
    }

    public static <T>
    Matcher<T> samePropertyValuesAs(T expectedBean){
        return SamePropertyValuesAs.samePropertyValuesAs(expectedBean);
    }
}
