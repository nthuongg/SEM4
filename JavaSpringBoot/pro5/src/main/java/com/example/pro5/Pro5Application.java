package com.example.pro5;

import com.example.pro5.jpa.Book;
import com.example.pro5.jpa.BookRepository;
import com.example.pro5.jpa.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Slf4j
@SpringBootApplication
public class Pro5Application implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(Pro5Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//Tao 1 Book, 2 Publisher
		bookRepository.save(new Book("Book 1",
				new Publisher("Pub A"),
				new Publisher("Pub B")));
	}
}

