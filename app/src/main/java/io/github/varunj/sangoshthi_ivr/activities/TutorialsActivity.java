/*
 * Copyright (c) 2017. Created by Deepak Sood
 */

package io.github.varunj.sangoshthi_ivr.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.github.varunj.sangoshthi_ivr.R;
import io.github.varunj.sangoshthi_ivr.adapters.TutorialsRecyclerViewAdapter;
import io.github.varunj.sangoshthi_ivr.models.TutorialListenModel;
import io.github.varunj.sangoshthi_ivr.models.TutorialModel;
import io.github.varunj.sangoshthi_ivr.network.RequestMessageHelper;
import io.github.varunj.sangoshthi_ivr.network.ResponseMessageHelper;
import io.github.varunj.sangoshthi_ivr.utilities.SharedPreferenceManager;

/**
 * This the activity called from HomeActivity and fetches the tutorials from server
 * The tutorials can be locked or unlocked
 */
public class TutorialsActivity extends AppCompatActivity {

    private static final String TAG = TutorialsActivity.class.getSimpleName();

    private RecyclerView rvTutorials;
    private TutorialsRecyclerViewAdapter mAdapter;

    private List<TutorialModel> tutorialList;
    private List<TutorialListenModel> tutorialListenModelList;
    final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);

        tutorialList = new ArrayList<>();

        final Handler incomingMessageHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                try {
                    Log.d(TAG, "Message received: " + msg.getData().getString("msg"));
                    JSONObject jsonObject = new JSONObject(msg.getData().getString("msg"));

                    switch (jsonObject.getString("objective")) {
                        case "get_show_id_for_gallery_ack":
                            handlegetShowIdForGalleryAck(jsonObject);
                            break;

                        case "broadcaster_content_listen_event_ack":
                            Log.d(TAG, "tutorial telemetry ack");
                            // TODO: 01-07-2017 ack can be out of sync then tutorialModelList can be smaller then given packet_id
                            if(jsonObject.getString("info").equals("OK") && !jsonObject.getString("packet_id").equals("")) {
                                int packetId = Integer.parseInt(jsonObject.getString("packet_id"));
                                Log.d(TAG, "packet_id - " + packetId);
                                Log.d(TAG, "tutorial list - " + tutorialListenModelList.toString());

                                int packet_id = Integer.parseInt(jsonObject.getString("packet_id"));
                                if(packet_id >= 0) {
                                    int tutorialListenModelId = -1;
                                    for(int i = 0; i < tutorialListenModelList.size(); i++) {
                                        if(tutorialListenModelList.get(i).getPacket_id() == packet_id) {
                                            tutorialListenModelId = i;
                                        }
                                    }
                                    Log.d(TAG, "packet found at - " + tutorialListenModelId);
                                    if(tutorialListenModelId != -1) {
                                        tutorialListenModelList.remove(tutorialListenModelId);
                                    }
                                    SharedPreferenceManager.getInstance().setTutorialListenModelList(tutorialListenModelList);
                                }
                            }
                            break;
                    }
                } catch (Exception e) {
                    Log.e(TAG, "" + e);
                }
            }
        };
        ResponseMessageHelper.getInstance().subscribeToResponse(incomingMessageHandler);

        RequestMessageHelper.getInstance().getShowIdForGallery();

        Log.d(TAG, "TutorialsActivityData - " + SharedPreferenceManager.getInstance().getTutorialsActivityData());
        if(SharedPreferenceManager.getInstance().getTutorialsActivityData().equals("NONE")) {
            Log.d(TAG, "No tutorial data present");

            // String tutorialName, String fileName, String showName, boolean locked
            tutorialList.add(new TutorialModel("Tutorial 1", "Tutorial1.mp3", "show_13", true));
            tutorialList.add(new TutorialModel("Tutorial 2", "Tutorial2.mp3", "show_8", true));
            tutorialList.add(new TutorialModel("Tutorial 3", "Tutorial3.mp3", "show_11", true));
            tutorialList.add(new TutorialModel("Tutorial 4", "Tutorial4.mp4", "", false));
            tutorialList.add(new TutorialModel("Tutorial 5", "Tutorial3.mp3", "show_14", true));

            SharedPreferenceManager.getInstance().setTutorialsActivityData(gson.toJson(tutorialList));

        } else {
            Log.d(TAG, "Tutorial data present");
            String json = SharedPreferenceManager.getInstance().getTutorialsActivityData();
            Type type = new TypeToken<List<TutorialModel>>(){}.getType();
            tutorialList = gson.fromJson(json, type);
        }

        rvTutorials = (RecyclerView) findViewById(R.id.rv_tutorials);
        mAdapter = new TutorialsRecyclerViewAdapter(this, tutorialList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvTutorials.setLayoutManager(layoutManager);
        rvTutorials.setAdapter(mAdapter);

        String json = SharedPreferenceManager.getInstance().getTutorialListenData();
        if(!json.equals("NONE")) {
            Type type = new TypeToken<List<TutorialListenModel>>(){}.getType();
            tutorialListenModelList = gson.fromJson(json, type);

            for(TutorialListenModel tutorialListenModel : tutorialListenModelList) {
                RequestMessageHelper.getInstance().broadcasterContentListenEvent(tutorialListenModel, tutorialListenModelList.indexOf(tutorialListenModel));
            }
        }
    }

    private void handlegetShowIdForGalleryAck(JSONObject jsonObject) throws JSONException {
        if(!jsonObject.getString("show_id").equals("") && !jsonObject.getString("show_id").equals(" ") &&!jsonObject.getString("show_id").equals("-1")) {
            String showId = jsonObject.getString("show_id");
            Log.d(TAG, "show_id - " + showId);

            for(int i = 0; i < tutorialList.size(); i++) {
                if(tutorialList.get(i).getShowName().equals(showId)) {
                    tutorialList.get(i).setLocked(false);
                }
            }

            SharedPreferenceManager.getInstance().setTutorialsActivityData(gson.toJson(tutorialList));
            mAdapter.notifyDataSetChanged();
        }
    }
}