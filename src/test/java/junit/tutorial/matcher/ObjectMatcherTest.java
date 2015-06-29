package junit.tutorial.matcher;

import org.junit.Test;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.waman.junitutil.matchers.ObjectMatchers.*;

public class ObjectMatcherTest {

    // org.hamcrest.CoreMatchers
    @Test
    public void isもしくはequalToでequalsによる検証を行う(){
        assertThat(asList(1, 2), is(asList(1, 2)));
        assertThat(asList(1, 2), is(not(asList(1L, 2L))));

        assertThat(asList(1, 2), is(equalTo(asList(1, 2))));
        assertThat(asList(1, 2), is(not(equalTo(asList(1L, 2L)))));
    }

    // org.hamcrest.CoreMatchers
    @Test
    public void nullValueでnull値であることnotNullValueでnull値でないことの検証を行う(){
        String actual = null;
        assertThat(actual, is(nullValue()));

        assertThat("JUnit", is(notNullValue()));
    }

    // org.hamcrest.CoreMatchers
    @Test
    public void sameInstanceもしくはtheInstanceで同じ参照のオブジェクトであることの検証を行う(){
        assertThat("JUnit", is(sameInstance("JUnit")));
        assertThat("JUnit", is(not(sameInstance(new String("JUnit")))));

        assertThat("JUnit", is(theInstance("JUnit")));
        assertThat("JUnit", is(not(theInstance(new String("JUnit")))));
    }

    // org.hamcrest.CoreMatchers
    @Test
    public void instanceOfもしくはisAもしくはanyで型の検証を行う(){
        assertThat("JUnit", is(instanceOf(Serializable.class)));
        assertThat("JUnit", isA(Serializable.class));
        assertThat("JUnit", is(any(Serializable.class)));
    }

    // org.hamcrest.object.HasToString
    @Test
    public void hasToStringでtoStringの返す文字列を検証する(){
        assertThat(asList("Hello", "world"), hasToString("[Hello, world]"));
    }

    // org.hamcrest.object.IsCompatibleType
    @Test
    public void typeCompatibleWithで指定したクラスがスーパータイプであることを検証する(){
        assertThat(ArrayList.class, is(typeCompatibleWith(List.class)));
        assert(List.class.isAssignableFrom(ArrayList.class));

        assertThat(List.class, is(not(typeCompatibleWith(ArrayList.class))));
    }

    // org.hamcrest.object.IsEventFrom
    @Test
    public void eventFromで(){
        // Setup
        List<ActionEvent> eventStock = new LinkedList<>();

        JButton button = new JButton("テスト");
        button.addActionListener(eventStock::add);

        // Exercise
        button.doClick();

        // Verify
        assertThat(eventStock.get(0), is(eventFrom(button)));
    }
}
