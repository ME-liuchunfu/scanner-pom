package xin.spring.javafx.bean;

import xin.spring.javafx.annotation.database.TableColumn;
import xin.spring.javafx.annotation.database.TableId;
import xin.spring.javafx.annotation.database.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 数据库文件表
 * @date 2019/12/27
 */
@TableName(table = "tb_files")
public class FileTable implements Serializable {

    @TableId(name = "id", comment = "表ID")
    private Long id;

    @TableColumn(name = "url", columnTypeDef = " varchar(255) NOT NULL ", comment = "路径")
    private String url;

    @TableColumn(name = "createtime", columnTypeDef = " datetime DEFAULT NULL ", comment = "入库时间")
    private Date createtime;

    @TableColumn(name = "updatetime", columnTypeDef = " datetime DEFAULT NULL ", comment = "更新时间")
    private Date updatetime;

    @Override
    public String toString() {
        return "FileTable{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }
}
