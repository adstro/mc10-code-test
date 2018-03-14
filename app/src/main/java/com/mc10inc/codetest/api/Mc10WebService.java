package com.mc10inc.codetest.api;

import com.mc10inc.codetest.model.Study;

import java.util.List;
import java.util.UUID;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public interface Mc10WebService {
    @FormUrlEncoded
    @POST("api/v1/user/login/email")
    public Single<LoginResponse> login(@Field("email") String email,
              @Field("password") String password);

    @GET("api/v1/accounts/{accountId}/studies")
    public Single<List<Study>> studies(@Path("accountId") UUID accountId,
                @Header("Authorization") String authorization);
}
