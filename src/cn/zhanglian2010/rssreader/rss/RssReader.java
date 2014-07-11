package cn.zhanglian2010.rssreader.rss;

import java.util.ArrayList;
import java.util.List;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndContent;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;

public class RssReader {

	public List<RssBlogBean> getAllRssItemBeanList(String rssUrl){  
		
        RssAtomFeedRetriever feedRetriever = new RssAtomFeedRetriever();
        SyndFeed feed = feedRetriever.getMostRecentNews( rssUrl );
        
        @SuppressWarnings("unchecked")
		List<SyndEntry> entrys = feed.getEntries();
        List<RssBlogBean> rssItemBeans = new ArrayList<RssBlogBean>();  

        for(SyndEntry entry : entrys){  
        	RssBlogBean item = new RssBlogBean();  

        	item.setTitle(entry.getTitle().trim());  
        	item.setType(feed.getTitleEx().getValue().trim());  
        	item.setUri(entry.getUri());    
        	item.setPubDate(entry.getPublishedDate());  
        	item.setAuthor(entry.getAuthor());  
        	
        	SyndContent o = (SyndContent)entry.getContents().get(0);
			item.setContent(o.getValue());

        	rssItemBeans.add(item);  
        }  
        return rssItemBeans;  
	}  

}
