package com.dgq.jl.java9.Stream;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * java9对 Stream 流的增强
 *
 * 新增流的方法：
 *  引入 ofNullable 方法
 *  新增 takeWhile 和 dropWhile 方法
 *  iterate(种子、判定器、单目运算)的有限迭代
 */
public class _Stream {

    /**
     * 引入 ofNullable 方法，避免空指针异常
     * ofNullable() 方法可以根据一个可能为 null 的值创建单元素或空 Stream
     */
    @Test
    public void testOfNullable() {
        // 传入 null 会被解析成一个数组对象，会进一步访问它的长度信息
        // 这里会有空指针问题 java.lang.NullPointerException: Cannot read the array length because "array" is null
        // Stream<Object> stream = Stream.of(null);
        // stream.forEach(System.out::println);

        /* 输出结果为
        * 111
        * 222
        *  */
        Stream<Object> stream2 = Stream.ofNullable(null);
        System.out.println("111");
        stream2.forEach(System.out::println); // 实际控制台不会打印任何数据，包括空行和回车换行
        System.out.println("222");
    }

    /**
     * takeWhile 从流中一直获取判定器为真的元素，一旦遇到元素为假，就终止处理
     * takeWhile() 方法可以从 Stream 中依次获取满足条件的元素，直到不满足条件为止结束获取
     */
    @Test
    public void testTakeWhile() {
        Stream<Integer> stream1 = Stream.of(3,9,20,22,40,7);
        Stream<Integer> stream2 = stream1.takeWhile(t -> t % 2 != 0);
        stream2.forEach(System.out::println);// 控制台输出 3 9

    }

    /**
     * dorpWhile 从流中一直获取元素，遇到真的元素就drop，一旦遇到假的，就取剩下的部分
     * 与 takeWhile 效果相反
     */
    @Test
    public void testDropWhile() {
        Stream<Integer> stream1 = Stream.of(3,9,20,22,40,7);
        Stream<Integer> stream2 = stream1.dropWhile(t -> t % 2 != 0);
        stream2.forEach(System.out::println); // 20 22 40 7
    }

    /**
     * iterate() 方法的新重载方法提供了一个 Predicate 参数（判断条件）来决定什么时候结束迭代
     */
    @Test
    public void testIterate() {
        // 使用原始 iterate() 方法输出数字 1~10
        Stream.iterate(1, i -> i + 1).limit(10).forEach(System.out::println);
        // 使用新的 iterate() 重载方法输出数字 1~10
        Stream.iterate(1, i -> i <= 10, i -> i + 1).forEach(System.out::println);

        // 没有新方法时，需要添加 limit 进行限制
        Stream<Integer> stream1 = Stream.iterate(1, t -> (2 * t) + 1);
        stream1.limit(10).forEach(System.out::println); // 10 表示限制十个元素

        // 有限的迭代 seed 表示起始元素；第二个参数表示限制条件，即循环条件；第三个元素表示下一个如何取值，即“步进表达式”
        Stream<Integer> stream2 = Stream.iterate(1, t -> t <1000, t -> (2 * t) + 1);
        stream2.forEach(System.out::println);
    }
}
