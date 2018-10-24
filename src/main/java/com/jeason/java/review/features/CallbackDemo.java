package com.jeason.java.review.features;

/**
 * @description: 理解java的回调函数
 * @author: jeason·wang
 * @date: 2018-07-31 16:16
 **/
public class CallbackDemo {

  // 回调接口
  private interface CallbackService {
    void service(Object parameter); // 业务方法
    void call(Object parameter); // 回调方法
  }

  // 回调接口实现类
  private static class CallableServiceImpl implements CallbackService {
    private Caller caller;

    public CallableServiceImpl(Caller caller) {
      this.caller = caller;
    }

    @Override
    public void service(Object parameter) {
      new Thread(() -> caller.call(this, parameter)).start();
      System.out.println("已经调用call方法，先等一会儿，干点别的事情");
      System.out.println("正在干别的事情......");
    }

    @Override
    public void call(Object parameter) {
      System.out.println("receive parameter: " + parameter);
      System.out.println("return response: Hello, 小王");
    }
  }

  // 执行回调者
  private static class Caller {
    void call(CallbackService callable, Object parameter) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      callable.call(parameter);
    }
  }

  public static void main(String[] args) {
    Caller caller = new Caller();
    CallbackService service = new CallableServiceImpl(caller);
    service.service("hello, 小李");
  }
}
