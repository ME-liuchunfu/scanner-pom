package xin.spring.javafx.views.index;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import xin.spring.javafx.bean.DataBase;
import xin.spring.javafx.enums.DataBaseVersion;
import xin.spring.javafx.logs.Slf4jLog;
import xin.spring.javafx.utils.AlertBoxFactory;
import xin.spring.javafx.utils.DefaultValueFactory;
import xin.spring.javafx.utils.FileScannerUtil;
import xin.spring.javafx.utils.FrameFactory;
import xin.spring.javafx.views.base.AbsInitializable;

import java.io.File;
import java.util.List;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 主程序动作监听
 * @date 2019/12/25
 */
public class IndexController extends AbsInitializable implements Slf4jLog {

    @FXML private AnchorPane container;
    // 右边
    @FXML private GridPane right;
    @FXML private ComboBox drivers;
    @FXML private TextField databases;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button saveButton;

    // 左边
    @FXML private Pane leftTop;
    @FXML private Label leftTitle;
    @FXML private HBox hBox;
    @FXML private Button selectFileButton;
    @FXML private Button clearAllButton;
    @FXML private Button toDbButton;
    @FXML private TreeView treeView;
    @FXML private ProgressIndicator loading;

    /**
     * 最小值
     */
    private double minValue = 0.0;

    /**
     * 刷新UI中， true： 刷新中， false： 不刷新
     */
    private boolean isUpdate = false;

    /**
     * 默认数据库配置
     */
    private DataBase dataBase = DefaultValueFactory.getInstance().getDataBaseDefault();

    @Override
    protected void beforeDatas() {
        ObservableList<DataBaseVersion> options = FXCollections.observableArrayList(
                DataBaseVersion.MYSQL, DataBaseVersion.MARIADB);
        drivers.getItems().addAll(options);
        setDefaultValue();
    }

    /**
     * 初始化默认配置
     */
    private void setDefaultValue() {
        loading.setVisible(false);
        // 下拉框选中第一项
        drivers.getSelectionModel().selectFirst();
        // 设置各个输入框的值
        databases.setText(dataBase.getDatabases());
        username.setText(dataBase.getUsername());
        password.setText(dataBase.getPassword());

        // 左边数据
        leftTitle.setText("欢迎使用文件检索入库系统");
    }

    @Override
    protected void initListener() {
        // 下拉框监听事件
        drivers.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue)->{
                log.info("observable:{},oldValue:{},newValue:{}", observable,
                        ((DataBaseVersion)oldValue).getDriver(),
                        ((DataBaseVersion)newValue).getDriver());
                dataBase.setDataBaseVersion((DataBaseVersion)newValue);
            });
        saveButton.setOnAction(event -> {
            log.info("事件：{}", event);
            // 更新数据
            dataBase.setUsername(username.getText().trim());
            dataBase.setPassword(password.getText().trim());
            dataBase.setDatabases(databases.getText().trim());
            log.info("更新数据：{}", dataBase);
            AlertBoxFactory.getInstance().display("提示", "更新成功");
        });

        // 左边按钮监听
        selectFileButton.setOnAction(event -> {
            File file = FrameFactory.getInstance().chooseFolder((folderChooser) ->{
                folderChooser.setTitle("请选择相对应的文件夹");
            });
            log.info("文件夹：{}", file.getAbsolutePath());
            // 显示加载窗
//            loading.setProgress(minValue);
//            loading.setVisible(true);
//            isUpdate = true;
//            new Thread(()->{
//                while (isUpdate){
//                    minValue+= 0.00001;
//                    loading.setProgress(minValue);
//                    //log.info(loading.getProgress()+"");
//                    if(minValue>=10)break;
//                }
//                isUpdate = false;
//                if(!isUpdate){
//                    loading.setProgress(10);
//                }
//                loading.setVisible(false);
//                log.info(loading.getProgress()+":大小");
//            }).start();
            // 开启扫描数据
            new FileScannerUtil(file, (list)->{
                System.out.println("个数：" + list.size());
            });
//            new FileScannerUtil(file, new FileScannerUtil.AbsScannerListener(){
//                @Override
//                public void onEach(List<FileScannerUtil.FileItem> fileItems, FileScannerUtil.FileItem fileItem, int index, long size) {
//                    String fileAbsPath = FileScannerUtil.tran2slash(fileItem.getFileAbsPath());
//                    fileAbsPath = fileAbsPath.replaceAll(this.getBasePath(), "");
//                    //System.out.println(fileAbsPath + "=====: 个数：" + size);
//                    //isUpdate = true;
//                    //minValue = 0.0;
//                    System.out.println("个数：" + size);
//                }
//            });
        });
    }
}
