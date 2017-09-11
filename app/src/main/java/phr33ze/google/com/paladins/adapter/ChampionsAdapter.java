package phr33ze.google.com.paladins.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import phr33ze.google.com.paladins.R;
import phr33ze.google.com.paladins.model.Champion;

/**
 * Created by Des. Android on 11/09/2017.
 */

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.ViewHolder> {

    public static final String DRAWABLE = "drawable";
    private static final String ICON_TERRITORIO = "icon_champion_";
    private final Resources mResources;
    private final String mPackageName;
    private final Activity mActivity;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<Champion> champions;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public ChampionsAdapter(Activity activity, List<Champion> data) {
        mActivity = activity;
        mContext = mActivity.getApplicationContext();
        mResources = mActivity.getResources();
        mPackageName = mActivity.getPackageName();
        mLayoutInflater = LayoutInflater.from(activity.getApplicationContext());
        champions = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_champions, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Champion champion = champions.get(position);
        System.out.println(champion.getName());
        //holder.itemView.setBackgroundColor(mResources.getColor(R.color.mcgpalette0300));
        String fileNameFormated = champion.getName().toLowerCase();
        fileNameFormated = fileNameFormated.replaceAll("[^a-zA-Z]+", "");
        Glide.with(mContext).load(mResources.getIdentifier("champion_" + fileNameFormated + "_icon", DRAWABLE, mPackageName)).into(holder.championIcon);
        holder.championName.setText(champion.getName());
        holder.championName.setBackgroundColor(mResources.getColor(R.color.mcgpalette0900));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return champions.size();
    }

    public Champion getItem(int position) {
        return champions.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private int getColor(@ColorRes int colorRes) {
        return ContextCompat.getColor(mActivity, colorRes);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView championIcon;
        TextView championName;

        public ViewHolder(View itemView) {
            super(itemView);
            championIcon = (CircleImageView) itemView.findViewById(R.id.championIcon);
            championName = (TextView) itemView.findViewById(R.id.championName);
        }
    }
}

