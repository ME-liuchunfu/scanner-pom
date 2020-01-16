package xin.spring.javafx.utils;

import xin.spring.javafx.domain.DataBase;
import xin.spring.javafx.enums.DataBaseVersion;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 默认值工厂
 * @date 2019/12/26
 */
public class DefaultValueFactory {

    private static final DefaultValueFactory instance = new DefaultValueFactory();

    private DefaultValueFactory(){}

    public static DefaultValueFactory getInstance(){return instance;}

    /**
     * 获取数据库配置默认值
     * @return
     */
    public DataBase getDataBaseDefault(){
        DataBase dataBase = new DataBase();
        dataBase.setDatabases("test");
        dataBase.setDataBaseVersion(DataBaseVersion.MYSQL);
        dataBase.setUsername("root");
        dataBase.setPassword("123456");
        dataBase.setOprions("useUnicode=true&characterEncoding=UTF-8");
        return dataBase;
    }

}
