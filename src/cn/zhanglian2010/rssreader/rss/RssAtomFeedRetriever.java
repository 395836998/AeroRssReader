package cn.zhanglian2010.rssreader.rss;


import java.io.IOException;
import java.net.URL;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.FeedFetcher;
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.FetcherException;
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.FeedException;

public class RssAtomFeedRetriever {
	
    public SyndFeed getMostRecentNews( final String feedUrl ) {
        try {
            return retrieveFeed( feedUrl );
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    private SyndFeed retrieveFeed( final String feedUrl ) throws IOException, FeedException, FetcherException {
        FeedFetcher feedFetcher = new HttpURLFeedFetcher();
        return feedFetcher.retrieveFeed( new URL( feedUrl ) );
    }
    
}
