package cn.plusman.programming.pearls.chapter1;

import java.util.BitSet;

/**
 * 1.6.1
 * 如果不缺内存，如何使用一个具有库的语言来实现一种排序算法以表示和排序集合？
 * 
 * @author plusman
 * @since 2022/9/14 21:50
 */
public class BucketDemo {
    public static void main(String[] args) {
        BitSet bits = new BitSet(100);
        // 起始下标位 0
        bits.set(0);
        // 位设置
        bits.set(65);
        // 范围设置
        bits.set(63,70);
        
        bits.clear(67);
        
        // 139.180.146.32
    
        System.out.println(String.format("%s", bits));
    }
    
    
}
