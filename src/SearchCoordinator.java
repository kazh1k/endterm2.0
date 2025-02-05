import java.util.Scanner;

public class SearchCoordinator {
    private final TitleSearcher titleSearcher = new TitleSearcher();
    private final DateSearcher dateSearcher = new DateSearcher();
    private final IdSearcher idSearcher = new IdSearcher();

    public void searchMenu(Scanner scanner) {
        System.out.println("\n--- Search Tasks ---");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Date");
        System.out.println("3. Search by ID");
        System.out.print("Choose search type: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> titleSearcher.search(scanner);
            case 2 -> dateSearcher.search(scanner);
            case 3 -> idSearcher.search(scanner);
            default -> System.out.println("Invalid search option");
        }
    }
}