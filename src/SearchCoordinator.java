import java.util.Scanner;

public class SearchCoordinator {
    private final TaskSearchable[] searchers = {
            new TitleSearcher(),
            new DateSearcher(),
            new IdSearcher()
    };

    public void searchMenu(Scanner scanner) {
        System.out.println("\n--- Search Tasks ---");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Date");
        System.out.println("3. Search by ID");
        System.out.print("Choose search type: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= searchers.length) {
            searchers[choice - 1].search(scanner);
        } else {
            System.out.println("Invalid search option");
        }
    }
}
