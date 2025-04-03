package Roi_Harush_Evgeniy_Vinokurov;
import java.util.Scanner;

public class Main {
    final static int INCREASER = 2;
    public static void main(String[] args) {
        /*
        Roi Harush
        Evgeniy Vinokurov
         */
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter collage name: ");
        String collageName = scan.nextLine();
        menu(scan);

    }

    /*
    This method calculates the average of a double array,
    ignoring zero values. Currently, this method is not used in the program.
    */
    public static double getAverage(double[] salary){
        int salCount = 0;
        double sum = 0;
        for (int i = 0; i <salary.length; i++){
            if (salary[i] != 0){
                sum += salary[i];
                salCount ++;
            }
        }
        if (salCount !=0)
            return sum/salCount;
        return salCount;
    }

    /*
    This method receives a String array, a label for the content,
    a Scanner, and the index to insert into. It asks the user to enter a new string,
    checks if it already exists in the array, and adds it if it's unique.
    If the array is full, it increases its size.
    Returns the updated array.
     */
    public static String[] addToArr(String[] arr, String content, Scanner scan, int lastPosition){
        System.out.print("Enter "+content);
        String name = scan.nextLine();
        while (isExist(arr,name)){
            System.out.println(content +" exist!");
            System.out.print("Enter "+content);
            name = scan.nextLine();
        }
        if (lastPosition == arr.length)
            arr = increaseArr(arr);
        arr[lastPosition] = name;
        System.out.println("Successfully added");
        return arr;

    }
    /*
    This method receives a String array and returns a new array
    with double the original size, copying over all existing elements.
    The increase factor is defined by a constant.
    */
    public static String [] increaseArr(String [] arr){
        int size =  arr.length;
        String[] newArr = new String[size*INCREASER];
        for (int i = 0; i<size;i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }

    /*
    This method checks whether a given string exists in the provided array.
    Returns true if found, false otherwise.
     */
    public static boolean isExist(String [] arr , String name){
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != null && arr[i].equals(name)){
                return true ;
            }
        }
        return false;
    }

    /*
    This method prints the array in a clean format,
    ignoring null values to avoid showing empty entries.
     */
    public static void printArr(String[] arr){
        int i = 0;
        System.out.print("[");
        for (; i < arr.length;i++){
            if (arr[i] !=null && i+1 < arr.length && arr[i+1] !=null){
                System.out.print(arr[i]+',');
            }
            else if (arr[i]!=null)
                System.out.print(arr[i]);

        }
        System.out.println(']');
    }

    /*
    this method will print the menu to simplizing the user experience in the program.
     */
    public static void printMenu(){
        System.out.println("Please choose option: ");
        System.out.println("0 - Exit");
        System.out.println("1 - Add lecturer");
        System.out.println("2 - Add Committee");
        System.out.println("3 - Add Study class");
        System.out.println("4 - Put lecturer to committee");
        System.out.println("5 - Show all lecturers average salary");
        System.out.println("6 - Show average salary of lecturers from specific study class");
        System.out.println("7 - Show all lecturers information");
        System.out.println("8 - Show all committee information");
    }

    /*
    this method will connect lecturer to committee, at this version, it only shows if one of them does not exist.
     */
    public static void assignLecturer(String[] lecArray , String[] comArray, String lecturer, String committee){
        if (!isExist(lecArray, lecturer) || !isExist(comArray, committee))
            System.out.println("One of them does not exited!");
    }

    /*
    This is the main menu loop of the program.
    It displays options to the user and handles input accordingly.
     */
    public static void menu(Scanner scan) {
        String[] lecturerArr = new String[1];
        String[] committeeArr = new String[1];
        String[] studyClassArr = new String[1];
        int[] arraysCounter = {0, 0, 0};
        int option;
        while (true) {
            printMenu();
            option = scan.nextInt();
            scan.nextLine();
            switch(option){
                case 0:
                    return;
                case 1:
                    lecturerArr = addToArr(lecturerArr,"Lecturer name: ", scan, arraysCounter[0]);
                    arraysCounter[0] ++;
                    break;
                case 2:
                    committeeArr = addToArr(committeeArr,"committee: ", scan, arraysCounter[1]);
                    arraysCounter[1] ++;
                    break;
                case 3:
                    studyClassArr = addToArr(studyClassArr,"study class: ", scan, arraysCounter[2]);
                    arraysCounter[2] ++;
                    break;
                case 4:
                    System.out.print("Enter lecturer: ");
                    String lecturer = scan.nextLine();
                    System.out.print("Enter committee: ");
                    String committee = scan.nextLine();
                    assignLecturer(lecturerArr, committeeArr, lecturer, committee);
                    break;
                case 5:
                    //addToArr(salary arr);
                    System.out.println("This feature is not implemented in this version.");
                    break;
                case 6:
                    //addToArr(salary arr);
                    System.out.println("This feature is not implemented in this version.");
                    break;
                case 7:
                    printArr(lecturerArr);
                    break;
                case 8:
                    printArr(committeeArr);
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }
}