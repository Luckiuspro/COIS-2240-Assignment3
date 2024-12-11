public class Book {
    private int id;
    private String title;
    private boolean available;

    // Constructor that validates the ID before initializing the object
    public Book(int id, String title) throws Exception {
        if (!isValidId(id)) {
            throw new Exception("Invalid book ID: " + id + ". ID must be between 100 and 999.");
        }
        this.id = id;
        this.title = title;
        this.available = true; // Books are available by default
    }

    // Method to validate if the book ID is between 100 and 999 (inclusive)
    public boolean isValidId(int id) {
        return id >= 100 && id <= 999;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for availability status
    public boolean isAvailable() {
        return available;
    }

    // Setter for availability status
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
