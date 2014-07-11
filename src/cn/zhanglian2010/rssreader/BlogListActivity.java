package cn.zhanglian2010.rssreader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import cn.zhanglian2010.rssreader.rss.RssBlogBean;
import cn.zhanglian2010.rssreader.rss.RssReader;

public class BlogListActivity extends ListActivity {

	private List<RssBlogBean> itemList;
	private static SimpleDateFormat df = new SimpleDateFormat("MM-dd");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blog_list);
		
		Intent t = getIntent();
		final String url = t.getStringExtra("url");
		
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				RssReader reader = new RssReader();
				List<RssBlogBean> list = reader.getAllRssItemBeanList(url);
				Message msg = handler.obtainMessage();
				msg.obj = list;
				handler.sendMessage(msg);
			}
			
		}).start();
		
	}

	private Handler handler = new Handler(){
		
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			itemList = (List<RssBlogBean>)msg.obj;
			
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			for(RssBlogBean item : itemList){
				HashMap<String, String> map1 = new HashMap<String, String>();
				map1.put("title", item.getTitle());
				map1.put("date", df.format(item.getPubDate()));
				list.add(map1);
			}
			
			SimpleAdapter adapter = new SimpleAdapter(BlogListActivity.this, list, R.layout.blog_item, 
					new String[]{"title", "date"}, new int[]{R.id.blog_title,R.id.blog_date});
			setListAdapter(adapter);
		}
	};
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		RssBlogBean item = itemList.get(position);
		
		Intent t = new Intent();
		t.setClass(BlogListActivity.this, BlogActivity.class);
		t.putExtra("content", item.getContent());
		
		startActivity(t);
	}
	

}
