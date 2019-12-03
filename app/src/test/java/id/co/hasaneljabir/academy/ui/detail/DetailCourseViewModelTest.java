package id.co.hasaneljabir.academy.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import id.co.hasaneljabir.academy.data.CourseEntity;
import id.co.hasaneljabir.academy.data.ModuleEntity;
import id.co.hasaneljabir.academy.data.source.AcademyRepository;
import id.co.hasaneljabir.academy.ui.utils.FakeDataDummy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailCourseViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailCourseViewModel viewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    private CourseEntity dummyCourse = FakeDataDummy.generateDummyCourses().get(0);
    private String courseId = dummyCourse.getCourseId();
    private ArrayList<ModuleEntity> dummyModules = FakeDataDummy.generateDummyModules(courseId);


    @Before
    public void setUp() {
        viewModel = new DetailCourseViewModel(academyRepository);
        viewModel.setCourseId(courseId);
    }

    @Test
    public void getCourse() {
        MutableLiveData<CourseEntity> courseEntities = new MutableLiveData<>();
        courseEntities.setValue(dummyCourse);

        when(academyRepository.getCourseWithModules(courseId)).thenReturn(courseEntities);

        Observer<CourseEntity> observer = mock(Observer.class);

        viewModel.getCourse().observeForever(observer);

        verify(observer).onChanged(dummyCourse);
    }

    @Test
    public void getModules() {
        MutableLiveData<List<ModuleEntity>> moduleEntities = new MutableLiveData<>();
        moduleEntities.setValue(dummyModules);

        when(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities);

        Observer<List<ModuleEntity>> observer = mock(Observer.class);

        viewModel.getModules().observeForever(observer);

        verify(observer).onChanged(dummyModules);
    }
}