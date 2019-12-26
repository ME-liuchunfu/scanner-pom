package xin.spring.javafx.utils;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 窗体工厂
 * @date 2019/12/27
 */
public class FrameFactory {

    private FrameFactory(){}

    private static final FrameFactory instance = new FrameFactory();

    public static FrameFactory getInstance(){return instance;}

    /**
     * 文件夹选择器
     * @return
     */
    public File chooseFolder(ChooseListiner listiner) {
        Stage fileStage = null;
        DirectoryChooser folderChooser = new DirectoryChooser();
        folderChooser.setTitle("选择文件夹");
        if(listiner != null){
            try {
                listiner.onLoad(folderChooser);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        File selectedFile = folderChooser.showDialog(fileStage);
        return selectedFile;
    }

    /**
     * 初始化选择监听
     */
    public interface ChooseListiner{
        void onLoad(DirectoryChooser folderChooser);
    }
}
