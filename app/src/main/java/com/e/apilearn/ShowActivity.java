package com.e.apilearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ClientAPI.HeroAPI;
import model.Hero;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowActivity extends AppCompatActivity {
    private TextView tvHeroID, tvHeroName, tvHeroDesc, tvHeroImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        tvHeroID = findViewById(R.id.tvHeroID);
        tvHeroName = findViewById(R.id.tvHeroName);
        tvHeroDesc = findViewById(R.id.tvHeroDesc);
        tvHeroImage = findViewById(R.id.tvHeroImage);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroAPI heroAPI = retrofit.create(HeroAPI.class);

        Call<List<Hero>> listCall =heroAPI.getHero();

        listCall.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Code"+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Hero> heroList = response.body();
                for (Hero hero: heroList){
                    tvHeroDesc.setText(hero.getDesc());
                    tvHeroID.setText(hero.getId());
                    tvHeroImage.setText(hero.getImage());
                    tvHeroName.setText(hero.getName());


                }
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error"+t.getMessage(),Toast.LENGTH_SHORT).show();



            }
        });
    }
}
