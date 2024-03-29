package id.co.hasaneljabir.academy.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.co.hasaneljabir.academy.data.source.local.entity.CourseEntity;
import id.co.hasaneljabir.academy.data.source.local.entity.ModuleEntity;

@Database(entities = {CourseEntity.class, ModuleEntity.class},
        version = 1,
        exportSchema = false)
public abstract class AcademyDatabase extends RoomDatabase {

    private static AcademyDatabase INSTANCE;

    public abstract AcademyDao academyDao();

    private static final Object sLock = new Object();

    public static AcademyDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AcademyDatabase.class, "Academies.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}
