package com.DNA.pojo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

/**
 * @Description
 * @Author 脱氧核糖
 * @Version 1.0
 * @Date 2021/4/26 9:13
 */
public class Dome4jTest {
    //读取xml文件，获取Document对象
    @Test
    public void test1() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/test.xml");
        System.out.println(document);
    }

    //通过Document对象获取根元素
    @Test
    public void test2() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/test.xml");
        Element rootElement = document.getRootElement();
        System.out.println(rootElement);

        //通过根元素获取book标签对象
        //element() 和 elements() 通过标签名查找子元素
        List<Element> books = rootElement.elements("book");
        for (Element book : books) {
            //asXML()：把标签对象转化为标签字符串
            Element nameElement = book.element("name");
            //getTest()：获取标签中的文本内容
            String nameText = nameElement.getText();
            //直接获取文本内容
            String priceTest = book.elementText("price");
            String sn = book.attributeValue("sn");
            String author = book.elementText("author");
            System.out.println(new Book(sn, nameText, Double.parseDouble(priceTest), author));
        }

    }
}