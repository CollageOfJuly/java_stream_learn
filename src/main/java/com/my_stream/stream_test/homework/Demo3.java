package com.my_stream.stream_test.homework;

import com.my_stream.stream_test.data.Book;

import java.util.Optional;

/**
 * Optional-学习
 *
 * @Author zj
 * @Date 2022/4/28
 */
public class Demo3 {
    static Optional<Book> bookOptional;
    static Optional<Book> nullOptional;


    static {
        Book book = new Book();
        book.setName("天空");
        bookOptional = Optional.ofNullable(book);
        nullOptional = Optional.ofNullable(null);
    }

    public static void main(String[] args) throws Throwable {
//        testConstructObject();
//        testGetData();
//        testConsumerData();
//        testFilter();
//        testJustfy();
        testConvert();
    }

    /**
     * Optional数据转换的方式
     * 方式：map()
     */
    private static void testConvert() {
        // 由原来的book对象，转成String对象
        Optional<String> s = bookOptional.map(book -> book.getName());
        System.out.println(s);
    }

    /**
     * Optional判断的方式
     * 方式：ifPresent()
     */
    private static void testJustfy() {
        if(bookOptional.isPresent()) {
            System.out.println("对象1不为空");
        }
        if(nullOptional.isPresent()) {
            System.out.println("对象2不为空");
        }
    }

    /**
     * Optional过滤的方式
     * 方式：filter()
     */
    private static void testFilter() {
        Optional<Book> bookOptional2 = bookOptional.filter(book -> book.getName().equals("大地"));
        // 空Optional对象，已过滤
        System.out.println(bookOptional2);
    }

    /**
     * Optional消费值的方式
     * 方式：ifPresent()  如果optional不为空，则执行ifPresent方法参数中的匿名内部类方法
     */
    private static void testConsumerData() {
        // 执行到ifPresent()参数中的方法
        bookOptional.ifPresent(book -> System.out.println(book.getName()));
        // 未执行ifPresent()参数中的方法
        nullOptional.ifPresent(book -> System.out.println("nullOptional:" + book.getName()));
    }

    /**
     * Optional获取值的方式
     * 1，get()
     * 2，orElse()
     * 3，orElseGet()
     * 4，orElseThrow()
     */
    private static void testGetData() throws Throwable {
        System.out.println(bookOptional.get());
        // 对象为空时，调用get()会报错：NoSuchElementException: No value present
//        System.out.println(nullOptional.get());
        // 对象为空时，调用orElse()获取对象，会获取ofElse()参数中的对象
        System.out.println(nullOptional.orElse(new Book()));
        // 对象为空时，调用orElseGet()获取对象，会获取内部类中重写方法返回的对象
        System.out.println(nullOptional.orElseGet(() -> {
            Book book = new Book();
            book.setName("空对象的book");
            return book;
        }));
        // 对象为空时，抛出异常
        nullOptional.orElseThrow(() -> new Exception("Optional对象为空的异常抛出"));
    }

    /**
     * Optional创建对象的方式
     * 1，Optional.ofNullable()
     * 2，Optional.of()
     * 3，Optional.empty()
     */
    private static void testConstructObject() {
        // 方式一：Optional.ofNullable()。无论对象是否为null，创建Optoinal对象
        Book book = new Book();
        book.setName("天空");
        Optional<Book> bookOptional = Optional.ofNullable(book);
        // 方式二：Optional.of()。只能创建非空的Optional对象
        Optional<Object> nullOptional = Optional.of(null);
        System.out.println(nullOptional);
        // 方式三：Optional.empty()。创建空的Optional对象
        Optional<Object> emptyOptional = Optional.empty();

        // bookOptional是空对象时，报错：NoSuchElementException: No value present
//        System.out.println(bookOptional.get());
        // bookOptional是空对象时，返回一个new Book()对象；不为空，则返回当前对象
//        System.out.println(bookOptional.orElse(new Book()));
    }
}
