package id.co.hasaneljabir.academy.ui.detail;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import id.co.hasaneljabir.academy.data.CourseEntity;
import id.co.hasaneljabir.academy.data.ModuleEntity;
import id.co.hasaneljabir.academy.data.source.AcademyRepository;
import id.co.hasaneljabir.academy.ui.utils.FakeDataDummy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailCourseViewModelTest {
    private DetailCourseViewModel viewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    private CourseEntity dummyCourse = FakeDataDummy.generateDummyCourses().get(0);
    private String courseId = dummyCourse.getCourseId();


    @Before
    public void setUp() {
        viewModel = new DetailCourseViewModel(academyRepository);
        viewModel.setCourseId(courseId);
    }

    @Test
    public void getCourse() {
        when(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse);
        CourseEntity courseEntity = viewModel.getCourse();
        verify(academyRepository).getCourseWithModules(courseId);
        assertNotNull(courseEntity);
        String courseId = courseEntity.getCourseId();
        assertNotNull(courseId);
        assertEquals(dummyCourse.getCourseId(), courseId);
    }

    @Test
    public void getModules() {
        when(academyRepository.getAllModulesByCourse(courseId)).thenReturn(FakeDataDummy.generateDummyModules(courseId));
        List<ModuleEntity> moduleEntities = viewModel.getModules();
        verify(academyRepository).getAllModulesByCourse(courseId);
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());
    }
}