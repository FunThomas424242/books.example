package gh.funthomas424242.webapp.books.service;

/*
 * #%L
 * Books.App
 * %%
 * Copyright (C) 2015 Pivotal Software, Inc.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import gh.funthomas424242.webapp.books.domain.Book;
import gh.funthomas424242.webapp.books.domain.ISBN;
import gh.funthomas424242.webapp.books.infrastructure.BookRepository;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class BookService {

	protected final BookRepository bookRepository;

	@Inject
	public BookService(final BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> findAll() {
		return this.bookRepository.findAll();
	}

	public void addBook(final String titel, final ISBN isbn) {
		final Book book = new Book(titel, isbn);
		this.bookRepository.save(book);
	}
	
	public void deleteBook(final Long id){
		this.bookRepository.delete(id);
	}

	public void deleteAll(){
		this.bookRepository.deleteAll();
	}
	
}
