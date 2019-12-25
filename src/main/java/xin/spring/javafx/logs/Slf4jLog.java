package xin.spring.javafx.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 日志
 * @date 2019/12/25
 */
public interface Slf4jLog {

    /**
     * 日志对象
     */
    Logger log = LoggerFactory.getLogger(Slf4jLog.class);

}
