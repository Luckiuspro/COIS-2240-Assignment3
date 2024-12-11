import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryManagementTest {
    private Book validBookLow;
    private Book validBookHigh;

    @BeforeEach
    public void setUp() {
        // Initialize valid books
        try {
            validBookLow = new Book(100, "Valid Book Low");
            validBookHigh = new Book(999, "Valid Book High");
        } catch (Exception e) {
            fail("Setup failed: " + e.getMessage());
        }
    }

    @Test
    public void testBookId_ValidBoundary() {
        try {
            // Valid boundary cases
            Book bookLow = new Book(100, "Boundary Book Low");
            Book bookHigh = new Book(999, "Boundary Book High");

            assertEquals(100, bookLow.getId());
            assertEquals(999, bookHigh.getId());
        } catch (Exception e) {
            fail("Exception thrown for valid boundary cases: " + e.getMessage());
        }
    }

    @Test
    public void testBookId_InvalidLessThan100() {
        Exception exception = assertThrows(Exception.class, () -> new Book(99, "Invalid Book"));
        assertEquals("Invalid book ID: 99. ID must be between 100 and 999.", exception.getMessage());
    }

    @Test
    public void testBookId_InvalidGreaterThan999() {
        Exception exception = assertThrows(Exception.class, () -> new Book(1000, "Invalid Book"));
        assertEquals("Invalid book ID: 1000. ID must be between 100 and 999.", exception.getMessage());
    }

    @Test
    public void testBookId_ValidNonBoundary() {
        try {
            Book book = new Book(500, "Middle Range Book");
            assertTrue(book.isValidId(book.getId()));
            assertEquals(500, book.getId());
        } catch (Exception e) {
            fail("Exception thrown for valid ID 500: " + e.getMessage());
        }
    }

    @Test
    public void testBookId_InvalidIDMessage() {
        Exception exception = assertThrows(Exception.class, () -> new Book(50, "Another Invalid Book"));
        assertTrue(exception.getMessage().contains("Invalid book ID"));
    }
}

