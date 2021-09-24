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
        //��ϵ���service���������avtivy�رշ���͹ر��ˡ�˳������startService��bindService���ر����Ƚ����ֹͣ
        //�ѷ������ڵĽ��̱�ɷ������
        startService(intent);
        //�õ��м����
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
	//��ʼ���Ű�ť
    public void play(View v){
    	mi.play();
    	condition.setText("����״̬11�����ڲ��š�����");
    }
    
    //��ͣ���Ű�ť
    public void pause(View v){
    	mi.pause();
    	condition.setText("����״̬11����ͣ���š�����");
    }
    
    //��������
    public void stop(View v){
    	mi.stop();
    	condition.setText("����״̬11��ֹͣ���š�����");
    }
    
    //�˳�
    public void quit(View v){
    	unbindService(conn);
    	stopService(intent);
    	finish();
    }


}
