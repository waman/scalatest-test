package junit.tutorial.matcher;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import static org.waman.junitutil.matchers.MapMatchers.*;

public class MapMatcherTest {

    final Map<String, Integer> map = new HashMap<>();

    @Before
    public void mapを初期化(){
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
    }

    // org.hamcrest.collection.IsMapContaining
    @Test
    public void hasEntryで指定したエントリを持つことを検証する(){
        assertThat(map, hasEntry("one", 1));
        assertThat(map, not(hasEntry("two", 3)));
    }

    // org.hamcrest.collection.IsMapContaining
    @Test
    public void hasKeyで指定したキーを持つことを検証する(){
        assertThat(map, hasKey("two"));
        assertThat(map, not(hasKey("four")));

        assertThat(map, hasKey(startsWith("t")));
    }

    // org.hamcrest.collection.IsMapContaining
    @Test
    public void hasValueで指定した値を持つことをを検証する(){
        assertThat(map, hasValue(3));
        assertThat(map, not(hasValue(4)));
    }
}
