package ae.azoolportal.azoolstore.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.activity.MainActivity;
import ae.azoolportal.azoolstore.model.Clothes;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class ScheduleFragment extends Fragment implements View.OnClickListener {

    ImageView menu_btn;
    EditText note;
    Button btn_next;

    RelativeLayout pickup_date_layout, pickup_time_layout, delivery_date_layout, delivery_time_layout;
    TextView txt_pickup_date, txt_pickup_time, txt_delivery_date, txt_delivery_time;

    //list pop window and data
    ListPopupWindow listPopupWindow;

    String[] fruitArray = {"Apple", "Banana", "Orange", "Grapes"};

    String[] timePickupArray = {"08 AM to 11 AM", "11 AM to 01 PM", "01 PM to 04 PM", "04 PM to 06 PM", "06 PM to 09 PM"};
    String[] timeDeliveryArray = {"08 AM to 11 AM", "11 AM to 01 PM", "01 PM to 04 PM", "04 PM to 06 PM", "06 PM to 09 PM"};

    String selectedPickupDate= "";
    String selectedDeliveryDate= "";
    String selectedPickupTime= "";
    String selectedDeliveryTime= "";


    //Received selected clothes list from home fragment
    private ArrayList<Clothes> washandfold_list = new ArrayList<>();
    private ArrayList<Clothes> washandiron_list = new ArrayList<>();
    private ArrayList<Clothes> iron_list = new ArrayList<>();
    private ArrayList<Clothes> premiumlaundry_list = new ArrayList<>();
    private ArrayList<Clothes> drycleaning_list = new ArrayList<>();
    long totalPrice;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(String param1, String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            washandfold_list= getArguments().getParcelableArrayList("WashAndFoldList");
            washandiron_list= getArguments().getParcelableArrayList("WashAndIronList");
            iron_list= getArguments().getParcelableArrayList("IronList");
            premiumlaundry_list= getArguments().getParcelableArrayList("PremiumLaundryList");
            drycleaning_list= getArguments().getParcelableArrayList("DryCleaningList");
            totalPrice= getArguments().getLong("TotalAmount");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_schedule, container, false);
        menu_btn= view.findViewById(R.id.menu_btn);
        pickup_date_layout= view.findViewById(R.id.pickup_date_layout);
        pickup_time_layout= view.findViewById(R.id.pickup_time_layout);
        delivery_date_layout= view.findViewById(R.id.delivery_date_layout);
        delivery_time_layout= view.findViewById(R.id.delivery_time_layout);
        txt_pickup_date= view.findViewById(R.id.txt_pickup_date);
        txt_pickup_time= view.findViewById(R.id.txt_pickup_time);
        txt_delivery_date= view.findViewById(R.id.txt_delivery_date);
        txt_delivery_time= view.findViewById(R.id.txt_delivery_time);
        note= view.findViewById(R.id.et_note);
        btn_next= view.findViewById(R.id.btn_next);

        selectedPickupDate= "";
        selectedDeliveryDate= "";
        selectedPickupTime= "";
        selectedDeliveryTime= "";

        //testing time starts here

        /*String nextTime= pickupTimeArrayData(1);
        Log.d("Next Hour: ", nextTime);*/

        //testing time ends here

        btn_next.setOnClickListener(this);
        menu_btn.setOnClickListener(this);
        pickup_date_layout.setOnClickListener(this);
        pickup_time_layout.setOnClickListener(this);
        delivery_date_layout.setOnClickListener(this);
        delivery_time_layout.setOnClickListener(this);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        if(v==menu_btn)
        {
            if(getActivity()!=null)
            {
                try
                {
                    MainActivity mainActivity= (MainActivity)getActivity();
                    mainActivity.onBackPressed();

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        if(v==btn_next)
        {
            if(getActivity()!=null)
            {
                //hide the open keyboard
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                //if keyboard is open then hide else skip
                if(getActivity().getCurrentFocus() !=null)
                {
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                }

                if(selectedPickupDate!="")
                {

                    if(selectedPickupTime!="")
                    {

                        if(selectedDeliveryDate!="")
                        {

                            if(selectedDeliveryTime!="")
                            {
                                try
                                {
                                    MainActivity mainActivity= (MainActivity)getActivity();
                                    mainActivity.selectAddress(washandfold_list, washandiron_list, iron_list, premiumlaundry_list, drycleaning_list, totalPrice, selectedPickupDate, selectedPickupTime, selectedDeliveryDate, selectedDeliveryTime, note.getText().toString());
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "Please choose delivery time", Toast.LENGTH_LONG).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Please choose delivery date", Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Please choose pickup time", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(getActivity(), "Please choose pickup date", Toast.LENGTH_LONG).show();
                }

            }
        }

        if(v==pickup_date_layout)
        {


            //next seven days for max
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 7);
            Date result = cal.getTime();

            //next day for min
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date());
            cal2.add(Calendar.DAY_OF_MONTH, 1);
            Date result2 = cal2.getTime();

            Log.d("Result 2 Min", String.valueOf(result2.getTime()));
            //for tomorrow date to show default selected date in date picker
            int day= cal2.get(Calendar.DAY_OF_MONTH);
            int month= cal2.get(Calendar.MONTH);
            int year= cal2.get(Calendar.YEAR);

            Log.d("day", String.valueOf(day));
            Log.d("month", String.valueOf(month));
            Log.d("year", String.valueOf(year));

            DatePickerDialog mDatePicker= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    Log.d("Date", String.valueOf(year));
                    Log.d("Date", String.valueOf(month));
                    Log.d("Date", String.valueOf(dayOfMonth));

                    int realMonth= month+1;
                    selectedPickupDate= dayOfMonth+"/"+realMonth+"/"+year;
                    //txt_pickup_date.setText(selectedPickupDate);

                    //reset delivery date after pickup date changed
                    txt_delivery_date.setText("Select Date");
                    selectedDeliveryDate="";

                    Log.d("Selected Pickup ", selectedPickupDate);


                    //get day of the date
                    Date pickDate2= null;
                    try {
                        pickDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(selectedPickupDate);

                        Log.d("Pickup date", pickDate2.toString());

                        Calendar cal3 = Calendar.getInstance();
                        cal3.setTime(pickDate2);
                        //Date result2 = cal3.getTime();

                        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                        String formattedDay=dayFormat.format(cal3.getTime());
                        System.out.println("Current day name of the day using Calendar: "+ formattedDay);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                        String formattedDate=dateFormat.format(cal3.getTime());
                        System.out.println("Current formatted date using Calendar: "+ formattedDate);

                        selectedPickupDate= String.valueOf(formattedDate);
                        Log.d("SelectedPickupDate", selectedPickupDate);
                        //txt_pickup_date.setText(selectedPickupDate+"   ("+formattedDay+")");
                        txt_pickup_date.setText(selectedPickupDate);

                        //txt_pickup_date.setText(txt_pickup_date.getText()+"   ("+formattedDay+")");


                    } catch (ParseException e)
                    {
                        e.printStackTrace();
                    }


                }
            }, year, month, day);
            mDatePicker.show();
            mDatePicker.getDatePicker().setMaxDate(result.getTime());
            mDatePicker.getDatePicker().setMinDate(result2.getTime());
            //mDatePicker.getDatePicker().setMinDate(new Date().getTime());
            //mDatePicker.setMaxDate(System.currentTimeMillis());
            //mDatePicker.setMinDate(result.getTime());

        }

        if(v==pickup_time_layout)
        {
            listPopupWindow = new ListPopupWindow(getActivity());
            listPopupWindow.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.list_item_popupwindow, timePickupArray));
            //BrandBaseAdapter brandBaseAdapter = new BrandBaseAdapter(brandList);
            //listPopupWindow.setAdapter(brandBaseAdapter);
            listPopupWindow.setAnchorView(pickup_time_layout);
            //listPopupWindow.setWidth(300);
            //listPopupWindow.setHeight(400);
            listPopupWindow.setModal(true);
            listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    txt_pickup_time.setText(timePickupArray[position]);
                    selectedPickupTime= timePickupArray[position];
                    /*txt_delivery_date.setText("-Select Date-");
                    selectedDeliveryDate="";*/
                    listPopupWindow.dismiss();
                }
            });

            listPopupWindow.show();
            //Toast.makeText(getActivity(),"Success",Toast.LENGTH_LONG).show();
        }

        if(v==delivery_date_layout)
        {
            //String txt_pickup= txt_pickup_date.getText().toString();

            if(!selectedPickupDate.equals("") && !selectedPickupDate.isEmpty())
            {

                Date pickDate= null;

                try
                {
                    pickDate = new SimpleDateFormat("dd-MMM-yyyy").parse(selectedPickupDate);
                    Log.d("Pick Date", pickDate.toString());


                    //next seven days for max
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(pickDate);
                    cal.add(Calendar.DAY_OF_MONTH, 8);
                    Date result = cal.getTime();

                    //two day after selectedPicupDate for min for delivery
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(pickDate);
                    cal2.add(Calendar.DAY_OF_MONTH, 2);
                    Date result2 = cal2.getTime();

                    Log.d("Result 2 Min", String.valueOf(result2.getTime()));
                    //for tomorrow date to show default selected date in date picker
                    int day= cal2.get(Calendar.DAY_OF_MONTH);
                    int month= cal2.get(Calendar.MONTH);
                    int year= cal2.get(Calendar.YEAR);

                    Log.d("day", String.valueOf(day));
                    Log.d("month", String.valueOf(month));
                    Log.d("year", String.valueOf(year));


                    DatePickerDialog mDatePicker= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            Log.d("Date", String.valueOf(year));
                            Log.d("Date", String.valueOf(month));
                            Log.d("Date", String.valueOf(dayOfMonth));

                            int realMonth= month+1;
                            selectedDeliveryDate= dayOfMonth+"/"+realMonth+"/"+year;
                            txt_delivery_date.setText(selectedDeliveryDate);

                            System.out.println(selectedDeliveryDate);


                            //get day of the date
                            Date pickDate2= null;
                            try {
                                pickDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(selectedDeliveryDate);

                                Calendar cal3 = Calendar.getInstance();
                                cal3.setTime(pickDate2);
                                //Date result2 = cal3.getTime();

                                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                                String formattedDay=dayFormat.format(cal3.getTime());
                                System.out.println("Current day name of the day using Calendar: "+ formattedDay);

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                                String formattedDate=dateFormat.format(cal3.getTime());
                                System.out.println("Current formatted date using Calendar: "+ formattedDate);

                                selectedDeliveryDate= String.valueOf(formattedDate);
                                Log.d("SelectedDeliveryDate", selectedDeliveryDate);

                                //txt_delivery_date.setText(selectedDeliveryDate+"   ("+formattedDay+")");
                                txt_delivery_date.setText(selectedDeliveryDate);

                                //txt_delivery_date.setText(txt_delivery_date.getText()+"   ("+formattedDay+")");


                            } catch (ParseException e)
                            {
                                e.printStackTrace();
                            }


                        }
                    }, year, month, day);
                    mDatePicker.show();
                    mDatePicker.getDatePicker().setMaxDate(result.getTime());
                    mDatePicker.getDatePicker().setMinDate(result2.getTime());
                    //mDatePicker.getDatePicker().setMinDate(new Date().getTime());
                    //mDatePicker.setMaxDate(System.currentTimeMillis());
                    //mDatePicker.setMinDate(result.getTime());


