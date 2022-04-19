package com.my_stream.labmda_test;

/**
 * lambda表达式-函数式编程
 * 更关注数据的实现
 *
 * @Author zj
 * @Date 2022/4/19
 */
public class Demo1 {
    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("创建了线程");
//            }
//        }).start();
        /**
         * 省略规则：
         * 当重写的方法体只有一句代码时，可以省略方法体、分号。
         * 入参的参数类型也可以省略，当只有单个参数时，小括号可以省略
         */
        new Thread(() -> System.out.println("创建了线程")).start();
    }
}
