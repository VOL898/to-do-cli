import java.util.Comparator;

class SortImportance implements Comparator<Data> {

    public int compare( Data a, Data b ){
        int valA = 0, valB = 0;
        if(a instanceof Important){
            valA = 1;
        }
        if(b instanceof Important){
            valB = 1;
        }
        return valB-valA;
    }

}
