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
	 * 友盟注册
	 */
	private void registerUmeng() {
		mPushAgent = PushAgent.getInstance(this);
		// 注册推送服务，每次调用register方法都会回调该接口
		mPushAgent.register(new IUmengRegisterCallback() {

			@Override
			public void onFailure(String s, String s1) {
				Log.d("lcb", "友盟注册失败：" + s + ",,,," + s1);
			}

			@Override
			public void onSuccess(String deviceToken) {
				Log.d("lcb", "当前手机友盟推送的设备号：" + deviceToken);
			}
		});
	}

	/**
	 * 比目后端注册
	 */
	private void regiseterBmob() {
		BmobConfig config = new BmobConfig.Builder(this)
		// 设置appkey
				.setApplicationId(getResources().getString(R.string.BMOB_APPLICATION_ID))
				// 请求超时时间（单位为秒）：默认15s
				.setConnectTimeout(15)
				// 文件分片上传时每片的大小（单位字节），默认512*1024
				.setUploadBlockSize(1024 * 1024)
				// 文件的过期时间(单位为秒)：默认1800s
				.setFileExpiration(2500).build();
		Bmob.initialize(config);
	}
}
