package com.jeason.java.review.nio.channel;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Auther: jeason
 * @Date: 2018/10/27 22:04
 * @Description:
 * SocketChannel不仅是一个TCP客户端，与之对应的有ServerSocketChannel，同时SocketChannel也是一个网络通道，可读可写
 * 以下Demo中的初始化方法等价于：
 * SocketChannel channel = SocketChannel.open();
 * channel.connect(new InetSocketAddress("localhost", 8088));
 * 同时可通过ServerSocketChannel的accept方法实例化一个SocketChannel
 */
public class SocketChannelDemo {
    public static void main(String[] args) {
        try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8088))) {
            ByteBuffer buffer = ByteBuffer.wrap("Hello, Server".getBytes());
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }

            ByteBuffer resBuffer = ByteBuffer.allocate(256);
            resBuffer.clear();
            int bytes = channel.read(resBuffer);
            if (bytes > 0) {
                System.out.println(new String(resBuffer.array()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
