package Roi_Harush_Evgeniy_Vinokurov;
import Roi_Harush_Evgeniy_Vinokurov.AcademicUnit.Committee;
import Roi_Harush_Evgeniy_Vinokurov.AcademicUnit.Department;

import java.util.Comparator;

public class CompareByArticles implements Comparator<Committee> {
    public int compare(Committee committee1, Committee committee2){
        return committee1.sumArticles()-committee2.sumArticles();
    }


}
