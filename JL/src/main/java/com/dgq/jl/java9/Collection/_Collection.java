package com.dgq.jl.java9.Collection;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 集合增强
 * 增加了 List.of()、Set.of()、Map.if()和Map.ofEntries() 等工厂方法来创建不可变集合
 *
 */
public class _Collection {

    /**
     * jdk8，使用 Collections.UnmodifiabledList 创建不可变集合，允许只读
     */
    @Test
    public void testCollection1() {
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("LiLei");
        list.add("Black");

        // 调用Collections中的方法，将变量 list 变成只读集合
        List<String> readOnlyList = Collections.unmodifiableList(list);
        // 只读集合不可再追加元素；具体异常 java.lang.UnsupportedOperationException
        // readOnlyList.add("Brown");
        // 只读集合只能进行遍历只读操作
        readOnlyList.forEach(System.out::println);
        // 此处进行集合的遍历修改元素，同样存在异常 java.lang.UnsupportedOperationException
        /*
        ListIterator<String> it = readOnlyList.listIterator();
        while (it.hasNext()) {
            String item = it.next();
            it.set(item + "ttt");  // 真正修改原集合中的元素
        }
        readOnlyList.forEach(System.out::println);
         */
        ListIterator<String> it2 = list.listIterator();
        while (it2.hasNext()) {
            String item = it2.next();
            it2.set(item + "ttt");  // 真正修改原集合中的元素
        }
        list.forEach(System.out::println);

        // 或者直接使用 Collections 的方法创建集合
        List<Integer> numList = Collections.unmodifiableList(Arrays.asList(1,2,3,4,5));
        Set<Integer> numSet = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(6,7,8,9,10)));
        Map<Object, Object> readOnlyMap = Collections.unmodifiableMap(new HashMap<>() {
            {
                put("Tom", 78);
                put("Jerry", 88);
                put("Tim", 68);
            }
        });
        numList.forEach(System.out::println);
        numSet.forEach(System.out::println);
        readOnlyMap.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });
    }

    /**
     * jdk9中，可以直接使用 List.of Set.of Map.of Map.ofEntries 的方式创建只读集合/map
     * 对于这些只读集合，进行add/put操作，通用会出现 java.lang.UnsupportedOperationException 异常报错
     */
    @Test
    public void testCollection2() {
        List<Integer> list = List.of(1,2,3);
        list.forEach(System.out::println);
        // list.add(7);

        Set<Integer> set = Set.of(4,5,6);
        set.forEach(System.out::println);
        // set.add(8);

        // 创建只读 Map 的方式一：使用 Map.of(),其中的元素数量必须是偶数
        Map<String, Integer> map1 = Map.of("Tom", 1, "Jerry", 2, "Billy", 3);
        // 方式二
        Map<String, Integer> map2 =
                Map.ofEntries(Map.entry("James", 23), Map.entry("Jordan", 23));

        System.out.println(map1);
        System.out.println(map2);

        // map1.put("Duncan", 21);
        // map2.put("Duncan", 21);
    }

    /**
     * 其他创建不可变集合的方式
     */
    @Test
    public void testCollection3() {
        // Guava
        ImmutableList<Integer> list = ImmutableList.of(99,88,77);
        List<Integer> oldList = Lists.newArrayList(1,2,3);
        ImmutableList<Integer> copy = ImmutableList.copyOf(oldList);

        ImmutableList<Integer> built = ImmutableList.<Integer>builder()
                .add(101)
                .addAll(oldList)
                .build();

        list.forEach(System.out::println);
        copy.forEach(System.out::println);
        built.forEach(System.out::println);

        // jdk10
        List<Integer> immutable = List.copyOf(oldList);
        Set<Integer> immutableSet = Set.copyOf(oldList);
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("Tom", 1);
        map1.put("Jerry", 2);
        map1.put("Billy", 3);

        Map<String, Integer> immutableMap = Map.copyOf(map1);

        // jdk16 toList() 方法返回的是不可编辑集合，而Collectors.toList() 返回的是可编辑集合
        List<Integer> list16 = oldList.stream().toList();

    }
}
