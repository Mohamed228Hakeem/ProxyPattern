package com.proxy.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proxy.db.DatabaseConnection;
import com.proxy.db.DataAccessException;

public class BookDaoImpl implements BookDao{
	private DatabaseConnection dbConnection;

    public BookDaoImpl(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    

    @Override
    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        
        String sql = "SELECT * FROM inv";
        

        try (Connection connection = dbConnection.getConnection();
        	     PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	     ResultSet rs = preparedStatement.executeQuery()) {

        	    while (rs.next()) {
        	        int id = rs.getInt("id");
        	        String title = rs.getString("title");
        	        String author = rs.getString("author");
        	        // Assuming you have a books list to store Book objects
        	        books.add(new Book(id, title, author));
        	        System.out.println("Data retrieved successfully: " + id + ", " + title + ", " + author);
        	    }
        	} catch (SQLException e) {
        	    // Handle SQLException	
        	    e.printStackTrace(); // This will print the stack trace for debugging
        	    // Wrap the SQLException in a custom unchecked exception
        	    throw new DataAccessException("Error accessing database", e);
        	}
        
        return books;
    }
    
}

