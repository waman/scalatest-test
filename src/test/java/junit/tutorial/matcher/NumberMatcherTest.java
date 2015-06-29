package junit.tutorial.matcher;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.waman.junitutil.matchers.NumberMatchers.*;

public class NumberMatcherTest {

    // org.hamcrest.number.OrderingComparison
    @Test
    public void comparesEqualToで値が等しいことを検証する(){
        assertThat(0, not(comparesEqualTo(-1)));
        assertThat(0, comparesEqualTo(0));
        assertThat(0, not(comparesEqualTo(1)));
    }

    // org.hamcrest.number.OrderingComparison
    @Test
    public void lessThanで指定された値より小さいことを検証する(){
        assertThat(0, is(not(lessThan(-1))));
        assertThat(0, is(not(lessThan(0))));
        assertThat(0, is(lessThan(1)));
    }

    // org.hamcrest.number.OrderingComparison
    @Test
    public void lessThanOrEqualToで指定された値より小さいか等しいことを検証する(){
        assertThat(0, is(not(lessThanOrEqualTo(-1))));
        assertThat(0, is(lessThanOrEqualTo(0)));
        assertThat(0, is(lessThanOrEqualTo(1)));
    }

    // org.hamcrest.number.OrderingComparison
    @Test
    public void greaterThanで指定された値より大きいことを検証する(){
        assertThat(0, is(greaterThan(-1)));
        assertThat(0, is(not(greaterThan(0))));
        assertThat(0, is(not(greaterThan(1))));
    }

    // org.hamcrest.number.OrderingComparison
    @Test
    public void greaterThanOrEqualToで指定された値より大きいか等しいことを検証する(){
        assertThat(0, is(greaterThanOrEqualTo(-1)));
        assertThat(0, is(greaterThanOrEqualTo(0)));
        assertThat(0, is(not(greaterThanOrEqualTo(1))));
    }

    // org.hamcrest.number.IsCloseTo
    @Test
    public void closeToで指定した範囲内で浮動小数点数が一致することを検証する(){
        assertThat(0.98, is(not(closeTo(1.0, 0.015))));
        assertThat(0.99, is(closeTo(1.0, 0.015)));
        assertThat(1.00, is(closeTo(1.0, 0.015)));
        assertThat(1.01, is(closeTo(1.0, 0.015)));
        assertThat(1.02, is(not(closeTo(1.0, 0.015))));
    }

    // org.hamcrest.number.BigDecimalCloseTo
    @Test
    public void closeToで指定した範囲内でBigDecimal値が一致することを検証する(){
        assertThat(big("0.98"), is(not(closeTo(big("1.0"), big("0.01")))));
        assertThat(big("0.99"), is(closeTo(big("1.0"), big("0.01"))));
        assertThat(big("1.00"), is(closeTo(big("1.0"), big("0.01"))));
        assertThat(big("1.01"), is(closeTo(big("1.0"), big("0.01"))));
        assertThat(big("1.02"), is(not(closeTo(big("1.0"), big("0.01")))));
    }

    static BigDecimal big(String d){
        return new BigDecimal(d);
    }
}
