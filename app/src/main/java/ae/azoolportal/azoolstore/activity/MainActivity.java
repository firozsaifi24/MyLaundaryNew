package ae.azoolportal.azoolstore.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.fragments.AboutFragment;
import ae.azoolportal.azoolstore.fragments.AddressAddFragment;
import ae.azoolportal.azoolstore.fragments.AddressFragment;
import ae.azoolportal.azoolstore.fragments.ClothesFragment;
import ae.azoolportal.azoolstore.fragments.FAQFragment;
import ae.azoolportal.azoolstore.fragments.HomeFragment;
import ae.azoolportal.azoolstore.fragments.NofificationFragment;
import ae.azoolportal.azoolstore.fragments.OrderPlacedSuccessFragment;
import ae.azoolportal.azoolstore.fragments.OrdersDetailsFragment;
import ae.azoolportal.azoolstore.fragments.OrdersFragment;
import ae.azoolportal.azoolstore.fragments.PriceFragment;
import ae.azoolportal.azoolstore.fragments.ProfileFragment;
import ae.azoolportal.azoolstore.fragments.ReviewOrderFragment;
import ae.azoolportal.azoolstore.fragments.ScheduleFragment;
import ae.azoolportal.azoolstore.fragments.ServicesFragment;
import ae.azoolportal.azoolstore.fragments.SupportFragment;
import ae.azoolportal.azoolstore.model.Address;
import ae.azoolportal.azoolstore.model.Clothes;
import ae.azoolportal.azoolstore.utilities.PublicValues;
import ae.azoolportal.azoolstore.utilities.SessionManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //side navigation drawer
    DrawerLayout drawer;
    NavigationView navigationView;
    TextView txt_name_nav;
    LinearLayout home_layout, profile_layout, price_layout, my_orders_layout, notification_layout,
            services_layout, support_layout, faq_layout, about_us_layout,  change_pass_layout, logout_layout;

    LinearLayout userLoginSignupLayout;
    TextView signin, signup;
    //side navigation drawer ends here
    private SessionManager session;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer=findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.navigation);
        home_layout= findViewById(R.id.row_home);
        profile_layout= findViewById(R.id.row_profile);
        price_layout= findViewById(R.id.row_price);
        my_orders_layout= findViewById(R.id.row_my_orders);
        notification_layout= findViewById(R.id.row_notification);
        services_layout= findViewById(R.id.row_services);
        support_layout= findViewById(R.id.row_support);
        faq_layout= findViewById(R.id.row_faq);
        about_us_layout= findViewById(R.id.row_about_us);
        change_pass_layout= findViewById(R.id.row_change_pass);
        logout_layout= findViewById(R.id.row_logout);

        userLoginSignupLayout= findViewById(R.id.userlogin);
        signin= findViewById(R.id.signin);
        signup= findViewById(R.id.signup);
        txt_name_nav= findViewById(R.id.txt_name_nav);

        //drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);


        //click listeners
        home_layout.setOnClickListener(this);
        profile_layout.setOnClickListener(this);
        price_layout.setOnClickListener(this);
        my_orders_layout.setOnClickListener(this);
        notification_layout.setOnClickListener(this);
        services_layout.setOnClickListener(this);
        support_layout.setOnClickListener(this);
        faq_layout.setOnClickListener(this);
        about_us_layout.setOnClickListener(this);
        change_pass_layout.setOnClickListener(this);
        logout_layout.setOnClickListener(this);
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);

        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn())
        {
            String role = session.getRole();
            Log.d("Role value ",role);
            // User is already logged in. Take him to main activity

            String welcomeName  = session.getName();
            Log.d("Name value ",welcomeName);
            welcomeName = welcomeName.substring(0,1).toUpperCase() + welcomeName.substring(1).toLowerCase();
            txt_name_nav.setText("Hi, " + welcomeName);
            txt_name_nav.setVisibility(View.VISIBLE);
            userLoginSignupLayout.setVisibility(View.GONE);
            logout_layout.setVisibility(View.VISIBLE);

/*            if(role.equals("ADMIN"))
            {
                home("ADMIN");

            }
            else
            {
                home("USER");
            }*/
        }


        //load home fragment
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, new HomeFragment(), PublicValues.FRAG_TAG_HOME).commit();

    }

    @Override
    public void onClick(View v) {

        if(v==home_layout)
        {
            // remove all the fragment from the stack
            popupAllFragments();
            loadFragment(new HomeFragment(), PublicValues.FRAG_TAG_HOME);

            //fragmentManager.beginTransaction().replace(R.id.fragmentFrame, new HomeFragment(), Constants.FRAG_TAG_HOME).commit();

            closeDrawerFunc();
        }

        if(v==profile_layout)
        {
            loadFragment(new ProfileFragment(), PublicValues.FRAG_TAG_MY_ACCOUNT);
            closeDrawerFunc();
        }

        if(v==price_layout)
        {
            loadFragment(new PriceFragment(), PublicValues.FRAG_TAG_PRICE);
            closeDrawerFunc();
        }

        if(v==my_orders_layout)
        {
            loadFragment(new OrdersFragment(), PublicValues.FRAG_TAG_MY_ORDERS);
            closeDrawerFunc();
        }

        if(v==notification_layout)
        {
            loadFragment(new NofificationFragment(), PublicValues.FRAG_TAG_NOTIFICATION);
            closeDrawerFunc();
        }

        if(v==services_layout)
        {
            // remove all the fragment from the stack
            //popupAllFragments();
            loadFragment(new ServicesFragment(), PublicValues.FRAG_TAG_SERVICES);
            closeDrawerFunc();
        }

        if(v==support_layout)
        {
            loadFragment(new SupportFragment(),PublicValues.FRAG_TAG_SUPPORT);
            closeDrawerFunc();
        }

        if(v==faq_layout)
        {
            loadFragment(new FAQFragment(), PublicValues.FRAG_TAG_FAQ);
            closeDrawerFunc();
        }

        if(v==about_us_layout)
        {
            loadFragment(new AboutFragment(), PublicValues.FRAG_TAG_ABOUT);
            closeDrawerFunc();
        }

        if(v==change_pass_layout)
        {
            //loadFragment(new AddProductFragment(),Constants.FRAG_TAG_ADD_PRODUCT);
            //closeDrawerFunc();
        }

        if(v==signin)
        {
            Intent i= new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }

        if(v==signup)
        {
            Intent i= new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(i);
        }

        if(v==logout_layout)
        {
            userLoginSignupLayout.setVisibility(View.VISIBLE);
            txt_name_nav.setVisibility(View.GONE);
            logout_layout.setVisibility(View.GONE);
            session.setLogin(false);
            session.clear();

            Toast.makeText(getApplicationContext(),"Logout Successfull!", Toast.LENGTH_LONG).show();

            closeDrawerFunc();

           /* HomeActivity homeActivity = (HomeActivity) getActivity();
            homeActivity.logout();*/
        }
    }

    public void loadFragment(Fragment fragment, String tag)
    {
        if (fragmentManager==null)
        {
            fragmentManager= getSupportFragmentManager();
        }
        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, fragment, tag).commit();
    }

    //open side drawer
    public void openDrewerFunc(){
        if (drawer != null){
            drawer.openDrawer(Gravity.LEFT);
        }
    }

    public void closeDrawerFunc()
    {
        if (drawer != null){
            drawer.closeDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            FragmentManager fragmentManager=getSupportFragmentManager();
            if (fragmentManager != null) {
                int backstackCount = fragmentManager.getBackStackEntryCount();
                Log.d("Back count old", String.valueOf(backstackCount));
                if (backstackCount > 0) {
                    MainActivity.super.onBackPressed();

                    int backstackCount2 = fragmentManager.getBackStackEntryCount();

                    Log.d("Back", "Back");
                    Log.d("Back count", String.valueOf(backstackCount2));

                    if(backstackCount2 == 0)
                    {
                        Log.d("Back 0 ", "0 Found");
                        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
                    }
                }
                else
                {
                    Log.d("Back exit", String.valueOf(backstackCount));


                    final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Are You Sure Want To Exit?");
                    builder.setCancelable(true);
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }
            }

        }

    }

