package xin.spring.javafx.views.index;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import xin.spring.javafx.domain.DataBase;
import xin.spring.javafx.domain.FileTable;
import xin.spring.javafx.enums.DataBaseVersion;
import xin.spring.javafx.logs.Slf4jLog;
import xin.spring.javafx.repositories.FileRepository;
import xin.spring.javafx.session.ApplicationSession;
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

    private List<FileScannerUtil.FileItem> fileItems = new ArrayList<>();

    private FileScannerUtil.FileItem fileItem = null;

    @Override
    protected void beforeDatas() {
        // 先缓存一个默认数据库
        ApplicationSession.getInstance().put(DataBase.class, dataBase);
        ObservableList<DataBaseVersion> options = FXCollections.observableArrayList(
                DataBaseVersion.MYSQL, DataBaseVersion.MARIADB, DataBaseVersion.MYSQL5_INNODB, DataBaseVersion.MYSQL_MYISAM);
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
            // 缓存
            ApplicationSession.getInstance().put(DataBase.class, dataBase);
        });

        // 左边按钮监听
        selectFileButton.setOnAction(event -> {
            baseFile = FrameFactory.getInstance().chooseFolder((folderChooser) ->{
                folderChooser.setTitle("请选择相对应的文件夹");
            });
            log.info("文件夹：{}", baseFile.getAbsolutePath());
            // 开启扫描数据
            ThreadUtil.getInstance().thread(new ThreadUtil.ThreadListener<FileScannerUtil.FileItem>() {
                @Override
                public FileScannerUtil.FileItem onLoad() {
                    new FileScannerUtil(baseFile, (item)->{
                        log.info("个数：" + item.getList().size());
                        fileItem = item;
                    },true);
                    return fileItem;
                }
            },(items)->{
                log.info("子线程成功:" + items);
                //isUpdate = false;
                ThreadUtil.getInstance().runOnUIThread(items, (datas)->{
                    showTreeViewTree(items);
                });
            });

        });

        clearAllButton.setOnAction(event -> {
            treeView.setRoot(null);
        });

        toDbButton.setOnAction(event -> {
            log.info("入库：");
            FileRepository repository = ApplicationSession.getInstance().getComponent(FileRepository.class);
            List<FileTable> list = new ArrayList<>();
            transfor(fileItem, list);
            try{
                repository.saveAll(list);
                AlertBoxFactory.getInstance().display("提示", "入库成功");
            }catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                AlertBoxFactory.getInstance().display("错误提示", "入库失败");
            }
        });
    }

    private void transfor(FileScannerUtil.FileItem item, List<FileTable> list){
        if(!item.getList().isEmpty()){
            for(int i=0;i<item.getList().size();i++){
                FileTable fileTable = new FileTable();
                fileTable.setUrl(item.getList().get(i).getFile().getAbsolutePath().replace(baseFile.getParent(),""));
                list.add(fileTable);
                transfor(item.getList().get(i), list);
            }
        }
    }

    private void showTreeViewTree(FileScannerUtil.FileItem item) {
        TreeItem<String> root = new TreeItem<>(item.getFile().getName());
        treeViewData(root, item.getList());
        treeView.setRoot(root);
    }

    private void treeViewData(TreeItem<String> parentView, List<FileScannerUtil.FileItem> items){
        if(!items.isEmpty()){
            parentView.setExpanded(true);
            for(FileScannerUtil.FileItem i : items){
                TreeItem<String> treeItem = new TreeItem<>(i.getFile().getName());
                treeViewData(treeItem, i.getList());
                parentView.getChildren().add(treeItem);
            }
        }
    }

    private void showTreeView(List<FileScannerUtil.FileItem> items){
        TreeItem<String> root = new TreeItem<>(new FileScannerUtil.FileItem(baseFile).getFileName());
        for(FileScannerUtil.FileItem item : items){
            TreeItem<String> treeItem = new TreeItem<>(item.getFileName());
            root.getChildren().add(treeItem);
        }
        treeView.setRoot(root);
    }
}
