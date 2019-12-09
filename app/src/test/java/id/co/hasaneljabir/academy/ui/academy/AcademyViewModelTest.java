package id.co.hasaneljabir.academy.ui.academy;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import id.co.hasaneljabir.academy.data.source.AcademyRepository;
import id.co.hasaneljabir.academy.data.source.local.entity.CourseEntity;
import id.co.hasaneljabir.academy.ui.utils.FakeDataDummy;
import id.co.hasaneljabir.academy.vo.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcademyViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AcademyViewModel viewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);

    private String USERNAME = "Dicoding";

    @Before
    public void setUp() {
        viewModel = new AcademyViewModel(academyRepository);
    }

    @Test
    public void getCourses() {
        Resource<List<CourseEntity>> resource = Resource.success(FakeDataDummy.generateDummyCourses());
        MutableLiveData<Resource<List<CourseEntity>>> dummyCourses = new MutableLiveData<>();
        dummyCourses.setValue(resource);

        when(academyRepository.getAllCourses()).thenReturn(dummyCourses);

        Observer<Resource<List<CourseEntity>>> observer = mock(Observer.class);

        viewModel.setUsername(USERNAME);

        viewModel.courses.observeForever(observer);

        verify(observer).onChanged(resource);
    }
}