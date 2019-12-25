package xin.spring.javafx.views.index;

import javafx.stage.Stage;
import xin.spring.javafx.logs.Slf4jLog;
import xin.spring.javafx.views.base.BaseApplication;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 主程序入口
 * @date 2019/12/25
 */
public class IndexApplication extends BaseApplication implements Slf4jLog {

    public static void main(String[] args) {
        log.info("{}", "主程序IndexApplication启动");
        launch(args);
        log.info("{}", "程序IndexApplication结束");
    }

    @Override
    protected void initViews(Stage primaryStage) {
        primaryStage.setResizable(true);
    }

}
