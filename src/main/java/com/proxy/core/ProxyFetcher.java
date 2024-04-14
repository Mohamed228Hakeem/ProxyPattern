package com.proxy.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.proxy.db.DatabaseConnection;



public class ProxyFetcher implements BookDao{

	private DatabaseConnection dbConnection;
	private BookDao object ;
	private List<Book> cache; 
	
	public ProxyFetcher (BookDao object,DatabaseConnection db)
	{
		this.object = object;
		this.dbConnection = db;
		this.cache = null ;
		
		 ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	        scheduler.scheduleAtFixedRate(() -> {
				try {
					refreshCache();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, 0, 10, TimeUnit.SECONDS);
	}
	
	@Override
	public List<Book> getAllBooks() throws Exception {
		if(cache == null)
		{
			cache = object.getAllBooks();
			System.out.println("Data Cached Successfully");
		}else {
			System.out.println("Data Fetched from Cache !");

		}
		return cache;
	}

	
	
	// Method to refresh cache
    private void refreshCache() throws Exception {
        System.out.println("Refreshing cache...");
        cache = object.getAllBooks(); // Fetch data from real object and update cache
    }

    // Method to invalidate cache manually
    public void invalidateCache() {
        System.out.println("Invalidating cache...");
        cache = null; // Set cache to null to force refresh on next request
    }
}
