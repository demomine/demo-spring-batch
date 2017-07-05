package com.lance.demo.spring.batch.tasklet;

import com.lance.demo.spring.batch.client.FileClient;
import com.lance.demo.spring.batch.client.FileClientConfig;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by perdonare on 2017/6/19.
 */
@Service
public class FtpDownloadTasklet implements Tasklet{
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        //获取执行参数
        Map<String, Object> params = chunkContext.getStepContext().getJobParameters();
        FTPClientConfig config = convert(params);
        FTPClient ftp = new FTPClient();
        String address = (String) params.get("address");
        int port = (int) params.get("port");
        //config.setXXX(YYY); // change required options
        // for example config.setServerTimeZoneId("Pacific/Pitcairn")
        //ftp.configure(config );
        boolean error = false;
        try {
            int reply;
            String server = "ftp.example.com";
            ftp.connect(address,port);
            System.out.println("Connected to " + server + ".");
            System.out.print(ftp.getReplyString());

            // After connection attempt, you should check the reply code to verify
            // success.
            reply = ftp.getReplyCode();

            if(!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
            ftp.logout();
        } catch(IOException e) {
            error = true;
            e.printStackTrace();
        } finally {
            if(ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch(IOException ioe) {
                    // do nothing
                }
            }
            System.exit(error ? 1 : 0);
        }
        //确定配置
        //下载文件
        return null;
    }

    private FTPClientConfig convert(Map<String, Object> params) {
        return null;
    }
}
