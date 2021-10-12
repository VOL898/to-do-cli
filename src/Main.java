import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws IOException {

        MainHelper help = new MainHelper();
        ArrayList<Data> list = new ArrayList<>();

        int choice = -1;

        help.create();
        help.read(list);

        do{
            help.printTitle();
            MultithreadingTime updateDate = new MultithreadingTime();
            updateDate.run();
            MultithreadingSort sortingThread = new MultithreadingSort();
            sortingThread.run( list );
            Calendar timeNow = updateDate.getTimeNow();
            int dayNow = timeNow.get(Calendar.DATE);
            int monthNow = timeNow.get(Calendar.MONTH);
            ++monthNow;
            int yearNow = timeNow.get(Calendar.YEAR);
            System.out.println("Current Date: " + dayNow + "/" + monthNow + "/" + yearNow);
            help.displayTasks( list );
            System.out.println("___________________________________________");
            help.printMainMenu();
            do{
                try{
                    System.out.print("Your Choice: ");
                    choice = help.scan.nextInt();
                } catch (Exception e){
                    choice = -1;
                    System.out.println("Invalid input!");
                } finally {
                    help.scan.nextLine();
                }
            }while( choice < 1 || choice > 4 );
            if( choice == 1 ){                              //ADD
                String taskName = "";
                int date = 0, year = 0, month = -1;
                String tempImportant = "";
                do{
                    System.out.print("Input task name: ");
                    taskName = help.scan.nextLine();
                } while( taskName.length() < 1 || taskName.length() > 25 );
                do{
                    try{
                        System.out.print("Input due date's year: ");
                        year = help.scan.nextInt();
                    } catch (Exception e){
                        year = 0;
                    } finally {
                        help.scan.nextLine();
                    }
                    if(( year < timeNow.get(Calendar.YEAR) || year > timeNow.getActualMaximum(Calendar.YEAR) )){
                        System.out.println("Please enter a valid Year Input!");
                    }
                } while( year < timeNow.get(Calendar.YEAR) );
                if( year != timeNow.get(Calendar.YEAR) ){
                    timeNow.set(year,0,1);
                }
                do{
                    try{
                        System.out.print("Input due date's month: ");
                        month = help.scan.nextInt();
                    } catch (Exception e){
                        month = -1;
                    } finally {
                        help.scan.nextLine();
                        --month;
                    }
                    if(( month < timeNow.get(Calendar.MONTH) || month > timeNow.getActualMaximum(Calendar.MONTH) )){
                        System.out.println("Please enter a valid Month Input!");
                    }
                } while( month < timeNow.get(Calendar.MONTH) || month > timeNow.getActualMaximum(Calendar.MONTH) );
                if( month != timeNow.get(Calendar.MONTH) ){
                    timeNow.set(year,month,1);
                }
                do{
                    try{
                        System.out.print("Input due date: ");
                        date = help.scan.nextInt();
                    } catch (Exception e){
                        date = 0;
                    } finally {
                        help.scan.nextLine();
                    }
                    if(( date < timeNow.get(Calendar.DATE) || date > timeNow.getActualMaximum(Calendar.DATE) )){
                        System.out.println("Please enter a valid Date Input!");
                    }
                } while( date < timeNow.get(Calendar.DATE) || date > timeNow.getActualMaximum(Calendar.DATE) );
                timeNow.set( year, month, date );
                do{
                    System.out.print("Is this task important [yes|no]? ");
                    tempImportant = help.scan.nextLine();
                } while( !tempImportant.equals("yes") && !tempImportant.equals("no") );
                Data x;
                if( tempImportant.equals("yes") ){
                    x = new Important( taskName, timeNow);
                } else {
                    x = new Unimportant( taskName, timeNow);
                }
                list.add(x);
                System.out.println("The new task added!");
                help.holdUser();
            } else if( choice == 2 ){                       //EDIT
                help.cls();
                help.displayTasks(list);
                help.extraOption(list);
                System.out.println("___________________________________________");
                if( !list.isEmpty() ){
                    int whichIndex = -1;
                    do{
                        try{
                            System.out.print("Input the index [1-" + (list.size() + 1) +"]: ");
                            whichIndex = help.scan.nextInt();
                        } catch (Exception e){
                            whichIndex = -1;
                        } finally {
                            help.scan.nextLine();
                        }
                    } while( whichIndex < 1 || whichIndex > (list.size() + 1) );
                    --whichIndex;
                    if( whichIndex != list.size() ){
                        help.cls();
                        Data x = list.get(whichIndex);
                        String taskName = x.getTaskName();
                        Calendar taskDue = x.getDueDate();
                        System.out.println("Task Name = " + taskName);
                        System.out.println("Due Date [dd/mm/yyyy]= " + taskDue.get(Calendar.DATE) + "/" + (taskDue.get(Calendar.MONTH)+1) + "/" + taskDue.get(Calendar.YEAR));
                        x.printImportance();
                        System.out.println("_________________________________");
                        help.editMenu();
                        int which = -1;
                        do{
                            try{
                                System.out.print("Your Choice: ");
                                which = help.scan.nextInt();
                            } catch (Exception e){
                                which = -1;
                            } finally {
                                help.scan.nextLine();
                            }
                        } while( which < 1 || which > 4 );
                        if( which == 1 ){
                            String tempName = "";
                            do{
                                System.out.print("Enter new task name: ");
                                tempName = help.scan.nextLine();
                            } while( tempName.length() < 1 || tempName.length() > 25 );
                            x.setTaskName(tempName);
                            System.out.println("Task name updated!");
                        } else if( which == 2 ){
                            int year = 0, month = 0, date = 0;
                            do{
                                try{
                                    System.out.print("Input due date's year: ");
                                    year = help.scan.nextInt();
                                } catch (Exception e){
                                    year = 0;
                                } finally {
                                    help.scan.nextLine();
                                }
                                if(( year < timeNow.get(Calendar.YEAR) || year > timeNow.getActualMaximum(Calendar.YEAR) )){
                                    System.out.println("Please enter a valid Year Input!");
                                }
                            } while( year < timeNow.get(Calendar.YEAR) );
                            if( year != timeNow.get(Calendar.YEAR) ){
                                timeNow.set( year,0,1 );
                            }
                            do{
                                try{
                                    System.out.print("Input due date's month: ");
                                    month = help.scan.nextInt();
                                } catch (Exception e){
                                    month = -1;
                                } finally {
                                    help.scan.nextLine();
                                    --month;
                                }
                                if(( month < timeNow.get(Calendar.MONTH) || month > timeNow.getActualMaximum(Calendar.MONTH) )){
                                    System.out.println("Please enter a valid Month Input!");
                                }
                            } while( month < timeNow.get(Calendar.MONTH) || month > timeNow.getActualMaximum(Calendar.MONTH) );
                            if( month != timeNow.get(Calendar.MONTH) ){
                                timeNow.set( year, month,1 );
                            }
                            do{
                                try{
                                    System.out.print("Input due date: ");
                                    date = help.scan.nextInt();
                                } catch (Exception e){
                                    date = 0;
                                } finally {
                                    help.scan.nextLine();
                                }
                                if(( date < timeNow.get(Calendar.DATE) || date > timeNow.getActualMaximum(Calendar.DATE) )){
                                    System.out.println("Please enter a valid Date Input!");
                                }
                            } while( date < timeNow.get(Calendar.DATE) || date > timeNow.getActualMaximum(Calendar.DATE) );
                            taskDue.set( year, month, date );
                            x.setDueDate(taskDue);
                            System.out.println("Task due date updated!");
                        } else if( which == 3 ){
                            list.remove(whichIndex);
                            if( x instanceof Important ){
                                x = new Unimportant( taskName, taskDue );
                            } else {
                                x = new Important( taskName, taskDue );
                            }
                            list.add(x);
                            System.out.println("Importance updated!");
                        } else {
                            System.out.println("Exiting to Main Menu");
                        }
                    } else {
                        System.out.println("Exiting to Main Menu");
                    }
                }
                help.holdUser();
            } else if( choice == 3 ){                       //DELETE
                help.cls();
                help.displayTasks(list);
                help.extraOption(list);
                System.out.println("___________________________________________");
                if( !list.isEmpty() ) {
                    int whichIndex = -1;
                    do {
                        try {
                            System.out.print("Input the index [1-" + (list.size() + 1) + "]: ");
                            whichIndex = help.scan.nextInt();
                        } catch (Exception e) {
                            whichIndex = -1;
                        } finally {
                            help.scan.nextLine();
                        }
                    } while (whichIndex < 1 || whichIndex > (list.size() + 1) );
                    --whichIndex;
                    if( whichIndex != list.size() ){
                        list.remove(whichIndex);
                    } else {
                        System.out.println("Exiting to Main Menu");
                    }
                }
                help.holdUser();
            }
        }while( choice != 4 );
        
        help.printThankYou();
    }

}