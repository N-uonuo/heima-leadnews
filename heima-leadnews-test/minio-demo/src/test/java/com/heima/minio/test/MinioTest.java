package com.heima.minio.test;

import com.heima.file.service.FileStorageService;
import com.heima.minio.MinIOApplication;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest(classes = MinIOApplication.class)//启动整个springboot工程
@RunWith(SpringRunner.class)//底层用junit
public class MinioTest {

    @Autowired
    private FileStorageService fileStorageService;

    /*把list.html文件上传到minio中，并且可以在浏览器中访问*/
    @Test
    public void test() throws FileNotFoundException {
        //创建一个新的字节输入流
        FileInputStream fileInputSteam = new FileInputStream("D:\\heima_leadnews_resource\\DataTest\\list.html");

        //上传
        String path = fileStorageService.uploadHtmlFile("", "list.html", fileInputSteam);

        //访问路径
        System.out.println(path);

        //关闭流
        try {
            fileInputSteam.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //把list.html文件上传到minio中，并且可以在浏览器中访问
    public static void main(String[] args) {


        try {
            //创建一个新的字节输入流
            FileInputStream fileInputSteam = new FileInputStream("D:\\heima_leadnews_resource\\DataTest\\index.js");

            //1.获取minio的链接信息，创建一个minio的客户端
            MinioClient minioClient = MinioClient.builder()
                    .credentials("minio", "minio123")
                    .endpoint("http://192.168.75.135:9000")
                    .build();

            //上传
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("plugins/js/index.js")//文件名
                    .contentType("text/js")//文件类型
                    .bucket("leadnews")//桶名 与minio管理界面中的桶名一致
                    .stream(fileInputSteam, fileInputSteam.available(), -1)//这里是文件流，文件大小，文件流的结束位置
                    .build(); //文件流
            minioClient.putObject(putObjectArgs);


            //访问路径
            System.out.println("http://192.168.75.135:9000/leadnews/plugins/js/index.js");



        } catch (Exception e) {
            throw new RuntimeException(e);
        }




    }
}
