
public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected TaskStatus status;

    public Task(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
        status = TaskStatus.NEW;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "name= " + name + ", " + "description= " + description + ", " + "id= " + id + ", " + "status= " +
                status;
    }
}