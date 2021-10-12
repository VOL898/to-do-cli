import java.util.Calendar;

public class Unimportant extends Data {

    public Unimportant( String taskName, Calendar dueDate ){
        super(taskName,dueDate);
    }

    @Override
    public void printImportance() {
        System.out.println("Unimportant Task");
    }

}