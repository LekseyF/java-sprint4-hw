public class Subtask extends Task {
    protected int epicId;

    public Subtask(String name, String description, int id, int epicId) {
        super(name, description, id);
        this.epicId = epicId;
    }

    @Override
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" + super.toString() + " " + "epicId = " + epicId + "}";
    }
}