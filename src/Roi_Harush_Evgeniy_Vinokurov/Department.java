package Roi_Harush_Evgeniy_Vinokurov;

import java.util.Arrays;
import java.util.Objects;

public class Department {
    private static final int RESIZE_FACTOR = 2;

    private String name;
    private int numOfStudent;
    private Lecturer[] lecturers;
    private int lecturerIndex = 0;

    public Department(String name, int numOfStudent) {
        setName(name);
        setNumOfStudent(numOfStudent);
        this.lecturers = new Lecturer[1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null)
            name = "Unknown Department";
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

    public boolean addLecturer(Lecturer lecturer){
        if(this.lecturers.length == this.lecturerIndex)
            reSizingArr();
        if(!isExist(lecturer)) {
            this.lecturers[this.lecturerIndex++] = lecturer;
            lecturer.setDepartment(this);
            return true;
        }
        return false;
    }

    public boolean isExist(Lecturer lecturer){
        for(Lecturer l : this.lecturers)
            if(l != null && l.equals(lecturer))
                return true;
        return false;
    }

    public void reSizingArr(){
        int size = this.lecturers.length;
        Lecturer[] newArr = new Lecturer[size*RESIZE_FACTOR];
        for (int i = 0; i < size; i++)
            newArr[i] = this.lecturers[i];
        this.lecturers = newArr;
    }

    public void removeLecturer(Lecturer lecturer){
        for (int i = 0; i < this.lecturers.length; i++)
            if(this.lecturers[i] != null && this.lecturers[i].equals(lecturer)) {
                this.lecturers[i] = null;
                shiftLeftArr(i);
                this.lecturers[--this.lecturerIndex] = null;
                lecturer.setDepartment(null);
                break;
            }
    }

    public void shiftLeftArr(int i){
        for(;i<this.lecturers.length-1;i++){
            this.lecturers[i] = this.lecturers[i+1];
        }
    }
}