package Roi_Harush_Evgeniy_Vinokurov.Lecturers;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.AssignLecturerToAcademicUnitException;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.LectureNullDetails;
import Roi_Harush_Evgeniy_Vinokurov.AcademicUnit.Committee;
import Roi_Harush_Evgeniy_Vinokurov.AcademicUnit.Department;

import java.util.Objects;

public class Lecturer implements Cloneable{
    private static final int RESIZE_FACTOR = 2;

    public enum Title {
        First,
        Second,
        Doctor,
        Professor,
    }

    protected String fullName;
    protected String id;
    protected Title title;
    protected String titleName;
    protected double salary;
    protected Department department;
    protected Committee[] committees;
    protected int committeeIndex;

    public Lecturer(String fullName, String id, String title, String titleName, double salary, Department department)throws LectureNullDetails,IllegalArgumentException{
        this.committees = new Committee[1];
        setFirstName(fullName);
        setId(id);
        setTitle(title);
        setTitleName(titleName);
        setSalary(salary);
        setDepartment(department);
    }



    public String getFullName() {
        return fullName;
    }

    public void setFirstName(String fullName) throws LectureNullDetails{
        if (fullName == null||fullName.isEmpty())
            throw new LectureNullDetails("Didn't get valid name");
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws LectureNullDetails {
        if (id == null || id.isEmpty())
            throw new LectureNullDetails("Didn't get valid name");
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(String title)  throws LectureNullDetails, IllegalArgumentException{
        this.title = Title.valueOf(title);
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) throws LectureNullDetails{
        if (titleName == null || titleName.isEmpty())
             throw new LectureNullDetails("Didn't get valid Title name");
        this.titleName = titleName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws LectureNullDetails {
        if (salary <= 0)
            throw new LectureNullDetails("Didn't get valid salary ");
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

    public void addCommittee(Committee committee)throws AssignLecturerToAcademicUnitException {
        if (this.committees.length == this.committeeIndex)
            reSizingArr();
        if (!isExist(committee)){
            this.committees[this.committeeIndex++] = committee;
        }
        else{
            throw new AssignLecturerToAcademicUnitException("Can't add lecturer");
        }

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