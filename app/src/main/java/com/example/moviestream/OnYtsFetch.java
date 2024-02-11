package com.example.moviestream;

import org.json.JSONObject;

public interface OnYtsFetch {
    public void  onSuccess(JSONObject response);
    public void onFailed(Exception e);
}
