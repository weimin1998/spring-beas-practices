package com.example.springmvcrequestBodydemo.config_handle_encrypt_dncrypt;

import com.example.springmvcrequestBodydemo.util.AESUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class EncryptResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream byteArrayOutputStream;

    public EncryptResponseWrapper(HttpServletResponse response) {
        super(response);
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener listener) {

            }

            @Override
            public void write(int b) throws IOException {
                byteArrayOutputStream.write(b);
            }
        };
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(new OutputStreamWriter(this.getOutputStream()));
    }

    @Override
    public void flushBuffer() throws IOException {
        byte[] original = byteArrayOutputStream.toByteArray();

        try {
            byte[] encryptedResponse = AESUtil.encrypt(original, "weimin1234567890".getBytes()).getBytes();
            // 设置加密后的响应内容长度
            setContentLength(encryptedResponse.length);

            // 将加密后的响应内容写入输出流
            getResponse().getOutputStream().write(encryptedResponse);
            getResponse().getOutputStream().flush();
            getResponse().getOutputStream().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
