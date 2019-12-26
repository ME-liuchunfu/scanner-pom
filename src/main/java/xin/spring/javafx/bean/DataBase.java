package xin.spring.javafx.bean;

import xin.spring.javafx.enums.DataBaseVersion;

import java.io.Serializable;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 数据库版本类
 * @date 2019/12/26
 */
public class DataBase implements Serializable {

    private DataBaseVersion dataBaseVersion;

    private String databases;

    private String username;

    private String password;

    private String oprions;

    public DataBaseVersion getDataBaseVersion() {
        return dataBaseVersion;
    }

    public void setDataBaseVersion(DataBaseVersion dataBaseVersion) {
        this.dataBaseVersion = dataBaseVersion;
    }

    public String getDatabases() {
        return databases;
    }

    public void setDatabases(String databases) {
        this.databases = databases;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOprions() {
        return oprions;
    }

    public void setOprions(String oprions) {
        this.oprions = oprions;
    }

    @Override
    public String toString() {
        return "DataBase{" +
                "dataBaseVersion=" + dataBaseVersion +
                ", databases='" + databases + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", oprions='" + oprions + '\'' +
                '}';
    }

}
