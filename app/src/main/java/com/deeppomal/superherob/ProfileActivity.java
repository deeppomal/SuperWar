package com.deeppomal.superherob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;
import static java.security.AccessController.getContext;

public class ProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    Toolbar mActionBarToolbar;

    private RecyclerView recyclerView;
    DatabaseReference databaseReference;
    RecyclerView.Adapter adapter ;
    List<FightModel> list = new ArrayList<>();
    SharedPrefManager sm;
    int winCount;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private TextView balT,figT,winT,note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/woodchuck_bold.otf");
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/woodchuck.otf");
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/woodchuck_light.otf");

        balT = (TextView)findViewById(R.id.balance);
        figT = (TextView)findViewById(R.id.fights);
        winT = (TextView)findViewById(R.id.winP);
        note = (TextView)findViewById(R.id.notesText);
        balT.setTypeface(typeface3);
        figT.setTypeface(typeface3);
        winT.setTypeface(typeface3);
        note.setTypeface(typeface);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        final String encodedEmail = Utils.encodeEmail(sharedPrefManager.getUserEmail().toLowerCase());
        TextView winP = (TextView)findViewById(R.id.winPT);
        TextView fights = (TextView)findViewById(R.id.fightsT);
        databaseReference = FirebaseDatabase.getInstance().getReference("battleData/"+encodedEmail+"/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    FightModel fightModel = postSnapshot.getValue(FightModel.class);
                    list.add(fightModel);
                    if(fightModel.getResult().equals("won"))
                        winCount++;
                }
                try {
                    winP.setText(String.valueOf((winCount * 100) / list.size()));
                    fights.setText(String.valueOf(list.size()));
                    winP.setTypeface(typeface2);
                    fights.setTypeface(typeface2);
                }
                catch (Exception e)
                {}
                Collections.reverse(list);
                adapter = new FightAdapter(ProfileActivity.this, list);
                recyclerView.setItemViewCacheSize(20);
                recyclerView.setDrawingCacheEnabled(true);
                recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        TextView balance = (TextView)findViewById(R.id.balanceT);
        TextView name = (TextView)findViewById(R.id.name);
        CircleImageView photo = (CircleImageView)findViewById(R.id.image);

        balance.setText(getIntent().getStringExtra("balance"));
        name.setText(sharedPrefManager.getName());

        balance.setTypeface(typeface2);
        name.setTypeface(typeface);

        Picasso.with(this)
                .load(sharedPrefManager.getPhoto())
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(photo);

        mAuth = FirebaseAuth.getInstance();
        configureSignIn();
        Button signout = (Button)findViewById(R.id.signout);
        PushDownAnim.setPushDownAnimTo( signout )
                .setScale( MODE_SCALE, 0.85f  )
                .setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick( View view ){
                        signOut();
                    }
                });
    }

    public void configureSignIn(){
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .build();
        mGoogleApiClient.connect();
    }

    private void signOut(){
        new SharedPrefManager(this).clear();
        mAuth.signOut();
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
