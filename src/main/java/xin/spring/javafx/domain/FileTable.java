package xin.spring.javafx.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import xin.spring.javafx.annotation.database.TableColumn;
import xin.spring.javafx.annotation.database.TableId;
import xin.spring.javafx.annotation.database.TableName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 数据库文件表
 * @date 2019/12/27
 */
@Entity
@Table(name = "tb_files_up")
@TableName(table = "tb_files_up")
public class FileTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(name = "id", comment = "表ID")
    private Long id;

    @Column(name = "url", columnDefinition = " text COMMENT '存储路径' ")
    @TableColumn(name = "url", columnTypeDef = " varchar(255) NOT NULL ", comment = "路径")
    private String url;

    @CreationTimestamp
    @Column(name = "createtime", columnDefinition = " datetime COMMENT '入库创建时间' ")
    @TableColumn(name = "createtime", columnTypeDef = " datetime DEFAULT NULL ", comment = "入库时间")
    private Date createtime;

    //@CreationTimestamp
    @UpdateTimestamp
    @Column(name = "updatetime", columnDefinition = " datetime COMMENT '更新时间' ")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
