package com.team3.aegisapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ResourceRow extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mArrResourceRowData;
    private ArrayList<String> resourcesData;
    private Activity activity;

    public ResourceRow(Context context, ArrayList arrResourceRowData, ArrayList arrResourcesData, Activity arrActivity) {
        // CONSTRUCTOR
        super();

        // set class data to the passed in data
        mContext = context;
        mArrResourceRowData = arrResourceRowData;
        resourcesData = arrResourcesData;
        activity = arrActivity;
    }

    public int getCount() {
        // return the number of records
        return mArrResourceRowData.size();
    }

    // getView method is called for each item of ListView
    public View getView(int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_listview_row, parent, false);


        // get the reference of textView and button
        TextView txtResourceRowTitle = (TextView) view.findViewById(R.id.txtResourceRowTitle);
        Button btnAction = (Button) view.findViewById(R.id.btnAction);

        // Set the title and button name
        txtResourceRowTitle.setText(mArrResourceRowData.get(position));

        final int position2 = position;

        // Click listener of button
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the url from the position we are at in the array
                String url = resourcesData.get(position2);
                // start new activity to go to the URL specified
                InformationPresenter presenter = new InformationPresenter(activity);
                presenter.goToInternet(url);

                //To put the elements in Admin Mode
                AdminListener smallListener = new AdminListener(url, System.currentTimeMillis());
                AdminSingleton.getInstance().setMap(url, smallListener);
            }
        });

        return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }}
