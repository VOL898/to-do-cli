import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;

public class MainHelper {

    public Scanner scan = new Scanner(System.in);

    public void cls(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /* Print Menus */
    public void printMainMenu(){
        System.out.println("MAIN MENU");
        System.out.println("=================");
        System.out.println("1. Add Task");
        System.out.println("2. Edit Task");
        System.out.println("3. Delete Task");
        System.out.println("4. Close Program");
        System.out.println("=================");
    }

    public void editMenu(){
        System.out.println("EDIT MENU");
        System.out.println("=======================");
        System.out.println("1. Edit Task Name");
        System.out.println("2. Edit Task Due Date");
        System.out.println("3. Edit Task Importance");
        System.out.println("4. Cancel");
        System.out.println("_______________________");
    }

    public void extraOption( ArrayList<Data> list ){
        int index = list.size();
        ++index;
        System.out.println( index + ". Go back to Main Menu");
    }
    /* Print Menus */

    public void displayTasks( ArrayList<Data> list ){
        System.out.println("Your To Do List:");
        if( list.isEmpty() ){
            System.out.println("you don't have anything to do...");
        } else {
            int index = 1;
            for (Data task : list) {
                int day = task.getDueDate().get(Calendar.DATE);
                int month = task.getDueDate().get(Calendar.MONTH);
                ++month;
                int year = task.getDueDate().get(Calendar.YEAR);
                if( task instanceof Important ){
                    System.out.println(index + ". !!! " + task.getTaskName() + " (" + day + "/" + month + "/" + year + ") !!!");
                } else {
                    System.out.println(index + ". " + task.getTaskName() + " (" + day + "/" + month + "/" + year + ")");
                }
                ++index;
            }
            write(list);
        }
    }

    public void holdUser(){
        System.out.print("Press ENTER to continue...");
        scan.nextLine();
        cls();
    }

    /* Print Special Titles */
    public void printTitle(){
        System.out.println();
        System.out.println(
                "'########::'#######:::::'########:::'#######:::::'##:::::::'####::'######::'########:\r\n" +
                "... ##..::'##.... ##:::: ##.... ##:'##.... ##:::: ##:::::::. ##::'##... ##:... ##..::\r\n" +
                "::: ##:::: ##:::: ##:::: ##:::: ##: ##:::: ##:::: ##:::::::: ##:: ##:::..::::: ##::::\r\n" +
                "::: ##:::: ##:::: ##:::: ##:::: ##: ##:::: ##:::: ##:::::::: ##::. ######::::: ##::::\r\n" +
                "::: ##:::: ##:::: ##:::: ##:::: ##: ##:::: ##:::: ##:::::::: ##:::..... ##:::: ##::::\r\n" +
                "::: ##:::: ##:::: ##:::: ##:::: ##: ##:::: ##:::: ##:::::::: ##::'##::: ##:::: ##::::\r\n" +
                "::: ##::::. #######::::: ########::. #######::::: ########:'####:. ######::::: ##::::\n");
    }

    public void printThankYou(){
        cls();
        System.out.println(
                "'########:'##::::'##::::'###::::'##::: ##:'##:::'##::::'##:::'##::'#######::'##::::'##:\r\n" +
                "... ##..:: ##:::: ##:::'## ##::: ###:: ##: ##::'##:::::. ##:'##::'##.... ##: ##:::: ##:\r\n" +
                "::: ##:::: ##:::: ##::'##:. ##:: ####: ##: ##:'##:::::::. ####::: ##:::: ##: ##:::: ##:\r\n" +
                "::: ##:::: #########:'##:::. ##: ## ## ##: #####:::::::::. ##:::: ##:::: ##: ##:::: ##:\r\n" +
                "::: ##:::: ##.... ##: #########: ##. ####: ##. ##::::::::: ##:::: ##:::: ##: ##:::: ##:\r\n" +
                "::: ##:::: ##:::: ##: ##.... ##: ##:. ###: ##:. ##:::::::: ##:::: ##:::: ##: ##:::: ##:\r\n" +
                "::: ##:::: ##:::: ##: ##:::: ##: ##::. ##: ##::. ##::::::: ##::::. #######::. #######::\r\n");
    }
    /* Print Special Titles */

    /* Save and Load File Functions */
    public void create(){
        try {
            File myObj = new File("TaskList.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("Save file exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void write(ArrayList<Data> list) {
        try {
            FileWriter myWriter = new FileWriter("TaskList.txt");
            BufferedWriter bw = new BufferedWriter(myWriter);

            if( list.isEmpty() ){
                System.out.println("you don't have anything to do...");
            } else {
                for (Data task : list) {
                    int day = task.getDueDate().get(Calendar.DATE);
                    int month = task.getDueDate().get(Calendar.MONTH);
                    ++month;
                    int year = task.getDueDate().get(Calendar.YEAR);
                    if (task instanceof Important) {
                        bw.write(task.getTaskName()+"#" + day + "#" + month + "#" + year+"#"+"important");

                    }else{
                        bw.write(task.getTaskName()+"#" + day + "#" + month + "#" + year+"#"+"unimportant");
                    }
                    bw.newLine();
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void read(ArrayList<Data> list) throws IOException{
        try {
            File myObj = new File("TaskList.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNext()) {
                MultithreadingTime updateDate = new MultithreadingTime();
                updateDate.run();
                MultithreadingSort sortingThread = new MultithreadingSort();
                sortingThread.run( list );
                Calendar timeNow = updateDate.getTimeNow();

                String data = myReader.nextLine();
                Scanner scr = new Scanner(data);
                scr.useDelimiter("#");
                String name = null, label = null, n = null;
                int day = 0, month = 0, year = 0;

                name = scr.next();
                day = scr.nextInt();
                month = scr.nextInt();
                year = scr.nextInt();
                label = scr.next();
                month = month - 1;
                timeNow.set( year, month, day );
                Data x;
                if( label.equals("important") ){
                    x = new Important( name, timeNow );
                } else {
                    x = new Unimportant( name, timeNow );
                }
                list.add(x);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /* Save and Load File Functions */

}
