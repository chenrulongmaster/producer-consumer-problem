package com.geekersquare.producerconsumer;

import com.geekersquare.producerconsumer.entity.DataIn;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * @author GeekerSquare - 龙叔
 * @version v1.0
 * @email chenrulong0513.master@gmail.com
 * @home www.geekersquare.com
 * @github https://github.com/chenrulongmaster
 * @Description 生产者，假设生产者从文件夹中读取数据，真实场景可能是从消息队列读取
 * @date 2018/4/6 14:44
 */
@Component
public class Producer {

    @Value("${producer.infolder}")
    private String inFolder;

    public DataIn produce() throws IOException  {
        DataIn result = null;
        File folder = new File(inFolder);
        // 从文件夹中随机选出一个文件
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null && files.length > 0) {
                // 读取到了一个文件
                File file = files[new Random().nextInt(files.length)];
                String dataText = FileUtils.readFileToString(file, "UTf-8");
                file.delete(); // 消息队列中读取一条数据，游标即转移到下一个
                // 备份文件，消息队列读取结束后，这个消息需要进行本地备份，如果处理失败，可以重新处理
                String backupFilePath = backupFile(dataText);
                // 解析数据
                String[] items = dataText.split(",");
                result = new DataIn(items[0], items[1], items[2], backupFilePath);
                System.out.println("Produced data - " + result.getId() + ", " + result.getName() + ", " + result.getRegisterDate());
            }
        }
        return result;
    }

    /**
     * 备份文件
     * @param dataText
     * @return
     * @throws IOException
     */
    private String backupFile(String dataText) throws  IOException {
        String backupFilePath = "./tmp/" + UUID.randomUUID().toString();
        FileUtils.write(new File(backupFilePath), dataText, "UTF-8");
        return backupFilePath;
    }

}
