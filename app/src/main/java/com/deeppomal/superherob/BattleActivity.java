package com.deeppomal.superherob;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Random;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class BattleActivity extends AppCompatActivity {

    private CircleImageView warrior1,warrior2;
    private TextView warname1,warname2,warpub1,warpub2,battlesite,battleinfo,w1s1,w1s2,w1s3,w1s4,w1s5,w1s6,
    w2s1,w2s2,w2s3,w2s4,w2s5,w2s6,pick_winner,title;
    private Button winner1,winner2,back;
    private TextView intT,strT,spdT,durT,powT,comT;
    private SharedPrefManager sharedPrefManager;
    User updatedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        warrior1= (CircleImageView) findViewById(R.id.warrior1);
        warrior2= (CircleImageView) findViewById(R.id.warrior2);

        warname1 = (TextView) findViewById(R.id.war_name1);
        warname2 = (TextView) findViewById(R.id.war_name2);
        warpub1 = (TextView) findViewById(R.id.war_publisher1);
        warpub2 = (TextView) findViewById(R.id.war_publisher2);

        battlesite = (TextView)findViewById(R.id.battle_site);
        battleinfo = (TextView)findViewById(R.id.battle_info);

        w1s1 = (TextView) findViewById(R.id.war1_stat1);
        w1s2 = (TextView) findViewById(R.id.war1_stat2);
        w1s3 = (TextView) findViewById(R.id.war1_stat3);
        w1s4 = (TextView) findViewById(R.id.war1_stat4);
        w1s5 = (TextView) findViewById(R.id.war1_stat5);
        w1s6 = (TextView) findViewById(R.id.war1_stat6);

        w2s1 = (TextView) findViewById(R.id.war2_stat1);
        w2s2 = (TextView) findViewById(R.id.war2_stat2);
        w2s3 = (TextView) findViewById(R.id.war2_stat3);
        w2s4 = (TextView) findViewById(R.id.war2_stat4);
        w2s5 = (TextView) findViewById(R.id.war2_stat5);
        w2s6 = (TextView) findViewById(R.id.war2_stat6);

        pick_winner = (TextView) findViewById(R.id.pick_winner);
        title = (TextView) findViewById(R.id.title);
        winner1 = (Button) findViewById(R.id.pick_warrior1);
        winner2 = (Button) findViewById(R.id.pick_warrior2);
        back = (Button) findViewById(R.id.back);

        intT = (TextView) findViewById(R.id.stat1);
        strT = (TextView) findViewById(R.id.stat2);
        spdT = (TextView) findViewById(R.id.stat3);
        durT = (TextView) findViewById(R.id.stat4);
        powT = (TextView) findViewById(R.id.stat5);
        comT = (TextView) findViewById(R.id.stat6);

        Picasso.with(this)
                .load(getIntent().getStringExtra("img1"))
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .error(R.drawable.ic_account_circle_black_24dp)
                .into(warrior1);

        Picasso.with(this)
                .load(getIntent().getStringExtra("img2"))
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .error(R.drawable.ic_account_circle_black_24dp)
                .into(warrior2);

        warname1.setText(getIntent().getStringExtra("name1"));
        warname2.setText(getIntent().getStringExtra("name2"));

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/woodchuck_bold.otf");
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/woodchuck.otf");
        warname1.setTypeface(typeface);
        warname2.setTypeface(typeface);
        warpub1.setTypeface(typeface2);
        warpub2.setTypeface(typeface2);

        intT.setTypeface(typeface2);
        strT.setTypeface(typeface2);
        spdT.setTypeface(typeface2);
        durT.setTypeface(typeface2);
        powT.setTypeface(typeface2);
        comT.setTypeface(typeface2);

        w1s1.setTypeface(typeface2);
        w1s2.setTypeface(typeface2);
        w1s3.setTypeface(typeface2);
        w1s4.setTypeface(typeface2);
        w1s5.setTypeface(typeface2);
        w1s6.setTypeface(typeface2);

        w2s1.setTypeface(typeface2);
        w2s2.setTypeface(typeface2);
        w2s3.setTypeface(typeface2);
        w2s4.setTypeface(typeface2);
        w2s5.setTypeface(typeface2);
        w2s6.setTypeface(typeface2);


        if(getIntent().getStringExtra("pub1").equals("null"))
        {
            warpub1.setText("");
        }
        else if(getIntent().getStringExtra("pub2").equals("null"))
        {
            warpub2.setText("");
        }
        else {
            warpub1.setText(getIntent().getStringExtra("pub1"));
            warpub2.setText(getIntent().getStringExtra("pub2"));
        }

        w1s1.setText(getIntent().getStringExtra("s11"));
        w1s2.setText(getIntent().getStringExtra("s12"));
        w1s3.setText(getIntent().getStringExtra("s13"));
        w1s4.setText(getIntent().getStringExtra("s14"));
        w1s5.setText(getIntent().getStringExtra("s15"));
        w1s6.setText(getIntent().getStringExtra("s16"));

        w2s1.setText(getIntent().getStringExtra("s21"));
        w2s2.setText(getIntent().getStringExtra("s22"));
        w2s3.setText(getIntent().getStringExtra("s23"));
        w2s4.setText(getIntent().getStringExtra("s24"));
        w2s5.setText(getIntent().getStringExtra("s25"));
        w2s6.setText(getIntent().getStringExtra("s26"));

        winner1.setText(getIntent().getStringExtra("name1"));
        winner2.setText(getIntent().getStringExtra("name2"));
        winner1.setTypeface(typeface);
        winner2.setTypeface(typeface);

        Random rand = new Random();
        BattleSiteDB battleSiteDB = new BattleSiteDB();
        int index = rand.nextInt(5);


        battlesite.setText("Battle Site : "+battleSiteDB.getBattleSiteName(index));
        battleinfo.setText(battleSiteDB.getBattleSiteInfo(index));
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/woodchuck_heavy.otf");
        battlesite.setTypeface(typeface3);
        battleinfo.setTypeface(typeface2);
        pick_winner.setTypeface(typeface3);
        title.setTypeface(typeface3);

        PushDownAnim.setPushDownAnimTo( back )
                .setScale( MODE_SCALE, 0.85f  )
                .setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick( View view ){
                        finish();
                    }
                });

        sharedPrefManager = new SharedPrefManager(this);

        PushDownAnim.setPushDownAnimTo( winner1 )
                .setScale( MODE_SCALE, 0.85f  )
                .setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick( View view ){
                        LayoutInflater inflater = (LayoutInflater)
                                getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = inflater.inflate(R.layout.result_popup, null);

                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        boolean focusable = false;
                        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                        TextView t = (TextView)popupView.findViewById(R.id.title);
                        TextView subt = (TextView)popupView.findViewById(R.id.subtitle);
                        TextView result = (TextView)popupView.findViewById(R.id.result);
                        Button confirm = (Button) popupView.findViewById(R.id.confirm);

                        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/woodchuck_bold.otf");
                        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/woodchuck.otf");
                        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/woodchuck_light.otf");
                        t.setTypeface(typeface);
                        confirm.setTypeface(typeface);
                        subt.setTypeface(typeface2);
                        result.setTypeface(typeface3);

                        PushDownAnim.setPushDownAnimTo( confirm)
                                .setScale( MODE_SCALE, 0.85f  )
                                .setOnClickListener( new View.OnClickListener(){
                                    @Override
                                    public void onClick( View view ){

                                        finish();
//                                        Intent intent = new Intent(BattleActivity.this,MainActivity.class);
//                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        startActivity(intent);
                                    }
                                });

                        if(BattleLogic(index).endsWith(getIntent().getStringExtra("name1")))
                        {
                            t.setText("You win!");
                            subt.setText(getIntent().getStringExtra("name1")+" overpowered "+getIntent().getStringExtra("name2"));
                            result.setText("$10 added to your balance");
                            win();
                            uploadBattleData("won");

                        }
                        else
                        {
                            t.setText("You lose!");
                            subt.setText(getIntent().getStringExtra("name2")+" overpowered "+getIntent().getStringExtra("name1"));
                            result.setText("$5 deducted from your balance");
                            lose();
                            uploadBattleData("lost");
                        }

                    }
                });

        PushDownAnim.setPushDownAnimTo( winner2)
                .setScale( MODE_SCALE, 0.85f  )
                .setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick( View view ){
                        LayoutInflater inflater = (LayoutInflater)
                                getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = inflater.inflate(R.layout.result_popup, null);

                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        boolean focusable = false;
                        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                        TextView t = (TextView)popupView.findViewById(R.id.title);
                        TextView subt = (TextView)popupView.findViewById(R.id.subtitle);
                        TextView result = (TextView)popupView.findViewById(R.id.result);
                        Button confirm = (Button) popupView.findViewById(R.id.confirm);

                        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/woodchuck_bold.otf");
                        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/woodchuck.otf");
                        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/woodchuck_light.otf");
                        t.setTypeface(typeface);
                        confirm.setTypeface(typeface);
                        subt.setTypeface(typeface2);
                        result.setTypeface(typeface3);

                        PushDownAnim.setPushDownAnimTo( confirm)
                                .setScale( MODE_SCALE, 0.85f  )
                                .setOnClickListener( new View.OnClickListener(){
                                    @Override
                                    public void onClick( View view ){

                                        finish();
//                                        Intent intent = new Intent(BattleActivity.this,MainActivity.class);
//                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        startActivity(intent);

                                    }
                                });
                        if(BattleLogic(index).endsWith(getIntent().getStringExtra("name2")))
                        {
                            t.setText("You win!");
                            subt.setText(getIntent().getStringExtra("name2")+" overpowered "+getIntent().getStringExtra("name1"));
                            result.setText("$10 added to your balance");
                            win();
                            uploadBattleData("won");
                        }
                        else{
                            t.setText("You Lose!");
                            subt.setText(getIntent().getStringExtra("name1")+" overpowered "+getIntent().getStringExtra("name2"));
                            result.setText("$5 deducted from your balance");
                            lose();
                            uploadBattleData("lost");
                        }
                    }
                });
    }

    public void win()
    {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            Query query = ref.child("users/");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {

                        User user = appleSnapshot.getValue(User.class);
                        if(user.getEmail().equals(Utils.encodeEmail(sharedPrefManager.getUserEmail().toLowerCase()))) {
                            user.setBalance(user.getBalance() + 10);
                            user.setTotalBattles(user.getTotalBattles() + 1);
                        }
                        appleSnapshot.getRef().setValue(user);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("ERROR: ", "onCancelled", databaseError.toException());
                }
            });
    }
    public void lose()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query query = ref.child("users/");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {

                    User user = appleSnapshot.getValue(User.class);
                    if(user.getEmail().equals(Utils.encodeEmail(sharedPrefManager.getUserEmail().toLowerCase()))) {
                        user.setBalance(user.getBalance() - 5);
                        user.setTotalBattles(user.getTotalBattles()+1);
                    }
                    appleSnapshot.getRef().setValue(user);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ERROR: ", "onCancelled", databaseError.toException());
            }
        });
    }
    public void uploadBattleData(String result)
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                SharedPrefManager sharedPrefManager = new SharedPrefManager(BattleActivity.this);
                final String encodedEmail = Utils.encodeEmail(sharedPrefManager.getUserEmail().toLowerCase());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("battleData/"+encodedEmail+"/");

                FightModel fightModel = new FightModel(getIntent().getStringExtra("name1"),getIntent().getStringExtra("name2"),
                        getIntent().getStringExtra("pub1"),getIntent().getStringExtra("pub2"),
                        getIntent().getStringExtra("img1"),getIntent().getStringExtra("img2"),result);
                String fightUpload = databaseReference.push().getKey();
                databaseReference.child(fightUpload).setValue(fightModel);
            }
        }, 100);
    }
    public String BattleLogic(int index)
    {
        BattleSiteDB battleSiteDB = new BattleSiteDB();
        double score1 = 0;
        double score2=0;
        for (int i =0;i<6;i++){
            score1 = score1 + (Double.valueOf(getIntent().getStringExtra("s1"+String.valueOf(i+1)))*battleSiteDB.getBattleScore(index,i));
            score2 = score2 + (Double.valueOf(getIntent().getStringExtra("s2"+String.valueOf(i+1)))*battleSiteDB.getBattleScore(index,i));
        }

        Random rand = new Random();
        score1=score1+rand.nextInt(100);
        score2=score2+rand.nextInt(100);

        return compareScores(score1,score2);
    }
    public String compareScores(double s1,double s2)
    {
        if(s1>s2)
            return getIntent().getStringExtra("name1");
        else
            return getIntent().getStringExtra("name2");
    }
}
