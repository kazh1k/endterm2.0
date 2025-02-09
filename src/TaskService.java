import java.sql.*;
import java.util.Scanner;

class TaskService {
    private final DatabaseConnection dbConnection = new DatabaseConnection();

    public void addTask(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        String status;
        while (true) {
            System.out.print("Enter task status (In Progress, Completed): ");
            status = scanner.nextLine().trim();
            if (status.equalsIgnoreCase("In Progress") || status.equalsIgnoreCase("Completed")) {
                break;
            }
            System.out.println("Invalid status! Use: In Progress or Completed.");
        }

        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();

        String tableName = status.equalsIgnoreCase("In Progress") ? "in_progress_tasks" : "completed_tasks";
        String query = "INSERT INTO " + tableName + " (title, description, due_date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setDate(3, Date.valueOf(dueDate));
            stmt.setString(4, status);
            stmt.executeUpdate();

            System.out.println("Task added successfully to " + tableName);
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    public void viewAllTasks() {
        String query = "SELECT id, title, description, due_date, status FROM in_progress_tasks " +
                "UNION ALL SELECT id, title, description, due_date, status FROM completed_tasks";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- All Tasks ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Title: " + rs.getString("title") +
                        " | Description: " + rs.getString("description") +
                        " | Due Date: " + rs.getDate("due_date") +
                        " | Status: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching tasks: " + e.getMessage());
        }
    }

    public void viewArchivedTasks() {
        String query = "SELECT id, title, description, due_date, status FROM archived_tasks";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- Archived Tasks ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Title: " + rs.getString("title") +
                        " | Description: " + rs.getString("description") +
                        " | Due Date: " + rs.getDate("due_date") +
                        " | Status: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching archived tasks: " + e.getMessage());
        }
    }

    public void updateTask(Scanner scanner, String currentTable) {
        System.out.print("Enter task ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();

        System.out.print("Enter new status (In Progress, Completed): ");
        String newStatus = scanner.nextLine().trim();

        String newTable;
        if (newStatus.equalsIgnoreCase("In Progress")) {
            newTable = "in_progress_tasks";
        } else if (newStatus.equalsIgnoreCase("Completed")) {
            newTable = "completed_tasks";
        } else {
            System.out.println("Invalid status! Use: In Progress or Completed.");
            return;
        }

        try (Connection conn = dbConnection.getConnection()) {
            if (!currentTable.equals(newTable)) {
                String insertQuery = "INSERT INTO " + newTable + " (id, title, description, due_date, status) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, id);
                    insertStmt.setString(2, title);
                    insertStmt.setString(3, description);
                    insertStmt.setDate(4, Date.valueOf(dueDate));
                    insertStmt.setString(5, newStatus);
                    insertStmt.executeUpdate();
                }

                String deleteQuery = "DELETE FROM " + currentTable + " WHERE id = ?";
                try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                    deleteStmt.setInt(1, id);
                    deleteStmt.executeUpdate();
                }

                System.out.println("Task updated and moved to " + newTable);
            } else {
                String updateQuery = "UPDATE " + currentTable + " SET title = ?, description = ?, due_date = ?, status = ? WHERE id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, title);
                    updateStmt.setString(2, description);
                    updateStmt.setDate(3, Date.valueOf(dueDate));
                    updateStmt.setString(4, newStatus);
                    updateStmt.setInt(5, id);
                    updateStmt.executeUpdate();
                }

                System.out.println("Task updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating task: " + e.getMessage());
        }
    }


    public void archiveTask(int id, String table) {
        String query = "INSERT INTO archived_tasks (id, title, description, due_date, status) " +
                "SELECT id, title, description, due_date, status FROM " + table + " WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                deleteTaskById(id, table);
                System.out.println("Task archived successfully.");
            } else {
                System.out.println("No task found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error archiving task: " + e.getMessage());
        }
    }

    public void deleteTask(Scanner scanner, String table) {
        System.out.print("Enter task ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        deleteTaskById(id, table);
    }

    private void deleteTaskById(int id, String table) {
        String query = "DELETE FROM " + table + " WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Task deleted successfully.");
            } else {
                System.out.println("No task found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }
}
