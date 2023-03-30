package masterous.if4b.ensiklopedia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import masterous.if4b.ensiklopedia.databinding.ActivityInputBinding;
import masterous.if4b.ensiklopedia.db.Ensiklopedia;
import masterous.if4b.ensiklopedia.loaders.InsertLoader;
import masterous.if4b.ensiklopedia.loaders.UpdateDataLoader;

public class InputActivity extends AppCompatActivity {
    private static final int INSERT_LOADER = 121;
    private static final int UPDATE_LOADER = 122;
    private ActivityInputBinding binding;
    private boolean editMode;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editMode = getIntent().getBooleanExtra("edit", false);
        if (editMode) {
            Ensiklopedia ensiklopedia = getIntent().getParcelableExtra("ensiklopedia");
            id = ensiklopedia.getId();
            setDetails(ensiklopedia);
        }
        
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    if (editMode) {
                        updateData();
                    } else {
                        insertData();
                    }
                }
            }
        });
    }

    private void setDetails(Ensiklopedia ensiklopedia) {
        binding.etTitle.setText(ensiklopedia.getTitle());
        binding.etDescription.setText(ensiklopedia.getDescription());
        binding.btnAdd.setText("Update");
    }

    private void insertData() {
        Ensiklopedia ensiklopedia = new Ensiklopedia();
        ensiklopedia.setTitle(binding.etTitle.getText().toString());
        ensiklopedia.setDescription(binding.etDescription.getText().toString());
        showProgressBar();
        Bundle args = new Bundle();
        args.putParcelable("ensiklopedia", ensiklopedia);
        LoaderManager.getInstance(this).restartLoader(INSERT_LOADER, args, new LoaderManager.LoaderCallbacks<Boolean>() {
            @NonNull
            @Override
            public Loader<Boolean> onCreateLoader(int id, @Nullable Bundle args) {
                return new InsertLoader(InputActivity.this, args.getParcelable("ensiklopedia"));
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Boolean> loader, Boolean data) {
                hideProgressBar();
                if (data) {
                    itemAdded();
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Boolean> loader) {

            }
        }).forceLoad();
    }

    private void itemAdded() {
        binding.etTitle.setText("");
        binding.etDescription.setText("");
        Toast.makeText(this, "Item added to database!", Toast.LENGTH_SHORT).show();
    }

    private void updateData() {
        Ensiklopedia ensiklopedia = new Ensiklopedia();
        ensiklopedia.setId(id);
        ensiklopedia.setTitle(binding.etTitle.getText().toString());
        ensiklopedia.setDescription(binding.etDescription.getText().toString());
        showProgressBar();
        Bundle args = new Bundle();
        args.putParcelable("ensiklopedia", ensiklopedia);
        LoaderManager.getInstance(this).restartLoader(UPDATE_LOADER, args, new LoaderManager.LoaderCallbacks<Integer>() {
            @NonNull
            @Override
            public Loader<Integer> onCreateLoader(int id, @Nullable Bundle args) {
                return new UpdateDataLoader(InputActivity.this, ensiklopedia);
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
                hideProgressBar();
                if (data != -1) {
                    itemUpdated();
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Integer> loader) {

            }
        }).forceLoad();
    }

    private void itemUpdated() {
        setResult(RESULT_OK);
        finish();
    }

    private boolean validateFields() {
        if (binding.etTitle.getText().toString().equals("")
            || binding.etDescription.getText().toString().equals("")) {
            Toast.makeText(this,
                    "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }
}