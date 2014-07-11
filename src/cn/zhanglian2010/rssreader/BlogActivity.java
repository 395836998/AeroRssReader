package cn.zhanglian2010.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class BlogActivity extends Activity {

	private WebView v;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blog);
		
		Intent intent = getIntent();  
        String content = intent.getStringExtra("content");  
        
		v = (WebView) findViewById(R.id.blogId);
		v.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
	}

}
