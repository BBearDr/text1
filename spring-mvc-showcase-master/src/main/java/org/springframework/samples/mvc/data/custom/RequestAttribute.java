package org.springframework.samples.mvc.data.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
/**
 * 注解作用的目标:
 * @Target(ElementType.TYPE)   //接口、类、枚举、注解
*　@Target(ElementType.FIELD) //字段、枚举的常量
*　@Target(ElementType.METHOD) //方法
*　@Target(ElementType.PARAMETER) //方法参数
*　@Target(ElementType.CONSTRUCTOR)  //构造函数
 *　@Target(ElementType.LOCAL_VARIABLE) //局部变量
 *　@Target(ElementType.ANNOTATION_TYPE) //注解
 *　@Target(ElementType.PACKAGE) ///包
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 *注解的保留位置
 * @Retention(RetentionPolicy.SOURCE) 注解仅存在于源码中,在class字节码文件中不包含
 * @Retention(RetentionPolicy.CLASS) 默认的保留策略,只是存在class字节码文件中,但是在运行时无法获得
 * @Retention(RetentionPolicy.RUNTIME) 注解会在class字节码文件中,运行时可以通过反射得到
 */
@Documented
/**
 * 该注解会被包含在javadoc文档中
 */
public @interface RequestAttribute {
	String value();
}