package com.jeason.java.review.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: jeason
 * @Date: 2018/10/27 23:17
 * @Description: 之前讲的SocketChannel和ServerSocketChannel都是基于tcp协议，而DatagramChannel基于UDP协议
 * 一个DatagramChannel类处理了客户端和服务端
 * 1.作为服务器监听端口时，可使用receive(ByteBuffer buffer)方法接受一个请求，如下：
 *   DatagramChannel channel = DatagramChannel.open();
 *   channel.socket().bind(new InetSocketAddress(9090));
 *   ByteBuffer buf = ByteBuffer.allocate(48);
 *   buf.clear();
 *   channel.receive(buf);
 * 2.作为客户端发送请求时，可使用send(ByteBuffer src, SocketAddress target)来发送请求，如下：
 *   String newData = "New String to write to file..." + System.currentTimeMillis();
 *   ByteBuffer buf = ByteBuffer.allocate(48);
 *   buf.clear();
 *   buf.put(newData.getBytes());
 *   buf.flip();
 *   int bytesSent = channel.send(buf, new InetSocketAddress("jenkov.com", 80));
 */
public class DatagramChannelDemo {
    private static final Integer _PORT = 8080;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        try (DatagramChannel serverChannel = DatagramChannel.open();
             DatagramChannel clientChannel = DatagramChannel.open()) {
            // 服务端Channel绑定端口
            serverChannel.bind(new InetSocketAddress(_PORT));

            // 服务端接收数据
            new Thread(() -> {
                try {
                    ByteBuffer buffer = ByteBuffer.allocate(512);
                    buffer.flip();
                    while (buffer.limit() <= 0) {
                        buffer.clear();
                        serverChannel.receive(buffer);
                        buffer.flip();
                    }
                    System.out.println(Thread.currentThread().getName() + " received: " + new String(buffer.array()));
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }, "server-thread").start();

            // 客户端发送数据
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                    ByteBuffer buffer = ByteBuffer.wrap("Hello, World".getBytes());
                    int sentBytes;
                    while ((sentBytes = clientChannel.send(buffer, new InetSocketAddress(_PORT))) <= 0) {
                        continue;
                    }
                    System.out.println(Thread.currentThread().getName() + " sent: " + sentBytes + " bytes data");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }, "client-thread").start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
