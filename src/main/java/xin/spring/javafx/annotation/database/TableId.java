package xin.spring.javafx.annotation.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 表id
 * @date 2019/12/27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableId {

    /**
     * 字段名
     * @return
     */
    String name() default "";

    /**
     * 字段类型定义
     * @return PRIMARY KEY
     */
    String columnTypeDef() default " bigint(20) NOT NULL AUTO_INCREMENT ";

    /**
     * 定义描述信息
     * @return
     */
    String comment() default " , ";

}
