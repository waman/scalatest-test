package org.waman.junitutil.matcher;

import org.hamcrest.*;

public class HasLength extends TypeSafeMatcher<String> {

    private final int length;

    private HasLength(int n){
        this.length = n;
    }

    @Override
    protected boolean matchesSafely(String s) {
        return s.length() == this.length;
    }

    @Override
    public void describeTo(Description desc) {
        desc.appendText("長さ");
        desc.appendValue(this.length);
        desc.appendText("の文字列");
    }

    @Override
    protected void describeMismatchSafely(String s, Description desc) {
        desc.appendValue(s);
        desc.appendText("の長さは");
        desc.appendValue(s.length());
        desc.appendText("です。");
    }

    @Factory
    public static Matcher<String> hasLength(int n){
        if(n < 0)
            throw new IllegalArgumentException("引数は0以上でなければなりません：実際の値は"+n);

        return new HasLength(n);
    }
}
