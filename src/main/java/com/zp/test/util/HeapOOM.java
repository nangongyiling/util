package com.zp.test.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @description HeapOOM
 * <p>Java堆异常测试</p>
 * <code>VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError</code>
 * <p>以上参数的含义是：限制Java堆大小为20MB，不可扩展</p>
 * <p>通过此参数可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照</p>
 * @author Yampery
 * @date 2017年6月16日 下午11:42:46
 */
public class HeapOOM {

    static class OOMObject { }

    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        } /// while end
    } /// main
} ///~

/**
 * 运行结果：
 * java.lang.OutOfMemoryError: Java heap space
 * Dumping heap to E:\job\java_pid4056.hprof ...
 * Heap dump file created [28010445 bytes in 0.373 secs]
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
