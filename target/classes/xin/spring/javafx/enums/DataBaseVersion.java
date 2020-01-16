package xin.spring.javafx.enums;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 数据库类型
 * @date 2019/12/26
 */
public enum DataBaseVersion {

    MYSQL("Mysql", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/", "org.hibernate.dialect.MySQL5Dialect"),

    MYSQL5_INNODB("Mysql5_InnoDB", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/", "org.hibernate.dialect.MySQL5InnoDBDialect"),

    MYSQL_MYISAM("Mysql5_InnoDB", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/", "org.hibernate.dialect.MySQLMyISAMDialect"),

    MARIADB("MariaDB", "org.mariadb.jdbc.Driver", "jdbc:maria://localhost:3306/", "org.hibernate.dialect.MariaDBDialect");

    private String value;

    private String driver;

    private String url;

    private String dialect;

    private DataBaseVersion(String value, String driver, String url, String dialect){
        this.value = value;
        this.driver = driver;
        this.url = url;
        this.dialect = dialect;
    }

    public String getValue(){return value;}

    public String getDriver(){return driver;}

    public String getUrl(){return url;}

    public String getDialect(){return dialect;}


    @Override
    public String toString() {
        return getValue();
    }
}
