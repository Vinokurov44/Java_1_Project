package Roi_Harush_Evgeniy_Vinokurov;
import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        /*
        Roi Harush
        Evgeniy Vinokurov
         */
        System.out.print("Enter collage name: ");
        String collegeName = scan.nextLine();
        CollegeManager college = new CollegeManager(collegeName);
        menu(college);
    }

    public static void menu(CollegeManager college) {
        int option;
        Main.printMenu();
        option = scan.nextInt();
        scan.nextLine();
        while (option !=0) {
            switch(option){
                case 1:
                    newLecturer(college);
                    break;
                case 2:
                    newCommittee(college);
                    break;
                case 3:
                    addMemberToCommittee(college);
                    break;
                case 4:
                    updateChair(college);
                    break;
                case 5:
                    removeMemberFromCommittee(college);
                    break;
                case 6:
                    addNewDepartment(college);
                    break;
                case 7:
                    assignLecturerToDepartment(college);
                    break;
                case 8:
                    removeLecturerFromDepartment(college);
                    break;
                case 9:
                    System.out.println(college.getAverage());
                    break;
                case 10:
                    System.out.println(averageFromDepartment(college));
                    break;
                case 11:
                    college.showLecturers();
                    break;
                case 12:
                    college.showCommittees();
                    break;
                case 13:
                    college.showDepartments();
                    break;
                case 14:
                    college.benchMark();
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
            Main.printMenu();
            option = scan.nextInt();
            scan.nextLine();
        }
    }

    public static void printMenu(){
        System.out.println("-------Menu-------");
        //System.out.println("-------------------------------------------------------------------");
        System.out.println("0 - Exit");
        System.out.println("1 - Add new lecturer");
        System.out.println("2 - Add new Committee");
        System.out.println("3 - Add member to committee");
        System.out.println("4 - update committee's Chair");
        System.out.println("5 - Remove member from committee");
        System.out.println("6 - Add new department");
        System.out.println("7 - Assign lecturer to department");
        System.out.println("8 - Remove lecturer from department");
        System.out.println("9 - Show all lecturers salary average");
        System.out.println("10 - Show all lecturers from specific department salary average");
        System.out.println("11 - Show lecturers information");
        System.out.println("12 - Show committees information");
        System.out.println("13 - Show department information");
        System.out.println("14 - run benchMarks");
        System.out.print("Please choose option: ");
    }

    public static void newLecturer(CollegeManager college){
        String fullName, id, title, titleName, departmentName;
        double salary;
        fullName = getStrFromUser("Full Name");
        while(college.isLecturerExist(fullName)){
            System.out.println("Lecturer already exist!");
            fullName = getStrFromUser("Full Name");
        }
        id = getStrFromUser("Id");
        title = getStrFromUser("Title");
        titleName = getStrFromUser("Title Name");
        salary = getDoubleFromUser("Salary");
        departmentName = getStrFromUser("Department");
        college.createLecturer(fullName,id,title,titleName,salary,departmentName);
        System.out.println("Lecturer added!");
    }

    public static void newCommittee(CollegeManager college){
        String committeeName, fullName;
        committeeName = getStrFromUser("Committee Name");
        while(college.isCommitteeExist(committeeName)){
            System.out.println("Committee already exist!");
            committeeName = getStrFromUser("Committee Name");
        }
        fullName = getStrFromUser("Full Name");
        boolean success = college.createCommittee(committeeName, fullName);
        System.out.println(success ? "Committee added!" : "Cant create committee");
    }

    public static void addMemberToCommittee(CollegeManager college){
        String committeeName, fullName;
        committeeName = getStrFromUser("Committee Name");
        fullName = getStrFromUser("Full Name");
        boolean success = college.addMember(committeeName, fullName);
        System.out.println(success ? "Member added!" : "Cant add member");
    }

    public static void updateChair(CollegeManager college){
        String committeeName, fullName;
        committeeName = getStrFromUser("Committee Name");
        fullName = getStrFromUser("Full Name");
        boolean success = college.updateCommitteeChair(committeeName, fullName);
        System.out.println(success ? "Member added!" : "Cant add member");
    }

    public static void removeMemberFromCommittee(CollegeManager college){
        String committeeName, fullName;
        committeeName = getStrFromUser("Committee Name");
        fullName = getStrFromUser("Full Name");
        boolean success = college.removeMember(committeeName, fullName);
        System.out.println(success ? "Member removed!" : "Cant remove member");
    }

    public static void addNewDepartment(CollegeManager college){
        String departmentName;
        int numOfStudent;
        departmentName = getStrFromUser("Department Name");
        while (college.isDepartmentExist(departmentName)){
            System.out.println("Department already exist!");;
            departmentName = getStrFromUser("Department Name");

        }
        numOfStudent = getIntFromUser("Num Of Student");
        college.createDepartment(departmentName, numOfStudent);
        System.out.println("Department added!");
    }

    public static void assignLecturerToDepartment(CollegeManager college){
        String departmentName, fullName;
        departmentName = getStrFromUser("Department Name");
        fullName = getStrFromUser("Full Name");
        boolean success = college.addLecturerToDepartment(departmentName, fullName);
        System.out.println(success ? "Lecturer assigned to "+ departmentName : "Cant assign Lecturer");
    }

    public static void removeLecturerFromDepartment(CollegeManager college){
        String departmentName, fullName;
        departmentName = getStrFromUser("Department Name");
        fullName = getStrFromUser("Full Name");
        boolean success = college.removeLecturerFromDepartment(departmentName, fullName);
        System.out.println(success ? "Lecturer removed from "+ departmentName : "Cant remove Lecturer");
    }

    public static double averageFromDepartment(CollegeManager college){
        String departmentName = getStrFromUser("Department Name");
        return college.getAverageByDepartment(departmentName);
    }

    public static String getStrFromUser(String type){
        System.out.print("Enter " + type + ": ");
        return scan.nextLine();
    }

    public static double getDoubleFromUser(String type){
        System.out.print("Enter " + type + ": ");
        double result =  scan.nextDouble();
        scan.nextLine();
        return result;
    }

    public static int getIntFromUser(String type){
        System.out.print("Enter " + type + ": ");
        int result =  scan.nextInt();
        scan.nextLine();
        return result;
    }
}

