package org.waman.junitutil.matcher;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.waman.junitutil.matcher.MatchRegex.matchRegex;

public class MatchRegexTest {

    @Test
    public void matchRegexで正規表現にマッチするか検証する(){
        assertThat("ab c", matchRegex("\\w*"));
    }
}
