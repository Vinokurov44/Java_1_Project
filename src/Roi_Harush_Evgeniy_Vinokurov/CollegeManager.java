package Roi_Harush_Evgeniy_Vinokurov;
import Roi_Harush_Evgeniy_Vinokurov.AcademicUnit.AcademicUnit;
import Roi_Harush_Evgeniy_Vinokurov.AcademicUnit.Department;
import Roi_Harush_Evgeniy_Vinokurov.AcademicUnit.Committee;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.*;
import Roi_Harush_Evgeniy_Vinokurov.Lecturers.Doctor;
import Roi_Harush_Evgeniy_Vinokurov.Lecturers.Lecturer;
import Roi_Harush_Evgeniy_Vinokurov.Lecturers.Professor;

public class CollegeManager {
        private static final int RESIZE_FACTOR = 2;

        private String name;
        private Lecturer[] lecturers = new Lecturer[1];
        private Department[] departments = new Department[1];
        private Committee[] committees = new Committee[1];
        private int[] arraysCounter = {0,0,0};

        public void createLecturer(String fullName, String id, String title, String titleName, double salary, String departmentName, String[] articles,String instituteName ) throws LectureNullDetails,IllegalArgumentException, AssignLecturerToAcademicUnitException {
            Department department = getDepartment(departmentName);
            Lecturer lecturer;
            switch (title) {
                case"Doctor":
                    lecturer = new Doctor(fullName, id, title, titleName, salary, department, articles);
                    break;
                case"Professor":
                    lecturer = new Professor(fullName, id, title, titleName, salary, department, articles, instituteName);
                    break;
                default:
                    lecturer = new Lecturer(fullName, id, title, titleName, salary, department);
            }
        if(department != null){
            department.addLecturer(lecturer);
        }
        addLecturer(lecturer);
    }

    public  CollegeManager(String collegeName){

            this.name = collegeName;
    }

        public void addLecturer(Lecturer lecturer){
        if(this.arraysCounter[0] == this.lecturers.length)
            reSizingLecturersArr();
        this.lecturers[this.arraysCounter[0]++] = lecturer;
    }

        public void reSizingLecturersArr(){
        int size =  this.lecturers.length;
        Lecturer[] newArr = new Lecturer[size* RESIZE_FACTOR];
        for (int i = 0; i < size; i++){
            newArr[i] = this.lecturers[i];
        }
        this.lecturers = newArr;
    }

        public boolean isLecturerExist(String fullName){
        Lecturer found = getLecturer(fullName);
        return found != null;
    }

        public Lecturer getLecturer(String fullName){
        for (Lecturer l : this.lecturers){
            if(l != null && l.getFullName().equals(fullName))
                return l;
        }
        return null;
    }

        public void createDepartment(String departmentName, int numOfStudent) throws AcademicUnitNullDetailsException{
        Department department = new Department(departmentName,numOfStudent);
        addDepartment(department);
    }

        public void addDepartment(Department department){
        if(this.arraysCounter[1] == this.departments.length)
            reSizingDepartmentArr();
        this.departments[this.arraysCounter[1]++] = department;
    }

        public boolean isDepartmentExist(String departmentName){
        Department found = getDepartment(departmentName);
        return found != null;
    }

        public Department getDepartment(String departmentName){
        for(Department department : this.departments){
            if(department != null && department.getName().equals(departmentName))
                return department;
        }
        return null;
    }

        public void reSizingDepartmentArr(){
        int size =  this.departments.length;
        Department[] newArr = new Department[size* RESIZE_FACTOR];
        for (int i = 0; i < size; i++){
            newArr[i] = this.departments[i];
        }
        this.departments = newArr;
    }

        public void createCommittee(String committeeName, String fullName) throws InvalidChairException, AcademicUnitNullDetailsException {
        Lecturer chair = getLecturer(fullName);
        Committee committee;
            committee = new Committee(committeeName, chair);
            addCommittee(committee);
    }

        private void addCommittee(Committee committee) {
        if(this.arraysCounter[2] == this.committees.length)
            reSizingCommitteeArr();
        this.committees[this.arraysCounter[2]++] = committee;
    }

