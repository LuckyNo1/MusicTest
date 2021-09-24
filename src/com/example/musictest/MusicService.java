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
	
	//ֹͣ�����ڷ������ٵ�ʱ�����
	@Override
	public void onDestroy(){
		super.onDestroy();
		//�رղ�����
		player.stop();
		//�ͷ���Դ,�ͷŵ�player����
		player.release();
		player = null;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}
	//����̳�binder������Ϊ�м���󱻷���
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
	//��������
	public void play(){
		//����
	    player.reset();
		try {    	
		    player = MediaPlayer.create(getApplicationContext(),R.raw.music_love);
	        player.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 //��ͣ����
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
