package me.newsapp.listview.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.newsapp.listview.data.rest.APIService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static me.newsapp.listview.util.Constants.BASE_URL;

@Singleton
@Module(includes = ViewModelModule.class)
public class ApplicationModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static APIService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }
}
