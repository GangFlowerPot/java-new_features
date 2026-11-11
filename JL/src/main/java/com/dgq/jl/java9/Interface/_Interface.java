package com.dgq.jl.java9.Interface;

import org.junit.jupiter.api.Test;

/**
 * 接口性能增强
 *
 * Java 9 允许在接口中使用私有方法。这样的话，接口的使用就更加灵活了，有点像是一个简化版的抽象类。
 */
interface MyInterface {

    // jdk7: 只能声明全局常量（public static final）和抽象方法（public abstract）
    // 实现类必须实现所有抽象方法，一旦接口增加新方法，所有实现类都要改

    public static final String TITLE = "title"; // 全局常量
    public abstract void method1(); // 抽象方法


    // jdk8：声明静态方法和默认方法
    public static void method2 () {
        System.out.println("jdk8 的静态方法 method2");
    }

    default void method3 () {
        System.out.println("jdk8 的默认方法 method3");

    }


    // jdk9：声明私有方法
    private void method4 () {
        System.out.println("私有方法");
    }
}

class MyInterfaceImpl implements  MyInterface{

    @Override
    public void method1() {
        System.out.println("实现接口中的抽象方法 method1");
    }

}

public class _Interface {
    @Test
    public void testInterface() {
        MyInterfaceImpl impl = new MyInterfaceImpl();
        impl.method3(); //  method3 私有方法

        impl.method1();
    }
}
