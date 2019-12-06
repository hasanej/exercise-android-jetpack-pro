package id.co.hasaneljabir.academy.ui.bookmark;

import id.co.hasaneljabir.academy.data.source.local.entity.CourseEntity;

interface BookmarkFragmentCallback {
    void onShareClick(CourseEntity course);
}
