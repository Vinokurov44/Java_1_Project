package Roi_Harush_Evgeniy_Vinokurov;


import java.util.Arrays;
import java.util.Objects;

public class Lecturer{
    private static final int RESIZE_FACTOR = 2;

    public enum Title {
        First,
        Second,
        Doctor,
        Professor,
    }

    private String fullName;
    private String id;
    private Title title;
    private String titleName;
    private double salary;
    private Department department;
    private Committee[] committees;
    private int committeeIndex;

    public Lecturer(String fullName, String id, String title, String titleName, double salary, Department department) {
        this.committees = new Committee[1];
        setFirstName(fullName);
        setId(id);
        setTitle(title);
        setTitleName(titleName);
        setSalary(salary);
        setDepartment(department);
    }

    public Lecturer(String fullName, String id, String title, String titleName, double salary) {
        this(fullName, id, title, titleName, salary, null);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFirstName(String fullName) {
        if (fullName == null)
            fullName = "Unknown";
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null)
            id = "00000000";
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Title.valueOf(title);
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        if (titleName == null)
            titleName = "Default";
        this.titleName = titleName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0)
            salary = 0;
        this.salary = salary;

    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return  "Name: '" + fullName + "' " +
                ", id: '" + id + "' " +
                ", title: " + title + "' " +
                ", titleName: " + titleName + "' " +
                ", salary: " + salary + "' " +
                ", department: " + (department != null ? department.getName() : "No Department yet") +
                ", committees: " + converteArrToString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(fullName, lecturer.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

    public String converteArrToString() {
        String strArr = "[";
        for (int i = 0; i < this.committeeIndex; i++) {
            strArr += this.committees[i].getName();
            if(i< this.committeeIndex-1)
                strArr += ", ";
        }
        strArr += ']';
        return strArr;
    }

    public boolean addCommittee(Committee committee) {
        if (this.committees.length == this.committeeIndex)
            reSizingArr();
        if (!isExist(committee)){
            this.committees[this.committeeIndex++] = committee;
            return true;
        }
        return false;
    }

    public boolean isExist(Committee committee) {
        for (Committee c : this.committees)
            if (c != null && c.equals(committee))
                return true;
        return false;
    }

    public void reSizingArr() {
        int size = this.committees.length;
        Committee[] newArr = new Committee[size * RESIZE_FACTOR];
        for (int i = 0; i < size; i++)
            newArr[i] = this.committees[i];
        this.committees = newArr;
    }

    public void removeCommittee(Committee committee) {
        for (int i = 0; i < this.committeeIndex; i++)
            if (this.committees[i] != null && this.committees[i].equals(committee)) {
                shiftLeftArr(i);
                this.committees[--committeeIndex] = null;
                break;
            }
    }

    public void shiftLeftArr(int i) {
        for (; i < this.committeeIndex-1; i++) {
            this.committees[i] = this.committees[i+1];
        }
    }
}