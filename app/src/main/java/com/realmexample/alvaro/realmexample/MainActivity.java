package com.realmexample.alvaro.realmexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import model.Game;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private TextView genreTv;
    private TextView nameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameTv = (TextView) findViewById(R.id.nameTv);
        genreTv = (TextView) findViewById(R.id.genreTv);


        Realm.init(this);
        realm = Realm.getDefaultInstance();
        addGame(realm);
    }

    private void addGame(Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Game game = realm.createObject(Game.class);
                game.setId(1);
                game.setGenre("Action");
                game.setFinished(false);
                game.setName("God of war 3");
            }
        });
        final Game game = realm.where(Game.class).findFirst();
        showStatus(game.getName(), game.getGenre());
    }

    private void showStatus(String name, String genre) {
        Log.i("TAG", name+ " "+genre);

        nameTv.setText(name);
        genreTv.setText(genre);
    }
}
