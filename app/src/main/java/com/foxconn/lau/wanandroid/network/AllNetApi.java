package com.foxconn.lau.wanandroid.network;

import com.foxconn.lau.wanandroid.bean.login.LoginResult;
import com.foxconn.lau.wanandroid.bean.main.ArticleInfo;
import com.foxconn.lau.wanandroid.bean.main.BannerInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/11/29 下午 03:16
 */
public interface AllNetApi {

    //登录https://www.wanandroid.com/user/login
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginResult> login(@Field("username")String username, @Field("password")String password);

    //https://www.wanandroid.com/user/register
    @POST("user/register")
    @FormUrlEncoded
    Observable<LoginResult> register(@Field("username") String username, @Field("password") String password,
                                @Field("repassword") String repassword);

    //https://www.wanandroid.com/banner/json
    @GET("banner/json")
    Observable<BannerInfo> getBanner();

    //https://www.wanandroid.com/article/list/0/json
    @GET("article/list/{page}/json")
    Observable<ArticleInfo> getArticle(@Path("page") int page);


}
