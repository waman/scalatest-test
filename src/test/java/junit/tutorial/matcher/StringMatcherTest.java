package junit.tutorial.matcher;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class StringMatcherTest {

    final String string = "Hello JUnit world";
    final String nullString = null;
    final String emptyString = "";

    // org.hamcrest.CoreMatchers
    @Test
    public void startsWithで特定の文字列から始まることを検証する(){
        assertThat(string, startsWith("Hello"));
    }

    // org.hamcrest.CoreMatchers
    @Test
    public void containsStringで特定の文字列が含まれることを検証する() {
        assertThat(string, containsString("JUnit"));
    }

    // org.hamcrest.CoreMatchers
    @Test
    public void endsWithで特定の文字列で終わることを検証する(){
        assertThat(string, endsWith("world"));
    }

    // org.hamcrest.text.IsEmptyString
    @Test
    public void isEmptyOrNullStringでnull値もしくは空文字であることを検証する(){
        assertThat(nullString, isEmptyOrNullString());
        assertThat(emptyString, isEmptyOrNullString());
        assertThat(string, not(isEmptyOrNullString()));
    }

    // org.hamcrest.text.IsEmptyString
    @Test
    public void isEmptyStringでだ空文字であることを検証する(){
        assertThat(nullString, not(isEmptyString()));
        assertThat(emptyString, isEmptyString());
        assertThat(string, not(isEmptyString()));
    }

    // org.hamcrest.text.IsEqualIgnoringWhiteSpace
    @Test
    public void equalToIgnoringWhiteSpaceで空白文字を除いて一致するか検証する(){
        assertThat(string, is(equalToIgnoringWhiteSpace("Hello\tJUnit\t\r\nworld")));
    }

    // org.hamcrest.text.IsEqualIgnoringCase
    @Test
    public void equalToIgnoringCaseで大文字小文字を除いて一致するか検証する(){
        assertThat(string, is(equalToIgnoringCase("hello junit world")));
    }

    // org.hamcrest.text.StringContainsInOrder
    @Test
    public void stringContainsInOrderで指定された複数の文字列が順に含まれているか検証する(){
        assertThat(string, stringContainsInOrder(asList("Hello", "world")));
        assertThat(string, not(stringContainsInOrder(asList("world", "Hello"))));
    }
}
