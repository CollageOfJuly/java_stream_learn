package com.my_stream.stream_test.homework;

import com.my_stream.stream_test.data.Author;
import com.my_stream.stream_test.data.DataUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * 高级用法
 * 基本数据类型优化，以及并行流的使用
 *
 * @Author zj
 * @Date 2022/5/5
 */
public class Demo6 {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        // 开启并行流parallel()方法
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer integer = stream.parallel()
                .peek(num -> System.out.println(num + Thread.currentThread().getName()))
                .filter(num -> num > 5)
                .reduce((result, ele) -> result + ele)
                .get();
        System.out.println(integer);
        // 也可通过parallelStream()来创建并行流对象
    }

    private static void test1() {
        List<Author> authors = DataUtils.getAuthors();
//        authors.stream()
//                .map(author -> author.getAge())
//                .map(age -> age + 10)
//                .filter(age->age>18)
//                .map(age->age+2)
//                .forEach(System.out::println);

        // mapToInt对Integer引用类型进行优化，直接进行拆箱，不再装箱
        authors.stream()
                .mapToInt(author -> author.getAge())
                .map(age -> age + 10)
                .filter(age->age>18)
                .map(age->age+2)
                .forEach(System.out::println);
    }
}
