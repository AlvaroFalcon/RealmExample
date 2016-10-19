package com.realmexample.alvaro.realmexample;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import model.Game;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AddFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Realm realm;
    Button addGameFragmentButton;
    EditText nameEdit, genreEdit;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add, container, false);
        initElements(view);
        initListeners(view);

        return view;
    }

    private void initListeners(View view) {
        addGameFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Game game = realm.createObject(Game.class);
                        setGameInformation(game);
                        clearEditTexts();
                        game.setFinished(false);
                        displayAddedGameToast();
                    }
                });
            }
        });
    }

    private void displayAddedGameToast() {
        Toast.makeText(getActivity(), "Game added successfully!",
                Toast.LENGTH_LONG).show();
    }

    private void setGameInformation(Game game) {
        game.setId(getIncrementalId());
        game.setGenre(genreEdit.getText().toString());
        game.setName(nameEdit.getText().toString());
    }

    private void clearEditTexts() {
        genreEdit.setText("");
        nameEdit.setText("");
    }

    private int getIncrementalId() {
        return realm.where(Game.class).max("id").intValue() + 1;
    }

    private void initElements(View view) {
        addGameFragmentButton = (Button) view.findViewById(R.id.addGameFragmentButton);
        nameEdit = (EditText) view.findViewById(R.id.gameNameField);
        genreEdit = (EditText) view.findViewById(R.id.gameGenreField);
        Realm.init(view.getContext());
        realm = Realm.getDefaultInstance();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}