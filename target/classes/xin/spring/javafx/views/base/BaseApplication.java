package xin.spring.javafx.views.base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xin.spring.javafx.annotation.Views;

import java.lang.reflect.Field;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description javafx-application基类
 * @date 2019/12/25
 */
public abstract class BaseApplication extends Application {

    /**
     * 当前启动类类型名
     */
    protected String typeName;

    /**
     * 文件夹路径类型如 ： /xin/spring/XXXXX
     */
    protected String clazzPackageFileName;

    /**
     * 当前启动类类型
     */
    protected Class clazz;

    /**
     * 窗体标题
     */
    protected String frameTitle;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.clazz = this.getClass();
        this.typeName = this.clazz.getTypeName();
        this.clazzPackageFileName = "/".concat(this.typeName.replace(".", "/"));
        if(this.clazz.getAnnotation(Views.class) != null) {
            Field[] fields = this.clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(Views.class) != null) {
                    field.setAccessible(true);
                    this.frameTitle = field.get(this).toString();
                    break;
                }
            }
        }
        try {
            Parent root = FXMLLoader.load(this.clazz.getResource(this.clazzPackageFileName + ".fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(this.clazz.getResource(this.clazzPackageFileName + ".css").toExternalForm());
            primaryStage.setTitle(frameTitle != null ? frameTitle : this.clazz.getName());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            // 自定义其他显示样式
            initViews(primaryStage);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 初始化视图
     */
    protected abstract void initViews(Stage primaryStage);

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getClazzPackageFileName() {
        return clazzPackageFileName;
    }

    public void setClazzPackageFileName(String clazzPackageFileName) {
        this.clazzPackageFileName = clazzPackageFileName;
    }
}
