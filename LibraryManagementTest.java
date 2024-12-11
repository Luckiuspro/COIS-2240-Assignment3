import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryManagementTest {
    @Test
    public void testSingletonTransaction() throws Exception {
        // Retrieve the constructor of the Transaction class
        Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();

        // Check if the constructor is private
        int modifiers = constructor.getModifiers();
        assertEquals(Modifier.PRIVATE, modifiers, "The Transaction constructor should be private to enforce Singleton.");

        // Attempt to create a new instance via reflection
        constructor.setAccessible(true);
        Exception exception = assertThrows(Exception.class, () -> {
            Transaction instance = constructor.newInstance();
        });
        assertTrue(exception instanceof ReflectiveOperationException,
                "Creating a new Transaction instance via reflection should throw an exception.");
    }

    @Test
    public void testBorrowReturn() throws Exception {
        // Create a book and a member
        Book book = new Book(200, "Test Book");
        Member member = new Member(1, "Test Member");

        // Retrieve the singleton Transaction instance
        Transaction transaction = Transaction.getInstance();

        // Assert the book is initially available
        assertTrue(book.isAvailable(), "The book should initially be available.");

        // Borrow the book
        assertTrue(transaction.borrowBook(book, member), "Borrowing should be successful.");
        assertFalse(book.isAvailable(), "The book should be unavailable after borrowing.");
        assertTrue(member.getBorrowedBooks().contains(book), "The book should be in the member's borrowed books list.");

        // Attempt to borrow the same book again
        assertFalse(transaction.borrowBook(book, member), "Borrowing the same book again should fail.");

        // Return the book
        assertTrue(transaction.returnBook(book, member), "Returning the book should be successful.");
        assertTrue(book.isAvailable(), "The book should be available after returning.");
        assertFalse(member.getBorrowedBooks().contains(book), "The book should not be in the member's borrowed books list after returning.");

        // Attempt to return the book again
        assertFalse(transaction.returnBook(book, member), "Returning the same book again should fail.");
    }
}
