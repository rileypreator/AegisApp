package com.team3.aegisapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdminAdapter extends ArrayAdapter {
    //https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc
    //Helped me create the view that I needed
    //Arrays to store the data for each of the different links
    private final Activity context;
    private final String[] imageArray;
    private final String[] linkArray;
    private final String[] lastClickedArray;
    private final Integer[] timesClickedArray;


    //CONSTRUCTOR
    public AdminAdapter(Activity context, String[] linkArrayParam, String[] lastClickedArrayParam,
                        Integer[] timesClickedArrayParam, String[] imageIDArrayParam){
        super(context,R.layout.admin_linear_row , linkArrayParam);

        //sets the arrays to the passed in values
        this.context=context;
        this.imageArray = imageIDArrayParam;
        this.linkArray = linkArrayParam;
        this.lastClickedArray = lastClickedArrayParam;
        this.timesClickedArray = timesClickedArrayParam;
    }

    //This is a function that gets automatically called each time that AdminInfoActivity
    //This displays the information in order based on the array
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        //Sets the information in in the custom View to be used in the list view.
        View rowView=inflater.inflate(R.layout.admin_linear_row, null,true);

        //this sets the Textview of each of the fields for each row that is added
        TextView linkTextField = (TextView) rowView.findViewById(R.id.nameOfLink);
        TextView timeLastClickedField = (TextView) rowView.findViewById(R.id.timelastClicked);
        TextView numberOfTimesField = (TextView) rowView.findViewById(R.id.numberOfTimes);
        TextView abuseTypePhoto = (TextView) rowView.findViewById(R.id.abuseType);

        //Sets the information in each field in their correct position in each row
        linkTextField.setText(linkArray[position]);
        timeLastClickedField.setText(lastClickedArray[position]);
        if (timesClickedArray[position] != null)
            numberOfTimesField.setText(Integer.toString(timesClickedArray[position]));
        else
            numberOfTimesField.setText("");
        abuseTypePhoto.setText(imageArray[position]);


        return rowView;
    }
}
