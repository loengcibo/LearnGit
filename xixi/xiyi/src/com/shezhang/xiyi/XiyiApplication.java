package com.shezhang.xiyi;

import android.app.Application;
import android.util.Log;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class XiyiApplication extends Application {

	public static PushAgent mPushAgent;

	public void onCreate() {
		registerUmeng();
		regiseterBmob();
	}

	/***
	 * ����ע��
	 */
	private void registerUmeng() {
		mPushAgent = PushAgent.getInstance(this);
		// ע�����ͷ���ÿ�ε���register��������ص��ýӿ�
		mPushAgent.register(new IUmengRegisterCallback() {

			@Override
			public void onFailure(String s, String s1) {
				Log.d("lcb", "����ע��ʧ�ܣ�" + s + ",,,," + s1);
			}

			@Override
			public void onSuccess(String deviceToken) {
				Log.d("lcb", "��ǰ�ֻ��������͵��豸�ţ�" + deviceToken);
			}
		});
	}

	/**
	 * ��Ŀ���ע��
	 */
	private void regiseterBmob() {
		BmobConfig config = new BmobConfig.Builder(this)
		// ����appkey
				.setApplicationId(getResources().getString(R.string.BMOB_APPLICATION_ID))
				// ����ʱʱ�䣨��λΪ�룩��Ĭ��15s
				.setConnectTimeout(15)
				// �ļ���Ƭ�ϴ�ʱÿƬ�Ĵ�С����λ�ֽڣ���Ĭ��512*1024
				.setUploadBlockSize(1024 * 1024)
				// �ļ��Ĺ���ʱ��(��λΪ��)��Ĭ��1800s
				.setFileExpiration(2500).build();
		Bmob.initialize(config);
	}
}
