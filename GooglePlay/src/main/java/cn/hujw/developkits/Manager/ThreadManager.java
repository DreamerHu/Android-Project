package cn.hujw.developkits.Manager;

import android.support.v4.util.TimeUtils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 2015/8/25.
 */
public class ThreadManager {

    //私有化构成方法
    private ThreadManager(){}
    private ThreadPoolProxy shortPool;
    private ThreadPoolProxy longPool;
    private static ThreadManager instance;

    public static ThreadManager getInstance(){
        if(instance==null){
            instance=new ThreadManager();
        }
        return instance;
    }
    public ThreadPoolProxy createShortPool(){
        if(shortPool==null){
            shortPool=new ThreadPoolProxy(3,3,2000L);
        }
        return shortPool;
    }
    public ThreadPoolProxy createLongPool(){
        if(longPool==null){
            longPool=new ThreadPoolProxy(5,5,2000L);
        }
        return longPool;
    }


    public class ThreadPoolProxy{
        private ThreadPoolExecutor poolExecutor;
        private int corePoolSize;
        private int maximumPoolSize;
        private long time;
        public ThreadPoolProxy(int corePoolSize,int maximumPoolSize,long time ){
            this.corePoolSize=corePoolSize;
            this.maximumPoolSize=maximumPoolSize;
            this.time=time;
        }
        public void execute(Runnable runnable){

            if(poolExecutor==null){
                poolExecutor=new ThreadPoolExecutor(corePoolSize,maximumPoolSize,time, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(10));
            }
            poolExecutor.execute(runnable);

        }

        public void cancle(Runnable runnable){
            if(poolExecutor!=null&&!poolExecutor.isShutdown()&&!poolExecutor.isTerminated()){
                poolExecutor.remove(runnable);
            }
        }


    }


}
