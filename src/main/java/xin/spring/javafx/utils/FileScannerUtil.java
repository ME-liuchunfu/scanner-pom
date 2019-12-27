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

    public FileScannerUtil(File path, ScannerListener scannerListener){
        this(path.getAbsolutePath(), scannerListener);
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
        new FileScannerUtil("F:\\go语言与区块链精品入门课程视频", new AbsScannerListener(){
            @Override
            public void onEach(List<FileItem> fileItems, FileItem fileItem, int index, long size) {
                String fileAbsPath = tran2slash(fileItem.getFileAbsPath());
                fileAbsPath = fileAbsPath.replaceAll(this.getBasePath(), "");
                System.out.println(fileAbsPath + "=====: 个数：" + size);
            }
        });

    }

    /**
     *  扫描成功监听
     */
    public interface ScannerListener{
        public void onSuccess(List<FileItem> fileItems);
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

        public FileItem(){}

        public FileItem(File file){
            this.file = file;
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
                    "fileName='" + file.getName() + '\'' +
                    ", fileAbsPath='" + file.getAbsolutePath() + '\'' +
                    ", file=" + file +
                    '}';
        }
    }

}
