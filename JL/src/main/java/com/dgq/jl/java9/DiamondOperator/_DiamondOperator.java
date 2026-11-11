package com.dgq.jl.java9.DiamondOperator;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 钻石操作符
 *
 * 是什么： Java 7 引入的一项语法糖，写法就是一对空尖括号 <>，用于在实例化泛型类时省略右侧构造器里重复的类型参数，由编译器根据左侧声明自动推断。
 * 解决的问题： Java 7 之前，左右两边必须同时写完整泛型，嵌套泛型时尤其冗余：
 *
 *
 */
public class _DiamondOperator {

    public static void main(String[] args) {
        // Java 7 之前
        Map<String, List<Trade>> trades = new TreeMap<String, List<Trade>>();

        // Java 7 之后（钻石操作符）
        Map<String, List<Trade>> trades2 = new TreeMap<>();
        /* NOTICE 该语法只适用于构造器调用，但不适用于方法调用或静态工厂方法；推断失败仍会报错，且不支持匿名内部类
            在JDK9中，对该语法进行了优化，使得兼容使用
         */
        // Java 8 及之前：匿名内部类必须显式写出泛型
        Handler<Integer> h = new Handler<Integer>(1) {
            @Override public void handle() { System.out.println("content"); }
        };

        // Java 9：可省略为 <>，编译器自动推断
        Handler<Integer> h2 = new Handler<>(1) {
            @Override public void handle() { System.out.println("content2"); }
        };

    }
}

class Trade {
    private String price;
}

abstract class Handler<T> {

    public T content;

    public Handler(T content) {
        this.content = content;
    }

    abstract void handle();
}
