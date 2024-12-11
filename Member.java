import java.util.ArrayList;
import java.util.List;

public class Member {
    private int id;
    private String name;
    private List<Book> borrowedBooks = new ArrayList<>();
    
    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book); // Add the book to the borrowed list
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book); // Remove the book from the borrowed list
    }

    public boolean hasBorrowed(Book book) {
        return borrowedBooks.contains(book); // Check if the book is in the borrowed list
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
