package org.waman.junitutil.matcher;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.Month.FEBRUARY;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.waman.junitutil.matcher.IsDate.dateOf;

public class IsDateTest {

    @Test
    public void 日付を比較する(){
        LocalDate date = LocalDate.now();
        assertThat(date, is(dateOf(2011, FEBRUARY, 10)));
//        assertThat(date, is(dateOf("2015-07-03")));
    }
}
