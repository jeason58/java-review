package com.jeason.java.review.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: jeason
 * @Date: 2018/10/27 22:21
 * @Description: ServerSocketChannel用来创建一个TCP服务端
 */
public class ServerSocketChannelDemo {
    private static final int _PORT = 8088;
    private static final int _THREAD_NUM = 5;

    // 处理接收到的客户端channel的任务类
    public static class ClientTask implements Runnable {
        private SocketChannel channel;
        public ClientTask(SocketChannel channel) {
            this.channel = channel;
        }
        @Override
        public void run() {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (channel.read(buffer) > 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + new String(buffer.array()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(args);
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind(new InetSocketAddress(_PORT));
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(_THREAD_NUM);
            System.out.println("server started on port: " + _PORT);
            while (true) {
                SocketChannel channel = serverChannel.accept();
                executor.execute(new ClientTask(channel));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
