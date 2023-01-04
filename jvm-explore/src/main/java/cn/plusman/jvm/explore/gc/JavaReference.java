package cn.plusman.jvm.explore.gc;

import java.lang.ref.WeakReference;

/**
 * @author plusman
 * @since 2021/10/10 9:03 PM
 */
public class JavaReference {
    public static void main(String[] args) {
        WeakReference<JavaReference> weakReference = new WeakReference<>(new JavaReference());
    }
}
