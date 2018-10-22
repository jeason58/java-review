package com.jeason.practice.study08;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: jeason
 * @Date: 2018/10/22 13:54
 * @Description:
 */
public class StringInternTest {
    public void sayHello() {
        System.out.println("Hello, World!");
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = new String("abc");
        System.out.println("s1 == s2: " + (s1 == s2));  // false

        String s3 = s1.intern();
        System.out.println("s1 == s3: " + (s1 == s3));  // true
        System.out.println("s2 == s3: " + (s2 == s3));  // false

        String s4 = s2.intern();
        System.out.println("s1 == s4: " + (s1 == s4));  // true

        String s5 = "abcd";
        final String s6 = "ab";
        final String s7 = "cd";
        String s8 = s6 + s7;
        System.out.println("s5 == s8: " + (s5 == s8));  // true：final变量在编译器会直接被替换为相应常量

        String s9 = new StringBuffer().append("计算机").append("软件").toString();
        System.out.println("s9 == s9.intern(): " + (s9 == s9.intern()));  // true

        String s10 = new StringBuffer("ja").append("va").toString();
        System.out.println("s10 == s10.intern(): " + (s10 == s10.intern()));  // false


        // CGlib动态代理的使用
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(StringInternTest.class);
        enhancer.setUseCache(false);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return methodProxy.invokeSuper(o, objects);
            }
        });

        StringInternTest obj = (StringInternTest) enhancer.create();
        System.out.println("obj.getClass(): " + obj.getClass());
        obj.sayHello();
    }
}
