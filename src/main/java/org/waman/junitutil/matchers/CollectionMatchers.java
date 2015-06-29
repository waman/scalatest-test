package org.waman.junitutil.matchers;

import java.util.Collection;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsEmptyIterable;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.collection.IsIterableWithSize;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.collection.IsIn;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;

public class CollectionMatchers {

    public static <T>
    Matcher<T> isIn(Collection<T> collection){
        return IsIn.isIn(collection);
    }
    
    public static <T>
    Matcher<T> isIn(T[] elements){
        return IsIn.isIn(elements);
    }
    
    public static <T>
    Matcher<T> isOneOf(T... elements){
        return IsIn.isOneOf(elements);
    }


    public static <E>
    Matcher<Iterable<? extends E>> emptyIterable(){
        return IsEmptyIterable.emptyIterable();
    }

    public static <E>
    Matcher<Iterable<E>> emptyIterableOf(Class<E> type){
        return IsEmptyIterable.emptyIterableOf(type);
    }

    public static <E>
    Matcher<Collection<? extends E>> empty(){
        return IsEmptyCollection.empty();
    }
    
    public static <E>
    Matcher<Collection<E>> emptyCollectionOf(Class<E> type){
        return IsEmptyCollection.emptyCollectionOf(type);
    }

    public static <E>
    Matcher<Iterable<E>> iterableWithSize(int size){
        return IsIterableWithSize.iterableWithSize(size);
    }
    
    public static <E>
    Matcher<Iterable<E>> iterableWithSize(Matcher<? super Integer> sizeMatcher){
        return IsIterableWithSize.iterableWithSize(sizeMatcher);
    }

    public static <E>
    Matcher<Collection<? extends E>> hasSize(int size){
        return IsCollectionWithSize.hasSize(size);
    }
    
    public static <E>
    Matcher<Collection<? extends E>> hasSize(Matcher<? super Integer> sizeMatcher){
        return IsCollectionWithSize.hasSize(sizeMatcher);
    }

    public static <E>
    Matcher<Iterable<? extends E>> contains(E... items){
        return IsIterableContainingInOrder.contains(items);
    }
    
    public static <E>
    Matcher<Iterable<? extends E>> contains(List<Matcher<? super E>> itemMatchers){
        return IsIterableContainingInOrder.contains(itemMatchers);
    }
    
    public static <E>
    Matcher<Iterable<? extends E>> contains(Matcher<? super E>... itemMatchers){
        return IsIterableContainingInOrder.contains(itemMatchers);
    }
    
    public static <E>
    Matcher<Iterable<? extends E>> contains(Matcher<? super E> itemMatcher){
        return IsIterableContainingInOrder.contains(itemMatcher);
    }

    public static <T>
    Matcher<Iterable<? extends T>> containsInAnyOrder(Collection<Matcher<? super T>> itemMatchers){
        return IsIterableContainingInAnyOrder.containsInAnyOrder(itemMatchers);
    }
    
    public static <T>
    Matcher<Iterable<? extends T>> containsInAnyOrder(Matcher<? super T>... itemMatchers){
        return IsIterableContainingInAnyOrder.containsInAnyOrder(itemMatchers);
    }
    
    public static <T>
    Matcher<Iterable<? extends T>> containsInAnyOrder(T... items){
        return IsIterableContainingInAnyOrder.containsInAnyOrder(items);
    }
}
