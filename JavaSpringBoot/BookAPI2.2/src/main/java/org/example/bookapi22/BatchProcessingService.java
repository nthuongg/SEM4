package org.example.bookapi22;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BatchProcessingService {
    //Tùy biến JPA bằng cách sử dụng:
    // EntityManager: về nhà đọc thêm cái này, JpaRepository mặc định không hỗ trợ
    // BatchProcessing nêu muốn sử dụng cái này thì cần tùy biến trong application

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Transactional //thực hiện 1 giao dịch kiểu ACID (Đảm bảo toàn bộ quá trình insert diễn ra trong một giao dịch)
    public void batchInsertBooks() {

        Publisher publisher1 = entityManager.find(Publisher.class, 1L);
        Publisher publisher2 = entityManager.find(Publisher.class, 2L);

        Set<Publisher> publishers = new HashSet<>();
        publishers.add(publisher1);
        publishers.add(publisher2);

        for (int i = 0; i< 1000; i++) {
            Book book = new Book();
            book.setName("Book" + i);
            book.setPublishers(publishers);
            entityManager.persist(book);

            // Sau mỗi 50 lần chèn, đẩy thay đổi và giải phóng bộ nhớ
            if ( i % 50 ==0) {
                entityManager.flush();  // Đẩy các thay đổi tới cơ sở dữ liệu
                entityManager.clear();  // Giải phóng bộ nhớ, xóa cache của EntityManager
            }
        }
    }

    @Transactional
    //phân trang
    public Page<Book> findBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Transactional
    public List<BookDTO> findBooksDTO() {
        return entityManager.createQuery("SELECT NEW org.example.bookapi22.BookDTO(b.name, p.name)"+
                " FROM Book b join b.publishers p", BookDTO.class)
                .getResultList();
    }




//    public List<Book> getAllBooks() {
//        // Tạo truy vấn JPQL để lấy tất cả các sách
//        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
//        // Thực hiện truy vấn và lấy kết quả
//        return query.getResultList();
//    }
//
//    @Transactional
//    public void batchInsertPublishers() {
//        for (int i = 0; i < 1000; i++) {
//            Publisher publisher = new Publisher();
//            publisher.setName("Publisher" + i);
//            entityManager.persist(publisher);
//
//            // Sau mỗi 50 lần chèn, đẩy thay đổi và giải phóng bộ nhớ
//            if ( i % 50 ==0) {
//                entityManager.flush();
//                entityManager.clear();
//            }
//        }
//    }

}
