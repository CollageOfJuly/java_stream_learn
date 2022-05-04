package com.my_stream.stream_test.homework;

import com.my_stream.stream_test.data.Author;
import com.my_stream.stream_test.data.DataUtils;

import java.util.List;
import java.util.function.Predicate;

/**
 * 函数式接口-常用的默认方法
 * and or negate
 *
 * @Author zj
 * @Date 2022/5/4
 */
public class Demo4 {
    private static List<Author> authors = null;
    static {
        authors = DataUtils.getAuthors();
    }
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    private static void test3() {
        // 打印作家中年龄不大于17的作家。
        // negate方法相当于是在判断添加前面加了个! 表示取反
        authors.stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() > 17;
                    }
                }.negate())
                .forEach(author -> System.out.println(author));
    }

    private static void test2() {
        // 打印作家中年龄大于17或者姓名的长度小于2的作家。
        // or可用于多个Predicate判断接口实现的拼接，进行逻辑或判断
        authors.stream()
                .filter((new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() > 17;
                    }
                }).or(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getName().length() > 2;
                    }
                }))
                .forEach(author -> System.out.println(author));
    }

    private static void test1() {
        // 打印作家中年龄大于17并且姓名的长度大于1的作家。
        // and可用于多个Predicate判断接口实现的拼接，进行逻辑与判断
        authors.stream()
                .filter((new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() > 17;
                    }
                }).and(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getName().length() > 1;
                    }
                }))
                .forEach(author -> System.out.println(author));
    }
}
