package org.waman.junitutil.matchers;

import org.hamcrest.Matcher;

import org.hamcrest.number.BigDecimalCloseTo;
import org.hamcrest.number.IsCloseTo;
import org.hamcrest.number.OrderingComparison;

import java.math.BigDecimal;

public class NumberMatchers {

    public static <T extends Comparable<T>>
    Matcher<T> comparesEqualTo(T value){
        return OrderingComparison.comparesEqualTo(value);
    }

    public static <T extends Comparable<T>>
    Matcher<T> lessThan(T value){
        return OrderingComparison.lessThan(value);
    }

    public static <T extends Comparable<T>>
    Matcher<T> lessThanOrEqualTo(T value){
        return OrderingComparison.lessThanOrEqualTo(value);
    }

    public static <T extends Comparable<T>>
     Matcher<T> greaterThan(T value){
        return OrderingComparison.greaterThan(value);
    }

    public static <T extends Comparable<T>>
    Matcher<T> greaterThanOrEqualTo(T value){
        return OrderingComparison.greaterThanOrEqualTo(value);
    }

    public static
    Matcher<Double> closeTo(double operand, double error){
        return IsCloseTo.closeTo(operand, error);
    }

    public static
    Matcher<BigDecimal> closeTo(BigDecimal operand, BigDecimal error){
        return BigDecimalCloseTo.closeTo(operand, error);
    }
}
