package org.example.bookapi30;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// the hien tinh chat DI
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    @Transactional
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void addPublisher(Long bookId, Long publisherId) {
        Book book = bookRepository.findById(bookId).orElse(null); // orElse: thay cho if else lam ngan code hon

        Publisher publisher = publisherRepository.findById(publisherId).orElse(null);

        book.getPublishers().add(publisher);  //Set<T>
        bookRepository.save(book);

    }

    @Override
    public void removePublisher(Long bookId, Long publisherId) {
        Book book = bookRepository.findById(bookId).orElse(null); // orElse: thay cho if else lam ngan code hon

        Publisher publisher = publisherRepository.findById(publisherId).orElse(null);

        book.getPublishers().remove(publisher);  //Set<T>
        bookRepository.save(book);

    }
}
