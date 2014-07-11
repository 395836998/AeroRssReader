package cn.zhanglian2010.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	public final static int MENU_EXIT = 0;
	
	private Button button1;
	private Button button2;
	private Button button3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1 = (Button) findViewById(R.id.rssZhangId);
		button1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				loadBlogList("http://www.zhanglian2010.cn/feed/");
			}
		});
		
		button2 = (Button) findViewById(R.id.rssYuanId);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				loadBlogList("http://www.yuanchunyang.cn/feed/");
			}
		});
		
		button3 = (Button) findViewById(R.id.rssCoolId);
		button3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				loadBlogList("http://coolshell.cn/feed/");
			}
		});
		
	}
	
	private void loadBlogList(String url){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, BlogListActivity.class);
		intent.putExtra("url", url);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		menu.add(0, MENU_EXIT, 0, R.string.app_exit)
		.setIcon(android.R.drawable.ic_media_previous);
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (item.getItemId() == MENU_EXIT) {
			this.finish();
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
