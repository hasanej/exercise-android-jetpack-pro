package id.co.hasaneljabir.academy.ui.reader;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import id.co.hasaneljabir.academy.data.ContentEntity;
import id.co.hasaneljabir.academy.data.CourseEntity;
import id.co.hasaneljabir.academy.data.ModuleEntity;
import id.co.hasaneljabir.academy.data.source.AcademyRepository;
import id.co.hasaneljabir.academy.ui.utils.FakeDataDummy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CourseReaderViewModelTest {
    private CourseReaderViewModel viewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    private CourseEntity dummyCourse = FakeDataDummy.generateDummyCourses().get(0);
    private String courseId = dummyCourse.getCourseId();
    private ArrayList<ModuleEntity> dummyModules = FakeDataDummy.generateDummyModules(courseId);
    private String moduleId = dummyModules.get(0).getmModuleId();

    @Before
    public void setUp() {
        viewModel = new CourseReaderViewModel(academyRepository);
        viewModel.setCourseId(courseId);
    }

    @Test
    public void getModules() {
        when(academyRepository.getAllModulesByCourse(courseId)).thenReturn(dummyModules);
        List<ModuleEntity> moduleEntities = viewModel.getModules();
        verify(academyRepository).getAllModulesByCourse(courseId);
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());
    }

    @Test
    public void getSelectedModule() {
        ModuleEntity moduleEntity = dummyModules.get(0);
        String content = "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>";
        moduleEntity.contentEntity = new ContentEntity(content);
        viewModel.setSelectedModule(moduleId);
        when(academyRepository.getContent(courseId, moduleId)).thenReturn(moduleEntity);
        ModuleEntity entity = viewModel.getSelectedModule();
        verify(academyRepository).getContent(courseId, moduleId);
        assertNotNull(entity);

        ContentEntity contentEntity = entity.contentEntity;
        assertNotNull(contentEntity);

        String resultContent = contentEntity.getContent();
        assertNotNull(resultContent);

        assertEquals(content, resultContent);
    }
}