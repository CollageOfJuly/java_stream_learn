package com.my_stream.stream_test.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zj
 * @Date 2022/4/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    //id
    private Long id;
    //书名
    private String name;

    //分类
    private String category;

    //评分
    private Integer score;

    //简介
    private String intro;
}
