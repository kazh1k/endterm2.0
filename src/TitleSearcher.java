import java.util.Scanner;

public class TitleSearcher extends BaseTaskSearcher implements TaskSearchable {
    @Override
    public void search(Scanner scanner) {
        System.out.print("Enter title (full or partial): ");
        String searchTerm = "%" + scanner.nextLine() + "%";

        String query = "SELECT * FROM in_progress_tasks WHERE title LIKE ? " +
                "UNION ALL SELECT * FROM completed_tasks WHERE title LIKE ?";
        executeSearch(query, stmt -> {
            stmt.setString(1, searchTerm);
            stmt.setString(2, searchTerm);
        });
    }
}
