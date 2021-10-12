import java.util.Calendar;

public class MultithreadingTime extends Thread {

    protected Calendar timeNow;

    public void run() {
        try {
             this.timeNow = Calendar.getInstance();
        } catch (Exception e) {
            System.out.println("Error Occurred in " + Thread.currentThread().getName());
        }
    }

    public Calendar getTimeNow() {
        return this.timeNow;
    }
}
