package id.co.hasaneljabir.academy.ui.detail;

import androidx.lifecycle.ViewModel;

import java.util.List;

import id.co.hasaneljabir.academy.data.CourseEntity;
import id.co.hasaneljabir.academy.data.ModuleEntity;
import id.co.hasaneljabir.academy.utils.DataDummy;

public class DetailCourseViewModel extends ViewModel {
    private CourseEntity mCourse;
    private String courseId;

    public CourseEntity getCourse() {
        for (int i = 0; i < DataDummy.generateDummyCourses().size(); i++) {
            CourseEntity courseEntity = DataDummy.generateDummyCourses().get(i);
            if (courseEntity.getCourseId().equals(courseId)) {
                mCourse = courseEntity;
            }
        }
        return mCourse;
    }

    public List<ModuleEntity> getModules() {
        return DataDummy.generateDummyModules(getCourseId());
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }
}
