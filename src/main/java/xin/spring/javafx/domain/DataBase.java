package xin.spring.javafx.domain;

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

    /**数据源**/
    private String providerClass = "org.hibernate.connection.C3P0ConnectionProvider";

    private String minSize = "1";

    private String maxSize = "10";

    private String hbm2ddl = "update";

    private String showSql = "true";

    private String formatSql = "true";

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

    public String getProviderClass() {
        return providerClass;
    }

    public void setProviderClass(String providerClass) {
        this.providerClass = providerClass;
    }

    public String getMinSize() {
        return minSize;
    }

    public void setMinSize(String minSize) {
        this.minSize = minSize;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getHbm2ddl() {
        return hbm2ddl;
    }

    public void setHbm2ddl(String hbm2ddl) {
        this.hbm2ddl = hbm2ddl;
    }

    public String getShowSql() {
        return showSql;
    }

    public void setShowSql(String showSql) {
        this.showSql = showSql;
    }

    public String getFormatSql() {
        return formatSql;
    }

    public void setFormatSql(String formatSql) {
        this.formatSql = formatSql;
    }

    @Override
    public String toString() {
        return "DataBase{" +
                "dataBaseVersion=" + dataBaseVersion +
                ", databases='" + databases + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", oprions='" + oprions + '\'' +
                ", providerClass='" + providerClass + '\'' +
                ", minSize='" + minSize + '\'' +
                ", maxSize='" + maxSize + '\'' +
                ", hbm2ddl='" + hbm2ddl + '\'' +
                ", showSql='" + showSql + '\'' +
                ", formatSql='" + formatSql + '\'' +
                '}';
    }

}
