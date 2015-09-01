package org.waman.junitutil.matcher;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class IsDate extends TypeSafeMatcher<LocalDate>{

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("<yyyy/M/d>");

    private final LocalDate date;

    private IsDate(LocalDate date){
        this.date = date;
    }

    @Override
    protected boolean matchesSafely(LocalDate localDate) {
        return localDate.equals(this.date);
    }

    @Override
    public void describeTo(Description desc) {
//        desc.appendValue(this.date);
        desc.appendText(this.date.format(FORMAT));
    }

    @Override
    protected void describeMismatchSafely(LocalDate actual, Description desc) {
        desc.appendText("was " + actual.format(FORMAT));
    }

    @Factory
    public static Matcher<LocalDate> dateOf(int year, int month, int day){
        return dateOf(year, Month.of(month), day);
    }

    @Factory
    public static Matcher<LocalDate> dateOf(int year, Month month, int day){
        return dateOf(LocalDate.of(year, month, day));
    }

    @Factory
    public static Matcher<LocalDate> dateOf(LocalDate date){
        if(date == null)throw new NullPointerException("IsDate#dateOfの引数はnullであってはいけません。");
        return new IsDate(date);
    }
}
