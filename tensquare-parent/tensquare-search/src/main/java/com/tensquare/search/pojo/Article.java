package com.tensquare.search.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * 文章搜索实体类 , elasticsearch搜索
 *
 * 对比
 * Elasticsearch    关系型数据库Mysql
 * 索引(index)        数据库(databases)
 * 类型(type)         表(table)
 * 文档(document)     行(row)
 */
@Document(indexName = "tensquare_article", type = "article")
public class Article implements Serializable {

    @Id
    private String id;//ID

    //是否索引，就是看该域是否能被搜索
    //是否分词，就是单词匹配还是整体匹配
    //是否存储，是否在页面上显示
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;//标题

    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;//文章正文

    private String state;//审核状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
