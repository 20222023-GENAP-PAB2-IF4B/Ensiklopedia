package masterous.if4b.ensiklopedia.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import masterous.if4b.ensiklopedia.db.Ensiklopedia;
import masterous.if4b.ensiklopedia.db.EnsiklopediaDatabase;

public class UpdateDataLoader extends AsyncTaskLoader<Integer> {
    private Ensiklopedia ensiklopedia;
    private EnsiklopediaDatabase db;

    public UpdateDataLoader(@NonNull Context context, Ensiklopedia ensiklopedia) {
        super(context);
        this.ensiklopedia = ensiklopedia;
        db = EnsiklopediaDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        return db.ensiklopediaDao().updateEnsiklopedia(ensiklopedia);
    }
}
