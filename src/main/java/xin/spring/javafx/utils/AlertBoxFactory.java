package xin.spring.javafx.utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 弹出层工厂
 * @date 2019/12/27
 */
public class AlertBoxFactory {

    private static AlertBoxFactory instance = new AlertBoxFactory();

    private AlertBoxFactory(){}

    public static AlertBoxFactory getInstance(){return instance;}

    public void display(String title , String message){
        this.display(title, message, window -> {
            window.setMinWidth(300);
            window.setMinHeight(150);
            Button button = new Button("点击关闭");
            button.setOnAction(e -> window.close());
            Label label = new Label(message);
            VBox layout = new VBox(10);
            layout.getChildren().addAll(label , button);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
        });
    }

    /**
     * 显示定义
     * @param title
     * @param message
     * @param listener
     */
    public void display(String title , String message, DisplayListener listener){
        Stage window = new Stage();
        window.setTitle(title);
        //modality要使用Modality.APPLICATION_MODEL
        window.initModality(Modality.APPLICATION_MODAL);
        if(listener != null){
            try{
                listener.onLoad(window);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
        window.showAndWait();
    }

    /**
     * 自定义显示监听
     */
    interface DisplayListener{
        void onLoad(Stage window);
    }

}
