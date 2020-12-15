package com.mouensis.framework.web.filter;

import org.springframework.lang.Nullable;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * {@link HttpServletResponse} wrapper that caches all content written to
 * the {@linkplain #getOutputStream() output stream} and {@linkplain #getWriter() writer},
 * and allows this content to be retrieved via a {@link #getBody()} byte array}.
 *
 * @author zhuyuan
 * @date 2020-12-01 19:41
 */
public class ContentCachingResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream cachedContent = new ByteArrayOutputStream(0);

    @Nullable
    private ServletOutputStream outputStream;

    @Nullable
    private PrintWriter writer;


    /**
     * Create a new ContentCachingResponseWrapper for the given servlet response.
     *
     * @param response the original servlet response
     */
    public ContentCachingResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (this.outputStream == null) {
            this.outputStream = new ResponseServletOutputStream((HttpServletResponse) getResponse());
        }
        return this.outputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (this.writer == null) {
            this.writer = new ResponsePrintWriter(getOutputStream(), getCharacterEncoding());
        }
        return this.writer;
    }

    @Override
    public void reset() {
        this.cachedContent.reset();
    }

    /**
     * Return the cached response content as a byte array.
     */
    public byte[] getBody() {
        return this.cachedContent.toByteArray();
    }

    private class ResponseServletOutputStream extends ServletOutputStream {

        private final HttpServletResponse response;

        public ResponseServletOutputStream(HttpServletResponse response) {
            this.response = response;
        }

        @Override
        public void write(int b) throws IOException {
            cachedContent.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            cachedContent.write(b, off, len);
        }

        @Override
        public void flush() throws IOException {
            if (!this.response.isCommitted()) {
                ServletOutputStream outputStream = this.response.getOutputStream();
                outputStream.write(cachedContent.toByteArray());
                outputStream.flush();
            }
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener listener) {
            throw new UnsupportedOperationException();
        }
    }

    private class ResponsePrintWriter extends PrintWriter {

        public ResponsePrintWriter(OutputStream outputStream, String characterEncoding) throws UnsupportedEncodingException {
            super(new OutputStreamWriter(outputStream, characterEncoding));
        }

        @Override
        public void write(char[] buf, int off, int len) {
            super.write(buf, off, len);
            super.flush();
        }

        @Override
        public void write(String s, int off, int len) {
            super.write(s, off, len);
            super.flush();
        }

        @Override
        public void write(int c) {
            super.write(c);
            super.flush();
        }
    }
}
