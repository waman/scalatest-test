package org.waman.junitutil.matcher;

import org.hamcrest.CustomMatcher;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class CustomMatcherTest {

    @Test
    public void CustomTypeSafeMatcherで無名クラスのMatcherを作成する(){
        Matcher<Integer> even = new CustomTypeSafeMatcher<Integer>("偶数") {
            @Override
            protected boolean matchesSafely(Integer i) {
                return i % 2 == 0;
            }
        };

        assertThat(101, is(even));
    }

    @Test
    public void CustomMatcherで無名クラスのMatcherを作成する(){
        Matcher<Set<?>> nullOrEmptySet = new CustomMatcher<Set<?>>("nullもしくは空集合") {
            @Override
            public boolean matches(Object obj) {
                if (obj == null)
                    return true;

                Set<?> set = (Set<?>)obj;
                return set.isEmpty();
            }
        };

        Set<Number> nullSet = getMaybeNull();
        assertThat(nullSet, is(nullOrEmptySet));

        Set<Number> eSet = Collections.emptySet();
        assertThat(eSet, is(nullOrEmptySet));

        Set<Number> set = Collections.singleton(1.0);
        assertThat(set, is(not(nullOrEmptySet)));
    }

    @Test
    public void CustomTypeSafeMatcherでは不適合な例(){
        Matcher<Set<?>> nullOrEmptySet = new CustomTypeSafeMatcher<Set<?>>("nullもしくは空集合") {
            @Override
            protected boolean matchesSafely(Set<?> set) {
                return set == null || set.isEmpty();
            }
        };

        Set<Number> nullSet = getMaybeNull();
        assertThat(nullSet, is(not(nullOrEmptySet)));  // 不適合

        Set<Number> eSet = Collections.emptySet();
        assertThat(eSet, is(nullOrEmptySet));

        Set<Number> set = Collections.singleton(1.0);
        assertThat(set, is(not(nullOrEmptySet)));
    }

    <E> E getMaybeNull(){
        return null;
    }
}
