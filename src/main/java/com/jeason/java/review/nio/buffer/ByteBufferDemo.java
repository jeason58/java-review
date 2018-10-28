package com.jeason.java.review.nio.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: jeason
 * @Date: 2018/10/26 15:58
 * @Description:
 */

/**
 * java NIO之ByteBuffer：Buffer是内存中的一块缓冲区空间，可将数据写入这块内存，也可从中读取数据，
 * 八大类型除了boolean，其他都有相应的Buffer类，ByteBuffer是被应用最普遍的，Buffer可理解为一个数组，例如ByteBuffer可理解为byte[]
 * 个基本属性：capacity、limit、position
 * capacity表示该缓冲区的容量，一旦初始化就不可更改
 * position和limit是变化的：
 * position的初始值为0，写操作时，每往Buffer中写入一个值，position会自增1；读操作时，每读取一个值，position也会自增1
 * 从写模式切换到读模式使用 flip()方法，position会自动归0
 * limit：在写模式下，表示该缓冲区最大能写入的数据量，limit=capacity；在读模式下，limit表示该缓冲区中的实际数据大小，因为buffer不一定被写满了
 *
 * Buffer的初始化一般使用Buffer的静态方法allocation(int capacity)来初始化，
 * 或者使用Buffer.wrap(byte[] bytes)对一个字节数组进行包装生成一个buffer对象
 *
 * put()方法用于给buffer末尾或者指定位置填充一个值，或者讲一个数组填充进去
 *
 * 当进行写入操作时，调用clear()方法将position=0；limit=capacity，mark=-1
 * 当进行读取操作时，调用flip()方法将 limit=position；position=0；mark=-1
 *
 * mark()/reset()方法用于读取到某个数据的时候做个标记mark,然后继续往后读取时随时可以reset到mark的位置重新读取
 * rewind()：会重置position为0，mark为-1，通常用于从头重新读写buffer
 * clear()：会重置position为0，limit=capacity，mark=-1，相当于重新实例化了buffer一样
 * compact()：会先处理没有读取的数据，也就是position到limit之间的数据，先将这些未处理的数据移动到左边，然后在这个基础上再进行写入
 * */
public class ByteBufferDemo {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(128);
        buffer.clear();
        for (byte i = 0; i < 20; i++) {
            buffer.put(i);
        }

        buffer.flip();
        for (int i=0; i<buffer.limit()-5; i++) {
            System.out.printf("%d\t", buffer.get());
        }
        System.out.println("\n\n---------------------------------------------------------\n");

        buffer.compact();
        for (byte i = 100; i < 127; i++) {
            buffer.put(i);
        }

        buffer.flip();
        while (buffer.position() < buffer.limit()) {
            if (buffer.position() == 5) {
                buffer.mark();
            }
            System.out.printf("%d\t", buffer.get());
        }
        System.out.println("\n\n---------------------------------------------------------\n");

        buffer.reset();
        while (buffer.position() < buffer.limit()) {
            System.out.printf("%d\t", buffer.get());
        }
    }
}
