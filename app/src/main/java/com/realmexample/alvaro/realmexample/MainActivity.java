package com.realmexample.alvaro.realmexample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
//import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;
import model.Game;

public class MainActivity extends AppCompatActivity implements AddFragment.OnFragmentInteractionListener, ViewGameListFragment.OnFragmentInteractionListener{

    private Realm realm;
    private Button addViewButton;
    private Button viewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
        initListeners();


    }

    private void initListeners() {

        addViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();

                AddFragment fragment = new AddFragment();
                transaction.replace(R.id.container, fragment);

                transaction.commit();

            }
        });
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();

                FragmentTransaction transaction = fragmentManager.beginTransaction();

                ViewGameListFragment fragment = new ViewGameListFragment();
                transaction.replace(R.id.container, fragment);

                transaction.commit();
            }
        });
    }

    private void initElements() {
        addViewButton = (Button) findViewById(R.id.addGameButton);
        viewButton = (Button) findViewById(R.id.viewAllGamesButton);
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
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
