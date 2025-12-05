package com.ysjz.wrapper;

import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class MyRequestWrapper1 extends HttpServletRequestWrapper {
    private final byte[] body; // 缓存请求体的字节数组

    public MyRequestWrapper1(HttpServletRequest request) throws IOException {
        super(request);
        // 关键步骤：在构造时一次性读取并存储原始请求流
        body = StreamUtils.copyToByteArray(request.getInputStream());
    }

    // 提供一个便捷方法，用于在过滤器中获取请求体内容（例如记录日志）
    // 使用时，直接调用 getBodyString() 即可
    public String getBodyString() throws UnsupportedEncodingException {
        return new String(body, this.getCharacterEncoding());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        // 每次调用都返回一个基于缓存数据的新流
        ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public int read() {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return bais.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                // 无需实现
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), this.getCharacterEncoding()));
    }
}