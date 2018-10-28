package com.firman.tmdbpradita;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PopularFragment extends Fragment {

    private final String TAG = PopularFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private MovieAdapter adapter;

    private List<Movie> movies = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.listMovie);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MovieAdapter(movies);
        recyclerView.setAdapter(adapter);

        Rx2AndroidNetworking.get(Constant.BASE_URL+"movie/popular?")
                .addQueryParameter("api_key", Constant.API_KEY)
                .build()
                .getJSONObjectObservable()
                .map(jsonObject -> {
                    List<Movie> movies = new ArrayList<>();

                    JSONArray array = jsonObject.getJSONArray("results");

                    for (int i=0;i<array.length();i++) {
                        JSONObject object = array.getJSONObject(i);
                        Movie movie = new Movie(
                                object.optInt("id"),
                                object.optString("title"),
                                object.optString("overview"),
                                object.optString("poster_path"),
                                object.optString("backdrop_path")
                        );
                        movies.add(movie);
                    }

                    return movies;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                    this.movies.clear();
                    this.movies.addAll(movies);
                    adapter.notifyDataSetChanged();
                }, throwable -> {
                    Log.d(TAG, throwable.toString());
                    Log.d(TAG, "message="+throwable.getCause());

                    Toast.makeText(getActivity(), "Something error.", Toast.LENGTH_SHORT).show();
                });
    }


}
