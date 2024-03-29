package id.co.hasaneljabir.academy.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import id.co.hasaneljabir.academy.R;
import id.co.hasaneljabir.academy.ui.reader.CourseReaderCallback;
import id.co.hasaneljabir.academy.ui.reader.CourseReaderViewModel;
import id.co.hasaneljabir.academy.ui.reader.content.ModuleContentFragment;
import id.co.hasaneljabir.academy.ui.reader.list.ModuleListFragment;
import id.co.hasaneljabir.academy.viewmodel.ViewModelFactory;

public class CourseReaderActivity extends AppCompatActivity implements CourseReaderCallback {

    public static final String EXTRA_COURSE_ID = "extra_course_id";
    private CourseReaderViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_reader);

        viewModel = obtainViewModel(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String courseId = bundle.getString(EXTRA_COURSE_ID);
            if (courseId != null) {
                viewModel.setCourseId(courseId);
                populateFragment();
            }
        }
    }

    @Override
    public void moveTo(int position, String moduleId) {
        Fragment fragment = ModuleContentFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment, ModuleContentFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void populateFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(ModuleListFragment.TAG);
        if (fragment == null) {
            fragment = ModuleListFragment.newInstance();
            fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment.TAG);
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @NonNull
    private static CourseReaderViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel.class);
    }
}
