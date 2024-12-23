import java.util.Scanner;

public class LibraryManagement {
    private Library library = new Library();
    private Transaction transaction = Transaction.getInstance(); // Use the Singleton instance of Transaction

    public static void main(String[] args) {
        new LibraryManagement().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("===========================");
            System.out.println("Library Management System");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrowed Books");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter member ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();

                    Member newMember = new Member(id, name);
                    library.addMember(newMember);
                    System.out.println("Member added successfully.");
                    break;

                case 2:
                    System.out.print("Enter book ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();

                    try {
                        Book newBook = new Book(id, title);
                        if (library.addBook(newBook)) {
                            System.out.println("Book added to library successfully.");
                        } else {
                            System.out.println("Error: Duplicate book ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("\n--- Available Members ---");
                    for (Member member : library.getMembers()) {
                        System.out.println(member.getId() + ". " + member.getName());
                    }

                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.println("\n--- Available Books ---");
                    for (Book book : library.getBooks()) {
                        if (book.isAvailable())
                            System.out.println(book.getId() + ". " + book.getTitle());
                    }

                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Member member = library.findMemberById(memberId);
                    Book book = library.findBookById(bookId);

                    if (member != null && book != null) {
                        transaction.borrowBook(book, member);
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;

                case 4:
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter book ID: ");
                    bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    member = library.findMemberById(memberId);
                    book = library.findBookById(bookId);

                    if (member != null && book != null) {
                        transaction.returnBook(book, member);
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;

                case 5:
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Member specificMember = library.findMemberById(memberId);

                    if (specificMember != null) {
                        System.out.println("Books borrowed by " + specificMember.getName() + ":");
                        for (Book bk : specificMember.getBorrowedBooks()) {
                            System.out.println(" - " + bk.getTitle());
                        }
                    } else {
                        System.out.println("Invalid member ID.");
                    }
                    break;

                case 6:
                    Transaction.getInstance().displayTransactionHistory();
                    break;

                case 7:
                    System.out.println("Exiting. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}