        public boolean isCommitteeExist(String committeeName){
        Committee found = getCommittee(committeeName);
        return found != null;
    }

        public void reSizingCommitteeArr(){
        int size = this.committees.length;
        Committee[] newArr = new Committee[size* RESIZE_FACTOR];
        for (int i  = 0; i < size; i++)
            newArr[i] = this.committees[i];
        this.committees = newArr;
    }

        public Committee getCommittee(String committeeName){
        for (Committee c : this.committees)
            if(c != null && c.getName().equals(committeeName))
                return c;
        return null;
    }

        public void updateCommitteeChair(String committeeName, String fullName) throws InvalidChairException{
        Committee committee = getCommittee(committeeName);
        Lecturer lecturer = getLecturer(fullName);
        if(committee != null && lecturer != null)
            committee.setChair(lecturer);

    }

        public void addMember(String committeeName, String fullName) throws AssignLecturerToAcademicUnitException{
        Committee committee = getCommittee(committeeName);
        Lecturer lecturer = getLecturer(fullName);
        if(committee != null && lecturer != null) {
            if(committee.getLecturers().length==committee.getLecturerIndex()){
                committee.reSizingArr();
            }
            committee.addLecturer(lecturer);
        }
    }

        public void removeMember(String committeeName, String fullName) throws RemoveLecturerFromAcademicUnitException {
        Committee committee = getCommittee(committeeName);
        Lecturer lecturer = getLecturer(fullName);
        if(committee != null && lecturer != null) {

            committee.removeLecturer(lecturer);
        }
    }

        public void  addLecturerToDepartment(String departmentName, String fullName) throws AssignLecturerToAcademicUnitException {
        Department department = getDepartment(departmentName);
        Lecturer lecturer = getLecturer(fullName);
        if(department != null && lecturer != null && lecturer.getDepartment() == null ){
            if(department.getLecturers().length==department.getLecturerIndex()){
                department.reSizingArr();
            }
              department.addLecturer(lecturer);
        }
    }

        public void removeLecturerFromDepartment(String departmentName, String fullName) throws RemoveLecturerFromAcademicUnitException{
        Department department = getDepartment(departmentName);
        Lecturer lecturer = getLecturer(fullName);
        if(department != null && lecturer != null){
            department.removeLecturer(lecturer);
        }
    }

        public double getAverage(){
        return calculateAverage(this.lecturers);
    }

        public double calculateAverage(Lecturer[] lecturers){
        double sum = 0;
        int numOfLecturers = 0;
        double salary;
        for (Lecturer l : lecturers){
            if(l != null){
                salary = l.getSalary();
                if (salary != 0) {
                    sum += salary;
                    numOfLecturers++;
                }
            }
        }
        if (numOfLecturers!=0)
            return sum/numOfLecturers;
        return 0;
    }

        public double getAverageByDepartment(String departmentName){
        Department department = getDepartment(departmentName);
        if(department != null)
            return calculateAverage(department.getLecturers());
        else
            return 0;
    }

        public void showLecturers(){
        for(Lecturer l:this.lecturers){
            if(l!=null)
                System.out.println(l);
        }
    }

        public void showDepartments() {
        for (Department d : this.departments) {
            if (d != null)
                System.out.println(d);
        }
    }

        public void showCommittees(){
        for (Committee committee : this.committees)
            if (committee != null)
                System.out.println(committee);
    }
    public int compareLecturer(String lecturer1Name, String lecturer2Name) {
        Doctor lecturer1 = (Doctor) getLecturer(lecturer1Name);
        Doctor lecturer2 = (Doctor) getLecturer(lecturer2Name);
        return lecturer1.compareTo(lecturer2);
    }

    public int compareCommittee(String committee1Name, String committee2Name, boolean defaultCompare){
        Committee committee1 = getCommittee(committee1Name);
        Committee committee2 = getCommittee(committee2Name);
        if (defaultCompare)
            return committee1.compareTo(committee2);
        return new CompareByArticles().compare(committee1,committee2);
    }

    public void cloneCommittee(String committeeName) throws CloneNotSupportedException{
            Committee currentCommittee = getCommittee(committeeName);
            addCommittee((Committee) currentCommittee.clone());
    }




}