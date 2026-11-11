package com.dgq.jl.java9.ModuleSystem;

/**
 * 模块化系统
 * 将一个模块看作是一组唯一命名、可重用的包、资源和模块描述文件（module-info-java.text）。
 *
 * 任意一个 jar 文件，只要加上一个模块描述文件（module-info-java.text），就可以升级为一个模块
 *
 * module	给模块起名字	说明这是一组有边界的代码
 * requires	我需要谁	声明依赖哪些模块
 * exports	我允许谁访问	只暴露指定的包
 * opens	允许反射访问	给框架、序列化、测试等开放权限
 * provides / uses	服务提供 / 使用	支持 SPI 插件式扩展
 *
 * 具体见文件 module-info-java.text
 */
public class demo {
}
