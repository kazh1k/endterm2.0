import java.util.Date;

class Task {
    private final String title;
    private final String description;
    private final String status;
    private final Date dueDate;
    private final int id;

    public Task(String title, String description, String status, Date dueDate) {
        this(title, description, status, dueDate, -1);
    }

    public Task(String title, String description, String status, Date dueDate, int id) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public java.sql.Date getDueDate() {
        return (java.sql.Date) dueDate;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nTitle: " + title + "\nDescription: " + description + "\nStatus: " + status + "\nDue Date: " + dueDate + "\n-------------------";
    }
}
