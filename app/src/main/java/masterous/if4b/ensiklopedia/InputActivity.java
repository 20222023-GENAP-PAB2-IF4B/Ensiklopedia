package masterous.if4b.ensiklopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import masterous.if4b.ensiklopedia.databinding.ActivityInputBinding;

public class InputActivity extends AppCompatActivity {
    private ActivityInputBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}