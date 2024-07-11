package org.example.bookapi22;

public class BookDTO {
    private String bookName;
    private String publisherName;

    public BookDTO(String b, String p) {
        this.bookName = b;
        this.publisherName = p;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
