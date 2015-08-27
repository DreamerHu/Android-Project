package com.comfd.cameraListener.Linstener;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by Admin on 2015/8/26.
 */
public class CamearMonitor {

    public CamearMonitor(){

    }
    private CamearMonitorStateListener stateListener;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0x001){
                if(isOpen){
                    CamearState camearState=isCamearCanUse();
                    judgeAndChangeState(camearState);
                    this.sendEmptyMessageDelayed(0x001,3000);
                }
            }
        }
    };
    ThreadManager threadManager=ThreadManager.getInstance();
    private Camera mCamera;
    //监听器的状态
    private boolean isOpen=false;
    private CamearState mCamearState=CamearState.Close;
    public enum CamearState{
        Open,Close
    }


    /**
     * 判定当前摄像头是否可用
     * @return false证明当前摄像头已经打开为不可用状态，true证明当前摄像头为关闭装填可用
     */
    private CamearState isCamearCanUse(){
        boolean canUse = true;
        Camera mCamera = null;
        try {
            // TODO camera驱动挂掉,处理??
            mCamera = Camera.open();
        } catch (Exception e) {
            canUse = false;
        }
        if (canUse) {
            mCamera.release();
            mCamera = null;
        }
        return canUse?CamearState.Close:CamearState.Open;
    }

    public void  judgeAndChangeState(CamearState camearState){
        if(camearState!=mCamearState){
            mCamearState=camearState;
            stateListener.onCamearMonitorStateChange(mCamearState);
        }
    }

    /**
     * 开启监听器开始监听
     */
    public void openCamearMonitor(){
        if(!isOpen) {
            isOpen = true;

            handler.sendEmptyMessage(0x001);
        }
    }

    /**
     * 关闭监听器
     */
    public void closeCamearMonitor(){
        isOpen=false;
    }

    /**
     * 设置摄像头状态改变的监听器
     * @param stateListener 摄像头的监听接口
     */
    public void setOnCamearMonitorStateListener(CamearMonitorStateListener stateListener){
        this.stateListener=stateListener;
    }

    public interface CamearMonitorStateListener{
        void onCamearMonitorStateChange(CamearState camearState);
    }

}
