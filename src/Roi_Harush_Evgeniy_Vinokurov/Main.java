package Roi_Harush_Evgeniy_Vinokurov;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.*;
import Roi_Harush_Evgeniy_Vinokurov.Lecturers.Lecturer;

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
                    compareLecturer(college);
                    break;
                case 15:
                    compareCommittee(college);
                    break;
                case 16:
                    cloneCommittee(college);
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
        System.out.println("14 - Compare by articles");
        System.out.println("15 - Compare departments");
        System.out.println("16 - Clone committee");
        System.out.print("Please choose option: ");
    }

    public static void newLecturer(CollegeManager college){
        String fullName, id, degree, degreeName, departmentName;
        String instituteName=null;
        String[] articles=null;

        double salary;
        fullName = getStrFromUser("Full Name");
        while(college.isLecturerExist(fullName)){
            System.out.println("Lecturer already exist!");
            fullName = getStrFromUser("Full Name");
        }
        id = getStrFromUser("Id");
        printAllTitles();
        degree=getStrFromUser("Title");
        degreeName = getStrFromUser("Title Name");
        salary = getDoubleFromUser("Salary");
        departmentName = getStrFromUser("Department");
        switch(degree){
            case "Doctor":
                System.out.println("Articles");
                articles=getArticlesFromUser();
                break;
            case "Professor":
                instituteName = getStrFromUser("Institute name");
                break;
        }
        try{
            college.createLecturer(fullName,id,degree,degreeName,salary,departmentName,articles,instituteName);
            System.out.println("Lecturer added!");
        }catch(LectureNullDetails e){
            System.out.println("Error 1: "+e.getMessage());
        }catch(IllegalArgumentException e){
            System.out.println("Error 2: "+e.getMessage());
        }catch(AssignLecturerToAcademicUnitException e){
            System.out.println("Error 3: "+e.getMessage());
        }
    }

    public static void newCommittee(CollegeManager college){
        String committeeName, fullName;
        committeeName = getStrFromUser("Committee Name");
        while(college.isCommitteeExist(committeeName)){
            System.out.println("Committee already exist!");
            committeeName = getStrFromUser("Committee Name");
        }
        fullName = getStrFromUser("Full Name");
        try{
            college.createCommittee(committeeName, fullName);
        }
        catch(InvalidChairException e){
            System.out.println("Error 1: "+e.getMessage());
        }catch(AcademicUnitNullDetailsException e){
            System.out.println("Error 2: "+e.getMessage());
        }
    }

    public static void addMemberToCommittee(CollegeManager college){
        String committeeName, fullName;
        committeeName = getStrFromUser("Committee Name");
        fullName = getStrFromUser("Full Name");
         try{
             college.addMember(committeeName, fullName);
             System.out.println("Member added!");
         }catch(AssignLecturerToAcademicUnitException e){
             System.out.println(e.getMessage());
         }
    }

    public static void updateChair(CollegeManager college){
        String committeeName, fullName;
        committeeName = getStrFromUser("Committee Name");
        fullName = getStrFromUser("Full Name");
        try{
            college.updateCommitteeChair(committeeName, fullName);
        }
        catch(InvalidChairException e){
            System.out.println("catch Exception ");
        }
    }
    public static void compareLecturer(CollegeManager college){
        String lecturer1, lecturer2;
        lecturer1=getStrFromUser("Lecturer name");
        lecturer2=getStrFromUser("Lecturer name");
        System.out.println(college.compareLecturer(lecturer1, lecturer2)>= 0 ? lecturer1:lecturer2+ "has more articles");
    }

    public static void compareCommittee(CollegeManager college){
        String department1, department2;
        department1=getStrFromUser("Department name");
        department2=getStrFromUser("Department name");
        boolean defaultCompare = getBooleanFromUser("Do you want to compare this departments by number of the lecturers? (yes/no): ");
        System.out.println(college.compareCommittee(department1, department2, defaultCompare) >= 0 ? department1:department2);
    }
    public static void cloneCommittee(CollegeManager college){
        String committeeName;
        committeeName=getStrFromUser("Committee name");
        try{
            college.cloneCommittee(committeeName);
        }catch(CloneNotSupportedException e){
            System.out.println(e.getMessage());
        }

    }
    public static void removeMemberFromCommittee(CollegeManager college){
        String committeeName, fullName;
        committeeName = getStrFromUser("Committee Name");
        fullName = getStrFromUser("Full Name");
        try{
            college.removeMember(committeeName, fullName);
            System.out.println("Member removed!");
        }catch(RemoveLecturerFromAcademicUnitException e){
            System.out.println(e.getMessage());
        }

    }

    public static void addNewDepartment(CollegeManager college) {
        String departmentName;
        int numOfStudent;
        departmentName = getStrFromUser("Department Name");
        while (college.isDepartmentExist(departmentName)){
            System.out.println("Department already exist!");;
            departmentName = getStrFromUser("Department Name");

        }
        numOfStudent = getIntFromUser("Num Of Student");
        try{
            college.createDepartment(departmentName, numOfStudent);
        }catch(AcademicUnitNullDetailsException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Department added!");
    }

    public static void assignLecturerToDepartment(CollegeManager college){
        String departmentName, fullName;
        departmentName = getStrFromUser("Department Name");
        fullName = getStrFromUser("Full Name");
        try{
            college.addLecturerToDepartment(departmentName, fullName);
            System.out.println("Lecturer assigned to ");
        }catch(AssignLecturerToAcademicUnitException e){
            System.out.println(e.getMessage());
        }
    }

    public static void removeLecturerFromDepartment(CollegeManager college){
        String departmentName, fullName;
        departmentName = getStrFromUser("Department Name");
        fullName = getStrFromUser("Full Name");
        try{
            college.removeLecturerFromDepartment(departmentName, fullName);
            System.out.println("Lecturer removed from "+ departmentName);
        }catch(RemoveLecturerFromAcademicUnitException e){
            System.out.println(e.getMessage());
        }
    }

    public static double averageFromDepartment(CollegeManager college){
        String departmentName = getStrFromUser("Department Name");
        return college.getAverageByDepartment(departmentName);
    }

    public static String getStrFromUser(String type){
        System.out.print("Enter " + type + ": ");
        return scan.nextLine();
    }
    public static String[] getArticlesFromUser(){
        int numOfArticles=getIntFromUser("how much articles this lecturer have");
        String[] articles= new String[numOfArticles];
        for(int i=0; i<numOfArticles;i++){
            articles[i]=getStrFromUser("Please enter " + (i+1) + " articles");
        }
        return articles;

    }
    public static double getDoubleFromUser(String type){
        System.out.print("Enter " + type + ": ");
        double result =  scan.nextDouble();
        scan.nextLine();
        return result;
    }
    public static boolean getBooleanFromUser(String type){
        String answer = getStrFromUser(type);
        return "Yes".equals(answer.toUpperCase());
    }

    public static int getIntFromUser(String type){
        System.out.print("Enter " + type + ": ");
        int result =  scan.nextInt();
        scan.nextLine();
        return result;
    }

    public static void printAllTitles() {
        System.out.println("Please choose you degree from those options: ");
        for (Lecturer.Title title : Lecturer.Title.values()) {
            System.out.print(title.name() + ' ');
            System.out.println();
        }
    }
}

