package Roi_Harush_Evgeniy_Vinokurov.AcademicUnit;

import Roi_Harush_Evgeniy_Vinokurov.Exeptions.AcademicUnitNullDetailsException;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.AssignLecturerToAcademicUnitException;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.RemoveLecturerFromAcademicUnitException;
import Roi_Harush_Evgeniy_Vinokurov.Lecturers.Doctor;
import Roi_Harush_Evgeniy_Vinokurov.Lecturers.Lecturer;

import java.util.Objects;

public class Department extends AcademicUnit  {


    private int numOfStudent;


    public Department(String name, int numOfStudent) throws AcademicUnitNullDetailsException {
        super(name);
        this.numOfStudent = numOfStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws AcademicUnitNullDetailsException {
        if(name == null || name.isEmpty())
            throw new AcademicUnitNullDetailsException("Didn't get invalid name");
        this.name = name;
    }

    public int getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(int numOfStudent) {
        if(numOfStudent < 0)
            numOfStudent = 0;
        this.numOfStudent = numOfStudent;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public void setLecturers(Lecturer[] lecturers) {
        if (lecturers == null)
            lecturers = new Lecturer[1];
        this.lecturers = lecturers;
    }

    @Override
    public String toString() {
        return  "name: '" + name + "' " +
                ", numOfStudent: " + numOfStudent +
                ", lecturers: " + converteArrToString();
    }

    public String converteArrToString(){
        String strArr = "[";
        for(int i = 0; i < this.lecturerIndex; i++){
            strArr += this.lecturers[i].getFullName();
            if(i < this.lecturerIndex-1)
                strArr += ", ";
        }
        strArr += ']';
        return strArr;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public void addLecturer(Lecturer lecturer) throws AssignLecturerToAcademicUnitException {
        if(!isExist(lecturer)) {
            this.lecturers[this.lecturerIndex++] = lecturer;
            lecturer.setDepartment(this);
        }
        else{
            throw new AssignLecturerToAcademicUnitException("Can't add lecturer");
        }

    }
    public void helper(Lecturer lecturer){
        lecturer.setDepartment(null);
    }

}