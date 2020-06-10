package com.deeppomal.superherob.ui.home;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.adprogressbarlib.AdCircleProgress;
import com.deeppomal.superherob.BattleModel;
import com.deeppomal.superherob.BattleSiteDB;
import com.deeppomal.superherob.GetData;
import com.deeppomal.superherob.InfoActivity;
import com.deeppomal.superherob.MyAdapter;
import com.deeppomal.superherob.ProfileActivity;
import com.deeppomal.superherob.R;
import com.deeppomal.superherob.RetrofitClient;
import com.deeppomal.superherob.SharedPrefManager;
import com.deeppomal.superherob.User;
import com.deeppomal.superherob.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private CircleImageView mProfileImageView;
    private String profileUri;
    private SharedPrefManager sharedPrefManager;
    private ArrayList<BattleModel> battleModelArrayList;
    private ArrayList<Integer> RandomIndex;
    private MyAdapter myAdapter;
    private RecyclerView myRecyclerView;
    DatabaseReference databaseReference;
    private String balance;
    AdCircleProgress adCircleProgress2;
    int i = 0;
    private TextView loading;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        mProfileImageView = (CircleImageView) root.findViewById(R.id.profileImage);
        sharedPrefManager = new SharedPrefManager(getActivity());
        profileUri = sharedPrefManager.getPhoto();

        Picasso.with(getActivity())
                .load(sharedPrefManager.getPhoto())
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .error(R.drawable.ic_account_circle_black_24dp)
                .into(mProfileImageView);

        myRecyclerView = root.findViewById(R.id.recyclerView1);
        battleModelArrayList = new ArrayList<>();

        TextView profilename=(TextView)root.findViewById(R.id.profileName);
        profilename.setText(sharedPrefManager.getName());
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/woodchuck_heavy.otf");
        profilename.setTypeface(typeface);

        myRecyclerView.setVisibility(View.GONE);
        adCircleProgress2 = root.findViewById(R.id.pgb_progress2);
        loading = (TextView) root.findViewById(R.id.loadingText);

        adCircleProgress2.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/woodchuck_light.otf");
        loading.setTypeface(typeface2);
        if (getActivity() != null) {
            final Timer t = new Timer();
            t.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                adCircleProgress2.setAdProgress(i);
                                i++;
                            }
                        });
                    }
                    catch (Exception e)
                    {}
                }
            }, 0, 50);
        }

        Random rand = new Random();
        RandomIndex = new ArrayList<>();
        BattleSiteDB battleSiteDB = new BattleSiteDB();
        for (int i =0;i<35;i++)
        {
            RandomIndex.add(battleSiteDB.getKnownHeroes(rand.nextInt(battleSiteDB.getknownHeroesSize())));
        }
        Collections.shuffle(RandomIndex);

        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);
        for (int i =0;i<RandomIndex.size();i++) {
            Call<BattleModel> call = service.getAllStats(String.valueOf(RandomIndex.get(i)));
            int finalI = i;
            call.enqueue(new Callback<BattleModel>() {
                @Override
                public void onResponse(Call<BattleModel> call, Response<BattleModel> response) {
                    try {
                        if (!response.body().getPowerstats().getCombat().equals("null") && !response.body().getPowerstats().getDurability().equals("null") &&
                                !response.body().getPowerstats().getIntelligence().equals("null") && !response.body().getPowerstats().getPower().equals("null") &&
                                !response.body().getPowerstats().getSpeed().equals("null") && !response.body().getPowerstats().getStrength().equals("null"))
                            battleModelArrayList.add(response.body());
                        if (finalI == RandomIndex.size() - 1) {
                            loadDataList(battleModelArrayList);
                            adCircleProgress2.invalidate();
                            adCircleProgress2.setVisibility(View.GONE);
                            myRecyclerView.setVisibility(View.VISIBLE);
                            loading.setVisibility(View.GONE);
                        }
                    }
                    catch (Exception e)
                    {}
                }
                @Override
                public void onFailure(Call<BattleModel> call, Throwable t) {
                    Toast.makeText(getContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                }
            });
        }

        TextView balanceT = (TextView) root.findViewById(R.id.profileBalance);
        databaseReference = FirebaseDatabase.getInstance().getReference("users/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    User user = postSnapshot.getValue(User.class);
                    try {
                        if (user.getEmail().equals(Utils.encodeEmail(sharedPrefManager.getUserEmail().toLowerCase()))) {

                            String balanceTemp = "";
                            if (Math.abs(user.getBalance() / 1000000) > 1) {
                                balanceTemp = (user.getBalance() / 1000000) + "m";

                            } else if (Math.abs(user.getBalance() / 1000) > 1) {
                                balanceTemp = (user.getBalance() / 1000) + "k";

                            } else {
                                balanceTemp = String.valueOf(user.getBalance());
                            }
                            balanceT.setText("$" + balanceTemp);
                            Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/woodchuck.otf");
                            balanceT.setTypeface(typeface);
                            balance ="$" + balanceTemp;
                        }
                    }
                    catch (Exception e)
                    {}
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Some error occurred",Toast.LENGTH_SHORT).show();
            }
        });

        CardView cardView = (CardView)root.findViewById(R.id.profile);
        PushDownAnim.setPushDownAnimTo( cardView )
                .setScale( MODE_SCALE, 0.85f  )
                .setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick( View view ){

                        Intent intent = new Intent(getActivity(), ProfileActivity.class);
                        intent.putExtra("balance",balance);
                        startActivity(intent);
                    }
                });

        Button info =(Button)root.findViewById(R.id.info);
        PushDownAnim.setPushDownAnimTo( info )
                .setScale( MODE_SCALE, 0.85f  )
                .setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick( View view ){
                        Intent intent = new Intent(getActivity(), InfoActivity.class);
                        startActivity(intent);
                    }
                });



        return root;
    }

    @Override
    public void onResume(){
        super.onResume();
        try {
            if(battleModelArrayList.size()>0) {
                Collections.shuffle(battleModelArrayList);
                loadDataList(battleModelArrayList);
            }
        }
        catch (Exception e)
        {}
    }
    private void loadDataList(ArrayList<BattleModel> usersList) {


        myAdapter = new MyAdapter(getActivity(),usersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setItemViewCacheSize(20);
        myRecyclerView.setDrawingCacheEnabled(true);
        myRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setItemViewCacheSize(usersList.size());
        myRecyclerView.setAdapter(myAdapter);
    }
}