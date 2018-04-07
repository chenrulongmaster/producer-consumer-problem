package com.geekersquare.producerconsumer;

import com.geekersquare.producerconsumer.entity.DataOut;
import org.springframework.stereotype.Component;

/**
 * @author GeekerSquare - 龙叔
 * @version v1.0
 * @email chenrulong0513.master@gmail.com
 * @home www.geekersquare.com
 * @github https://github.com/chenrulongmaster
 * @Description 最后的处理结果
 * @date 2018/4/6 15:22
 */
@Component
public class Pipleline {

    public void out(DataOut dataOut) {
        System.out.println("Pipelined out data - " + dataOut.getId() + ", "
                + dataOut.getName() + ", " + dataOut.getRegisterDate() + ", "
                + dataOut.getGeneratedId() + ", " + dataOut.getIdentify());
    }

}
