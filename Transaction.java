import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private static Transaction instance;
    private static final String TRANSACTION_FILE = "transactions.txt";

    private Transaction() {
        // Private constructor to prevent direct instantiation
    }

    public static Transaction getInstance() {
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
    }

    // Save transaction details to a file
    private void saveTransaction(String transactionDetails) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true))) {
            writer.write(transactionDetails);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            member.borrowBook(book);
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails); // Save transaction to file
            return true;
        } else {
            System.out.println(book.getTitle() + " is already borrowed.");
            return false;
        }
    }

    public boolean returnBook(Book book, Member member) {
        if (member.hasBorrowed(book)) { // Ensure the book is in the borrowed list
            book.setAvailable(true); // Mark the book as available
            member.returnBook(book); // Remove the book from the member's borrowed books list
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails); // Save transaction to file
            return true;
        } else {
            System.out.println("This book was not borrowed by " + member.getName() + ".");
            return false;
        }
    }

    // Display all past transactions from transactions.txt
    public void displayTransactionHistory() {
        File file = new File(TRANSACTION_FILE);
        if (!file.exists()) {
            System.out.println("No transaction history found.");
            return;
        }

        try (java.util.Scanner scanner = new java.util.Scanner(file)) {
            System.out.println("\n--- Transaction History ---");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Error reading transaction history: " + e.getMessage());
        }
    }

    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
