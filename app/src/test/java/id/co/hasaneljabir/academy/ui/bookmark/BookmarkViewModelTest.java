package id.co.hasaneljabir.academy.ui.bookmark;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import id.co.hasaneljabir.academy.data.CourseEntity;
import id.co.hasaneljabir.academy.data.source.AcademyRepository;
import id.co.hasaneljabir.academy.ui.utils.FakeDataDummy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookmarkViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    private BookmarkViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new BookmarkViewModel(academyRepository);
    }

    @Test
    public void getBookmark() {
        ArrayList<CourseEntity> dummyCourses = FakeDataDummy.generateDummyCourses();

        MutableLiveData<List<CourseEntity>> courses = new MutableLiveData<>();
        courses.setValue(dummyCourses);

        when(academyRepository.getBookmarkedCourses()).thenReturn(courses);

        Observer<List<CourseEntity>> observer = mock(Observer.class);

        viewModel.getBookmarks().observeForever(observer);

        verify(observer).onChanged(dummyCourses);
    }
}