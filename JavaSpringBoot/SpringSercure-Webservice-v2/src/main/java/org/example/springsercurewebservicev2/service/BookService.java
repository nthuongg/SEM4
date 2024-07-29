package org.example.springsercurewebservicev2.service;

import org.example.springsercurewebservicev2.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book createBook(Book book);
    Optional<Book> getBookById(Long id);    List<Book> getAllBooks();
    void addPublisher(Long bookId,Long publisherId);

    void removePublisher(Long bookId,Long publisherId);

    Book updateBook(Long id, Book bookDetails);

    void removeBook(Long bookId);

    void batchInsertBooks(List<Long> PublisherIds);
}
