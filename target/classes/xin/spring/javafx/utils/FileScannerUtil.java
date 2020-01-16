package xin.spring.javafx.utils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 文件扫描
 * @date 2019/12/24
 */
public class FileScannerUtil {

    protected static String basePath;

    public FileScannerUtil(){}

    public FileScannerUtil(String path){
        basePath = tran2slash(path);
    }

    /**
     * 反斜杠转换正斜杠 如 \ 转 /
     * @param path
     * @return
     */
    public static String tran2slash(String path){
        if(path.indexOf("\\\\") != -1){
            return path.replaceAll("\\\\", "/");
        }else if(path.indexOf("\\") != -1){
            return path.replace("\\", "/");
        }else {
            return path;
        }
    }

    public static String getScannerBasePath(){return basePath;}

    public FileScannerUtil(String path, ScannerListener scannerListener){
        this(path);
        scanner(path, scannerListener);
    }

    public FileScannerUtil(String path, ScannerTreeListener scannerTreeListener, boolean tree){
        this(path);
        scannerTree(path, scannerTreeListener);
    }

    public FileScannerUtil(File path, ScannerListener scannerListener){
        this(path.getAbsolutePath(), scannerListener);
    }

    public FileScannerUtil(File path, ScannerTreeListener scannerTreeListener, boolean tree){
        this(path.getAbsolutePath(), scannerTreeListener, tree);
    }

    public List<FileItem> scannerFileInDir(File file, List<FileItem> fileItems){
        if(file.exists() && !file.isHidden()) {
            if (file.isDirectory()) {
                // 拿到当前路径下的文件夹
                File[] files = file.listFiles();
                for (File f : files) {
                    scannerFileInDir(f, fileItems);
                }
            }else {
                fileItems.add(new FileItem(file));
            }
        }
        return fileItems;
    }

    public void scanner(String path, ScannerListener scannerListener){
        ArrayList<FileItem> fileItems = new ArrayList<>();
        File file = new File(path);
        scannerFileInDir(file, fileItems);
        if(scannerListener != null){
            scannerListener.onSuccess(fileItems);
        }
    }

    public void scannerTree(String path, ScannerTreeListener scannerTreeListener){
        FileItem fileItem = new FileItem(new File(path));
        scannerFileInDirTree(fileItem, fileItem.getList());
        if(scannerTreeListener != null){
            scannerTreeListener.onSuccess(fileItem);
        }
    }

    private FileItem scannerFileInDirTree(FileItem fileItem, List<FileItem> list) {
        File file = fileItem.getFile();
        if(file.exists() && !file.isHidden()) {
            if (file.isDirectory()) {
                // 拿到当前路径下的文件夹
                File[] files = file.listFiles();
                for (File f : files) {
                    FileItem temp = new FileItem(f);
                    list.add(temp);
                    scannerFileInDirTree(temp, temp.getList());
                }
            }
//            else {
//                list.add(new FileItem(file));
//            }
        }
        return fileItem;
    }

//    public FileItem treeFileItem(List<FileItem> items){
//        FileItem fileItem = new FileItem();
//        if(items != null && items.size() > 0){
//            for(FileItem item : items){
//                if(){
//
//                }
//            }
//        }
//        return fileItem;
//    }


    public static void main(String[] args) {
        /*new FileScannerUtil("F:\\go语言与区块链精品入门课程视频", new AbsScannerListener(){
            @Override
            public void onEach(List<FileItem> fileItems, FileItem fileItem, int index, long size) {
                String fileAbsPath = tran2slash(fileItem.getFileAbsPath());
                fileAbsPath = fileAbsPath.replaceAll(this.getBasePath(), "");
                System.out.println(fileAbsPath + "=====: 个数：" + size);
            }
        });*/
        new FileScannerUtil("/Users/mac/Desktop/WanAndroid", (item)->{
            System.out.println(item);
//            while (!item.getList().isEmpty()){
//                System.out.println(item.fileAbsPath);
//            }
        },true);
    }

    /**
     *  扫描成功监听
     */
    public interface ScannerListener{
        public void onSuccess(List<FileItem> fileItems);
    }

    /**
     *  扫描tree成功监听
     */
    public interface ScannerTreeListener{
        public void onSuccess(FileItem fileItem);
    }

    /**
     *  扫描成功抽象监听
     */
    public abstract static class AbsScannerListener implements ScannerListener{

        protected String basePath;

        public String getBasePath(){return basePath;}

        @Override
        public void onSuccess(List<FileItem> fileItems){
            basePath = getScannerBasePath();
            try{
                if(fileItems != null && fileItems.size() > 0){
                    for(int i=0; i<fileItems.size(); i++){
                        onEach(fileItems, fileItems.get(i), i, fileItems.size());
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        /**
         * 循环
         * @param fileItems
         * @param fileItem
         * @param index
         * @param size
         */
        public abstract void onEach(List<FileItem> fileItems, FileItem fileItem, int index, long size);

    }

    /**
     * 文件实体
     */
    public static class FileItem implements Serializable {

        private String fileName;

        private String fileAbsPath;

        private File file;

        private List<FileItem> list = new ArrayList<>();

        public FileItem(){}

        public FileItem(File file){
            this.file = file;
            this.fileName = this.file.getName();
            this.fileAbsPath = this.file.getAbsolutePath();
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setFileAbsPath(String fileAbsPath) {
            this.fileAbsPath = fileAbsPath;
        }

        public List<FileItem> getList() {
            return list;
        }

        public void setList(List<FileItem> list) {
            this.list = list;
        }

        public void setFile(File file){
            this.file = file;
        }

        public File getFile(){return this.file;}

        public String getFileName(){return getFile().getName();}

        public String getFileAbsPath(){return getFile().getAbsolutePath();}

        public boolean isHident(){return getFile().isHidden();}

        public boolean isFile(){return getFile().isFile();}

        @Override
        public String toString() {
            return "FileItem{" +
                    "fileName='" + fileName + '\'' +
                    ", fileAbsPath='" + fileAbsPath + '\'' +
                    ", file=" + file +
                    ", list=" + list +
                    '}';
        }
    }

}
