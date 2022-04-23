package com.my_stream.stream_test.homework;

import com.my_stream.stream_test.data.Author;
import com.my_stream.stream_test.data.Book;
import com.my_stream.stream_test.data.DataUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 终结操作
 *
 * @Author zj
 * @Date 2022/4/23
 */
public class Demo2 {
    public static void main(String[] args) {
        List<Author> authors = DataUtils.getAuthors();
//        testCount(authors);
//        testMaxAndMin(authors);
//        testCollect1(authors);
//        testCollect2(authors);
//        testCollect3(authors);
        // 查询与匹配
//        testAnyMatch(authors);
//        testAllMatch(authors);
//        testNoneMatch(authors);
//        testFindAny(authors);
//        testFindFirst(authors);
        // reduce归并
//        testReduce1(authors);
//        testReduce2(authors);
        testReduce3(authors);
    }

    private static void testReduce3(List<Author> authors) {
        // 使用reduce求所有作者中年龄的最小值
        Integer reduce = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, (integer, integer2) -> integer < integer2 ? integer : integer2);
        System.out.println(reduce);
    }

    private static void testReduce2(List<Author> authors) {
        // 使用reduce求所有作者中年龄的最大值
        Integer reduce = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, (integer, integer2) -> integer > integer2 ? integer : integer2);
        System.out.println(reduce);
    }

    private static void testReduce1(List<Author> authors) {
        // 使用reduce求所有作者年龄的和
        Optional<Integer> reduce = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce((integer, integer2) -> integer + integer2);
        System.out.println(reduce.get());
    }

    private static void testFindFirst(List<Author> authors) {
        // 获取一个年龄最小的作家，并输出他的姓名。
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
        System.out.println(first.get().getName());

    }

    private static void testFindAny(List<Author> authors) {
        // 获取任意一个年龄大于18的作家，如果存在就输出他的名字
        Optional<Author> any = authors.stream()
                .distinct()
                .filter(author -> author.getAge() > 18)
                .findAny();
        any.ifPresent(author -> System.out.println(author.getName()));
    }

    private static void testNoneMatch(List<Author> authors) {
        // 判断作家是否都没有超过100岁的。
        boolean b = authors.stream()
                .distinct()
                .noneMatch(author -> author.getAge() > 100);
        System.out.println(b);
    }

    private static void testAllMatch(List<Author> authors) {
        // 判断是否所有的作家都是成年人
        boolean b = authors.stream()
                .distinct()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println(b);
    }

    private static void testAnyMatch(List<Author> authors) {
        // 判断是否有年龄在29以上的作家
        boolean b = authors.stream()
                .distinct()
                .anyMatch(author -> author.getAge() > 29);
        System.out.println(b);
    }

    private static void testCollect3(List<Author> authors) {
        // 获取一个Map集合，map的key为作者名，value为List<Book>
        Map<String, List<Book>> collect = authors.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
        System.out.println(collect);
    }

    private static void testCollect2(List<Author> authors) {
        // 获取一个所有书名的Set集合。
        Set<String> collect = authors.stream()
                .distinct()
                .flatMap(author -> author.getBooks().stream())
                .map(author -> author.getName())
                .collect(Collectors.toSet());
        System.out.println(collect);
    }

    private static void testCollect1(List<Author> authors) {
        // 获取一个存放所有作者名字的List集合。
        List<String> collect = authors.stream()
                .map(author -> author.getName())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void testMaxAndMin(List<Author> authors) {
        // 分别获取这些作家的所出书籍的最高分和最低分并打印。
        Optional<Integer> max = authors.stream()
                .distinct()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(book -> book.getScore())
                .max((o1, o2) -> o1 - o2);
        System.out.println(max.get());
        Optional<Book> min = authors.stream()
                .distinct()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .min((o1, o2) -> o1.getScore() - o2.getScore());
        System.out.println(min.get().getScore());
    }

    private static void testCount(List<Author> authors) {
        // 打印书籍的数目，注意删除重复元素。
        long count = authors.stream()
                .distinct()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println("书籍总数：" + count);
    }
}
