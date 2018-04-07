package com.geekersquare.producerconsumer.entity;

/**
 * @author GeekerSquare - 龙叔
 * @version v1.0
 * @email chenrulong0513.master@gmail.com
 * @home www.geekersquare.com
 * @github https://github.com/chenrulongmaster
 * @Description 输入数据
 * @date 2018/4/7 14:47
 */
public class DataIn {

    private String id;

    private String name;

    private String registerDate;

    private String backupFile;

    public DataIn(String id, String name, String registerDate, String backupFile) {
        this.id = id;
        this.name = name;
        this.registerDate = registerDate;
        this.backupFile = backupFile;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getBackupFile() {
        return backupFile;
    }
}
