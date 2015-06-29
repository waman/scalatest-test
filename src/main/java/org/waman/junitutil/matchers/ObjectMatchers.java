package org.waman.junitutil.matchers;

import org.hamcrest.Matcher;
import java.util.EventObject;
import org.hamcrest.object.HasToString;
import org.hamcrest.object.IsCompatibleType;
import org.hamcrest.object.IsEventFrom;

public class ObjectMatchers {
    
    public static <T>
    Matcher<T> hasToString(String expectedToString){
        return HasToString.hasToString(expectedToString);
    }

    public static <T>
    Matcher<T> hasToString(Matcher<? super String> toStringMatcher){
        return HasToString.hasToString(toStringMatcher);
    }

    public static <T>
    Matcher<Class<?>> typeCompatibleWith(Class<T> baseType){
        return IsCompatibleType.typeCompatibleWith(baseType);
    }

    public static
    Matcher<EventObject> eventFrom(Object source){
        return IsEventFrom.eventFrom(source);
    }

    public static
    Matcher<EventObject> eventFrom(Class<? extends EventObject> eventClass, Object source){
        return IsEventFrom.eventFrom(eventClass, source);
    }
}
