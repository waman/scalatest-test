package junit.tutorial.matcher;


import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.waman.junitutil.matchers.BeanMatchers.*;

public class BeanMatcherTest {

    public static class Person{

        private final String name;
        private final int age;
        private final boolean isMarried;

        public Person(String name, int age, boolean isMarried) {
            this.name = name;
            this.age = age;
            this.isMarried = isMarried;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public boolean isMarried() {
            return isMarried;
        }
    }

    final Person me = new Person("waman", 100, false);
    final Person anotherMe = new Person("waman", 100, false);
    final Person you = new Person("waman", 50, true);

    // org.hamcrest.beans.HasProperty
    @Test
    public void hasPropertyで指定されたプロパティが存在することを検証する(){
        assertThat(me, hasProperty("name"));
        assertThat(me, hasProperty("age"));
        assertThat(me, hasProperty("married"));
    }

    // org.hamcrest.beans.HasPropertyWithValue
    @Test
    public void hasPropertyで指定されたプロパティが指定された値を持つことを検証する(){
        assertThat(me, hasProperty("name", is("waman")));
        assertThat(me, hasProperty("age", is(100)));
        assertThat(me, hasProperty("married", is(false)));
    }

    // org.hamcrest.beans.SamePropertyValuesAs
    @Test
    public void samePropertyValueAsで指定されたオブジェクトとプロパティがすべて同じことを検証する(){
        assertThat(anotherMe, is(not(sameInstance(me))));
        assertThat(anotherMe, is(samePropertyValuesAs(me)));
        assertThat(you, is(not(samePropertyValuesAs(me))));
    }
}
