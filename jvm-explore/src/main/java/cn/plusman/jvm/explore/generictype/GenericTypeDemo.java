package cn.plusman.jvm.explore.generictype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author plusman
 * @since 2022/9/7 16:51
 */
public class GenericTypeDemo {
    private static class Dog {
        private String name;
        
        public Dog(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    }
    
    public static void main(String[] args) {
        List<Dog> list = new ArrayList<>();
        list.add(new Dog("XiaoBei"));
    }
}
