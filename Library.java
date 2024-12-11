import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    // Add a new member to the library
    public boolean addMember(Member member) {
        if (findMemberById(member.getId()) != null) {
            System.out.println("Error: A member with ID " + member.getId() + " already exists.");
            return false; // Addition failed due to duplicate ID
        }
        members.add(member);
        return true; // Addition successful
    }

    // Add a new book to the library
    public boolean addBook(Book book) {
        if (findBookById(book.getId()) != null) {
            System.out.println("Error: A book with ID " + book.getId() + " already exists.");
            return false; // Addition failed due to duplicate ID
        }
        books.add(book);
        return true; // Addition successful
    }

    // Find a member by ID
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // Find a book by ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // Get the list of members
    public List<Member> getMembers() {
        return members;
    }

    // Get the list of books
    public List<Book> getBooks() {
        return books;
    }
}
