package ae.azoolportal.azoolstore.interfaces;

import org.json.JSONObject;

public interface OnRegisterCallback {

    void onRegisterSuccess(JSONObject response);
    void onRegisterError(String error);
    void onNetworkError();

}
