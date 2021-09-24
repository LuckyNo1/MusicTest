package com.example.musictest;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	mediaInterface mi;
	private mediaServiceConn conn;
	private Intent intent;
	private TextView condition;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		condition=(TextView) findViewById(R.id.music_condition);
		intent = new Intent(this, com.example.musictest.MusicService.class);
        conn = new mediaServiceConn();
        //混合调用service，避免服务avtivy关闭服务就关闭了。顺序是先startService再bindService，关闭是先解绑再停止
        //把服务所在的进程变成服务进程
        startService(intent);
        //拿到中间对象
        bindService(intent, conn, BIND_AUTO_CREATE);
	}

	class mediaServiceConn implements ServiceConnection{
		 
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mi = (mediaInterface) service;
		}
 
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
	//开始播放按钮
    public void play(View v){
    	mi.play();
    	condition.setText("播放状态11：正在播放。。。");
    }
    
    //暂停播放按钮
    public void pause(View v){
    	mi.pause();
    	condition.setText("播放状态11：暂停播放。。。");
    }
    
    //继续播放
    public void stop(View v){
    	mi.stop();
    	condition.setText("播放状态11：停止播放。。。");
    }
    
    //退出
    public void quit(View v){
    	unbindService(conn);
    	stopService(intent);
    	finish();
    }


}
