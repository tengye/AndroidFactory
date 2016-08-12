package com.teng.androidfactory.Service;

import com.teng.androidfactory.model.HttpResult;
import com.teng.androidfactory.model.Menu;
import com.teng.androidfactory.model.MenuModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by teng on 16/8/11.
 *
 *  www.app.net/api/searchtypes/862189/filters?Type=6&SearchText=School
 *
 *  www.app.net/api/searchtypes/{Path}/filters?Type={Query}&SearchText={Query}
 *
 */
public interface MenuService  {

//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();

//    "menu={menuName}" + // 菜单名
//            "&dtype=json" + // 数据类型格式
//            "&pn=3" +       // 数据返回的起始下标
//            "&rn=20" +      // 返回的最大的条数
//            "&albums=" +    // albums字段类型，1字符串，默认数组
//            "&=" +
//            "&key=bfea5cd2bb0e4f51142f72a2f0c6303f";

    @GET("cook/query")
    Observable<HttpResult<MenuModel>> getMenuList(@Query("menu") String menu ,
//                                      @Query("dtype") String dtype ,
                                                  @Query("pn") String pn,
                                                  @Query("rn") String rn,
                                                  @Query("key") String key);
}
