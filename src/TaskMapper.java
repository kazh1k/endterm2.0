import java.sql.ResultSet;
import java.sql.SQLException;

class TaskMapper {
    public static Task map(ResultSet rs) throws SQLException {
        return new Task(
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getDate("due_date"),
                rs.getInt("id")
        );
    }
}