/*
                    //formatting date only excluding time and time zone etc.
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String formattedDate=dateFormat.format(cal.getTime());
                    System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedDate);

                    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                    String formattedDay=dayFormat.format(cal.getTime());
                    System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedDay);

                    String formattedDateAndDay= formattedDate+"   ("+formattedDay+")";

                    System.out.println(formattedDateAndDay);*/


                } catch (ParseException e)
                {
                    e.printStackTrace();
                }


            }
            else
            {
                Toast.makeText(getActivity(), "Please select pickup date first", Toast.LENGTH_LONG).show();
            }

        }

        if(v==delivery_time_layout)
        {
            listPopupWindow = new ListPopupWindow(getActivity());
            listPopupWindow.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.list_item_popupwindow, timeDeliveryArray));
            //BrandBaseAdapter brandBaseAdapter = new BrandBaseAdapter(brandList);
            //listPopupWindow.setAdapter(brandBaseAdapter);
            listPopupWindow.setAnchorView(delivery_time_layout);
            //listPopupWindow.setWidth(300);
            //listPopupWindow.setHeight(400);
            listPopupWindow.setModal(true);
            listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    txt_delivery_time.setText(timeDeliveryArray[position]);
                    selectedDeliveryTime= timeDeliveryArray[position];
                    /*txt_delivery_date.setText("-Select Date-");
                    selectedDeliveryDate="";*/
                    listPopupWindow.dismiss();
                }
            });

            listPopupWindow.show();
            //Toast.makeText(getActivity(),"Success",Toast.LENGTH_LONG).show();
        }

    }

    public String pickupTimeArrayData(int num_of_hours)
    {
        ////To get next hour using calendar class
        Calendar cal = Calendar.getInstance();
        Date time=cal.getTime();
        System.out.println("Current Time: "+time);
        cal.setTime(time);  //it is necessary to set the today date first
        //cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.HOUR_OF_DAY, num_of_hours);  //number of days to bee added to today
        System.out.println("Next Time: "+cal.getTime());

        //Input date and time: 15/02/2014 22:22:12(24 hour format dd/MM/yyyy HH:mm:ss)
        //Output date and time: 2014-02-15 10:22:12 PM (12 hour format yyyy-MM-dd hh:mm:ss aa)

        //formatting date only excluding time and time zone etc.
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
        String formattedTime=timeFormat.format(cal.getTime());
        System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedTime);

        return formattedTime;
    }

    public void dateData()
    {

        /* Get date using Date class
        Date date = new Date();
        String strDateFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);

         //Get date using calendar class
        Calendar cal = Calendar.getInstance();
        Date date2=cal.getTime();
        DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
        String formattedDate2=dateFormat2.format(date2);
        System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedDate2);*/

        /* //To get next date using calendar class
        Calendar cal = Calendar.getInstance();
        Date date2=cal.getTime();
        System.out.println("Current Date: "+date2);
        cal.setTime(date2);
        cal.add(Calendar.DATE, 1);
        System.out.println("Next Date: "+cal.getTime());

                //formatting date
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate2=dateFormat2.format(date2);
        System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedDate2);
        */

        /*
        int no_of_day_to_add = 1;

        Date today = new Date();
        Date tomorrow = new Date( today.getYear(), today.getMonth(), today.getDate() + no_of_day_to_add );*/
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
