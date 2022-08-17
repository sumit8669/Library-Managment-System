package com.lsm.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsm.helper.Message;
import com.lsm.model.Book;
import com.lsm.model.User;
import com.lsm.service.BookService;
import com.lsm.service.UserService;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {

		String userName = principal.getName();
		System.out.println("c --common data aaded ---" + userName);
		User user = userService.getUserByuserName(userName);
		System.out.println("c --common data aaded ---user added to the model as a common data--" + user);
		model.addAttribute("cUser", user);
	}

	@RequestMapping("/librarian_dashboard")
	public String adminDashBoard(Model model, Principal principal, HttpSession session) {

		String userName = principal.getName();
		System.out.println("current loggedin from Librarian dashboard user----" + userName);
		User user = userService.getUserByuserName(userName);

		session.setAttribute("user", user);
		return "/librarian/librarianDashboard";
	}

	@RequestMapping("/add_book")
	public String addBook(Book book, Model model) {

		model.addAttribute("title", "add book");
		model.addAttribute("book", new Book());
		return "/librarian/addBook";
	}

	@PostMapping("/processform")
	public String processAddbookForm(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model,
			HttpSession session) {

		try {
			if (bindingResult.hasErrors()) {
				model.addAttribute("book", book);
				return "librarian/addBook";
			}
			Book result = bookService.addBook(book);
			model.addAttribute("book", new Book());
			session.setAttribute("message", new Message("Book Successfully Added", "alert-success"));
			return "librarian/addBook";
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message",
					new Message("opps!! Something went wrong" + e.getMessage(), "alert-danger"));
			return "librarian/addBook";
		}
	}

	@RequestMapping("/view_books")
	public String viewBooks(Model model) {

		List<Book> books = bookService.getAll();
		model.addAttribute("books", books);
		return "/librarian/viewBooks";
	}

	@RequestMapping("/view_book/{id}")
	public String viewBook(@PathVariable("id") int id, Model model) {

		Book book = bookService.getById(id);
		model.addAttribute("book", book);

		return "librarian/viewBook";
	}

	@RequestMapping("/update_book/{id}")
	public String updateBook(@PathVariable("id") int id, Model model) {
		Book retriveBook = bookService.getById(id);
		model.addAttribute("book", retriveBook);
		return "/librarian/updateBook";
	}

	@PostMapping("/process_updateform")
	public String processUpdateBookForm(@ModelAttribute("book") Book book, Model model) {

		bookService.update(book, book.getId());
		return "redirect:/librarian/view_books";
	}

	@RequestMapping("/delete_book/{id}")
	public String deletebook(@PathVariable("id") int id, Model model) {

		boolean deleteById = bookService.deleteById(id);

		return "redirect:/librarian/view_books";

	}

	@RequestMapping("/profile")
	public String viewProfile(Model model, Principal principal, HttpSession httpSession) {
		System.out.println("view profile method invoked");
		String userName = principal.getName();
		System.out.println("current loggedin user----" + userName);
		User user = userService.getUserByuserName(userName);

		httpSession.setAttribute("user", user);
		model.addAttribute("user", user);
		model.addAttribute("title", "Admin Profile");
		System.out.println(user);
		return "/librarian/viewProfile";
	}

}
