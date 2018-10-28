package com.jeason.java.review.features;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: jeason
 * @Date: 2018/10/25 11:31
 * @Description:
 */
public class DynamicProxyDemo {
    interface HelloIface {
        void sayHello();
    }

    static class HelloImpl implements HelloIface {

        @Override
        public void sayHello() {
            System.out.println("hello, world");
        }
    }

    static class HelloHandler implements InvocationHandler {
        private HelloIface target;

        public HelloHandler(HelloIface target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("pre operation...");
            Object res = method.invoke(target, args);
            System.out.println("after operation");
            return res;
        }

        public HelloIface getProxyInstance() {
            return (HelloIface) Proxy.newProxyInstance(
                    this.getClass().getClassLoader(),
                    target.getClass().getInterfaces(), this);
        }
    }

    public static void main(String[] args) {
        HelloIface hello = new HelloImpl();
        HelloHandler handler = new HelloHandler(hello);
        HelloIface helloProxy = handler.getProxyInstance();
        helloProxy.sayHello();
    }
}
