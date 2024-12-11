import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LibraryManagementTest {
    private Book validBookLow;
    private Book validBookHigh;

    @Before
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
        try {
            new Book(99, "Invalid Book");
            fail("Exception was not thrown for ID less than 100.");
        } catch (Exception e) {
            assertEquals("Invalid book ID: 99. ID must be between 100 and 999.", e.getMessage());
        }
    }

    @Test
    public void testBookId_InvalidGreaterThan999() {
        try {
            new Book(1000, "Invalid Book");
            fail("Exception was not thrown for ID greater than 999.");
        } catch (Exception e) {
            assertEquals("Invalid book ID: 1000. ID must be between 100 and 999.", e.getMessage());
        }
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
        try {
            new Book(50, "Another Invalid Book");
            fail("Exception was not thrown for invalid ID.");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Invalid book ID"));
        }
    }
}
