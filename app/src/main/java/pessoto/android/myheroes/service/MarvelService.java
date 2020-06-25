package pessoto.android.myheroes.service;

import java.util.List;

import pessoto.android.myheroes.model.Marvel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MarvelService {

    @GET("characters?ts=1593021387&apikey=8d871c0bc7c23a81ba3e2724f812040a&hash=4f2e348e25805e591e6b03cfad430aeb&limit=6")
    Call<Marvel> listMarvel();

}
