package Roi_Harush_Evgeniy_Vinokurov;

public class CollegeManager {
        private static final int RESIZE_FACTOR = 2;

        private String name;
        private Lecturer[] lecturers = new Lecturer[1];
        private Department[] departments = new Department[1];
        private Committee[] committees = new Committee[1];
        private int[] arraysCounter = {0,0,0};

        public void createLecturer(String fullName, String id,String title, String titleName, double salary, String departmentName ){
        Department department = getDepartment(departmentName);
        Lecturer lecturer = new Lecturer(fullName,id,title,titleName,salary,department);
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

        public void createDepartment(String departmentName, int numOfStudent){
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

        public boolean createCommittee(String committeeName, String fullName){
        Lecturer chair = getLecturer(fullName);
        Committee committee;
        if(chair != null && (chair.getTitle() == Lecturer.Title.Doctor || chair.getTitle() == Lecturer.Title.Professor)) {
            committee = new Committee(committeeName, chair);
            addCommittee(committee);
            return true;
        }
        return false;
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

        public boolean updateCommitteeChair(String committeeName, String fullName){
        Committee committee = getCommittee(committeeName);
        Lecturer lecturer = getLecturer(fullName);
        if(committee != null && lecturer != null)
            if(committee.setChair(lecturer))
                return true;
        return false;
    }

        public boolean addMember(String committeeName, String fullName){
        Committee committee = getCommittee(committeeName);
        Lecturer lecturer = getLecturer(fullName);
        if(committee != null && lecturer != null) {
            if(committee.addLecturer(lecturer))
                return true;
        }
        return false;
    }

        public boolean removeMember(String committeeName, String fullName){
        Committee committee = getCommittee(committeeName);
        Lecturer lecturer = getLecturer(fullName);
        if(committee != null && lecturer != null) {
            committee.removeMember(lecturer);
            return true;
        }
        return false;
    }

        public boolean addLecturerToDepartment(String departmentName, String fullName){
        Department department = getDepartment(departmentName);
        Lecturer lecturer = getLecturer(fullName);
        if(department != null && lecturer != null){
            if(lecturer.getDepartment() == null && department.addLecturer(lecturer)){
                return true;
            }
        }
        return false;
    }

        public boolean removeLecturerFromDepartment(String departmentName, String fullName){
        Department department = getDepartment(departmentName);
        Lecturer lecturer = getLecturer(fullName);
        if(department != null && lecturer != null){
            department.removeLecturer(lecturer);
            return true;
        }
        return false;
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

        public void benchMark(){
        benchMarkDepartment("Software Engineering", 10);
        benchMarkDepartment("science", 47);
        benchMarkLecturer("Roi Harush", "1", "Doctor", "r", 100, "Software Engineering");
        benchMarkLecturer("Evgeniy Vinokurov", "1", "Professor", "e", 59, "Software Engineering");
        benchMarkLecturer("Daniel Dazyuba", "3", "First", "r", 33, "science");
        benchMarkLecturer("Idan Amrani", "4", "First", "i", 75, "science");
        benchMarkCommittee("1", "Roi Harush");
        benchMarkCommittee("1", "-");
        benchMarkCommittee("2", "Daniel Dazyuba");
        benchMarkCommittee("2", "Evgeniy Vinokurov");
    }

        public void benchMarkLecturer(String fullName, String id, String title, String titleName, double salary, String departmentName){
        Department department = getDepartment(departmentName);
        Lecturer lecturer = new Lecturer(fullName,id,title,titleName,salary,department);
        if(department != null)
            department.addLecturer(lecturer);
        addLecturer(lecturer);
    }

        public void benchMarkDepartment(String name, int numOfStudent){
        Department department = new Department(name,numOfStudent);
        addDepartment(department);
    }

        public void benchMarkCommittee(String name, String fullName){
        Lecturer chair = getLecturer(fullName);
        Committee committee;
        if(chair != null && (chair.getTitle() == Lecturer.Title.Doctor || chair.getTitle() == Lecturer.Title.Professor)) {
            committee = new Committee(name, chair);
            addCommittee(committee);
        }
    }


}