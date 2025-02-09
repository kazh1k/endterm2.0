import java.util.Scanner;

class TaskManagerApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskService taskService = new TaskService();

    public void run() {
        while (true) {
            System.out.println("\n--- Task Manager ---");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. View Archived Tasks");
            System.out.println("4. Update Task");
            System.out.println("5. Archive Task");
            System.out.println("6. Delete Task");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> taskService.addTask(scanner);
                case 2 -> taskService.viewAllTasks();
                case 3 -> taskService.viewArchivedTasks();
                case 4 -> taskService.updateTask(scanner, selectTable());
                case 5 -> {
                    String table = selectTable();
                    System.out.print("Enter task ID to archive: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    taskService.archiveTask(id, table);
                }
                case 6 -> taskService.deleteTask(scanner, selectTable());
                case 7 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private String selectTable() {
        System.out.println("Choose table:");
        System.out.println("1. In Progress Tasks");
        System.out.println("2. Completed Tasks");
        System.out.println("3. Archived Tasks");
        System.out.print("Enter choice: ");

        String input = scanner.nextLine().trim().toLowerCase();
        return switch (input) {
            case "1", "in progress tasks", "in_progress_tasks" -> "in_progress_tasks";
            case "2", "completed tasks", "completed_tasks" -> "completed_tasks";
            case "3", "archived tasks", "archived_tasks" -> "archived_tasks";
            default -> {
                System.out.println("Invalid table. Defaulting to 'in_progress_tasks'.");
                yield "in_progress_tasks";
            }
        };
    }
}
