import java.sql.Date;
import java.util.Scanner;

public class TitleSearcher extends BaseTaskSearcher {
    public void search(Scanner scanner) {
        System.out.print("Enter title (full or partial): ");
        String searchTerm = "%" + scanner.nextLine() + "%";

        String query = "SELECT * FROM tasks WHERE title LIKE ?";
        executeSearch(query, stmt -> stmt.setString(1, searchTerm));
    }
}