# 百度java开发面试总结  2018年11月1日22:49:09
## 一面：
- 1.java基础

  ArrayList和LinkedList区别
  
  HashMap原理
  
  用户线程和守护线程的区别
  
  synchronized是如何实现的，与ReentrantLock的区别，AQS原理，CAS操作
  
  volatile的内存语义，内存模型
  
  ThreadLocal的作用和实现原理
  
  线程池的基本参数，工作原理，可创建哪几种线程池
  
  JVM类加载机制（双亲委派机制）
  
  Springboot的优点，为什么用springboot
  
  SpringCloud是否用过
  
  Spring的优点，IOC与AOP解释及原理，动态代理的实现方式
  
  讲一讲thrift框架，rpc与http的区别
  
- 2.mysql数据库
  
  pgsql与mysql的区别，为什么选择pgsql
  
  mysql有哪几种存储引擎
  
  MyISAM与InnoDB的区别
  
  InnoDB的索引原理
  
  什么是最左前缀匹配
  
  事务的隔离级别
  
  mysql的主从同步怎么做
  
- 3.计算机网络
  
  tcp三次握手与四次挥手
  
  为什么四次挥手的时候中间的ack包和fin包不同时发送而要分开发送呢
- 4.数据结构与算法：

  代码实现单链表的反转
  
## 二面：
- 1.java基础：

  守护线程与用户线程的区别（一面也问了）
  
  线程池的基本参数，如何配置线程池，
  
  生产环境中如何选择线程池的饱和策略
  
  JVM的内存区域划分、JVM的垃圾回收
  
  HashMap与ConcurrentHashMap的区别，ConcurrentHashMap如何实现线程安全
  
  HashTable是如何实现线程安全的
  
  synchronized与ReentrantLock的区别（一面也问了）
  
  maven中引入了A包，A包中依赖了B包的1.0版本，然后又单独引入了B包的1.2版本，问最后引入的B包是哪个版本？
  （我回答：会出现jar包冲突，应该在引入A时使用exclusion将A中依赖的1.0版本的B包排除掉）

- 2.mysql数据库：
  
  mysql中的事务隔离级别（读未提交、读已提交、可重复读、可串行化）
  
  可重复读是如何解决“幻读”问题的
  
  什么是MVCC（多版本并发控制）

- 3.聊项目

- 4.中间件——kafka如何设置partition

- 5.数据结构与算法：
  
  二叉树的先序遍历非递归实现（想不起来了，但是我记得层序遍历的非递归实现）
  好吧，那你写一下二叉树的层序非递归遍历吧
  再写出给定的二叉树的前中后序的遍历结果
  
  
  
  
  
  
