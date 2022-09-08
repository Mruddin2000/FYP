package com.example.progressChecker.teachOwnWay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progressChecker.R;

import java.util.ArrayList;


public class ImageListAdapter extends BaseAdapter {

    private final Context context;
    private final int layout;
    private final ArrayList<Image> imageList;

    public ImageListAdapter(Context context, int layout, ArrayList<Image> imageList) {
        this.context = context;
        this.layout = layout;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtDesc;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = row.findViewById(R.id.txtName);
            holder.txtDesc = row.findViewById(R.id.txtDesc);
            holder.imageView = row.findViewById(R.id.imgSrc);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Image img = imageList.get(position);

        holder.txtName.setText(img.getName());
        holder.txtDesc.setText(img.getDesc());

        byte[] image = img.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
