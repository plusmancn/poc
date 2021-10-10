package cn.plusman.jvm.explore;

/**
 * @author plusman
 * @since 2021/10/10 4:27 PM
 */
public class GcTest {
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[30900*1024];
        allocation2 = new byte[49000*1024];
    }
}
