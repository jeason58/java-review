package com.jeason.java.review.nio.selector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther: jeason
 * @Date: 2018/10/26 17:45
 * @Description: Selector建立在非阻塞的基础之上，经常听到的多路复用在Java中指的就是它，用于实现一个线程管理多个Channel。
 * 1.首先，开启一个 Selector, 翻译成选择器也好，多路复用器也好。
 *   Selector selector = Selector.open();
 * 2.将Channel注册到Selector上。Selector建立在非阻塞模式之上，所以注册到Selector的Channel必须要支持非阻塞模式，
 * FileChannel 不支持非阻塞，我们这里讨论最常见的 SocketChannel 和 ServerSocketChannel
 *   channel.configureBlocking(false); // 将通道设置为非阻塞模式，因为默认都是阻塞模式的
 *   SelectionKey key = channel.register(selector, SelectionKey.OP_READ); // 注册
 * 注意————：
 * register 方法的第二个 int 型参数（使用二进制的标记位）用于表明需要监听哪些感兴趣的事件，共以下四种事件：
 * ①.SelectionKey.OP_READ:   对应 00000001，通道中有数据可以进行读取
 * ②.SelectionKey.OP_WRITE:  对应 00000100，可以往通道中写入数据
 * ③.SelectionKey.OP_CONNECT:对应 00001000，成功建立 TCP 连接
 * ④.SelectionKey.OP_ACCEPT: 对应 00010000，接受 TCP 连接
 * 3.调用 select() 方法获取通道信息。用于判断是否有我们感兴趣的事件已经发生了
 *
 *
 * 4.Selector中维护3个特别重要的SelectionKey集合，分别是
 * ①.keys：所有注册到Selector的Channel所表示的SelectionKey都会存在于该集合中。keys元素的添加会在Channel注册到Selector时发生。
 * ②.selectedKeys：该集合中的每个SelectionKey都是其对应的Channel在上一次操作selection期间被检查到至少有一种SelectionKey中所感兴趣的操作已经准备好被处理。该集合是keys的一个子集。
 * ③.cancelledKeys：执行了取消操作的SelectionKey会被放入到该集合中。该集合是keys的一个子集。
 */
public class SelectorDemo {
    private static final int _PORT = 8888;

    public static void main(String[] args) throws IOException {
        // 初始化一个多路复用器(选择器)——Selector
        Selector selector = Selector.open();

        // 创建一个用于监听的channel
        ServerSocketChannel channel = ServerSocketChannel.open();
        // 为该channel绑定一个监听的端口号
        channel.bind(new InetSocketAddress(_PORT));
        // 将该channel配置为非阻塞模式
        channel.configureBlocking(false);
        // 将channel注册到创建好的Selector上
        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);

        while(true) {
            // 判断是否有事件准备好
            int readyChannels = selector.select();
            if(readyChannels == 0) continue;

            // 遍历
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                    System.out.println("acceptable...");
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                    System.out.println("connectable...");
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                    System.out.println("readable...");
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                    System.out.println("writable...");
                }

                keyIterator.remove();
            }
        }
    }
}
