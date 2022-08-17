package com.lsm.repository;

import java.util.List;

import com.lsm.model.Book;

public interface BookRepository {

public Book addBook(Book book);
	
    public List<Book> getAll();
	
	public Book getById(int id);
	
	public boolean deleteById(int id);
	
	public boolean update(Book e, int id);

}
