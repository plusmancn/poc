package cn.plusman.jvm.explore.trydemo;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 在 finally 中不能轻易是用 return
 * 在 catch 和 finally 同事
 * @author plusman
 * @since 2022/9/7 10:09
 */
public class Try {
    public static void main(String[] args) {
        String a = Arrays.stream(new String[]{"a", "b"}).collect(Collectors.joining(""));
        System.out.printf(a);
        
        try {
            System.out.println(String.format("%s: %s", "case1 res", case1()));
        } catch (Exception e) {
            System.out.println("Root Catch Exception -> " + e.getMessage());
        }
    }
    
    public static String case1() {
        try {
            System.out.println("Try to do something");
            throw new RuntimeException("RuntimeException");
            // return "正确结果";
        } catch (Exception e) {
            System.out.println("Catch Exception -> " + e.getMessage());
            throw new RuntimeException("RuntimeException Again");
            // return "空结果";
        } finally {
            System.out.println("In Finally");
            // throw new RuntimeException("RuntimeException from finally");
            return "兜底结果";
        }
    }
}
