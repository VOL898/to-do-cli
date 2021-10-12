import java.util.Calendar;

abstract public class Data implements Importance {

    protected String taskName;
    protected Calendar dueDate;

    public Data ( String taskName, Calendar dueDate ){
        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

}