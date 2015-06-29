package org.waman.junitutil.matchers;

import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsArrayWithSize;
import org.hamcrest.collection.IsArray;
import org.hamcrest.collection.IsArrayContaining;
import org.hamcrest.collection.IsArrayContainingInOrder;
import org.hamcrest.collection.IsArrayContainingInAnyOrder;

public class ArrayMatchers {

    public static <E>
    Matcher<E[]> arrayWithSize(int size){
        return IsArrayWithSize.arrayWithSize(size);
    }

    public static <E>
    Matcher<E[]> arrayWithSize(Matcher<? super Integer> sizeMatcher){
        return IsArrayWithSize.arrayWithSize(sizeMatcher);
    }

    public static <E>
    Matcher<E[]> emptyArray(){
        return IsArrayWithSize.emptyArray();
    }

    public static <T>
    IsArray<T> array(Matcher<? super T>... elementMatchers){
        return IsArray.array(elementMatchers);
    }

    public static <T>
    Matcher<T[]> hasItemInArray(T element){
        return IsArrayContaining.hasItemInArray(element);
    }

    public static <T>
    Matcher<T[]> hasItemInArray(Matcher<? super T> elementMatcher){
        return IsArrayContaining.hasItemInArray(elementMatcher);
    }

    public static <E>
    Matcher<E[]> arrayContaining(E... items){
        return IsArrayContainingInOrder.arrayContaining(items);
    }
    
    public static <E>
    Matcher<E[]> arrayContaining(List<Matcher<? super E>> itemMatchers){
        return IsArrayContainingInOrder.arrayContaining(itemMatchers);
    }
    
    public static <E>
    Matcher<E[]> arrayContaining(Matcher<? super E>... itemMatchers){
        return IsArrayContainingInOrder.arrayContaining(itemMatchers);
    }

    public static <E>
    Matcher<E[]> arrayContainingInAnyOrder(Collection<Matcher<? super E>> itemMatchers){
        return IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(itemMatchers);
    }
    
    public static <E>
    Matcher<E[]> arrayContainingInAnyOrder(E... items){
        return IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(items);
    }
    
    public static <E>
    Matcher<E[]> arrayContainingInAnyOrder(Matcher<? super E>... itemMatchers){
        return IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(itemMatchers);
    }
}
