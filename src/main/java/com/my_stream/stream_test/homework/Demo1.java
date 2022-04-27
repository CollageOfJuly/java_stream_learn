package com.my_stream.stream_test.homework;

import com.my_stream.stream_test.data.Author;
import com.my_stream.stream_test.data.DataUtils;

import java.util.Arrays;
import java.util.List;

/**
 * stream流-中间操作的学习
 *
 * @Author zj
 * @Date 2022/4/19
 */
public class Demo1 {
    public static void main(String[] args) {
        List<Author> authors = DataUtils.getAuthors();
//        testFilter(authors);
//        testMap(authors);
//        testDistinct(authors);
        testSorted(authors);
//        testLimit(authors);
//        testSkip(authors);
//        testFlatMap1(authors);
//        testFlatMap2(authors);
    }

    private static void testFlatMap2(List<Author> authors) {
        // 打印现有数据的所有分类。要求对分类进行去重。不能出现这种格式：哲学,爱情
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .filter(category -> !category.equals("哲学") && !category.equals("爱情"))
                .forEach(category -> System.out.println(category));
    }

    private static void testFlatMap1(List<Author> authors) {
        // 打印所有书籍的名字。要求对重复的元素进行去重。
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
    }

    private static void testSkip(List<Author> authors) {
        // 打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序。
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge()-o1.getAge())
                .skip(1)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static void testLimit(List<Author> authors) {
        // 对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素,然后打印其中年龄最大的两个作家的姓名。
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge()-o1.getAge())
                .limit(2)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static void testSorted(List<Author> authors) {
        // 对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素。
        // 分为内部比较器，实体实现Comparable接口和外部比较器，创建Comparator比较器匿名内部类。选择一种方式即可
        authors.stream().distinct().sorted((o1, o2) -> o2.getAge()-o1.getAge()).forEach(author -> System.out.println(author));
    }

    private static void testDistinct(List<Author> authors) {
        // 打印所有作家的姓名，并且要求其中不能有重复元素。
        authors.stream().map(author -> author.getName()).distinct().forEach(name -> System.out.println(name));
    }

    private static void testMap(List<Author> authors) {
        // 打印所有作家的姓名
        authors.stream().map(author -> author.getName()).forEach(s -> System.out.println(s));
    }

    private static void testFilter(List<Author> authors) {
        // 打印所有姓名长度大于1的作家的姓名
        authors.stream()
                .filter(author -> author.getName().length() > 1)
                .forEach(author -> System.out.println(author.getName()));
    }
}
