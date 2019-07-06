package com.peitu.commons.download;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载文件的工具类
 *
 * @author Rising
 * @date 2019/7/4
 */
public class DownloadUtil {

    /**
     * 下载本地路径的文件
     *
     * @param fileUrl http://{url}/projectName/{**}/合同文件.doc
     */
    public ResponseEntity<byte[]> downLoadLocalContract(String fileUrl) {
        try {
            File file = new File(fileUrl);
            HttpHeaders headers = new HttpHeaders();
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            //String fileName = "合同文件.doc";
            //为了解决中文名称乱码问题
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            //throw  new RuntimeException("文件流内容读取错误");
        }
        return null;
    }

    /**
     * 远程文件下载
     *
     * @param fileUrl http://{url}/projectName/{**}/合同文件.doc
     */
    public void downLoadCurrentRemoteContract(String fileUrl, HttpServletResponse response) throws IOException {
        int pointIndex = fileUrl.lastIndexOf(".");
        BufferedInputStream bis;
        //如果有找到符号"."，
        if (pointIndex != -1) {
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            URL url = new URL(fileUrl);
            URLConnection conn = url.openConnection();
            // 取数据长度
            int fileSize = conn.getContentLength();
            bis = new BufferedInputStream(conn.getInputStream());
            // 清空response
            response.reset();
            // 文件名称转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
            response.addHeader("Content-Length", "" + fileSize);
            BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            // 从输入流中读入字节流，然后写到文件中
            byte[] buffer = new byte[1024];
            int nRead;
            // bis为网络输入流
            while ((nRead = bis.read(buffer, 0, 1024)) > 0) {
                os.write(buffer, 0, nRead);
            }
            bis.close();
            os.flush();
            os.close();
        }
    }

}
