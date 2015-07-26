package gh.funthomas424242.webapp.books.web;

import gh.funthomas424242.webapp.books.domain.Book;
import gh.funthomas424242.webapp.books.domain.ISBN;
import gh.funthomas424242.webapp.books.domain.InvalidISBNException;
import gh.funthomas424242.webapp.books.service.BookService;
import gh.funthomas424242.webapp.books.service.ISBNService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BookController{

	protected final BookService bookService;

	protected final ISBNService isbnService;

	@Autowired
	public BookController(final BookService bookService,
			final ISBNService isbnService) {
		this.bookService = bookService;
		this.isbnService = isbnService;
	}

	@RequestMapping({ "${link.books}" })
	public ModelAndView listeBuecher() {
		return new ModelAndView("booklist", "books", retrieveAllBooks());
	}

	protected List<Book> retrieveAllBooks() {
		return bookService.findAll();
	}
	
	@RequestMapping({ "${link.books}/json" })
	public List<Book> listeBuecherJSON() {
		return  retrieveAllBooks();
	}

	//@ApiMethod
	@RequestMapping(value="${link.buch.loeschen}/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void loescheBuch(@PathVariable("id") Long id) {
		System.out.println("loeschen aufgerufen");
		System.out.println("ID:"+id);
		bookService.deleteBook(id);
		
		//return listeBuecher();
	}
	
	
	@RequestMapping("${link.buch.erfassen}")
	public ModelAndView erfasseBuch() {
		final Map<String, Object> modelMap = erzeugeModelMap();
		 modelMap.put("message", null);
		 modelMap.put("invalidISBN", false);
		
		return new ModelAndView("erfassebuch",modelMap);
	}

	@RequestMapping("${link.buch.registrieren}")
	public ModelAndView speichereBuch(final HttpServletRequest request,
			@RequestParam("titel") final String titel,
			@RequestParam("isbn") final String isbnraw) {

		ModelAndView nextModelView = null;
		try {
			ISBN isbn = null;

			if (isbnraw.length()>0) {
				isbn = ISBN.parseFromString(isbnraw);
				isbnService.addISBN(isbn);
			}
			bookService.addBook(titel, isbn);
			nextModelView = new ModelAndView("redirect:/books");
		} catch (InvalidISBNException e) {
			final Map<String, Object> modelMap = erzeugeModelMap();
			modelMap.put("message", "Es wurde eine ungültige ISBN eingegeben ("
					+ isbnraw + " ). Bitte korrigieren Sie diese.");
			modelMap.put("titel", titel);
			modelMap.put("isbn", isbnraw);
			modelMap.put("invalidISBN", true);

			nextModelView = new ModelAndView("erfassebuch", modelMap);
		}

		return nextModelView;
	}

	protected Map<String, Object > erzeugeModelMap() {
		final Map<String, Object> modelMap = new HashMap<String, Object>();
		return modelMap;
	}

}
