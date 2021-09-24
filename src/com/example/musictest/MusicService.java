package com.example.musictest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service{
	private MediaPlayer player;
	
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new zhongjian();
	}
	
	@Override
	public void onCreate(){
		super.onCreate();
		player = new MediaPlayer();
	}
	
	//停止播放在服务销毁的时候调用
	@Override
	public void onDestroy(){
		super.onDestroy();
		//关闭播放器
		player.stop();
		//释放资源,释放掉player对象
		player.release();
		player = null;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}
	//必须继承binder才能作为中间对象被返回
	class zhongjian extends Binder implements mediaInterface{
		public void play(){
			MusicService.this.play();
		}
		public void pause(){
			MusicService.this.pause();
		}
		public void stop() {
			// TODO Auto-generated method stub
			MusicService.this.stop();				
		}
	}
	//播放音乐
	public void play(){
		//重置
	    player.reset();
		try {    	
		    player = MediaPlayer.create(getApplicationContext(),R.raw.music_love);
	        player.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 //暂停播放
    public void pause(){
    	player.pause();
    }
    public void stop(){
		player.stop();
        player.reset();
        player.release();
        player = null;
	}
}
