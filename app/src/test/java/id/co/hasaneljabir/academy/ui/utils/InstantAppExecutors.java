package id.co.hasaneljabir.academy.ui.utils;

import java.util.concurrent.Executor;

import id.co.hasaneljabir.academy.utils.AppExecutors;

public class InstantAppExecutors extends AppExecutors {
    private static Executor instant = Runnable::run;

    public InstantAppExecutors() {
        super(instant, instant, instant);
    }
}
