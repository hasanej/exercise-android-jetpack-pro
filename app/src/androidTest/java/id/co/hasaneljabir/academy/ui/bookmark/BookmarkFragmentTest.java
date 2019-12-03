package id.co.hasaneljabir.academy.ui.bookmark;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.co.hasaneljabir.academy.R;
import id.co.hasaneljabir.academy.testing.SingleFragmentActivity;
import id.co.hasaneljabir.academy.utils.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class BookmarkFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private BookmarkFragment bookmarkFragment = new BookmarkFragment();

    @Before
    public void setUp() {
        activityRule.getActivity().setFragment(bookmarkFragment);
    }

    @Test
    public void loadBookmarks() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_bookmark)).check(new RecyclerViewItemCountAssertion(5));
    }
}