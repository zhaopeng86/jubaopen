package com.minigame.info.listview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.minigame.info.R;
import com.minigame.info.entity.GameItemBean;
import com.minigame.info.view.RectangleView;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.Random;

public class GamelistAdapter extends BaseAdapter {
    private WeakReference<Context> context;
    private GameItemBean[]  data;
    public GamelistAdapter(Context context,GameItemBean[] da){
        this.context=new WeakReference<>(context);
        this.data=da;
    }
    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView ==null){
            if (context.get()!=null){

                convertView = LayoutInflater.from(context.get()).inflate(R.layout.gameitem,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.imageButton = (RectangleView) convertView.findViewById(R.id.imbtn);
                viewHolder.textViewDes = (TextView) convertView.findViewById(R.id.descrition);
                viewHolder.DesDetaile=convertView.findViewById(R.id.detail);
                viewHolder.ratingBar=convertView.findViewById(R.id.rating);
                viewHolder.onLineNumber=convertView.findViewById(R.id.onlinenumber);
                convertView.setTag(viewHolder);
            }
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewDes.setText(data[position].getDes());
        viewHolder.DesDetaile.setText(data[position].getDetatle());

        try {
            viewHolder.ratingBar.setRating(Integer.parseInt(data[position].getRating()));
        }catch (Exception e){
            e.printStackTrace();
        }

        viewHolder.onLineNumber.setText(data[position].getOnlineNumber()+"人在线");

        if (!TextUtils.isEmpty(data[position].getImageUrl())){
            Picasso.get()
                    .load(data[position].getImageUrl())  // 图片的 uri，可以是网络链接，也可以说本地资源
                    .into(viewHolder.imageButton);
        }else {
            Picasso.get()
                    .load(data[0].getImageUrl())  // 图片的 uri，可以是网络链接，也可以说本地资源
                    .into(viewHolder.imageButton);
        }
        return convertView;
    }
    class ViewHolder{
        RectangleView imageButton;
        TextView textViewDes;
        TextView DesDetaile;
        RatingBar ratingBar;

        TextView onLineNumber;
    }
}
