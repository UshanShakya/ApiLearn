package ClientAPI;

import java.util.List;

import model.Hero;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HeroAPI{

    @GET("heroes")
    Call<List<Hero>> getHero();

    @POST("heroes")
    Call<Void> createHero(@Body Hero hero);
}
