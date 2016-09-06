package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by aleckeller on 3/30/16.
 */
public class CustomAdapter extends BaseAdapter {

    private final Activity_ListView myActivity;
    private LayoutInflater inflater;

    private class ViewHolder{
        TextView model;
        TextView price;
        TextView description;
        ImageView imageView;
    }

    public CustomAdapter(Activity_ListView myActivity){
        this.myActivity = myActivity;
        inflater = (LayoutInflater)myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Activity_ListView.bikeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder myVh;
        String correctUrl = "http://www.tetonsoftware.com/bikes/bikes.json";
        String addPic = "http://www.tetonsoftware.com/bikes/";

        if (convertView == null){

            convertView = inflater.inflate(R.layout.listview_row_layout,null);
            myVh = new ViewHolder();
            myVh.model = (TextView)convertView.findViewById(R.id.Model);
            myVh.price = (TextView)convertView.findViewById(R.id.Price);
            myVh.description = (TextView)convertView.findViewById(R.id.Description);
            myVh.imageView = (ImageView)convertView.findViewById(R.id.imageView1);

            convertView.setTag(myVh);
            }
        else{
                myVh = (ViewHolder)convertView.getTag();
            }
            myVh.model.setText(Activity_ListView.bikeList.get(position).MODEL);
            myVh.description.setText(Activity_ListView.bikeList.get(position).DESCRIPTION);
            String stringPrice = Double.toString(Activity_ListView.bikeList.get(position).PRICE);
            myVh.price.setText("$" + stringPrice);
            DownloadImageTask downloadImage = new DownloadImageTask(Activity_ListView.bikeList.get(position).PICTURE,myVh.imageView);
            if (Activity_ListView.URL.equals(correctUrl)){
                downloadImage.execute(addPic + Activity_ListView.bikeList.get(position).PICTURE);
            }
        return convertView;
    }
}
