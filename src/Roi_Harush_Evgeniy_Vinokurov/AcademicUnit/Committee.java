package Roi_Harush_Evgeniy_Vinokurov.AcademicUnit;

import Roi_Harush_Evgeniy_Vinokurov.Exeptions.AcademicUnitNullDetailsException;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.AssignLecturerToAcademicUnitException;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.RemoveLecturerFromAcademicUnitException;
import Roi_Harush_Evgeniy_Vinokurov.Lecturers.Doctor;
import Roi_Harush_Evgeniy_Vinokurov.Lecturers.Lecturer;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.InvalidChairException;

import java.util.Objects;

public class Committee extends AcademicUnit implements Comparable<Committee>, Cloneable{


    private Lecturer chair;
    private int lecturerIndex = 0;

    public Object clone() throws CloneNotSupportedException {
        Committee cloned = (Committee) super.clone();
        cloned.lecturers=this.lecturers.clone();
        cloned.name = "New "+ this.name;
        cloned.chair=this.chair;
        return cloned;
    }

    public Committee(String name, Lecturer chair) throws InvalidChairException,AcademicUnitNullDetailsException {
        super(name);
        setChair(chair);
    }

    public int getLecturerIndex() {
        return lecturerIndex;
    }

    public void setLecturerIndex(int lecturerIndex)  {
        this.lecturerIndex = lecturerIndex;
    }

    public Lecturer getChair() {
        return chair;
    }

    @Override
    public String toString() {
        return  "name: " + name + "' " +
                ", lecturers: " + converteArrToString() +
                ", chair: '" + chair.getFullName() + "' ";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Committee committee = (Committee) o;
        return lecturerIndex == committee.lecturerIndex && Objects.equals(name, committee.name) && Objects.deepEquals(lecturers, committee.lecturers) && Objects.equals(chair, committee.chair);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setChair(Lecturer chair) throws InvalidChairException {
        if (!checkChair(chair)){
            throw new InvalidChairException("Cant");
        }
        this.chair = chair;
    }


    public boolean checkChair(Lecturer chair){
        if(chair.getTitle() != Lecturer.Title.Doctor && chair.getTitle() != Lecturer.Title.Professor)
            return false;
        for (Lecturer l : this.lecturers){
            if(l != null && l.equals(chair))
                return false;
        }
        return true;
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

    public void addLecturer(Lecturer lecturer) throws AssignLecturerToAcademicUnitException {
        if(!isExist(lecturer) && !lecturer.equals(chair)) {
            this.lecturers[this.lecturerIndex++] = lecturer;
            lecturer.addCommittee(this);
        }
        else{
            throw new AssignLecturerToAcademicUnitException("Can't add lecturer");
        }

    }
    @Override
    public int compareTo(Committee other){
        return this.lecturerIndex - other.lecturerIndex;
    }
    public void helper(Lecturer lecturer){
        lecturer.removeCommittee(this);
    }
    public int sumArticles(){
        int sum=0;
        for(int i=0; i<this.lecturerIndex; i++){
            if(lecturers[i] instanceof Doctor){
                sum+=((Doctor) lecturers[i]).getNumOfArticles();
            }
        }
        return sum;
    }

}