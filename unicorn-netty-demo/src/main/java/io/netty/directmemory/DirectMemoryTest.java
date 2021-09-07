package io.netty.directmemory;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: unicorn
 * Date: 2021/7/26 2:19 下午
 * Description: No Description
 */
public class DirectMemoryTest {
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            heapAccess();
            directAccess();
        }
        System.out.println();
        for(int i=0;i<10;i++){
            heapAllocate();
            directAllocate();
        }
    }

    public static void heapAccess() {
        long startTime = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(1000);
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 200; j++) {
                buffer.putInt(j);
            }
            buffer.flip();
            for (int j = 0; j < 200; j++) {
                buffer.getInt();
            }
            buffer.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("堆内存访问：" + (endTime - startTime) + "ms");
    }

    public static void directAccess() {
        long startTime = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocateDirect(1000);
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 200; j++) {
                buffer.putInt(j);
            }
            buffer.flip();
            for (int j = 0; j < 200; j++) {
                buffer.getInt();
            }
            buffer.clear();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("直接内存访问：" + (endTime - startTime) + "ms");
    }

    public static void heapAllocate() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            ByteBuffer.allocate(100);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("堆内存申请：" + (endTime - startTime) + "ms");
    }

    public static void directAllocate() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            ByteBuffer.allocateDirect(100);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("直接内存申请：" + (endTime - startTime) + "ms");
    }
}
