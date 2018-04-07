package com.geekersquare.producerconsumer;

import com.geekersquare.producerconsumer.entity.DataIn;
import com.geekersquare.producerconsumer.entity.DataOut;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author GeekerSquare - 龙叔
 * @version v1.0
 * @email chenrulong0513.master@gmail.com
 * @home www.geekersquare.com
 * @github https://github.com/chenrulongmaster
 * @Description 消费者，假设这里需要有比较多的业务逻辑，目前仅仅为转换以下格式，模拟一个多线程同步处理数据
 * @date 2018/4/6 14:44
 */
@Component
public class Consumer {

    private AtomicLong identify = new AtomicLong(0); // 模拟多线程之间使用原子类，防止数据混乱

    public DataOut consume(DataIn dataIn) throws IOException {
        System.out.println("Consuming data - " + dataIn.getId() + ", " + dataIn.getName() + ", " + dataIn.getRegisterDate());
        DataOut result = new DataOut(dataIn.getId(), dataIn.getName(), dataIn.getRegisterDate());
        result.setGeneratedId(UUID.randomUUID().toString());
        result.setIdentify(identify.incrementAndGet());
        String backupFile = dataIn.getBackupFile();
        // 消费完毕，删除临时文件
        if (new File(backupFile).exists()) {
            FileUtils.forceDelete(new File(backupFile));
        }
        return result;
    }

}
