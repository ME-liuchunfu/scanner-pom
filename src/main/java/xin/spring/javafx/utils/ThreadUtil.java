package xin.spring.javafx.utils;

import javafx.application.Platform;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 线程工具类
 * @date 2019/12/27
 */
public class ThreadUtil {

    private static final ThreadUtil instance = new ThreadUtil();

    private ThreadUtil(){}

    public static ThreadUtil getInstance(){return instance;}

    /**
     * 创建子线程
     * @param listener
     */
    public <T> void thread(ThreadListener<T> listener, ThreadFinishListener<T> finishListener){
        new Thread(()->{
            try {
                if(listener != null){
                    T t = listener.onLoad();
                    if(finishListener != null){
                        finishListener.onFinish(t);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 线程等待
     * @param timeout
     */
    public void threadWait(long timeout){
        //synchronized (instance) {
            try {
                Thread.currentThread().sleep(timeout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
    }

    /**
     * 主线程更新UI
     * @param finishListener
     * @param <T>
     */
    public <T> void runOnUIThread(AbsThreadFinishListener finishListener){
        Platform.runLater(()->{
            try{
                if(finishListener != null){
                    finishListener.onLoad();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    /**
     * 主线程更新UI
     * @param finishListener
     * @param <T>
     */
    public <T> void runOnUIThread(T t, ThreadFinishListener finishListener){
        Platform.runLater(()->{
            try{
                if(finishListener != null){
                    finishListener.onFinish(t);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    /**
     * 线程监听
     */
    public interface ThreadListener<T>{
        public T onLoad();
    }

    /**
     * 线程处理成功结束后监听
     * @param <T>
     */
    public interface ThreadFinishListener<T>{
        public void onFinish(T t);
    }

    /**
     * 主线程接口回调
     */
    public abstract class AbsThreadFinishListener implements ThreadFinishListener<Object>{
        @Override
        public void onFinish(Object o) {
            onLoad();
        }

        /**
         * 加载
         */
        public abstract void onLoad();
    }

}
