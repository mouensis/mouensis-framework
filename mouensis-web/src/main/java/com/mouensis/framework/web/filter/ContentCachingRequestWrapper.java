package com.mouensis.framework.web.filter;

import org.springframework.lang.Nullable;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * {@link HttpServletRequest} wrapper that caches all content read from
 * the {@linkplain #getInputStream() input stream} and {@linkplain #getReader() reader},
 * and allows this content to be retrieved via a {@link #getBody()} byte array}.
 *
 * @author zhuyuan
 * @date 2020-12-01 19:41
 */
public class ContentCachingRequestWrapper extends HttpServletRequestWrapper {

    private final ByteArrayOutputStream cachedContent = new ByteArrayOutputStream(0);

    @Nullable
    private ServletInputStream inputStream;

    @Nullable
    private BufferedReader reader;

    private boolean usingInputStream = false;

    /**
     * Create a new ContentCachingRequestWrapper for the given servlet request.
     *
     * @param request the original servlet request
     */
    public ContentCachingRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    private void writeCachedContent(HttpServletRequest request) {
        try (InputStream is = request.getInputStream()) {
            while (is.available() > 0) {
                cachedContent.write(is.read());
            }
        } catch (IOException e) {
            throw new CacheRequestBodyException("Exception in reading request content", e);
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (this.inputStream == null) {
            this.inputStream = new ContentCachingInputStream();
            writeCachedContent((HttpServletRequest) super.getRequest());
        }
        return this.inputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (this.reader == null) {
            this.reader = new BufferedReader(new InputStreamReader(getInputStream(), getCharacterEncoding()));
        }
        return this.reader;
    }

    /**
     * Return the cached request content as a byte array.
     */
    public byte[] getBody() {
        return this.cachedContent.toByteArray();
    }

    private class ContentCachingInputStream extends ServletInputStream {

        private final InputStream delegate;

        public ContentCachingInputStream() {
            this.delegate = new ByteArrayInputStream(cachedContent.toByteArray());
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int read() throws IOException {
            return this.delegate.read();
        }

        @Override
        public int read(byte[] b) throws IOException {
            return this.delegate.read(b);
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            return this.delegate.read(b, off, len);
        }

        @Override
        public int readLine(byte[] b, int off, int len) throws IOException {
            return this.delegate.read(b, off, len);
        }
    }

}
