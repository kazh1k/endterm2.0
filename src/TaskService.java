import java.sql.*;
import java.util.Scanner;

class TaskService {
    private final DatabaseConnection dbConnection = new DatabaseConnection();

    public void addTask(Scanner scanner) {
        Task task = TaskInput.collectTaskInput(scanner);
        String query = "INSERT INTO tasks (title, description, due_date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, task.getDueDate());
            stmt.setString(4, task.getStatus());
            stmt.executeUpdate();
            System.out.println("Task added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    public void viewTasks() {
        String query = "SELECT * FROM tasks";
        try (Connection conn = dbConnection.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(query)) {
                    System.out.println("\n--- Tasks ---");
                    while (rs.next()) {
                        Task task = TaskMapper.map(rs);
                        System.out.println(task);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
    }

    public void updateTask(Scanner scanner) {
        System.out.print("Enter task ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Task updatedTask = TaskInput.collectTaskInput(scanner);
        String query = "UPDATE tasks SET title = ?, description = ?, status = ?, due_date = ? WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, updatedTask.getTitle());
            stmt.setString(2, updatedTask.getDescription());
            stmt.setString(3, updatedTask.getStatus());
            stmt.setDate(4, updatedTask.getDueDate());
            stmt.setInt(5, id);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Task updated successfully!" : "Task not found.");
        } catch (SQLException e) {
            System.out.println("Error updating task: " + e.getMessage());
        }
    }

    public void deleteTask(Scanner scanner) {
        System.out.print("Enter task ID to delete: ");
        int id = scanner.nextInt();
        String query = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Task deleted successfully!" : "Task not found.");
        } catch (SQLException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }
    public void moveTaskToCompleted(int taskId) {
        String moveQuery = "INSERT INTO completed_tasks (title, description, due_date, status) " +
                "SELECT title, description, due_date, status FROM tasks WHERE id = ?";
        String deleteQuery = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement moveStmt = conn.prepareStatement(moveQuery);
             PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {

            moveStmt.setInt(1, taskId);
            deleteStmt.setInt(1, taskId);
            int rows = moveStmt.executeUpdate();
            deleteStmt.executeUpdate();

            System.out.println(rows > 0 ? "Task moved to Completed!" : "Task not found.");
        } catch (SQLException e) {
            System.out.println("Error moving task: " + e.getMessage());
        }
    }

    public void moveTaskToInProgress(int taskId) {
        String moveQuery = "INSERT INTO in_progress_tasks (title, description, due_date, status) " +
                "SELECT title, description, due_date, status FROM tasks WHERE id = ?";
        String deleteQuery = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement moveStmt = conn.prepareStatement(moveQuery);
             PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {

            moveStmt.setInt(1, taskId);
            deleteStmt.setInt(1, taskId);
            int rows = moveStmt.executeUpdate();
            deleteStmt.executeUpdate();

            System.out.println(rows > 0 ? "Task moved to In Progress!" : "Task not found.");
        } catch (SQLException e) {
            System.out.println("Error moving task: " + e.getMessage());
        }
    }
}
