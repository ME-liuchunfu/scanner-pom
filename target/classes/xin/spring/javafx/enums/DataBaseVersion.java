package xin.spring.javafx.enums;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 数据库类型
 * @date 2019/12/26
 */
public enum DataBaseVersion {

    MYSQL("Mysql", "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/"),

    MARIADB("MariaDB", "org.mariadb.jdbc.Driver", "jdbc:maria://localhost:3306/");

    private String value;

    private String driver;

    private String url;

    private DataBaseVersion(String value, String driver, String url){
        this.value = value;
        this.driver = driver;
        this.url = url;
    }

    public String getValue(){return value;}

    public String getDriver(){return driver;}

    public String getUrl(){return url;}


    @Override
    public String toString() {
        return getValue();
    }
}
