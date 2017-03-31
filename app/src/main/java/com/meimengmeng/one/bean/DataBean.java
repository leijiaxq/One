package com.meimengmeng.one.bean;

import java.util.List;

/**
 * Create by  leijiaxq
 * Date       2017/3/6 16:06
 * Describe
 */

public class DataBean {


    /**
     * result : ok
     * urls : [{"color":"FF0001","id":1,"url":"https://po.baidu.com/feed/share?isBdboxShare=1&context=%7B%22nid%22%3A%22news_3083051110577958357%22%2C%22sourceFrom%22%3A%22bjh%22%7D"},{"color":"FF0001","id":1,"url":"https://po.baidu.com/feed/share?isBdboxShare=1&context=%7B%22nid%22%3A%22news_3083051110577958357%22%2C%22sourceFrom%22%3A%22bjh%22%7D"},{"color":"FF0002","id":2,"url":"https://po.baidu.com/feed/share?isBdboxShare=1&context=%7B%22nid%22%3A%22news_3055443279863245635%22%2C%22sourceFrom%22%3A%22bjh%22%7D"},{"color":"FF0003","id":3,"url":"https://baijiahao.baidu.com/po/feed/share?context=%7B%22nid%22%3A%22news_3527214466467693204%22%2C%22sourceFrom%22%3A%22bjh%22%7D"}]
     */

    public String result;
    public List<UrlsEntity> urls;

    public static class UrlsEntity {
        /**
         * color : FF0001
         * id : 1
         * url : https://po.baidu.com/feed/share?isBdboxShare=1&context=%7B%22nid%22%3A%22news_3083051110577958357%22%2C%22sourceFrom%22%3A%22bjh%22%7D
         */

        public String color;
        public int    id;
        public String url;
    }
}
