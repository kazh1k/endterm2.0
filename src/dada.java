import java.sql.*;
import java.util.Scanner;

class dada {
    private final DatabaseConnection dbConnection = new DatabaseConnection();

    public void addTask(Scanner scanner) {
        Task task = TaskInput.collectTaskInput(scanner);
        String tableName = getTableName(task.getStatus());

        if (tableName == null) {
            System.out.println("Invalid status! Use: Pending, In Progress, or Completed.");
            return;
        }

        String query = "INSERT INTO " + tableName + " (title, description, due_date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, task.getDueDate());
            stmt.setString(4, task.getStatus());
            stmt.executeUpdate();
            System.out.println("Task added to " + tableName + " successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    private String getTableName(String status) {
        return switch (status.toLowerCase()) {
            case "pending" -> "tasks";
            case "in progress" -> "in_progress_tasks";
            case "completed" -> "completed_tasks";
            default -> null;
        };
    }
}
