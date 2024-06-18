package org.example.bookmanagement;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/libraries")
public class LibraryController {
    //Call instance of model
    private final LibraryRepository libraryRepository;

    private final BookRepository bookRepository;

    @Autowired
    public LibraryController(LibraryRepository libraryRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Library> createLibrary(@Valid @RequestBody Library library) {
        Library savedLibrary = libraryRepository.save(library);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedLibrary.getId()).toUri();

        return ResponseEntity.created(location).body(savedLibrary);
    }

    @GetMapping("/")
    public ResponseEntity<Page<Library>>GetAll(Pageable pageable) {
        return ResponseEntity.ok(libraryRepository.findAll(pageable));
    }

    //edit
    @PutMapping("/{id}")
    public ResponseEntity<Library> update(@PathVariable Integer id, @Valid @RequestBody Library library) {
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        library.setId(optionalLibrary.get().getId());
        libraryRepository.save(library);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Library> deleteLibrary(@PathVariable Integer id) {
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        if (!optionalLibrary.isPresent()) {  //
            return ResponseEntity.unprocessableEntity().build();
        }
        deleteLibraryTransaction(optionalLibrary.get());
        return ResponseEntity.noContent().build();
    }

    @Transactional //Đảm bảo tính toàn vẹn của giao dịch ACID
    public void deleteLibraryTransaction(Library library) {
        bookRepository.deleteByLibraryId(library.getId());
        libraryRepository.delete(library);
    }

    @GetMapping("/{id}")
    ResponseEntity<Library> getById(@PathVariable Integer id) {
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalLibrary.get());
    }
}
