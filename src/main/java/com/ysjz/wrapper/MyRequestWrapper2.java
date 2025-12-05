package com.ysjz.wrapper;

import lombok.SneakyThrows;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 使用此wrapper时，要获取流
 * String jsonStr = IOUtils.toString(wrappedRequest.getInputStream(), "UTF-8");
 * MyRequestWrapper1、MyRequestWrapper2 皆能解决 POST 请求中body参数，是以流形式存在，流数据只能读取一次
 */
public class MyRequestWrapper2 extends HttpServletRequestWrapper {

    /**
     * 缓存下来的HTTP body
     */
    private byte[] body;
    private Charset charset;

    @SneakyThrows
    public MyRequestWrapper2(HttpServletRequest request, Charset charset) {
        super(request);
        try {
            body = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.charset = charset;
    }

    public MyRequestWrapper2(HttpServletRequest request) {
        this(request, StandardCharsets.UTF_8);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new RepeatableInputStream();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream(),charset));
    }

    private class RepeatableInputStream extends ServletInputStream{
        private ByteArrayInputStream byteArrayInputStream;

        @Override
        public synchronized void reset() throws IOException {
            byteArrayInputStream.reset();
        }

        @Override
        public synchronized void mark(int readlimit) {
            byteArrayInputStream.mark(readlimit);
        }

        public RepeatableInputStream() {
            byteArrayInputStream = new ByteArrayInputStream(body);
        }

        @Override
        public boolean isFinished() {
            return byteArrayInputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            throw new UnsupportedOperationException("不支持监听");
        }

        @Override
        public int read() throws IOException {
            return byteArrayInputStream.read();
        }

        @Override
        public boolean markSupported() {
            return byteArrayInputStream.markSupported();
        }
    }
}