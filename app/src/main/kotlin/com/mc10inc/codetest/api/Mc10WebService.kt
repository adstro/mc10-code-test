package com.mc10inc.codetest.api

import com.mc10inc.codetest.model.Study
import io.reactivex.Single
import retrofit2.http.*
import java.util.*

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
interface Mc10WebService {
    @FormUrlEncoded
    @POST("api/v1/user/login/email")
    fun login(@Field("email") email: String,
              @Field("password") password: String): Single<LoginResponse>

    @GET("api/v1/accounts/{accountId}/studies")
    fun studies(@Path("accountId") accountId: UUID,
                @Header("Authorization") authorization: String): Single<List<Study>>
}