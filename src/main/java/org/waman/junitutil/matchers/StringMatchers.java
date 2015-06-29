package org.waman.junitutil.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.text.IsEmptyString;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.hamcrest.text.IsEqualIgnoringWhiteSpace;
import org.hamcrest.text.StringContainsInOrder;

public class StringMatchers {

    public static
    Matcher<String> isEmptyOrNullString(){
        return IsEmptyString.isEmptyOrNullString();
    }
        
    public static
    Matcher<String> isEmptyString(){
        return IsEmptyString.isEmptyString();
    }

    public static
    Matcher<String> equalToIgnoringWhiteSpace(String expectedString){
        return IsEqualIgnoringWhiteSpace.equalToIgnoringWhiteSpace(expectedString);
    }

    public static
    Matcher<String> equalToIgnoringCase(String expectedString){
        return IsEqualIgnoringCase.equalToIgnoringCase(expectedString);
    }

    public static
    Matcher<String> stringContainsInOrder(Iterable<String> substrings){
        return StringContainsInOrder.stringContainsInOrder(substrings);
    }
}
