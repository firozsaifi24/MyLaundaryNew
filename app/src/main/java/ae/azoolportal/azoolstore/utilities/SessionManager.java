package ae.azoolportal.azoolstore.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {


    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "MyLoginData";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_ROLE = "role";
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn)
    {
        editor.putBoolean(KEY_IS_LOGGEDIN,isLoggedIn);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }
    public boolean isLoggedIn()
    {
        return pref.getBoolean(KEY_IS_LOGGEDIN,false);
    }

    public void setRole(String role)
    {
        editor.putString(KEY_ROLE,role);
        // commit changes
        editor.commit();
        Log.d(TAG, "User Role");
    }

    public String getRole()
    {
        return pref.getString(KEY_ROLE,null);
    }

    public void setUserId(int userid)
    {
        editor.putInt(KEY_USER_ID,userid);
        // commit changes
        editor.commit();
        Log.d(TAG, "User Id");
    }

    public int getUserId()
    {
        return pref.getInt(KEY_USER_ID,0);
    }

    public void setName(String name)
    {
        editor.putString(KEY_NAME,name);
        // commit changes
        editor.commit();
        Log.d(TAG, "User Name");
    }

    public String getName()
    {
        return pref.getString(KEY_NAME,null);
    }

    public void setPhone(String phone)
    {
        editor.putString(KEY_PHONE, phone);
        // commit changes
        editor.commit();
        Log.d(TAG, "User Phone");
    }

    public String getPhone()
    {
        return pref.getString(KEY_PHONE,null);
    }

    public void setEmail(String email)
    {
        editor.putString(KEY_EMAIL,email);
        // commit changes
        editor.commit();
        Log.d(TAG, "User Email");
    }

    public String getEmail()
    {
        return pref.getString(KEY_EMAIL,null);
    }

    public void clear()
    {
        //editor.remove("name");  //use this to remove specific key value
        //use clear to remove all the value from the shared preferences
        editor.clear();
        editor.commit();
    }

}
