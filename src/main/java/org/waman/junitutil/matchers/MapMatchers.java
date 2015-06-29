package org.waman.junitutil.matchers;

import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsMapContaining;

public class MapMatchers {

    public static <K, V>
    Matcher<Map<? extends K, ? extends V>> hasEntry(K key, V value){
        return IsMapContaining.hasEntry(key, value);
    }

    public static <K, V>
    Matcher<Map<? extends K, ? extends V>>
    hasEntry(Matcher<? super K> keyMatcher, Matcher<? super V> valueMatcher){
        return IsMapContaining.hasEntry(keyMatcher, valueMatcher);
    }

    public static <K>
    Matcher<Map<? extends K,?>> hasKey(K key){
        return IsMapContaining.hasKey(key);
    }

    public static <K>
    Matcher<Map<? extends K,?>> hasKey(Matcher<? super K> keyMatcher){
        return IsMapContaining.hasKey(keyMatcher);
    }

    public static <V>
    Matcher<Map<?,? extends V>> hasValue(V value){
        return IsMapContaining.hasValue(value);
    }

    public static <V>
    Matcher<Map<?,? extends V>> hasValue(Matcher<? super V> valueMatcher){
        return IsMapContaining.hasValue(valueMatcher);
    }
}
