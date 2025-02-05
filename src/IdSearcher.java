import java.util.Scanner;

public class IdSearcher extends BaseTaskSearcher {
    public void search(Scanner scanner) {
        System.out.print("Enter task ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String query = "SELECT * FROM tasks WHERE id = ?";
        executeSearch(query, stmt -> stmt.setInt(1, id));
    }
}