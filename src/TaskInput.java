import java.sql.Date;
import java.util.Scanner;

class TaskInput {
    public static Task collectTaskInput(Scanner scanner) {
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

        return new Task(title, description, status, Date.valueOf(dueDate));
    }
}
