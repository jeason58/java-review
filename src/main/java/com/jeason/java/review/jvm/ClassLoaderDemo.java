package com.jeason.java.review.jvm;

/**
 * @Auther: jeason
 * @Date: 2018/10/24 16:10
 * @Description: java类加载机制，静态代码块与匿名代码块的先后顺序
 */
public class ClassLoaderDemo {
    static class SuperClass {
        {
            System.out.println("super nesta code block...");
        }

        static {
            System.out.println("super static code block...");
        }

        public SuperClass() {
            System.out.println("superClass init...");
        }

        static final String HELLO = "hello";
        static String HelloWorld = "Hello, World";
    }

    static class SubClass extends SuperClass {
        static {
            System.out.println("sub static code block...");
        }

        public SubClass() {
            System.out.println("subClass init...");
        }
    }


    public static void main(String[] args) {
        System.out.println("SubClass.HELLO: " + SubClass.HELLO);
        System.out.println(" -------------------- ");

        System.out.println("SubClass.HelloWorld: " + SubClass.HelloWorld);
        System.out.println(" -------------------- ");

        SuperClass sc = new SubClass();
        System.out.println(" -------------------- ");

        sc = new SubClass();
        System.out.println(" -------------------- ");

        sc = new SubClass();
        System.out.println(" -------------------- ");
    }
}
