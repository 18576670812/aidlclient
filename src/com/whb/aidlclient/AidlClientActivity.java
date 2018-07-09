package com.whb.aidlclient;

import com.whb.aidlremoteservice.aidl.BookQuery;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AidlClientActivity extends Activity {
	private final String TAG = "AidlClientActivity";
	private static EditText mInput = null;
	private Button mQueryByIdBT = null;
	private Button mQueryByNameBT = null;
	private Button mQueryByAuthorBT = null;
	private View.OnClickListener listener = null;
	private BookQuery mQueryBinder = null;
	private boolean isBind = false;
	private Context mContext = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aidl_client);
		
		mContext = this;
		
		Log.d(TAG, "onCreate()...");
		
		Resources r =getResources();
		String mQueryByIdRes = r.getString(R.string.querybyid);
		
		listener = new ButtonOnClickListener();
		initialComponents();
		setButtonOnClickListener(listener);
		
		isBind = bindQueryService(mServiceConnection);
		//startQueryService();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "onStop()...");
		if(isBind) {
			unbindQueryService(mServiceConnection);
			isBind = false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		Log.d(TAG, "onCreateOptionsMenu()...");
		getMenuInflater().inflate(R.menu.activity_aidl_client, menu);
		return true;
	}
	
	void initialComponents() {
		Log.d(TAG, "initialComponents()...");
		
		mInput = (EditText)findViewById(R.id.inputedit);
		mQueryByIdBT = (Button)findViewById(R.id.bquerybyid);
		mQueryByNameBT = (Button)findViewById(R.id.bquerybyname);
		mQueryByAuthorBT = (Button)findViewById(R.id.bquerybyauthor);
	}
	
	void setButtonOnClickListener(View.OnClickListener listener) {
		Log.d(TAG, "setButtonOnClickListener()...");
		if(listener != null){
			if(mQueryByIdBT != null) {
				mQueryByIdBT.setOnClickListener(listener);
			}
			if(mQueryByNameBT != null) {
				mQueryByNameBT.setOnClickListener(listener);
			}
			if(mQueryByAuthorBT != null) {
				mQueryByAuthorBT.setOnClickListener(listener);
			}
		}
	}
	
	boolean bindQueryService(ServiceConnection conn) {
		Log.d(TAG, "bindToQueryService()... conn: " + conn);
		
		//Intent intent = new Intent("com.whb.aidlremoteservice.QUERYBOOK");
		
		Intent intent = new Intent();
		intent.setClassName("com.whb.aidlremoteservice", "com.whb.aidlremoteservice.BookQueryService");
		
		if(conn != null) {
			return bindService(intent, conn, BIND_AUTO_CREATE);
		}
		
		return false;
	}
	
	void unbindQueryService(ServiceConnection conn) {
		Log.d(TAG, "unbindQueryService()... conn: " + conn);
		
		if(conn != null) {
			unbindService(conn);
		}
	}
	
	private class ButtonOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onClick()... View ID: " + v.getId());
			if(v != null) {
				switch(v.getId()) {
				case R.id.bquerybyid:
					if(mInput != null) {
						String id = mInput.getText().toString();
						if(!id.equals("")){
							Bundle bundle = queryById(id);
							if(bundle != null) {
								Toast.makeText(mContext, "_id: " + bundle.getString("BOOKID")
										+ " | name: " + bundle.getString("BOOKNAME")
										+ " | author: " + bundle.getString("BOOKAUTHOR"), 
										Toast.LENGTH_LONG).show();
							} else {
								Log.w(TAG, "there is no any book info.");
							}
						} else {
							Log.e(TAG, "paramenters error, id is empty!!!");
						}
					}
					break;
					
				case R.id.bquerybyname:
					if(mInput != null) {
						String name = mInput.getText().toString();
						if(name != null && !TextUtils.isEmpty(name)){
							Bundle[] bundle = new Bundle[2];
							bundle = queryByName(name);
							showBundle(bundle);
						} else {
							Log.e(TAG, "paramenters error, name is empty!!!");
						}
					}
					break;
					
				case R.id.bquerybyauthor:
					if(mInput != null) {
						String author = mInput.getText().toString();
						if(author != null && !TextUtils.isEmpty(author)){
							Bundle[] bundle = new Bundle[2];
							bundle = queryByAuthor(author);
							showBundle(bundle);
						} else {
							Log.e(TAG, "paramenters error, author is empty!!!");
						}
					}
					break;
					
				default:
					break;
				}
			}
		}
	}
	
	private void showBundle(Bundle[] bundle) {
		Log.d(TAG, "showBundle()... bundle: " + bundle);
		
		if(bundle != null) {
			int len = bundle.length;
			int index = 0;
			StringBuilder sb = new StringBuilder();
			
			while(index > len) {
				sb.append("_id: " + bundle[index].getString("BOOKID"));
				sb.append(" | name: " + bundle[index].getString("BOOKNAME"));
				sb.append(" | author: " + bundle[index].getString("BOOKAUTHOR") + "\n");
				index++;
			}
			
			Toast.makeText(mContext, sb.toString(), Toast.LENGTH_LONG).show();
		} else {
			Log.d(TAG, "there is no any book info.");
		}
	}
	
	private ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onServiceConnected()...");
			mQueryBinder = (BookQuery)BookQuery.Stub.asInterface(arg1);
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onServiceDisconnected()...");
			mQueryBinder = null;
		}
	};
	
	void startQueryService() {
		Log.d(TAG, "startQueryService()...");
		
		Intent intent = new Intent();
		intent.setClassName("com.whb.aidlremoteservice", "com.whb.aidlremoteservice.BookQueryService");
		
		startService(intent);
	}

	Bundle queryById(String id) {
		Log.d(TAG, "queryById()... id: " + id);
		
		Bundle bundle = new Bundle();
		if(id != null && mQueryBinder != null){
			try {
				bundle = mQueryBinder.queryBookInfoById(id);
				return bundle;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	Bundle[] queryByAuthor(String author) {
		Log.d(TAG, "queryByAuthor()... author: " + author);
		
		if(author != null && mQueryBinder != null){
			try {
				Log.d(TAG, "return bundle: " + mQueryBinder.queryBookInfoByAuthor(author));
				//return mQueryBinder.queryBookInfoByAuthor(author);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	Bundle[] queryByName(String name) {
		Log.d(TAG, "queryByName()... name: " + name);
		
		if(name != null && mQueryBinder != null){
			try {
				return mQueryBinder.queryBookInfoByName(name);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
