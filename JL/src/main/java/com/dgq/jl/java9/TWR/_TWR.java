package com.dgq.jl.java9.TWR;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

/**
 * IO 增强 即 TWR增强
 * TWR(try-with-resource) 语句
 * try-with-resources 是 Java 7 引入的资源自动管理语法，
 * 把“必须手动在 finally 中 close 的资源”改造成“离开 try 块时自动按逆序关闭”；
 * Java 9 的增强只放宽了语法限制，允许括号里直接引用外部已声明的 effectively final 变量，不再强制在 try() 内重新声明
 *
 */
public class _TWR {

    /**
     * java7之前，处理资源关闭的写法
     */
    @Test
    public void testTWR1() {
        // NOTICE 流、连接等资源，在trycatch中使用结束后，需要在finally中进行手动关闭/清理
        InputStreamReader reader = null;

        try {
            reader = new InputStreamReader(System.in);
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 资源的关闭操作
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * java7~java8的写法，要求资源对象的实例化，必须放在try的括号内完成
     */
    @Test
    public void testTWR2() {
        // NOTICE 只能在 try-with-resources 块中声明变量

        try (InputStreamReader reader = new InputStreamReader(System.in)){
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 可以实例化多个资源，都会在try使用结束后自动释放
        try (Scanner scanner = new Scanner(new File("testRead.txt"));
             PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) {
            // omitted
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * jdk9中：可以在try()中调用已经实例化的资源对象
     * 直接在try括号中直接写入变量就好，如果有多个流/资源，就用分好隔开
     */
    @Test
    public void testTWR3() {
        // 在 try-with-resources 语句中可以使用 effectively-final 变量
        // 什么是 effectively-final 变量？ 简单来说就是没有被 final 修饰但是值在初始化后从未更改的变量
        // 即 writer 变量没有被显示声明为 final，但它在第一次被赋值后就不会改变了，因此，它就是 effectively-final 变量
        // NOTICE 如果对 final 修饰的变量或者 effectively-final 变量进行重新赋值，则会出现编译报错
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);

        try (reader; writer) {
            // 此时的reader是final的，不可再被赋值
            // reader = null;
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
