package masterous.if4b.ensiklopedia.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

import masterous.if4b.ensiklopedia.db.Ensiklopedia;
import masterous.if4b.ensiklopedia.db.EnsiklopediaDatabase;

public class GetDataLoader extends AsyncTaskLoader<List<Ensiklopedia>> {
    private EnsiklopediaDatabase db;

    public GetDataLoader(@NonNull Context context) {
        super(context);
        db = EnsiklopediaDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public List<Ensiklopedia> loadInBackground() {
        return db.ensiklopediaDao().getAllEnsiklopedia();
    }
}
