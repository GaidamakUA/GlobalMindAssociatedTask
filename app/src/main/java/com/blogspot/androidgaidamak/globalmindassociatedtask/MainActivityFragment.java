package com.blogspot.androidgaidamak.globalmindassociatedtask;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = "MainActivityFragment";
    private RecyclerView mRecyclerView;
    private boolean mIsLoggedIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsLoggedIn = getArguments() != null
                && getArguments().getBoolean(MainActivity.IS_LOGGED_IN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setAdapter(new MyAdapter(new ArrayList<Car>() {{
//            add(new Car("автомобиль", "BMW", "red", "б/у", "Антон", "+38123123456"));
//            add(new Car("автомобиль", "BMW", "red", "б/у", "Антон", "+38123123456"));
//        }}));
        getData(new DataCallback() {
            @Override
            public void onDataReceived(final Car[] carData) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.setAdapter(new MyAdapter(carData, mIsLoggedIn));
                    }
                });
            }
        });
        return mRecyclerView;
    }

    // Toy implementation of data provider
    private void getData(final DataCallback callback) {
        ExecutorService singleThreadExecutorService = Executors.newSingleThreadExecutor();
        // It is possible to rewrite this runnable to do something useful,
        // but any library would do a better job
        Runnable getDataRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Car[] testData = new Car[]{
                        new Car("автомобиль", "BMW", "red", "б/у", "Антон", "+38123123456")
//                    add(new Car("автомобиль", "BMW", "red", "б/у", "Антон", "+38123123456"));
                };
                Gson gson = new Gson();
                String testJson = gson.toJson(testData);
                Log.v(TAG, "testJson=" + testJson);
                callback.onDataReceived(gson.fromJson(testJson, Car[].class));
            }
        };
        singleThreadExecutorService.submit(getDataRunnable);
    }


    public interface DataCallback {
        void onDataReceived(Car[] carData);
    }
}
