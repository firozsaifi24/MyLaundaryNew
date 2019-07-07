package ae.azoolportal.azoolstore.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.data.CustomRequest;
import ae.azoolportal.azoolstore.data.ServiceRequest;
import ae.azoolportal.azoolstore.interfaces.OnLoginCallback;
import ae.azoolportal.azoolstore.utilities.PublicValues;
import ae.azoolportal.azoolstore.utilities.SessionManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnLoginCallback {

    ImageView menu_btn;

    private static final String TAG = LoginActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private CustomRequest customRequest;
    private SessionManager session;

    TextView txt_register_req;

    EditText et_email, et_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        menu_btn= findViewById(R.id.menu_btn);
        menu_btn.setOnClickListener(this);

        et_email= findViewById(R.id.et_email);
        et_password= findViewById(R.id.et_password);
        btn_login= findViewById(R.id.btn_login);
        txt_register_req= findViewById(R.id.txt_register_page_req);
        btn_login.setOnClickListener(this);
        txt_register_req.setOnClickListener(this);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        customRequest=new CustomRequest();
        // Session manager
        session = new SessionManager(getApplicationContext());

    }

    @Override
    public void onClick(View v) {

        if(v==menu_btn)
        {
            try
            {
                super.onBackPressed();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        if(v==txt_register_req)
        {
            Intent i= new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(i);
        }


        if(v==btn_login)
        {
            String email= et_email.getText().toString().trim();
            String pass= et_password.getText().toString().trim();

            if(!email.isEmpty())
            {
                if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    if(!pass.isEmpty())
                    {
                        pDialog.setMessage("Logging in ...");
                        showDialog();
                        //login user
                        ServiceRequest.getInstance(getApplicationContext()).addToRequestQueue(customRequest.login(PublicValues.URL_LOGIN,this,email,pass));

                    }
                    else
                    {
                        Toast.makeText(this,"Pls enter your password", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(this,"Pls enter a valid email address!", Toast.LENGTH_LONG).show();
                }

            }
            else
            {
                Toast.makeText(this,"Pls enter your email", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onLoginSuccess(JSONObject credentials) {
// user successfully logged in
        // Create login session
        hideDialog();
        session.setLogin(true);

        JSONObject user = null;
        try {

            user = credentials.getJSONObject("user");
            String name = user.getString("name");
            String role = user.getString("role");
            int userid = user.getInt("id");
            String phone = user.getString("phone");
            String email = user.getString("email");

            session.setName(name);
            session.setUserId(userid);
            session.setRole(role);
            session.setPhone(phone);
            session.setEmail(email);

            Log.d("user id and role: ", role+" and "+userid+" and "+name+ " and"+phone+ " and "+email);

            Toast.makeText(this, "Login Successfull", Toast.LENGTH_LONG).show();

            if(role.equals("ADMIN"))
            {
                // Launch main activity
               /* Intent intent = new Intent(LoginActivity.this,
                        AdminViewPage.class);
                startActivity(intent);
                finish();*/

                /*HomeActivity homeActivity = (HomeActivity) getActivity();
                homeActivity.home("ADMIN");*/

                Intent i= new Intent(this,MainActivity.class);
                startActivity(i);
                finish();

            }
            else
            {
                // Launch main activity
              /*  Intent intent = new Intent(LoginActivity.this,
                        UserViewPage.class);
                startActivity(intent);
                finish();*/

                /*HomeActivity homeActivity = (HomeActivity) getActivity();
                homeActivity.home("USER");*/

                Intent i= new Intent(this,MainActivity.class);
                startActivity(i);
                finish();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoginError(String error) {
        hideDialog();
        if (getApplicationContext() != null){
            if (error != null && !error.isEmpty()){
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Some error has been occurred!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onNetworkError() {
        hideDialog();
        if(getApplicationContext() !=null)
        {
            Toast.makeText(this,"Nn Internet Connection!",Toast.LENGTH_LONG).show();
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
