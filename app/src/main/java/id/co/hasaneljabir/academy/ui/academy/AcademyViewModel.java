package id.co.hasaneljabir.academy.ui.academy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.co.hasaneljabir.academy.data.CourseEntity;
import id.co.hasaneljabir.academy.data.source.AcademyRepository;

public class AcademyViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public AcademyViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    LiveData<List<CourseEntity>> getCourses() {
        return academyRepository.getAllCourses();
    }
}
