import java.sql.Date;
import java.util.Scanner;

class TaskInput {
    public static Task collectTaskInput(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task status (In Progress, Completed): ");
        String status = scanner.nextLine();
        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();

        return new Task(title, description, status, Date.valueOf(dueDate));
    }
}
