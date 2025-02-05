import java.sql.*;

public abstract class BaseTaskSearcher {
    protected final DatabaseConnection dbConnection = new DatabaseConnection();

    protected interface ParameterSetter {
        void setParameters(PreparedStatement stmt) throws SQLException;
    }

    protected void executeSearch(String query, ParameterSetter parameterSetter) {
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            parameterSetter.setParameters(stmt);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("\n--- Search Results ---");
                boolean found = false;

                while (rs.next()) {
                    Task task = TaskMapper.map(rs);
                    System.out.println(task);
                    found = true;
                }

                if (!found) {
                    System.out.println("No tasks found matching criteria");
                }
            }
        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
        }
    }
}