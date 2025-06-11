package Roi_Harush_Evgeniy_Vinokurov.Lecturers;

import Roi_Harush_Evgeniy_Vinokurov.AcademicUnit.Department;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.LectureNullDetails;

public class Doctor extends Lecturer implements Comparable<Doctor>{
    protected String[] articles;

    public Doctor(String fullName, String id, String title, String titleName, double salary, Department department, String[] articles) throws LectureNullDetails,IllegalArgumentException {
        super(fullName, id, title, titleName, salary, department);
        this.articles = articles;
    }


    public String[] getArticles() {
        return articles;
    }

    public int getNumOfArticles(){
        return this.articles.length;
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }
    @Override
    public int compareTo(Doctor other){
        return this.articles.length - other.articles.length;
    }


}
