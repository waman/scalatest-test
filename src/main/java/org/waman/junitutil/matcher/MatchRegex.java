package org.waman.junitutil.matcher;

import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class MatchRegex extends TypeSafeMatcher<String>{

    private final Pattern pattern;

    private MatchRegex(Pattern pattern){
        this.pattern = pattern;
    }

    @Override
    protected boolean matchesSafely(String s){
        return this.pattern.matcher(s).matches();
    }

    @Override
    public void describeTo(Description desc) {
        desc.appendText("a regex ");
        desc.appendValue(this.pattern);
    }

    @Override
    protected void describeMismatchSafely(String item, Description desc) {
        desc.appendText("actual text was");
        desc.appendValue(item);
    }

    @Factory
    public static Matcher<String> matchRegex(String regex){
        return matchRegex(Pattern.compile(regex));
    }

    @Factory
    public static Matcher<String> matchRegex(Pattern regex){
        return new MatchRegex(regex);
    }
}
