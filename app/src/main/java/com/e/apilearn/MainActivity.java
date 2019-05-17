package com.e.apilearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ClientAPI.HeroAPI;
import model.Hero;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText etName,etDesc,etImage;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        etImage = findViewById(R.id.etImage);

        btnSave = findViewById(R.id.btnSave);
        
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create();
            }
        });
    }

    private void Create() {

        String name,image,desc;
        name = etName.getText().toString();
        desc = etDesc.getText().toString();
        image = etImage.getText().toString();

        Hero hero = new Hero(image, name, desc);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroAPI heroAPI = retrofit.create(HeroAPI.class);
        Call<Void> voidCall = heroAPI.createHero(hero);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();

            }
        });



    }
}
