package com.deeppomal.superherob;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class FightAdapter extends RecyclerView.Adapter<FightAdapter.ViewHolder>{


    Context context;
    List<FightModel> fightList;

    public FightAdapter(Context context, List<FightModel> TempList) {

        this.context = context;
        this.fightList = TempList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fight_row_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            final FightModel UploadInfo = fightList.get(position);

            holder.name1.setText(String.valueOf(fightList.get(position).getWar1()));
            holder.name2.setText(String.valueOf(fightList.get(position).getWar2()));
            holder.pub1.setText(String.valueOf(fightList.get(position).getPub1()));
            holder.pub2.setText(String.valueOf(fightList.get(position).getPub2()));

            Picasso.with(context)
                    .load(fightList.get(position).getWarPic1())
                    .placeholder(R.drawable.ic_account_circle_black_24dp)
                    .error(R.drawable.ic_account_circle_black_24dp)
                    .into(holder.battle1);

            Picasso.with(context)
                    .load(fightList.get(position).getWarPic2())
                    .placeholder(R.drawable.ic_account_circle_black_24dp)
                    .error(R.drawable.ic_account_circle_black_24dp)
                    .into(holder.battle2);

            holder.result.setText("You "+fightList.get(position).getResult());

            if(fightList.get(position).getResult().equals("won"))
                holder.result.setTextColor(context.getResources().getColor(R.color.accent2));
            else
                holder.result.setTextColor(context.getResources().getColor(R.color.accent1));

            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/woodchuck_bold.otf");
            Typeface typeface2 = Typeface.createFromAsset(context.getAssets(), "fonts/woodchuck_light.otf");

            holder.name1.setTypeface(typeface);
            holder.name2.setTypeface(typeface);

            holder.pub1.setTypeface(typeface2);
            holder.pub2.setTypeface(typeface2);

            holder.result.setTypeface(typeface);
        }
        catch (Exception e)
        {

        }

    }

    @Override
    public int getItemCount() {

        return fightList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name1,name2,pub1,pub2,result;
        CircleImageView battle1,battle2;
        RelativeLayout rl;

        public ViewHolder(View myView) {
            super(myView);
            name1 = myView.findViewById(R.id.battle1_name);
            name2 = myView.findViewById(R.id.battle2_name);
            pub1 = myView.findViewById(R.id.battle1_first_name);
            pub2 = myView.findViewById(R.id.battle2_first_name);
            result =myView.findViewById(R.id.result);
            battle1 = myView.findViewById(R.id.battle1);
            battle2=myView.findViewById(R.id.battle2);
            rl = (RelativeLayout)myView.findViewById(R.id.rl);
        }
    }
}
