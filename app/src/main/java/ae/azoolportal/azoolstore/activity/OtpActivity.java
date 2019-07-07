package ae.azoolportal.azoolstore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ae.azoolportal.azoolstore.R;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView menu_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        menu_btn= findViewById(R.id.menu_btn);
        menu_btn.setOnClickListener(this);

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
    }
}
