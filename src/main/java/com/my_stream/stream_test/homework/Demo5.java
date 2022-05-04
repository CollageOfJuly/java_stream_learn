package com.my_stream.stream_test.homework;

import com.my_stream.stream_test.data.Author;
import com.my_stream.stream_test.data.DataUtils;

import java.util.List;

/**
 * 方法引用
 * 使用Lambda时，如果匿名内部类中的方法体只有一个方法的调用，则可以通过方法引用，进一步简化代码
 * 引用类的静态方法、引用对象的实例方法、引用类的实例方法、构造器引用
 *
 * @Author zj
 * @Date 2022/5/4
 */
public class Demo5 {
    private static List<Author> authors = null;

    static {
        authors = DataUtils.getAuthors();
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test4() {
//        authors.stream()
//                .map(author -> author.getName())
//                .map(name->new StringBuilder(name))
//                .forEach(stringBuilder -> System.out.println(stringBuilder));

        // 进一步简化，构造器引用
        authors.stream()
                .map(author -> author.getName())
                .map(StringBuilder::new)
                .forEach(stringBuilder -> System.out.println(stringBuilder));
    }

    interface UseString{
        String use(String str,int start,int length);
    }

    private static String subAuthorName(String str, UseString useString) {
        int start = 0;
        int length = 1;
        return useString.use(str,start,length);
    }

    private static void test3() {
//        subAuthorName("三更草堂", new UseString() {
//            @Override
//            public String use(String str, int start, int length) {
//                return str.substring(start, length);
//            }
//        });

        // 进一步简化，引用类的实例方法
        subAuthorName("三更草堂", String::substring);
    }

    private static void test2() {
        StringBuilder sb = new StringBuilder();
//        authors.stream()
//                .map(Author::getName)
//                .forEach(name -> sb.append(name));

        // 进一步简化，引用对象的实例方法
        authors.stream()
                .map(Author::getName)
                .forEach(sb::append);
    }

    private static void test1() {
//        authors.stream()
//                .map(author -> author.getAge())
//                .map(age -> String.valueOf(age))
//                .forEach(age -> System.out.println(age));

        // 进行简化，引用类的静态方法
        authors.stream()
                .map(author -> author.getAge())
                .map(String::valueOf)
                .forEach(age -> System.out.println(age));

        // 再进行简化，引用类的实例方法
        authors.stream()
                .map(Author::getAge)
                .map(String::valueOf)
                .forEach(System.out::println);
    }


}
