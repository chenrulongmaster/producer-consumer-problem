package com.geekersquare.producerconsumer.entity;

/**
 * @author GeekerSquare - 龙叔
 * @version v1.0
 * @email chenrulong0513.master@gmail.com
 * @home www.geekersquare.com
 * @github https://github.com/chenrulongmaster
 * @Description 输出结果
 * @date 2018/4/7 14:47
 */
public class DataOut {

    private String id;

    private String name;

    private String registerDate;

    private String generatedId;

    private long identify;

    public DataOut(String id, String name, String registerDate) {
        this.id = id;
        this.name = name;
        this.registerDate = registerDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getGeneratedId() {
        return generatedId;
    }

    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }

    public long getIdentify() {
        return identify;
    }

    public void setIdentify(long identify) {
        this.identify = identify;
    }
}
