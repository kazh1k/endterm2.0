import java.sql.Date;
import java.util.Scanner;

public class DateSearcher extends BaseTaskSearcher {
    public void search(Scanner scanner) {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        String query = "SELECT * FROM tasks WHERE due_date = ?";
        executeSearch(query, stmt -> stmt.setDate(1, Date.valueOf(date)));
    }
}