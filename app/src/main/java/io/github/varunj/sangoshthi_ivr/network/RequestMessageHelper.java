package io.github.varunj.sangoshthi_ivr.network;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

import io.github.varunj.sangoshthi_ivr.utilities.SharedPreferenceManager;

/**
 * Created by deepak on 03-06-2017.
 */

public class RequestMessageHelper {

    private static RequestMessageHelper instance;

    private RequestMessageHelper() {

    }

    public static synchronized RequestMessageHelper getInstance() {
        if(instance == null) {
            instance = new RequestMessageHelper();
        }
        return instance;
    }

    /* All the JSON Packets */

    /**
     * App install
     *
     * Request - {"objective" : "app_install_notify", "broadcaster" : “9716517818”, “timestamp” : “2017-06-15 18:00:00” }
     * Response - {"objective" : "ack", "info" : “2” } // cohort id
     * Response - {"objective" : "ack", "info" : “-1” } // in case fetching of cohort id failed
     *
     * @param broadcaster
     */
    public void appInstallNotify(String broadcaster) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "app_install_notify");
            jsonObject.put("broadcaster", broadcaster);
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Upcoming Show Data (Host Show)
     *
     * Request - {"objective" : "get_upcoming_show", "broadcaster" : "9716517818", "cohort_id" : "3", "timestamp" : "erfs" }
     * Response - {"objective": "upcoming_show_data", "show_id": "show_3", "time_of_airing": "2017-06-15 18:00:00", "topic": "play and communication"}'
     *
     */
    public void getUpcomingShow() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "get_upcoming_show");
            jsonObject.put("broadcaster", SharedPreferenceManager.getInstance().getBroadcaster());
            jsonObject.put("cohort_id", SharedPreferenceManager.getInstance().getCohortId());
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start Show
     *
     */
    public void startShow() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "start_show");
            jsonObject.put("broadcaster", SharedPreferenceManager.getInstance().getBroadcaster());
            jsonObject.put("cohort_id", SharedPreferenceManager.getInstance().getCohortId());
            jsonObject.put("show_id", SharedPreferenceManager.getInstance().getShowId());
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dial Listeners
     *
     */
    public void dialListeners() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "dial_listeners");
            jsonObject.put("broadcaster", SharedPreferenceManager.getInstance().getBroadcaster());
            jsonObject.put("cohort_id", SharedPreferenceManager.getInstance().getCohortId());
            jsonObject.put("show_id", SharedPreferenceManager.getInstance().getShowId());
            jsonObject.put("conference_name", SharedPreferenceManager.getInstance().getConferenceName());
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mute
     *
     * @param listener_phoneno
     */
    public void mute(String listener_phoneno, int turn) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "mute");
            jsonObject.put("broadcaster", SharedPreferenceManager.getInstance().getBroadcaster());
            jsonObject.put("cohort_id", SharedPreferenceManager.getInstance().getCohortId());
            jsonObject.put("show_id", SharedPreferenceManager.getInstance().getShowId());
            jsonObject.put("conference_name", SharedPreferenceManager.getInstance().getConferenceName());
            jsonObject.put("listener_phoneno", listener_phoneno);
            jsonObject.put("turn", turn);
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unmute
     *
     * @param listener_phoneno
     */
    public void unmute(String listener_phoneno, int turn) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "unmute");
            jsonObject.put("broadcaster", SharedPreferenceManager.getInstance().getBroadcaster());
            jsonObject.put("cohort_id", SharedPreferenceManager.getInstance().getCohortId());
            jsonObject.put("show_id", SharedPreferenceManager.getInstance().getShowId());
            jsonObject.put("conference_name", SharedPreferenceManager.getInstance().getConferenceName());
            jsonObject.put("listener_phoneno", listener_phoneno);
            jsonObject.put("turn", turn);
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * objective - flush_callers
     */
    public void flushCallers() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "flush_callers");
            jsonObject.put("broadcaster", SharedPreferenceManager.getInstance().getBroadcaster());
            jsonObject.put("cohort_id", SharedPreferenceManager.getInstance().getCohortId());
            jsonObject.put("show_id", SharedPreferenceManager.getInstance().getShowId());
            jsonObject.put("conference_name", SharedPreferenceManager.getInstance().getConferenceName());
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * objective - play_show_content
     */
    public void playShowContent() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "play_show_content");
            jsonObject.put("broadcaster", SharedPreferenceManager.getInstance().getBroadcaster());
            jsonObject.put("cohort_id", SharedPreferenceManager.getInstance().getCohortId());
            jsonObject.put("show_id", SharedPreferenceManager.getInstance().getShowId());
            jsonObject.put("conference_name", SharedPreferenceManager.getInstance().getConferenceName());
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * objective - pause_show_content
     */
    public void pauseShowContent() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "pause_play_show_content");
            jsonObject.put("broadcaster", SharedPreferenceManager.getInstance().getBroadcaster());
            jsonObject.put("cohort_id", SharedPreferenceManager.getInstance().getCohortId());
            jsonObject.put("show_id", SharedPreferenceManager.getInstance().getShowId());
            jsonObject.put("conference_name", SharedPreferenceManager.getInstance().getConferenceName());
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void playFeedback() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "play_feedback");
            jsonObject.put("broadcaster", SharedPreferenceManager.getInstance().getBroadcaster());
            jsonObject.put("cohort_id", SharedPreferenceManager.getInstance().getCohortId());
            jsonObject.put("show_id", SharedPreferenceManager.getInstance().getShowId());
            jsonObject.put("conference_name", SharedPreferenceManager.getInstance().getConferenceName());
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showPlaybackMetadata() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "show_playback_metadata");
            jsonObject.put("broadcaster", SharedPreferenceManager.getInstance().getBroadcaster());
            jsonObject.put("cohort_id", SharedPreferenceManager.getInstance().getCohortId());
            jsonObject.put("show_id", SharedPreferenceManager.getInstance().getShowId());
            jsonObject.put("conference_name", SharedPreferenceManager.getInstance().getConferenceName());
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showEndShow() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("objective", "end_show_call");
            jsonObject.put("broadcaster", SharedPreferenceManager.getInstance().getBroadcaster());
            jsonObject.put("cohort_id", SharedPreferenceManager.getInstance().getCohortId());
            jsonObject.put("show_id", SharedPreferenceManager.getInstance().getShowId());
            jsonObject.put("conference_name", SharedPreferenceManager.getInstance().getConferenceName());
            jsonObject.put("timestamp", DateFormat.getDateTimeInstance().format(new Date()));
            AMQPPublish.getInstance().publishMessage(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}