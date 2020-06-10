package com.deeppomal.superherob;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder>  {

    private ArrayList<BattleModel> dataList;
    private Context context;
    private int listSize=7;
    public MyAdapter(Context context, ArrayList<BattleModel> dataList){

        this.dataList = dataList;
        this.context=context;
        setHasStableIds(true);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {


        public final View myView;

        TextView name1,name2,first_name1,first_name2;
        CircleImageView battle1,battle2;
        CardView cardView;
        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            name1 = myView.findViewById(R.id.battle1_name);
            name2 = myView.findViewById(R.id.battle2_name);
            first_name1 = myView.findViewById(R.id.battle1_first_name);
            first_name2 = myView.findViewById(R.id.battle2_first_name);

            battle1 = myView.findViewById(R.id.battle1);
            battle2=myView.findViewById(R.id.battle2);

            cardView = myView.findViewById(R.id.card);

            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/woodchuck_bold.otf");
            Typeface typeface2 = Typeface.createFromAsset(context.getAssets(), "fonts/woodchuck_light.otf");

            name1.setTypeface(typeface);
            name2.setTypeface(typeface);

            first_name1.setTypeface(typeface2);
            first_name2.setTypeface(typeface2);

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Random rand = new Random();

        int batt1 = position;
        int batt2 = position+5;

        holder.name1.setText(String.valueOf(dataList.get(batt1).getName()));
        holder.name2.setText(String.valueOf(dataList.get(batt2).getName()));

        if(dataList.get(batt1).getBiography().getPublisher().equals("null"))
            holder.first_name1.setText("");
        else
            holder.first_name1.setText(String.valueOf(dataList.get(batt1).getBiography().getPublisher()));

        if(dataList.get(batt2).getBiography().getPublisher().equals("null"))
            holder.first_name2.setText("");
        else
            holder.first_name2.setText(String.valueOf(dataList.get(batt2).getBiography().getPublisher()));


        Picasso.with(context)
                .load(dataList.get(batt1).getImage().getUrl())
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .error(R.drawable.ic_account_circle_black_24dp)
                .into(holder.battle1);

        Picasso.with(context)
                .load(dataList.get(batt2).getImage().getUrl())
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .error(R.drawable.ic_account_circle_black_24dp)
                .into(holder.battle2);


        PushDownAnim.setPushDownAnimTo( holder.cardView )
                .setScale( MODE_SCALE, 0.85f  )
                .setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick( View view ){
                        Intent intent = new Intent(context,BattleActivity.class);
                        intent.putExtra("name1",String.valueOf(dataList.get(batt1).getName()));
                        intent.putExtra("pub1",String.valueOf(dataList.get(batt1).getBiography().getPublisher()));
                        intent.putExtra("s11",String.valueOf(dataList.get(batt1).getPowerstats().getIntelligence()));
                        intent.putExtra("s12",String.valueOf(dataList.get(batt1).getPowerstats().getStrength()));
                        intent.putExtra("s13",String.valueOf(dataList.get(batt1).getPowerstats().getSpeed()));
                        intent.putExtra("s14",String.valueOf(dataList.get(batt1).getPowerstats().getDurability()));
                        intent.putExtra("s15",String.valueOf(dataList.get(batt1).getPowerstats().getPower()));
                        intent.putExtra("s16",String.valueOf(dataList.get(batt1).getPowerstats().getCombat()));
                        intent.putExtra("img1",String.valueOf(dataList.get(batt1).getImage().getUrl()));

                        intent.putExtra("name2",String.valueOf(dataList.get(batt2).getName()));
                        intent.putExtra("pub2",String.valueOf(dataList.get(batt2).getBiography().getPublisher()));
                        intent.putExtra("s21",String.valueOf(dataList.get(batt2).getPowerstats().getIntelligence()));
                        intent.putExtra("s22",String.valueOf(dataList.get(batt2).getPowerstats().getStrength()));
                        intent.putExtra("s23",String.valueOf(dataList.get(batt2).getPowerstats().getSpeed()));
                        intent.putExtra("s24",String.valueOf(dataList.get(batt2).getPowerstats().getDurability()));
                        intent.putExtra("s25",String.valueOf(dataList.get(batt2).getPowerstats().getPower()));
                        intent.putExtra("s26",String.valueOf(dataList.get(batt2).getPowerstats().getCombat()));
                        intent.putExtra("img2",String.valueOf(dataList.get(batt2).getImage().getUrl()));
                        context.startActivity(intent);

                    }
                });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return listSize;
    }
}
