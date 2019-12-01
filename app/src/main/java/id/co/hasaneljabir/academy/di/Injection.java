package id.co.hasaneljabir.academy.di;

import android.app.Application;

import id.co.hasaneljabir.academy.data.source.AcademyRepository;
import id.co.hasaneljabir.academy.data.source.remote.RemoteRepository;
import id.co.hasaneljabir.academy.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Application application) {

        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));

        return AcademyRepository.getInstance(remoteRepository);
    }
}
