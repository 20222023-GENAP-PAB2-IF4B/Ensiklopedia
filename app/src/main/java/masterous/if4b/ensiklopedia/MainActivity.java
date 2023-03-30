package masterous.if4b.ensiklopedia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import masterous.if4b.ensiklopedia.databinding.ActivityMainBinding;
import masterous.if4b.ensiklopedia.db.Ensiklopedia;
import masterous.if4b.ensiklopedia.loaders.DeleteLoader;
import masterous.if4b.ensiklopedia.loaders.GetDataLoader;

public class MainActivity extends AppCompatActivity {
    private static final int DATA_LOADER_CODE = 123;
    private static final int DELETE_LOADER_CODE = 124;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        showProgressBar();
        LoaderManager.getInstance(this).restartLoader(DATA_LOADER_CODE, null, new LoaderManager.LoaderCallbacks<List<Ensiklopedia>>() {
            @NonNull
            @Override
            public Loader<List<Ensiklopedia>> onCreateLoader(int id, @Nullable Bundle args) {
                return new GetDataLoader(MainActivity.this);
            }

            @Override
            public void onLoadFinished(@NonNull Loader<List<Ensiklopedia>> loader, List<Ensiklopedia> data) {
                hideProgressBar();
                initAdapter(data);
            }

            @Override
            public void onLoaderReset(@NonNull Loader<List<Ensiklopedia>> loader) {

            }
        }).forceLoad();
    }

    private void initAdapter(List<Ensiklopedia> data) {
        EnsiklopediaViewAdapter adapter = new EnsiklopediaViewAdapter();
        binding.rvEnsiklopedia.setLayoutManager(new LinearLayoutManager(this));
        binding.rvEnsiklopedia.setAdapter(adapter);

        adapter.setData(data);
        adapter.setOnClickListener(new EnsiklopediaViewAdapter.OnClickListener() {
            @Override
            public void onEditClicked(Ensiklopedia ensiklopedia) {
                gotoUpdateEnsiklopediaActivity(ensiklopedia);
            }

            @Override
            public void onDeleteClicked(int id) {
                deleteEnsiklopedia(id);
            }
        });
    }

    private void deleteEnsiklopedia(int id) {
        showProgressBar();
        Bundle args = new Bundle();
        args.putInt("id", id);
        LoaderManager.getInstance(this).restartLoader(DELETE_LOADER_CODE, args, new LoaderManager.LoaderCallbacks<Integer>() {
            @NonNull
            @Override
            public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {
                return new DeleteLoader(MainActivity.this, args.getInt("id"));
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
                hideProgressBar();
                if (data != -1) {
                    itemDeleted();
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Integer> loader) {

            }
        }).forceLoad();
    }

    private void itemDeleted() {
        Toast.makeText(this, "Item deleted!", Toast.LENGTH_SHORT).show();
        getData();
    }

    private void gotoUpdateEnsiklopediaActivity(Ensiklopedia ensiklopedia) {

    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }
}