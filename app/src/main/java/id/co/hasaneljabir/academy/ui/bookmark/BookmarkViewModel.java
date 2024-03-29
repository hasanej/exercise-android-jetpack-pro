package id.co.hasaneljabir.academy.ui.bookmark;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.co.hasaneljabir.academy.data.source.AcademyRepository;
import id.co.hasaneljabir.academy.data.source.local.entity.CourseEntity;
import id.co.hasaneljabir.academy.vo.Resource;

public class BookmarkViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public BookmarkViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    LiveData<Resource<List<CourseEntity>>> getBookmarks() {
        return academyRepository.getBookmarkedCourses();
    }
}
