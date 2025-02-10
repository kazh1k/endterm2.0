import java.sql.Date;
import java.util.Scanner;

public class DateSearcher extends BaseTaskSearcher implements TaskSearchable {
    @Override
    public void search(Scanner scanner) {
        System.out.print("Enter due date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        String query = "SELECT * FROM in_progress_tasks WHERE due_date = ? " +
                "UNION ALL SELECT * FROM completed_tasks WHERE due_date = ?";
        executeSearch(query, stmt -> {
            stmt.setDate(1, Date.valueOf(date));
            stmt.setDate(2, Date.valueOf(date));
        });
    }
}
