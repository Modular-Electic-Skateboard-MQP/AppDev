package mqp.mesb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
*/

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_peripherals:
                    mTextMessage.setText("Current Peripherals Connected:");
                    getPeripherals();
                    return true;
                case R.id.navigation_data:
                    mTextMessage.setText("Prior Run Data:");
                    //getData();
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText("Settings:");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void getPeripherals(){

    }

    public void getData(){
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("runs");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    final String runID = snap.getKey();
                    String duration = (String) snap.child("duration").getValue();
                    long speed = (long) snap.child("avgSpeed").getValue();
                    long dist = (long) snap.child("distance").getValue();

                    final Run run = new Run();
                    //run.addToRealm();
                }

                initializeRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void initializeRecyclerView(){

    }

}
