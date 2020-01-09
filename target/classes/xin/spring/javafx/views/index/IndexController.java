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
import javafx.util.Callback;
import xin.spring.javafx.bean.DataBase;
import xin.spring.javafx.enums.DataBaseVersion;
import xin.spring.javafx.logs.Slf4jLog;
import xin.spring.javafx.utils.*;
import xin.spring.javafx.views.base.AbsInitializable;

import java.io.File;
import java.util.ArrayList;
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

    private File baseFile;

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
            baseFile = FrameFactory.getInstance().chooseFolder((folderChooser) ->{
                folderChooser.setTitle("请选择相对应的文件夹");
            });
            log.info("文件夹：{}", baseFile.getAbsolutePath());
            // 开启扫描数据
            ThreadUtil.getInstance().thread(new ThreadUtil.ThreadListener<List<FileScannerUtil.FileItem>>() {
                @Override
                public List<FileScannerUtil.FileItem> onLoad() {
                    List<FileScannerUtil.FileItem> result = new ArrayList<>();
                    new FileScannerUtil(baseFile, (list)->{
                        System.out.println("个数：" + list.size());
                        result.addAll(list);
                    });
                    return result;
                }
            },(items)->{
                System.out.println("子线程成功:" + items);
                //isUpdate = false;
                ThreadUtil.getInstance().runOnUIThread(items, (datas)->{
                    showTreeView(items);
                });
            });

        });

        clearAllButton.setOnAction(event -> {
            treeView.setRoot(null);
        });

        toDbButton.setOnAction(event -> {
            log.info("入库：");
        });
    }

    private void showTreeView(List<FileScannerUtil.FileItem> items){
        TreeItem<String> root = new TreeItem<>(new FileScannerUtil.FileItem(baseFile).getFileName());
        //root.setExpanded(true);
        for(FileScannerUtil.FileItem item : items){
            TreeItem<String> treeItem = new TreeItem<>(item.getFileName());
            root.getChildren().add(treeItem);
        }
        treeView.setRoot(root);
    }
}
