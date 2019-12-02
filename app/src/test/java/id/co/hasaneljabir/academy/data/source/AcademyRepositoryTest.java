package id.co.hasaneljabir.academy.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import id.co.hasaneljabir.academy.data.CourseEntity;
import id.co.hasaneljabir.academy.data.ModuleEntity;
import id.co.hasaneljabir.academy.data.source.remote.RemoteRepository;
import id.co.hasaneljabir.academy.data.source.remote.response.ContentResponse;
import id.co.hasaneljabir.academy.data.source.remote.response.CourseResponse;
import id.co.hasaneljabir.academy.data.source.remote.response.ModuleResponse;
import id.co.hasaneljabir.academy.ui.utils.FakeDataDummy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcademyRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remote = Mockito.mock(RemoteRepository.class);
    private FakeAcademyRepository academyRepository = new FakeAcademyRepository(remote);

    private ArrayList<CourseResponse> courseResponses = FakeDataDummy.generateRemoteDummyCourses();
    private String courseId = courseResponses.get(0).getId();
    private ArrayList<ModuleResponse> moduleResponses = FakeDataDummy.generateRemoteDummyModules(courseId);
    private String moduleId = moduleResponses.get(0).getModuleId();
    private ContentResponse content = FakeDataDummy.generateRemoteDummyContent(moduleId);

    @Test
    public void getAllCourses() {
        when(remote.getAllCourses()).thenReturn(courseResponses);
        ArrayList<CourseEntity> courseEntities = academyRepository.getAllCourses();
        verify(remote).getAllCourses();
        assertNotNull(courseEntities);
        assertEquals(courseResponses.size(), courseEntities.size());
    }

    @Test
    public void getAllModulesByCourse() {
        when(remote.getModules(courseId)).thenReturn(moduleResponses);
        ArrayList<ModuleEntity> moduleEntities = academyRepository.getAllModulesByCourse(courseId);
        verify(remote).getModules(courseId);
        assertNotNull(moduleEntities);
        assertEquals(moduleResponses.size(), moduleEntities.size());
    }

    @Test
    public void getBookmarkedCourses() {
        when(remote.getAllCourses()).thenReturn(courseResponses);
        ArrayList<CourseEntity> courseEntities = academyRepository.getBookmarkedCourses();
        verify(remote).getAllCourses();
        assertNotNull(courseEntities);
        assertEquals(courseResponses.size(), courseEntities.size());
    }

    @Test
    public void getContent() {
        when(remote.getModules(courseId)).thenReturn(moduleResponses);
        when(remote.getContent(moduleId)).thenReturn(content);
        ModuleEntity resultModule = academyRepository.getContent(courseId, moduleId);
        verify(remote).getContent(moduleId);
        assertNotNull(resultModule);
        assertEquals(content.getContent(), resultModule.contentEntity.getContent());
    }


    @Test
    public void getCourseWithModules() {
        when(remote.getAllCourses()).thenReturn(courseResponses);
        CourseEntity resultCourse = academyRepository.getCourseWithModules(courseId);
        verify(remote).getAllCourses();
        assertNotNull(resultCourse);
        assertEquals(courseResponses.get(0).getTitle(), resultCourse.getTitle());
    }
}