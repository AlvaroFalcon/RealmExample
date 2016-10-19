package com.realmexample.alvaro.realmexample;

import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import model.Game;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewGameListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ViewGameListFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Realm realm;
    int clickCounter = 0;

    public ViewGameListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_game_list, container, false);
        initElements(view);
        initListeners();
        addItems();
        return view;
    }

    public void addItems() {
        RealmResults<Game> gameList = realm.where(Game.class).findAll();
        addAllGames(gameList);
    }

    private void addAllGames(RealmResults<Game> gameList) {
        for (int i = 0; i < gameList.size(); i++) {
            listItems.add("Game :"+gameList.get(i).getName()+ " Genre: " +gameList.get(i).getGenre());
        }
        adapter.notifyDataSetChanged();
    }

    private void initListeners() {
    }

    private void initElements(View view) {
        Realm.init(view.getContext());
        realm = Realm.getDefaultInstance();
        adapter = new ArrayAdapter<>(view.getContext(),
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
