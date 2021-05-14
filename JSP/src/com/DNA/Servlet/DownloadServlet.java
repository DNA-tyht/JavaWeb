package com.DNA.Servlet;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @Description
 * @Author 脱氧核糖
 * @Version 1.0
 * @Date 2021/5/14 15:53
 */
public class DownloadServlet extends HttpServlet {
    public static void main(String[] args) throws IOException {
        String content = "需要Base64编码的内容";
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encode = base64Encoder.encode(content.getBytes("UTF-8"));
        System.out.println(encode);
        //创建Base64解码器
        BASE64Decoder base64Decoder = new BASE64Decoder();
        //解码操作
        byte[] bytes = base64Decoder.decodeBuffer(encode);
        String str = new String(bytes, "UTF-8");
        System.out.println(str);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取下载的文件名
        String downloadFileName = "小野猪.jpg";
        //获取下载的文件类型
        ServletContext servletContext = getServletContext();
        String mimeType = servletContext.getMimeType(downloadFileName);
        InputStream resourceAsStream = servletContext.getResourceAsStream(downloadFileName);
        //把下载的内容回传给客户端
        ServletOutputStream outputStream = response.getOutputStream();
        //读取输出流的全部数据，复制给输出流，输出给客户端
        IOUtils.copy(resourceAsStream, outputStream);
        //回传前，通过响应头告诉客户端返回的数据类型
        response.setContentType(mimeType);
        //告诉客户端数据用于下载
        //attachment：附件
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(downloadFileName, "UTF-8"));
    }
}
