import java.util.Comparator;
import java.util.Calendar;

class SortDate implements Comparator<Data> {

    public int compare(Data a, Data b){
        Calendar x = a.getDueDate();
        Calendar y = b.getDueDate();
        return x.compareTo(y);
    }

}