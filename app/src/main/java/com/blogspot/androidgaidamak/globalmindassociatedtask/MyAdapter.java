package com.blogspot.androidgaidamak.globalmindassociatedtask;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gaidamak on 6/18/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CarViewHolder> {
    private List<Car> mDataset;
    private boolean mIsLoggedIn;

    public MyAdapter(List<Car> dataset, boolean isLoggedIn) {
        mDataset = dataset;
        mIsLoggedIn = isLoggedIn;
    }

    public MyAdapter(Car[] dataset, boolean isLoggedIn) {
        this(Arrays.asList(dataset), isLoggedIn);
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_car, parent, false);
        CarViewHolder vh = new CarViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        holder.bindCar(mDataset.get(position));
    }

    class CarViewHolder extends RecyclerView.ViewHolder {
        private TextView mainInfoTextView;
        private TextView additionalInfoTextView;
        private boolean mIsExpanded = false;

        public CarViewHolder(View itemView) {
            super(itemView);
            mainInfoTextView = (TextView) itemView.findViewById(R.id.mainInfoTextView);
            additionalInfoTextView = (TextView) itemView.findViewById(R.id.additionalTextView);
        }

        public void bindCar(Car car) {
            mainInfoTextView.setText("|" + car.vehicleType + "|" + car.brand + "|" + car.color
                    + "|" + car.state);
            additionalInfoTextView.setVisibility(View.GONE);

            if (mIsLoggedIn) {
                mainInfoTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mIsExpanded = !mIsExpanded;
                        if (mIsExpanded) {
                            additionalInfoTextView.setVisibility(View.VISIBLE);
                        } else {
                            additionalInfoTextView.setVisibility(View.GONE);
                        }
                    }
                });
                additionalInfoTextView.setText("|" + car.contactName + "|" + car.contactPhone);
            }
        }
    }
}
