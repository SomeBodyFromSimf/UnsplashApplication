package com.mihailchistousov.unsplashapplication.data.Network;
import com.mihailchistousov.unsplashapplication.model.Photo;

import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UnsplashAPI {

    @GET("/photos/?per_page=25")
    Observable<List<Photo>> getPhotos(@Query("page") Integer page, @Query("client_id") String key);

    @GET("/photos/{id}")
    Flowable<Photo> getCurrentPhotos(@Path("id") String id,@Query("client_id") String key);

}
