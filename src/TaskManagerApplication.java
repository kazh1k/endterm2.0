import java.util.Scanner;

class TaskManagerApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskService taskService = new TaskService();
    private final SearchCoordinator searchCoordinator = new SearchCoordinator();

    public void run() {
        while (true) {
            System.out.println("\n--- Task Manager ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Search by...");
            System.out.println("6. Move Task to Completed");
            System.out.println("7. Move Task to In Progress");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> taskService.addTask(scanner);
                case 2 -> taskService.viewTasks();
                case 3 -> taskService.updateTask(scanner);
                case 4 -> taskService.deleteTask(scanner);
                case 5 -> searchCoordinator.searchMenu(scanner);
                case 6 -> {
                    System.out.print("Enter Task ID to move to Completed: ");
                    int id = scanner.nextInt();
                    taskService.moveTaskToCompleted(id);
                }
                case 7 -> {
                    System.out.print("Enter Task ID to move to In Progress: ");
                    int id = scanner.nextInt();
                    taskService.moveTaskToInProgress(id);
                }
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
