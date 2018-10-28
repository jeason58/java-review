package com.jeason.java.review.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: jeason
 * @Date: 2018/10/26 17:44
 * @Description:
 * 关于Channel(通道)：所有的 NIO 操作始于通道，通道是数据来源或数据写入的目的地，类似 IO 中的流，用于读取和写入
 * - FileChannel：文件通道，用于文件的读和写
 * - DatagramChannel：用于 UDP 连接的接收和发送
 * - SocketChannel：把它理解为 TCP 连接通道，简单理解就是 TCP 客户端
 * - ServerSocketChannel：TCP 对应的服务端，用于监听某个端口进来的请求
 */

public class FileChannelDemo {
    /**
     * FileChannel：文件通道，用于文件的读和写
     * FileChannel的初始化如下：
     * 1. FileInputStream inputStream = new FileInputStream(new File("/data.txt"));
     *    FileChannel fileChannel = inputStream.getChannel();
     * 2. RandomAccessFile file = new RandomAccessFile("D:\\test.txt", "r);
     *    FileChannel channel = file.getChannel();
     * Channel一般主要和Buffer打交道，channel可把数据读到Buffer中，也可把Buffer中的数据写入到自身，如下：
     * 把数据读到Buffer中：
     * ByteBuffer buffer = ByteBuffer.allocate(1024);
     * fileChannel.read(buffer)
     * 把Buffer中的数据写入到自身：
     * ByteBuffer buffer = ByteBuffer.wrap(new byte[]{1, 2, 3, 4, 5});
     * fileChannel.write()
     *
     * 此外，javaNIO支持scatter和gather操作
     * scatter：将一个channel中的数据分散读取到多个buffer中，通过read()方法接收一个buffer数组
     * gather：将多个buffer中的数据聚集写入到一个channel中，通过write()方法接收一个buffer数组
     *
     * scatter操作在往下一个buffer中读取数据之前必须将上一个buffer写满
     * gather操作时，只会写入buffer中的有效数据(处于position和limit之间的数据)
     * */
    public static void main(String[] args) {
        try (RandomAccessFile srcFile = new RandomAccessFile("D:\\comments_of_ying.txt", "r");
             RandomAccessFile dstFile = new RandomAccessFile("D:\\testnio.txt", "rw");
             FileChannel srcChannel = srcFile.getChannel();
             FileChannel dstChannel = dstFile.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (srcChannel.read(buffer) > 0) {
                System.out.println(new String(buffer.array()));

                buffer.flip();
                dstChannel.write(buffer);

                buffer.rewind();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
