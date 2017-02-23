package au.edu.holmesglen.maryan.threeinarow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by maryanmendozanunez on 31/10/16.
 */

public class ImageAdapter extends BaseAdapter {

    Item[]gridArray;
    private Context mContext;

    public ImageAdapter(Context c, Item[] gridArray) {
        mContext = c;
        this.gridArray=gridArray;
    }

    @Override
    public int getCount() {
        return gridArray.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView==null)
        {

            imageView=new ImageView(mContext);
            int width=((GridView)parent).getColumnWidth();
            imageView.setLayoutParams(new GridView.LayoutParams(width,width));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(0,0,0,0);
        }else{
            imageView= (ImageView) convertView;
        }
        imageView.setImageResource(gridArray[position].getColor());
        return imageView;
    }
}

