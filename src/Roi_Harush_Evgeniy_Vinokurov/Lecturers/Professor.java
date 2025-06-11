package Roi_Harush_Evgeniy_Vinokurov.Lecturers;

import Roi_Harush_Evgeniy_Vinokurov.AcademicUnit.Department;
import Roi_Harush_Evgeniy_Vinokurov.Exeptions.LectureNullDetails;

public class Professor extends Doctor {
    private String institutionName;

    public Professor(String fullName, String id, String title, String titleName, double salary, Department department, String[] articles, String institutionName) throws LectureNullDetails,IllegalArgumentException {
        super(fullName, id, title, titleName, salary, department, articles);
        this.institutionName = institutionName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) throws LectureNullDetails {
        if(institutionName==null || institutionName.isEmpty())
            throw new LectureNullDetails("Didn't get valid institution");
        this.institutionName = institutionName;
    }
}
