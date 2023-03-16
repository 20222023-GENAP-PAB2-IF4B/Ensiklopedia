package masterous.if4b.ensiklopedia.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import masterous.if4b.ensiklopedia.db.EnsiklopediaDatabase;

public class DeleteLoader extends AsyncTaskLoader<Integer> {
    private int ensiklopediaId;
    private EnsiklopediaDatabase db;

    public DeleteLoader(@NonNull Context context, int ensiklopediaId) {
        super(context);
        this.ensiklopediaId = ensiklopediaId;
        db = EnsiklopediaDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        return db.ensiklopediaDao().deleteEnsiklopedia(ensiklopediaId);
    }
}
