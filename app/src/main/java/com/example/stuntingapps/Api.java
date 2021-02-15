package com.example.stuntingapps;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("user_name") String user_name,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("allusers")
    Call<UsersResponse> getUsers();

    @FormUrlEncoded
    @PUT("updateuser/{user_id}")
    Call<LoginResponse> updateUser(
            @Path("user_id") int id,
            @Field("user_name") String user_name,
            @Field("email") String email
    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );

    @DELETE("deleteuser/{user_id}")
    Call<DefaultResponse> deleteUser(@Path("user_id") int user_id);

    @FormUrlEncoded
    @POST("createdataibu")
    Call<DefaultResponse> createDataIbu(
            @Field("user_id") int user_id,
            @Field("nama_ibu") String nama_ibu,
            @Field("tanggal_lahir_ibu") String tanggal_lahir_ibu,
            @Field("tinggi_badan_ibu") double tinggi_badan_ibu,
            @Field("berat_badan_ibu") double berat_badan_ibu,
            @Field("jumlah_anak_ibu") int jumlah_anak_ibu,
            @Field("tempat_tinggal") String tempat_tinggal,
            @Field("tingkat_pendidikan") String tingkat_pendidikan,
            @Field("pekerjaan") String pekerjaan,
            @Field("pekerjaan_desc") String pekerjaan_desc,
            @Field("wealth_index") String wealth_index

/*            @Field("nama_ibu") String user_id,
            @Field("nama_ibu") String nama_ibu,
            @Field("tanggal_lahir_ibu") String tanggal_lahir_ibu,
            @Field("tinggi_badan_ibu") String tinggi_badan_ibu,
            @Field("berat_badan_ibu") String berat_badan_ibu,
            @Field("jumlah_anak_ibu") String jumlah_anak_ibu,
            @Field("tempat_tinggal") String tempat_tinggal,
            @Field("tingkat_pendidikan") String tingkat_pendidikan,
            @Field("pekerjaan") String pekerjaan,
            @Field("pekerjaan_desc") String pekerjaan_desc,
            @Field("wealth_index") String wealth_index*/
    );

    //ibu_id, nama_anak, tanggal_lahir_anak, berat_lahir_anak, tinggi_badan_anak, berat_badan_anak
    //    , jenis_kelamin_anak, penyakit_anemia_anak
    @FormUrlEncoded
    @POST("createdataanak")
    Call<DefaultResponse> createDataAnak(
            @Field("ibu_id") int ibu_id,
            @Field("nama_anak") String nama_anak,
            @Field("tanggal_lahir_anak") String tanggal_lahir_anak,
            @Field("berat_lahir_anak") String berat_lahir_anak,
            @Field("tinggi_badan_anak") double tinggi_badan_anak,
            @Field("berat_badan_anak") double berat_badan_anak,
            @Field("jenis_kelamin_anak") String jenis_kelamin_anak,
            @Field("penyakit_anemia_anak") String penyakit_anemia_anak

    );

    @GET("getuser/{email}")
    Call<UsersResponse> getUserByEmail(
            @Path("email") String email
    );

    @GET("getibu/{user_id}")
    Call<IbusResponse> getIbuByUserId(
            @Path("user_id") int user_id
    );
}
