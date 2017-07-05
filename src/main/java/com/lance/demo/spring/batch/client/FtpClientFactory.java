package com.lance.demo.spring.batch.client;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

/**
 * Created by perdonare on 2017/6/19.
 */
public class FtpClientFactory {
    FTPClient instance(String address) throws IOException {
        instance(address, 0);
    }

    FTPClient instance(String address, int port) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setAutodetectUTF8(true);
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.connect(address);
        return ftpClient;
    }
}
