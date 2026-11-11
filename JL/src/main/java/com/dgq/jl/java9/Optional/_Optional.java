package com.dgq.jl.java9.Optional;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Optional 类中新增了 ifPresentOrElse()、or() 和 stream() 等方法
 */
public class _Optional {

    @Test
    public void testOptionalStream() {
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Tim");

        Optional<List<String>> optional = Optional.ofNullable(list);
        Stream<String> stream = optional.stream().flatMap(x -> x.stream());
        stream.forEach(System.out::println);
    }

    /**
     * ifPresentOrElse() 方法接受两个参数 Consumer 和 Runnable，
     * 如果 Optional 不为空调用 Consumer 参数，为空则调用 Runnable 参数
     */
    @Test
    public void testIfPresentOrElse() {
        Optional<Object> optionalObject = Optional.empty();
        Optional<Object> optionalObject1 = Optional.of("TEST");
        optionalObject.ifPresentOrElse(System.out::println, () -> System.out.println("Empty!!"));
        optionalObject1.ifPresentOrElse(System.out::println, () -> System.out.println("Empty!!"));
    }

    /**
     * or() 方法接受一个 Supplier 参数，如果 Optional 为空则返回 Supplier 参数指定的 Optional 值
     */
    @Test
    public void testOr() {
        Optional<Object> optionalObject = Optional.empty();
        optionalObject.or(() -> Optional.of("java")).ifPresent(System.out::println);
    }
}
