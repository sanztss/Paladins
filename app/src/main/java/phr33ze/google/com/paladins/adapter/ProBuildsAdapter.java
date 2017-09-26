package phr33ze.google.com.paladins.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import phr33ze.google.com.paladins.R;
import phr33ze.google.com.paladins.model.ProBuild;

/**
 * Created by Des. Android on 26/09/2017.
 */

public class ProBuildsAdapter extends RecyclerView.Adapter<ProBuildsAdapter.MyViewHolder> {

    List<ProBuild> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public ProBuildsAdapter(Context context, List<ProBuild> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_builds, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ProBuild current = data.get(position);
    }

    @Override
    public int getItemCount() {
        return  this.data == null ? 0 : this.data.size();
    }

    public void clear() {
        if (this.data != null){
            this.data.clear();
            notifyDataSetChanged();
        }
    }

    public void addAll(List<ProBuild> list) {
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }

}
