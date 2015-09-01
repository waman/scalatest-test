package junit.tutorial.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class CollectionMatcherTest {

    //***** Core Matchers *****

    final List<String> list = asList("Hello", "JUnit", "world");
    final List<String> emptyList = Collections.emptyList();

    // org.hamcrest.CoreMatchers
    @Test
    public void hasItemで特定の要素を含むことを検証する(){
        assertThat(list, hasItem("JUnit"));
        assertThat(list, not(hasItem("ScalaTest")));
    }

    // org.hamcrest.CoreMatchers
    @Test
    public void hasItemsで指定された複数の要素すべてを含むことを検証する(){
        assertThat(list, hasItems("Hello", "world"));
        assertThat(list, not(hasItems("Hi", "world")));
    }

    // org.hamcrest.CoreMatchers
    @Test
    public void everyItemですべて指定された条件満たすことを検証する(){
        assertThat(list, everyItem(hasLength(4)));
    }

    static Matcher<String> hasLength(int n){
        return new TypeSafeMatcher<String>() {

            @Override
            protected boolean matchesSafely(String s){
                return s.length() == n;
            }

            @Override
            public void describeTo(Description description) {
                description.appendValue("Length of String is " + n);
            }
        };
    }

    //***** Collection Matchers *****

    // org.hamcrest.collection.IsIn
    @Test
    public void isInで引数のコレクションもしくは配列に含まれていることを検証する(){
        assertThat("JUnit", isIn(asList("Hello", "JUnit", "world")));
        assertThat("JUnit", isIn(new String[]{"Hello", "JUnit", "world"}));
    }

    // org.hamcrest.collection.IsIn
    @Test
    public void isOneOfで引数のMatcherのいずれかにマッチすることを検証する(){
        assertThat("JUnit", isOneOf("Hello", "JUnit", "world"));
    }

    // org.hamcrest.collection.IsEmptyCollection
    @Test
    public void emptyとemptyCollectionOfでコレクションが空であることを検証する(){
        assertThat(emptyList, is(empty()));
        assertThat(emptyList, is(emptyCollectionOf(String.class)));
    }

    // org.hamcrest.collection.IsCollectionWithSize
    @Test
    public void hasSizeで要素数を検証する(){
        assertThat(emptyList, hasSize(0));
        assertThat(list, hasSize(3));
        assertThat(list, hasSize(lessThan(10)));
    }

    //***** Iterable Matchers *****

    final Iterable<String> emptyIte = Collections.emptySet();
    final Iterable<String> ite = new HashSet<>(list);

    // org.hamcrest.collection.IsEmptyCollection
    @Test
    public void emptyIterableとemptyIterableOfで空であることを検証する(){
        assertThat(emptyIte, is(emptyIterable()));
        assertThat(emptyIte, is(emptyIterableOf(String.class)));
    }

    // org.hamcrest.collection.IsCollectionWithSize
    @Test
    public void iterableWithSizeで要素数を検証する(){
        assertThat(emptyIte, is(iterableWithSize(0)));
        assertThat(ite, is(iterableWithSize(3)));
        assertThat(ite, is(iterableWithSize(lessThan(10))));
    }

    // org.hamcrest.collection.IsCollectionContainingInOrder
    @Test
    public void containsで要素が指定された順序で含まれていることを検証する(){
        assertThat(list, contains("Hello", "JUnit", "world"));
        assertThat(list, not(contains("Hello", "JUnit")));
    }

    // org.hamcrest.collection.IsCollectionWithSize
    @Test
    public void containsInAnyOrderで要素が順序を問わず含まれているを検証する(){
        assertThat(ite, containsInAnyOrder("Hello", "world", "JUnit"));
        assertThat(ite, not(containsInAnyOrder("world", "JUnit")));
    }
}
