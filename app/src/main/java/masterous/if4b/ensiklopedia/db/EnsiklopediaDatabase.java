package masterous.if4b.ensiklopedia.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Ensiklopedia.class, exportSchema = false, version = 1)
public abstract class EnsiklopediaDatabase extends RoomDatabase {
    private static final String DB_NAME = "ensiklopedia_db";
    private static EnsiklopediaDatabase instance;

    public static synchronized EnsiklopediaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EnsiklopediaDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract EnsiklopediaDao ensiklopediaDao();
}
