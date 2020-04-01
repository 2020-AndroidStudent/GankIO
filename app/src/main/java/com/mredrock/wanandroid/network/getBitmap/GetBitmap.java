package com.mredrock.wanandroid.network.getBitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mredrock.wanandroid.network.httphelper.CallBack;
import com.mredrock.wanandroid.network.httphelper.NetUtil;
import com.mredrock.wanandroid.network.httphelper.Request;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 行云流水
 * @date 2020/4/1
 * @description
 */
public class GetBitmap {

    //使用静态内部类的单例模式来创建网络请求专用的线程池,线程安全,同时达到了懒加载效果

    private static class Holder{
        private final static GetBitmap INSTANCE = new GetBitmap();

    }

    public static GetBitmap getInstance(){
        return GetBitmap.Holder.INSTANCE;
    }

    /**
     * ThreadPoolExecutor的构造方法中的参数含义
     */
    //核心线程数量,同时能够执行的线程数量
    private int corePoolSize;
    //最大线程的数量,能够容纳的最大排队数
    private int maxPoolSize;
    //正在排队的任务的最长的排队时间
    private long keepAliveTime = 30;
    //正在排队的任务的最长排队时间的单位
    private TimeUnit timeUnit = TimeUnit.MINUTES;
    //线程池对象
    private ThreadPoolExecutor executor;

    //将构造方法设为私有,故无法从其他方式创建对象,构成单例
    private GetBitmap() {
        //获取当前设备可用的核心处理器核心数*2+1,赋值给核心线程的数量,能使性能达到极致
        corePoolSize = Runtime.getRuntime().availableProcessors()*2+1;
        //30应该够......
        maxPoolSize = 30;
        //初始化线程池
        executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                timeUnit,
                //缓冲队列,存放排队的任务,类似栈结构
                new LinkedBlockingDeque<Runnable>(),
                //创建线程的工厂(工厂模式?
                Executors.defaultThreadFactory(),
                //处理那些超出最大线程数量的策略
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    //下面的这个方法的参数我已经去掉,需要自己添加应该需要的参数
    public void execute(final String url, final BitmapCallBack callBack) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //这里就是子线程,可以进行IO操作
                start(url, callBack);
            }
        });
    }

    void start(String path, BitmapCallBack callBack) {
        HttpURLConnection httpURLConnection=null;
        InputStream is=null;
        ByteArrayOutputStream bos=null;

        try {
            URL url = new URL(path);
            httpURLConnection=(HttpURLConnection) url.openConnection();
            //请求方式
            httpURLConnection.setRequestMethod("GET");
            // 读取超时
            httpURLConnection.setConnectTimeout(5000);
            // 连接超时
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();
            //获取响应码
            if(httpURLConnection.getResponseCode()==200) {
                //拿到流
                is = httpURLConnection.getInputStream();
                bos = new ByteArrayOutputStream();
                int count = 0;
                //开辟空间
                byte[] buffer = new byte[1024];
                while ((count = is.read(buffer)) != -1) {
                    bos.write(buffer, 0, count);
                }
                byte[] buff = bos.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(buff, 0, buff.length);
                callBack.onResponse(bitmap);
            }
        } catch (Exception e) {
            callBack.onFailed(e);
        }
    }
}

