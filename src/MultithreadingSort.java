import java.util.ArrayList;
import java.util.Collections;

public class MultithreadingSort extends Thread {

    public void run( ArrayList<Data> list ) {
        try {
            Collections.sort( list, new SortDate() );
            Collections.sort( list, new SortImportance() );
        } catch (Exception e) {
            System.out.println("Error occurred during sorting.");
        }
    }

}