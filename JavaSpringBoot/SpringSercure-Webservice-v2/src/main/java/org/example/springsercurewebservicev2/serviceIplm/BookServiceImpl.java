package org.example.springsercurewebservicev2.serviceIplm;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.springsercurewebservicev2.model.Book;
import org.example.springsercurewebservicev2.model.Publisher;
import org.example.springsercurewebservicev2.repository.BookRepository;
import org.example.springsercurewebservicev2.repository.PublisherRepository;
import org.example.springsercurewebservicev2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// the hien tinh chat DI
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
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

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setName(bookDetails.getName());
            book.setPublishers(bookDetails.getPublishers());
            return bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("Book with id " + id + " not found");
        }
    }


    @Override
    @Transactional
    public void removeBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    public void batchInsertBooks(List<Long> publisherIds) {
        Set<Publisher> publishers = new HashSet<>();
        for (Long publisherId : publisherIds) {
            Publisher publisher = entityManager.find(Publisher.class, publisherId);
            if (publisher != null) {
                publishers.add(publisher);
            } else {
                throw new IllegalArgumentException("Publisher with id " + publisherId + " not found");
            }
        }

        for (int i = 0; i < 1000; i++) {
            Book book = new Book();
            book.setName("Book " + i);
            book.setPublishers(publishers);
            entityManager.persist(book);
            // Sau mỗi 50 lần chèn, đẩy thay đổi và giải phóng bộ nhớ
            if (i % 50 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

    }

}
