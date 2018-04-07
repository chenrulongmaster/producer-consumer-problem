package com.geekersquare.test;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author GeekerSquare - 龙叔
 * @version v1.0
 * @email chenrulong0513.master@gmail.com
 * @home www.geekersquare.com
 * @github https://github.com/chenrulongmaster
 * @Description 产生一些测试的数据
 * @date 2018/4/7 22:18
 */
@Component
@Lazy
public class GenerateTestFiles {

    @Value("${producer.infolder}")
    private String inFolder;

    public void gen() throws IOException  {

        String fileEncoding = "UTF-8";

        File folder = new File(inFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String dataText = "1,lilei,20180301";
        FileUtils.write(new File(inFolder + File.separator + UUID.randomUUID().toString()), dataText, fileEncoding);
        dataText = "2,hanmeimei,20180302";
        FileUtils.write(new File(inFolder + File.separator + UUID.randomUUID().toString()), dataText, fileEncoding);
        dataText = "3,zhangming,20180302";
        FileUtils.write(new File(inFolder + File.separator + UUID.randomUUID().toString()), dataText, fileEncoding);

    }

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        GenerateTestFiles generateTestFiles = ctx.getBean(GenerateTestFiles.class);
        generateTestFiles.gen();
        ctx.close();
    }

}
