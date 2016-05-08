package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.jokeandroidlib.app.jokeDisplayActivity;
import com.udacity.gradle.builditbigger.networkAPI.EndPoint;
import com.udacity.gradle.builditbigger.networkAPI.onJokeReceived;

public class MainActivityFragment extends Fragment{
    private InterstitialAd mInterstitialAd;
    private String mJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeActivity(mJoke);
                loadInterstitialAd();
            }
        });

        loadInterstitialAd();

        Button button = (Button) root.findViewById(R.id.jokebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchJoke();
            }
        });

        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
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
                mJoke = joke;
                if(joke!=null) {
                    Log.d("log", joke);
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        startJokeActivity(mJoke);
                    }
                }
            }

        });
    }
}
