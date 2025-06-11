package Roi_Harush_Evgeniy_Vinokurov.AcademicUnit;

import Roi_Harush_Evgeniy_Vinokurov.Exeptions.AcademicUnitNullDetailsException;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.AssignLecturerToAcademicUnitException;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.RemoveLecturerFromAcademicUnitException;
import Roi_Harush_Evgeniy_Vinokurov.Lecturers.Lecturer;

public abstract class AcademicUnit implements Cloneable {
    private static final int RESIZE_FACTOR = 2;
    protected String name;
    protected Lecturer[] lecturers;

    protected int lecturerIndex = 0;

    public AcademicUnit(String name) throws AcademicUnitNullDetailsException {
        this.name = name;
        this.lecturers = new Lecturer[1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name)throws AcademicUnitNullDetailsException {
        if(name==null || name.isEmpty())
            throw new AcademicUnitNullDetailsException("Didn't get invalid name");
        this.name = name;
    }

    public int getLecturerIndex() {
        return lecturerIndex;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }


    public boolean isExist(Lecturer lecturer){
        for(Lecturer l : this.lecturers)
            if(l != null && l.equals(lecturer))
                return true;
        return false;
    }

    public void reSizingArr(){
        int size = this.lecturers.length;
        Lecturer[] newArr = new Lecturer[size * RESIZE_FACTOR];
        for (int i = 0; i < size; i++)
            newArr[i] = this.lecturers[i];
        this.lecturers = newArr;
    }

    public  void removeLecturer(Lecturer lecturer) throws RemoveLecturerFromAcademicUnitException{
        for (int i = 0; i < this.lecturerIndex; i++){
            if (this.lecturers[i] != null && this.lecturers[i].equals(lecturer)) {
                shiftLeftArr(i);
                this.lecturers[--lecturerIndex] = null;
                helper(lecturer);
                return;
            }
        }
        throw new RemoveLecturerFromAcademicUnitException("Can't remove lecturer");
    }
    public abstract void helper(Lecturer lecturer);

    public void shiftLeftArr(int i){
        for(;i<this.lecturers.length-1;i++){
            this.lecturers[i] = this.lecturers[i+1];
        }
    }
    public abstract void addLecturer(Lecturer lecturer)throws AssignLecturerToAcademicUnitException;


}
