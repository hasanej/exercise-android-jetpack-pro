package id.co.hasaneljabir.academy.ui.academy;

import androidx.lifecycle.ViewModel;

import java.util.List;

import id.co.hasaneljabir.academy.data.CourseEntity;
import id.co.hasaneljabir.academy.utils.DataDummy;

public class AcademyViewModel extends ViewModel {
    public List<CourseEntity> getCourses() {
        return DataDummy.generateDummyCourses();
    }
}
