package com.yjy.jd.util;

import com.yjy.jd.entity.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析html工具
 */
public class HtmlParseUtil {
    public static void main(String[] args) throws Exception {
        List<Content> contents = HtmlParseUtil.parseJD("java");
        contents.forEach(item -> {
            System.out.println(item);
        });
    }

    public static List<Content> parseJD(String keywords) throws Exception {
        //获取请求
        String url = "https://search.jd.com/Search?keyword=" + keywords;
        //解析网页（jsoup返回的document就是浏览器返回的Document对象）
        Document document = Jsoup.parse(new URL(url), 30000);
        // 所有在js中的方法，都可以使用
        Element element = document.getElementById("J_goodsList");
        // 获取所有的li元素
        Elements elements = element.getElementsByTag("li");
        List<Content> goodList = new ArrayList<>();
        // 获取元素内容,每个li标签
        for (Element el : elements) {
//            String img = el.getElementsByTag("img").eq(0).attr("src");
            String img = el.getElementsByClass("p-img").first().getElementsByTag("img").first().attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
            goodList.add(content);
        }
        return goodList;
    }
}
