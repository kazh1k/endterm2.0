import java.util.Scanner;

public class IdSearcher extends BaseTaskSearcher implements TaskSearchable {
    @Override
    public void search(Scanner scanner) {
        System.out.print("Enter task ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String query = "SELECT * FROM in_progress_tasks WHERE id = ? " +
                "UNION ALL SELECT * FROM completed_tasks WHERE id = ?";
        executeSearch(query, stmt -> {
            stmt.setInt(1, id);
            stmt.setInt(2, id);
        });
    }
}
