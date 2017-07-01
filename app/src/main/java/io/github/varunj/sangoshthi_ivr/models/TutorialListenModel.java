/*
 * Copyright (c) 2017. Created by Deepak Sood
 */

package io.github.varunj.sangoshthi_ivr.models;

/**
 * Created by Deepak on 30-06-2017.
 */

public class TutorialListenModel {

    String show_id;
    String show_status;
    String listen_timestamp;
    String topic;

    public TutorialListenModel(String show_id, String show_status, String listen_timestamp, String topic) {
        this.show_id = show_id;
        this.show_status = show_status;
        this.listen_timestamp = listen_timestamp;
        this.topic = topic;
    }

    public String getShow_id() {
        return show_id;
    }

    public void setShow_id(String show_id) {
        this.show_id = show_id;
    }

    public String getShow_status() {
        return show_status;
    }

    public void setShow_status(String show_status) {
        this.show_status = show_status;
    }

    public String getListen_timestamp() {
        return listen_timestamp;
    }

    public void setListen_timestamp(String listen_timestamp) {
        this.listen_timestamp = listen_timestamp;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "TutorialListenModel{" +
                "show_id='" + show_id + '\'' +
                ", show_status='" + show_status + '\'' +
                ", listen_timestamp='" + listen_timestamp + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}