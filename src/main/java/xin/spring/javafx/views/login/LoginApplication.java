package xin.spring.javafx.views.login;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import xin.spring.javafx.views.base.BaseApplication;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description denglu
 * @date 2019/12/26
 */
public class LoginApplication extends BaseApplication {
    @Override
    protected void initViews(Stage primaryStage) {
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/earth.jpg")));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
