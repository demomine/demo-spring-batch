package com.lance.demo.spring.batch.client;

import java.io.File;

/**
 * Created by perdonare on 2017/6/19.
 */
public interface FileClient<T> {
    T connect(FileClientConfig fileClientConfig);

    File downloadFile(T t);

    void disconnect(T t);
}
