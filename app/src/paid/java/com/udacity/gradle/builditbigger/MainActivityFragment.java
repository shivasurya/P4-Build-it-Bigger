package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jokeandroidlib.app.jokeDisplayActivity;
import com.udacity.gradle.builditbigger.networkAPI.EndPoint;
import com.udacity.gradle.builditbigger.networkAPI.onJokeReceived;

public class MainActivityFragment extends Fragment{
    private String mJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) root.findViewById(R.id.jokebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchJoke();
            }
        });
        return root;
    }


    private void startJokeActivity(String joke){
        Intent mIntent = new Intent(getActivity(),jokeDisplayActivity.class);
        mIntent.putExtra("JOKE",joke);
        startActivity(mIntent);
    }
    private void fetchJoke(){
        new EndPoint().execute(new onJokeReceived() {
            @Override
            public void OnJokeReceivedListener(String joke) {
                if(joke!=null)
                    startJokeActivity(joke);
                else
                    android.widget.Toast.makeText(getActivity(), "Error!Something went Wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
