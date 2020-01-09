package xin.spring.javafx.views.base;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 抽象控制器
 * @date 2019/12/25
 */
public abstract class AbsInitializable implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources){
        beforeDatas();
        initListener();
    }

    /**
     * 监听前初始化数据
     */
    protected abstract void beforeDatas();

    /**
     * 监听事件
     */
    protected abstract void initListener();

}
