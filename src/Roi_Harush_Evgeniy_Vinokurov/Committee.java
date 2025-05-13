package Roi_Harush_Evgeniy_Vinokurov;
import java.util.Objects;

public class Committee {
    private static final int RESIZE_FACTOR = 2;

    private String name;
    private Lecturer[] lecturers;
    private Lecturer chair;
    private int lecturerIndex = 0;

    public Committee(String name, Lecturer chair) {
        this.lecturers = new Lecturer[1];
        setName(name);
        setChair(chair);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null)
            name = "Unknown Committee";
        this.name = name;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
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

    public boolean setChair(Lecturer chair) {
        if (checkChair(chair)){
            this.chair = chair;
            return true;
        }
        return false;
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


    public boolean addLecturer(Lecturer lecturer){
        if(this.lecturers.length == this.lecturerIndex)
            reSizingArr();
        if(!isExist(lecturer) && !lecturer.equals(chair)) {
            this.lecturers[this.lecturerIndex++] = lecturer;
            lecturer.addCommittee(this);
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
        Lecturer[] newArr = new Lecturer[size * RESIZE_FACTOR];
        for (int i = 0; i < size; i++)
            newArr[i] = this.lecturers[i];
        this.lecturers = newArr;
    }

    public void removeMember(Lecturer lecturer){
        for (int i = 0; i < this.lecturerIndex; i++)
            if(this.lecturers[i] != null && this.lecturers[i].equals(lecturer)) {
                shiftLeftArr(i);
                this.lecturers[--lecturerIndex] = null;
                lecturer.removeCommittee(this);
                break;
            }
    }

    public void shiftLeftArr(int i){
        for(;i<this.lecturers.length-1;i++){
            this.lecturers[i] = this.lecturers[i+1];
        }
    }

}