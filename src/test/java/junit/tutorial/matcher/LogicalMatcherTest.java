package junit.tutorial.matcher;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsMapContaining.*;

import java.util.*;

public class LogicalMatcherTest {

    final Map<String, Integer> map = new HashMap<>();

    @Before
    public void mapを初期化(){
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
    }

    // org.hamcrest.CoreMatchers
    @Test
    public void anythingはSUTに関係なくマッチすることを検証する(){
        assertThat("JUnit", is(anything()));
    }

    // org.hamcrest.CoreMatchers
    @Test
    public void notで否定を検証する(){
        assertThat("JUnit", is(not("ScalaTest")));
    }

    // org.hamcrest.CoreMatchers
    @Test
    public void allOfまたはbothで複数の条件を同時に満たすことを検証する(){
        assertThat("JUnit", is(allOf(
                startsWith("J"),
                containsString("Uni"),
                endsWith("Unit"))));

        assertThat("JUnit", is(
                both(startsWith("J"))
                        .and(containsString("Uni"))
                        .and(endsWith("Unit"))
        ));

        assertThat(map, is(
                both(hasKey("one")).and(not(hasValue(7)))
        ));
    }

    // org.hamcrest.CoreMatchers
    @Test
    public void anyOfもしくはeitherで複数の条件のどれかを満たすことを検証する(){
        assertThat("JUnit", anyOf(
                                is("JUnit"),
                                is("ScalaTest"),
                                is("Spock")));

        assertThat("JUnit", either(is("JUnit"))
                                .or(is("ScalaTest"))
                                .or(is("Spock")));
    }
}
