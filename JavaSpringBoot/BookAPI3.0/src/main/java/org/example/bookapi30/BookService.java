package org.example.bookapi30;

import java.util.List;

public interface BookService {
    Book createBook(Book book);
    Book getBookById(Long id);
    List<Book> getAllBooks();
    void addPublisher(Long bookId,Long publisherId);

    void removePublisher(Long bookId,Long publisherId);
}
