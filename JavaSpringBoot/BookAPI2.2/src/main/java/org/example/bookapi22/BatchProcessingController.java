package org.example.bookapi22;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2.2")
public class BatchProcessingController {
    @Autowired
    private BatchProcessingService batchProcessingService;

    @GetMapping("/batch-insert")
    public String batchInsert() {
        batchProcessingService.batchInsertBooks();
        return "Batch insert successfully !";
    }

    @GetMapping("/books")
    public Page<Book> getBooks(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "20") int size
                               ) {
        return batchProcessingService.findBooks(PageRequest.of(page, size));

    }











//    @GetMapping("/batch-read")
//    public String batchRead(){
//        batchProcessingService.getAllBooks();
//        return "Batch read successfully!";
//    }
//
//
//    @GetMapping("/batch-insert-publisher")
//    public String batchInsertPublisher() {
//        batchProcessingService.batchInsertPublishers();
//        return "Batch insert successfully !";
//    }

}
