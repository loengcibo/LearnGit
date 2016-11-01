package com.shezhang.xiyi;

import android.app.Activity;
import android.os.Bundle;

import com.umeng.message.PushAgent;

public class XiyiBaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// 统计应用启动数据（友盟推送）
		PushAgent.getInstance(this).onAppStart();
	}
}
