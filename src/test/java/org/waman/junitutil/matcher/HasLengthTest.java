package org.waman.junitutil.matcher;

import org.hamcrest.*;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.waman.junitutil.matcher.HasLength.hasLength;

public class HasLengthTest {

    @Test
    public void hasLengthで文字列の長さを検証する(){
        assertThat("JUnit", hasLength(5));
        assertThat("ScalaTest", hasLength(5));
    }

    @Test
    public void hasLengthで空文字列の長さを検証する(){
        assertThat("", hasLength(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void hasLength負の整数を渡されるとIllegalArgumentExceptionを投げる(){
        assertThat("JUnit", hasLength(-1));
    }

    // ***** CustomMatcher *****
    @Test
    public void hasLengthをCustomMatcherで実装(){
        Matcher<String> hasLength5 = new CustomMatcher<String>("長さ5の文字列") {
            @Override
            public boolean matches(Object o) {
                return o instanceof String
                        && String.class.cast(o).length() == 5;
            }
        };

        assertThat("JUnit", hasLength5);
        assertThat("ScalaTest", hasLength5);
    }

    // ***** CustomTypeSafeMatcher *****
    @Test
    public void hasLengthをCustomTypeSafeMatcherで実装(){
        Matcher<String> hasLength5 = new CustomTypeSafeMatcher<String>("長さ5の文字列") {
            @Override
            public boolean matchesSafely(String s) {
                return s.length() == 5;
            }
        };

        assertThat("JUnit", hasLength5);
        assertThat("ScalaTest", hasLength5);
    }

    //***** DiagnosingTypeSafeMatcher *****
    public class TypeSafeDiagnosingHasLength extends TypeSafeDiagnosingMatcher<String> {

        private final int length;

        public TypeSafeDiagnosingHasLength(int n){
            this.length = n;
        }

        @Override
        protected boolean matchesSafely(String s, Description desc) {
            if(s.length() == this.length) return true;

            desc.appendValue(s);
            desc.appendText("の長さは");
            desc.appendValue(s.length());
            desc.appendText("です。");
            return false;
        }

        @Override
        public void describeTo(Description desc) {
            desc.appendText("長さ");
            desc.appendValue(this.length);
            desc.appendText("の文字列");
        }
    }

    @Test
    public void hasLengthをTypeSafeDiagnosingMatcherで実装(){
        Matcher<String> hasLength5 = new TypeSafeDiagnosingHasLength(5);
        assertThat("JUnit", hasLength5);
        assertThat("ScalaTest", hasLength5);
    }

    //***** FeatureMatcher *****
    public class FeatureHasLength extends FeatureMatcher<String, Integer> {

        private FeatureHasLength(int n){
            super(CoreMatchers.equalTo(n),
                    "a string with length",
                    "actual length");
        }

        @Override
        protected Integer featureValueOf(String s) {
            return s.length();
        }
    }

    @Test
    public void hasLengthをFeatureMatcherで実装(){
        Matcher<String> hasLength5 = new FeatureHasLength(5);
        assertThat("JUnit", hasLength5);
        assertThat("ScalaTest", hasLength5);
    }
}
