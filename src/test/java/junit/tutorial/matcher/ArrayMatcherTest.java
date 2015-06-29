package junit.tutorial.matcher;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.waman.junitutil.matchers.ArrayMatchers.*;
import static org.waman.junitutil.matchers.NumberMatchers.lessThan;

public class ArrayMatcherTest {

    final String[] anArray = new String[]{"one", "two", "three"};
    final Integer[] intArray = new Integer[]{1, 2, 3};
    final String[] emptyArray = new String[]{};

    // org.hamcrest.collection.IsArray
    @Test
    public void arrayWithSizeとemptyArrayで配列のサイズが指定した値であることを検証する() {
        assertThat(anArray, is(arrayWithSize(3)));
        assertThat(intArray, is(arrayWithSize(lessThan(10))));
        assertThat(emptyArray, is(emptyArray()));
    }

    // org.hamcrest.collection.IsArray
    @Test
    public void arrayで各要素がMatcherにマッチすることを検証する() {
        assertThat(anArray, is(array(is("one"), is("two"), is("three"))));
        assertThat(intArray, is(array(equalTo(1), equalTo(2), equalTo(3))));
    }

    // org.hamcrest.collection.IsArrayContaining
    @Test
    public void hasItemInArrayで配列が指定した要素を持つことを検証する() {
        assertThat(anArray, hasItemInArray("one"));
        assertThat(anArray, hasItemInArray(startsWith("th")));
    }

    // org.hamcrest.collection.IsArrayContainingInOrder
    @Test
    public void arrayContainingで指定した要素を順に含むことを検証する() {
        assertThat(anArray, arrayContaining("one", "two", "three"));
        assertThat(anArray, not(arrayContaining("one", "two")));
    }

    // org.hamcrest.collection.IsArrayContainingInAnyOrder
    @Test
    public void arrayContainingInAnyOrderで順序を問わず指定した要素を含むことを検証する() {
        assertThat(anArray, arrayContainingInAnyOrder("two", "one", "three"));
        assertThat(anArray, not(arrayContainingInAnyOrder("one", "two")));
    }
}