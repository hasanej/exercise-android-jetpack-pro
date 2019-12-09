package id.co.hasaneljabir.academy.di;

import android.app.Application;

import id.co.hasaneljabir.academy.data.source.AcademyRepository;
import id.co.hasaneljabir.academy.data.source.local.LocalRepository;
import id.co.hasaneljabir.academy.data.source.local.room.AcademyDatabase;
import id.co.hasaneljabir.academy.data.source.remote.RemoteRepository;
import id.co.hasaneljabir.academy.utils.AppExecutors;
import id.co.hasaneljabir.academy.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Application application) {
        AcademyDatabase database = AcademyDatabase.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(database.academyDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
        AppExecutors appExecutors = new AppExecutors();

        return AcademyRepository.getInstance(localRepository, remoteRepository, appExecutors);
    }
}
