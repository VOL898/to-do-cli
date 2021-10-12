import java.util.Calendar;

public class Important extends Data {

    public Important( String taskName, Calendar dueDate ){
        super(taskName,dueDate);
    }

    @Override
    public void printImportance() {
        System.out.println("Important Task");
    }

}