package com.teng.androidfactory.function.Service;

import com.teng.androidfactory.function.model.HttpResult;
import com.teng.androidfactory.function.model.MenuModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ConnectService {


    @GET("cook/query")
    Observable<HttpResult<MenuModel>> getMenuList(@Query("menu") String menu ,
                                                  @Query("pn") String pn,
                                                  @Query("rn") String rn,
                                                  @Query("key") String key);

//    @FormUrlEncoded
//    @POST("app/mechanism/findTeacher.do")
//    Observable<MainTeacherEntity> getTeacherList(@FieldMap Map<String, String> params);

}