/*
        //Callback functions
    public void visitShop()
    {
        //loadFragment(new DealerFragment(),Constants.FRAG_TAG_DEALER);
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        DealerFragment dealerFragment= new DealerFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, dealerFragment, Constants.FRAG_TAG_DEALER).addToBackStack(null).commit();
    }*/

/*
        //Callback functions
    public void dealerProductGallery()
    {
        //loadFragment(new DealerProductGalleryFragment(), Constants.FRAG_TAG_DEALER_GALLERY);
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        DealerProductGalleryFragment dealerProductGalleryFragment= new DealerProductGalleryFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, dealerProductGalleryFragment, Constants.FRAG_TAG_DEALER_GALLERY).addToBackStack(null).commit();

    }*/

    public void selectAddress(ArrayList<Clothes> washandfold_list, ArrayList<Clothes> washandiron_list, ArrayList<Clothes> iron_list, ArrayList<Clothes> premiumlaundry_list, ArrayList<Clothes> dryclean_list, long totalAmount, String pickupDate, String pickupTime, String deliveryDate, String deliveryTime, String note)
    {
        Log.d("1", String.valueOf(washandfold_list.size()));
        Log.d("2", String.valueOf(washandiron_list.size()));
        Log.d("3", String.valueOf(iron_list.size()));
        Log.d("4", String.valueOf(premiumlaundry_list.size()));
        Log.d("5", String.valueOf(dryclean_list.size()));
        Log.d("6", String.valueOf(totalAmount));

        Log.d("Pickup Date", pickupDate);
        Log.d("Pickup Time", pickupTime);
        Log.d("Delivery Date", deliveryDate);
        Log.d("Delivery Time", deliveryTime);
        Log.d("Note", note);

        //loadFragment(new DealerProductGalleryFragment(), Constants.FRAG_TAG_DEALER_GALLERY);
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        AddressFragment addressFragment= new AddressFragment();

        Bundle bundle = new Bundle();
        //bundle.putString("dealer_id", dealer_id);
        //Log.d("dealer id", dealer_id);
        // set MyFragment Arguments
        bundle.putParcelableArrayList("WashAndFoldList", washandfold_list);
        bundle.putParcelableArrayList("WashAndIronList", washandiron_list);
        bundle.putParcelableArrayList("IronList", iron_list);
        bundle.putParcelableArrayList("PremiumLaundryList", premiumlaundry_list);
        bundle.putParcelableArrayList("DryCleaningList", dryclean_list);
        bundle.putLong("TotalAmount", totalAmount);

        bundle.putString("PickupDate", pickupDate);
        bundle.putString("PickupTime", pickupTime);
        bundle.putString("DeliveryDate", deliveryDate);
        bundle.putString("DeliveryTime", deliveryTime);
        bundle.putString("Note", note);
        addressFragment.setArguments(bundle);

        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, addressFragment, PublicValues.FRAG_TAG_SELECT_ADDRESS).addToBackStack(null).commit();
    }

    public void addAddress()
    {
        //loadFragment(new DealerProductGalleryFragment(), Constants.FRAG_TAG_DEALER_GALLERY);
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        AddressAddFragment addressAddFragment= new AddressAddFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, addressAddFragment, PublicValues.FRAG_TAG_ADD_ADDRESS).addToBackStack(null).commit();
    }

    public void scheduleDateAndTime(ArrayList<Clothes> washandfold_list, ArrayList<Clothes> washandiron_list, ArrayList<Clothes> iron_list, ArrayList<Clothes> premiumlaundry_list, ArrayList<Clothes> dryclean_list, long totalAmount)
    {
        Log.d("1", String.valueOf(washandfold_list.size()));
        Log.d("2", String.valueOf(washandiron_list.size()));
        Log.d("3", String.valueOf(iron_list.size()));
        Log.d("4", String.valueOf(premiumlaundry_list.size()));
        Log.d("5", String.valueOf(dryclean_list.size()));
        Log.d("6", String.valueOf(totalAmount));

        //loadFragment(new DealerProductGalleryFragment(), Constants.FRAG_TAG_DEALER_GALLERY);
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        ScheduleFragment scheduleFragment= new ScheduleFragment();
        Bundle bundle = new Bundle();
        //bundle.putString("dealer_id", dealer_id);
        //Log.d("dealer id", dealer_id);
        // set MyFragment Arguments
        //putting clohtes data and total price
        bundle.putParcelableArrayList("WashAndFoldList", washandfold_list);
        bundle.putParcelableArrayList("WashAndIronList", washandiron_list);
        bundle.putParcelableArrayList("IronList", iron_list);
        bundle.putParcelableArrayList("PremiumLaundryList", premiumlaundry_list);
        bundle.putParcelableArrayList("DryCleaningList", dryclean_list);
        bundle.putLong("TotalAmount", totalAmount);

        scheduleFragment.setArguments(bundle);

        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, scheduleFragment, PublicValues.FRAG_TAG_SCHEDULE).addToBackStack(null).commit();
    }

    public void selectClothes(String pickupDate, String pickupTime, String deliveryDate, String deliveryTime, String note, ArrayList<Address> address)
    {
        Log.d("Pickup Date", pickupDate);
        Log.d("Pickup Time", pickupTime);
        Log.d("Delivery Date", deliveryDate);
        Log.d("Delivery Time", deliveryTime);
        Log.d("Note", note);
        Log.d("Address count", String.valueOf(address.size()));
        //loadFragment(new DealerProductGalleryFragment(), Constants.FRAG_TAG_DEALER_GALLERY);
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        ClothesFragment clothesFragment= new ClothesFragment();

        Bundle bundle = new Bundle();
        //bundle.putString("dealer_id", dealer_id);
        //Log.d("dealer id", dealer_id);
        // set MyFragment Arguments
        bundle.putString("PickupDate", pickupDate);
        bundle.putString("PickupTime", pickupTime);
        bundle.putString("DeliveryDate", deliveryDate);
        bundle.putString("DeliveryTime", deliveryTime);
        bundle.putString("Note", note);
        bundle.putParcelableArrayList("Address", address);
        clothesFragment.setArguments(bundle);

        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, clothesFragment, PublicValues.FRAG_TAG_CLOTHES).addToBackStack(null).commit();
    }

    public void reviewOrder(ArrayList<Clothes> washandfold_list, ArrayList<Clothes> washandiron_list, ArrayList<Clothes> iron_list, ArrayList<Clothes> premiumlaundry_list, ArrayList<Clothes> dryclean_list, long totalAmount, String pickupDate, String pickupTime, String deliveryDate, String deliveryTime, String note, ArrayList<Address> address)
    {
        Log.d("1", String.valueOf(washandfold_list.size()));
        Log.d("2", String.valueOf(washandiron_list.size()));
        Log.d("3", String.valueOf(iron_list.size()));
        Log.d("4", String.valueOf(premiumlaundry_list.size()));
        Log.d("5", String.valueOf(dryclean_list.size()));
        Log.d("6", String.valueOf(totalAmount));

        Log.d("Pickup Date", pickupDate);
        Log.d("Pickup Time", pickupTime);
        Log.d("Delivery Date", deliveryDate);
        Log.d("Delivery Time", deliveryTime);
        Log.d("Note", note);
        Log.d("Address count", String.valueOf(address.size()));

        //loadFragment(new DealerProductGalleryFragment(), Constants.FRAG_TAG_DEALER_GALLERY);
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        ReviewOrderFragment reviewOrderFragment= new ReviewOrderFragment();

        Bundle bundle = new Bundle();
        //bundle.putString("dealer_id", dealer_id);
        //Log.d("dealer id", dealer_id);
        // set MyFragment Arguments
        //putting clohtes data and total price
        bundle.putParcelableArrayList("WashAndFoldList", washandfold_list);
        bundle.putParcelableArrayList("WashAndIronList", washandiron_list);
        bundle.putParcelableArrayList("IronList", iron_list);
        bundle.putParcelableArrayList("PremiumLaundryList", premiumlaundry_list);
        bundle.putParcelableArrayList("DryCleaningList", dryclean_list);
        bundle.putLong("TotalAmount", totalAmount);

        //putting date time and address data
        bundle.putString("PickupDate", pickupDate);
        bundle.putString("PickupTime", pickupTime);
        bundle.putString("DeliveryDate", deliveryDate);
        bundle.putString("DeliveryTime", deliveryTime);
        bundle.putString("Note", note);
        bundle.putParcelableArrayList("Address", address);
        reviewOrderFragment.setArguments(bundle);

        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, reviewOrderFragment, PublicValues.FRAG_TAG_REVIEW_ORDER).addToBackStack(null).commit();
    }

    public void orderPlacedSuccess()
    {
        //loadFragment(new DealerProductGalleryFragment(), Constants.FRAG_TAG_DEALER_GALLERY);
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        OrderPlacedSuccessFragment orderPlacedSuccessFragment= new OrderPlacedSuccessFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, orderPlacedSuccessFragment, PublicValues.FRAG_TAG_ORDER_PLACED_SUCCESS).addToBackStack(null).commit();
    }

    public void ordersDetails()
    {
        //loadFragment(new DealerProductGalleryFragment(), Constants.FRAG_TAG_DEALER_GALLERY);
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        OrdersDetailsFragment ordersDetailsFragment= new OrdersDetailsFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, ordersDetailsFragment, PublicValues.FRAG_TAG_ORDERS_DETAIL).addToBackStack(null).commit();
    }

    public void home()
    {
        //loadFragment(new DealerProductGalleryFragment(), Constants.FRAG_TAG_DEALER_GALLERY);
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        popupAllFragments();
        HomeFragment homeFragment= new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, homeFragment, PublicValues.FRAG_TAG_HOME).commit();
    }

    /*public void trackOrder()
    {
        //loadFragment(new DealerProductGalleryFragment(), Constants.FRAG_TAG_DEALER_GALLERY);
        if (fragmentManager == null){
            fragmentManager = getSupportFragmentManager();
        }
        OrderPlacedSuccessFragment orderPlacedSuccessFragment= new OrderPlacedSuccessFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, orderPlacedSuccessFragment, PublicValues.FRAG_TAG_ORDER_PLACED_SUCCESS).addToBackStack(null).commit();
    }*/

    //to remove all the fragments from the stack
    private void popupAllFragments(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        if (fragmentManager != null) {
            int backstackCount = fragmentManager.getBackStackEntryCount();
            if (backstackCount > 0) {
                for (int i = 0; i < backstackCount; i++) {
                    // fragmentManager.popBackStackImmediate();
                    fragmentManager.popBackStack();
                }
            }
        }
    }

}
