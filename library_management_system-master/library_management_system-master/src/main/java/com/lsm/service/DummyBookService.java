package com.lsm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lsm.model.Book;
import com.lsm.repository.BookRepository;


@Service
public class DummyBookService implements BookRepository{
	
	public List<Book> books =new ArrayList<>(Arrays.asList(
			new Book(1,"book1","book1 title","book1 authur","book1 description"),
			new Book(2,"book2","book2 title","book2 authur","book2 description"),
			new Book(3,"book3","book3 title","book3 authur","book3 description"),
			new Book(4,"book4","book4 title","book4 authur","book4 description"),
			new Book(5,"book5","book5 title","book5 authur","book5 description")
			));

	@Override
	public Book addBook(Book book) {
		if(books.add(book))return book;
		return null;
	}

	@Override
	public List<Book> getAll() {
		return books;
	}

	@Override
	public Book getById(int id) {
		Optional<Book> matchingObject = books.stream().
			    filter(p -> p.getId()==id).
			    findFirst();
		Book book=matchingObject.get();
		if(book!=null)return book;
		return null;
	}

	@Override
	public boolean deleteById(int id) {
		
		return books.removeIf(e -> e.getId()==id);
	}

	@Override
	public boolean update(Book e, int id) {
		Book b=getById(id);
		System.out.println(e);
		
		if(b!=null) {
			b.setId(e.getId());
			b.setName(e.getName());
			b.setAuthor(e.getAuthor());
			b.setDescription(e.getDescription());
			b.setTitle(e.getTitle());
			
			books.set(books.indexOf(b), b);
			System.out.println(b);
			return true;
		}
		return false;
		
		
	}

	

}




































/*@Query("")
 * @Autowired JdbcTemplate jdbcTemplate;
 * 
 * @Override public List<Employee> findAll() { return
 * jdbcTemplate.query("SELECT * FROM tbl_employees", new
 * BeanPropertyRowMapper<Employee>(Employee.class)); }
 * 
 * @Override public Employee findById(int id) { return
 * jdbcTemplate.queryForObject("SELECT * FROM tbl_employees WHERE id=?", new
 * BeanPropertyRowMapper<Employee>(Employee.class), id); }
 * 
 * @Override public int deleteById(int id) { return
 * jdbcTemplate.update("DELETE FROM tbl_employees WHERE id=?", id); }
 * 
 * @Override public int save(Employee e) { return jdbcTemplate.
 * update("INSERT INTO tbl_employees (name, location, department) VALUES (?, ?, ?)"
 * , new Object[] {e.getName(), e.getLocation(), e.getDepartment()}); }
 * 
 * @Override public int update(Employee e, int id) { return jdbcTemplate.
 * update("UPDATE tbl_employees SET name = ?, location = ?, department = ? WHERE id = ?"
 * , new Object[] {e.getName(), e.getLocation(), e.getDepartment(), id}); }
 */