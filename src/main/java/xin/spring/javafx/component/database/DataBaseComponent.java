package xin.spring.javafx.component.database;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 数据库组件
 * @date 2019/12/27
 */
public class DataBaseComponent {

    /**
     *
     * @param tableName
     * @return
     */
    public boolean isExistOfTableName(String tableName)
    {
        Connection conn = null;
        ResultSet tabs = null;
        try {
            //conn = jdbcTemplate.getDataSource().getConnection();
            java.sql.DatabaseMetaData dbMetaData = conn.getMetaData();
            String[] types = { "TABLE" };
            tabs = dbMetaData.getTables("portals", null, tableName, types);
            if (tabs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                tabs.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
