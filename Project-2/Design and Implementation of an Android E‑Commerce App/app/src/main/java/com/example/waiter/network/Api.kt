package com.example.waiter.network

import com.example.waiter.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @FormUrlEncoded
    @POST("Login/login")
    fun login(
            @Field("user") user: String,
            @Field("pass") pass: String,
            @Field("token") token: String
    ): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("Login/registrasi")
    fun signUp(
        @Field("no_hp") no_hp: String,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("password") pass: String
    ): Call<ResponseRegis>

    @GET("meja/meja")
    fun getmeja(
    ): Call<ResponseMeja>

    @FormUrlEncoded
    @POST("Meja/meja")
    fun mejaPost(
            @Field("id_user") id_user: String,
            @Field("id_meja") id_meja: String
    ): Call<Any>

    @FormUrlEncoded
    @POST("Meja/batal_meja")
    fun batalMejaPost(
            @Field("id_meja") id_meja: String
    ): Call<Any>


    @GET("kategori/kategori")
    fun getKategori(
    ): Call<ResponseKategori>

    @GET("kategori/kategori_detail/{id_kategori}")
    fun getMenu(
            @Path("id_kategori") id_kategori: String?,
            @Query("q") q: String?,
    ): Call<ResponseMenu>

    @GET("Menu/menu_all")
    fun getMenuAll(
        @Query("q") q: String?,
    ): Call<ResponseMenu>

    @GET("Keranjang/{id_user}")
    fun getTemp(
            @Path("id_user") id_user: String?
    ): Call<ResponseTemp>

    @FormUrlEncoded
    @POST("Keranjang/keranjang")
    fun posttemp(
            @Field("id_user") id_user: String,
            @Field("id_menu") id_menu: String,
            @Field("jumlah") jumlah: String,
            @Field("catatan") catatan: String
    ): Call<Any>

    @FormUrlEncoded
    @POST("Keranjang/update_keranjang")
    fun updateTemp(
            @Field("id_temp") id_temp: String,
            @Field("jumlah") jumlah: String
    ): Call<ResponsePostTemp>

    @FormUrlEncoded
    @POST("Keranjang/delete_keranjang")
    fun deleteTemp(
        @Field("id_temp") id_temp: String
    ): Call<ResponsePostTemp>

    @FormUrlEncoded
    @POST("transaksi/transaksi")
    fun postTransaksi(
        @Field("id_user") id_user : String,
        @Field("id_meja") id_meja : String,
        @Field("lat") lat : String,
        @Field("lng") lng : String,
        @Field("jenis_pesanan") jenis_pesanan : String,
    ): Call<Any>

    @FormUrlEncoded
    @POST("transaksi/transaksi")
    fun postTransaksitake(
        @Field("id_user") id_user : String,
        @Field("id_meja") id_meja : String,
        @Field("lat") lat : String,
        @Field("lng") lng : String,
        @Field("jenis_pesanan") pembayaran : String,
    ): Call<ResponsePostTransaksi>

    @GET("Transaksi/{id_user}")
    fun getTransaksi(
            @Path("id_user") id_user: String?
    ): Call<ResponseTransaksi>

    @GET("Transaksi/detail_transaksi/{id_transaksi}")
    fun getDetailTransaksi(
            @Path("id_transaksi") id_transaksi: String?
    ): Call<ResponseDetailTransaksi>

    @FormUrlEncoded
    @POST("transaksi/batal_transaksi")
    fun batalTransaksi(
            @Field("id_transaksi") id_transaksi : String
    ): Call<Any>

    @Multipart
    @POST("Transaksi/uploadBukti")
    fun uploadImage(
        @Part file: MultipartBody.Part?,
        @Part("id_transaksi") idTransaksi: RequestBody?
    ): Call<Any>
}