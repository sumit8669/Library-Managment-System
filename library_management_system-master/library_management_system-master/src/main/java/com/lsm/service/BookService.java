package com.lsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lsm.model.Book;
import com.lsm.repository.BookRepository;

@Service
public class BookService implements BookRepository{
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String ADD_BOOK = "INSERT INTO book (name,title, author,description) VALUES (?,?,?,?)";
	private static final String GET_ALL_BOOK = "SELECT * FROM book";
	private static final String GET_BOOK_BY_ID = "SELECT * FROM book WHERE id=?";
	private static final String DELETE_BOOK_BY_ID = "DELETE FROM book WHERE id=?";
	private static final String UPDATE_BOOK = "UPDATE book SET name = ?, title = ?, author = ?,description=? WHERE id = ?";

	@Override
	public Book addBook(Book book) {
		int update = jdbcTemplate.update(ADD_BOOK, new Object[] {  
				book.getName(),
				book.getTitle(),
				book.getAuthor(),
				book.getDescription()
			  });
			if(update == 1) {
			System.out.println("book inserted ..");
			return book;
			}
			return book;
	}

	@Override
	public List<Book> getAll() {
		List<Book> books = jdbcTemplate.query(GET_ALL_BOOK,
	            new BeanPropertyRowMapper<Book>(Book.class));
           return books;
	}

	@Override
	public Book getById(int id) {
		Book retriveUser = jdbcTemplate.queryForObject(GET_BOOK_BY_ID, 
                new BeanPropertyRowMapper<Book>(Book.class), id);
                return retriveUser;
	}

	@Override
	public boolean deleteById(int id) {
		int update = jdbcTemplate.update(DELETE_BOOK_BY_ID, id);
		if(update==1)return true;
		return false;
	}

	@Override
	public boolean update(Book e, int id) {
		int update = jdbcTemplate.update(UPDATE_BOOK, new Object[] {
                e.getName(), e.getTitle(), e.getAuthor(),e.getDescription(), id});
				if(update==1)return true;
				return false;
	}

}
