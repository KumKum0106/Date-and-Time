package com.example.dateandtimepicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button pickDate, pickTime; // pickDate Button is the date Picker variable and pickTime Button is the Time picker variable.
    private TextView selectedDate, selectedTime, status; // selectedDate is the date Picker variable and selectedTime is the Time picker variable.
    private TextClock textClock;
    private Switch switchview; // status and switchview are Switch variables

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initializing variables for Switch
        status = findViewById(R.id.idStatus);
        switchview = findViewById(R.id.idSwitch);

        // Initializing variables for Date picker
        pickDate = findViewById(R.id.idBtnPickDate);
        selectedDate = findViewById(R.id.idTVSelectedDate);

        // Initializing variables for Time picker
        pickTime = findViewById(R.id.idBtnPickTime);
        selectedTime = findViewById(R.id.idTVSelectedTime);

        // Initializing variables for TextCLock
        textClock = findViewById(R.id.idTXClock);

        // Checking Switch Status
        if(switchview.isChecked()){
            status.setText("Switch is Checked"); // If switch is checked, this text will be shown
        }else{
            status.setText("Switch is UnChecked"); // If switch is not checked, this text will be shown
        }

        // Setting Check Change Listener for switch
        switchview.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // Check if the switch is check or not
                if(isChecked){
                    status.setText("Switch is Checked"); // If switch is checked
                }else{
                    status.setText("Switch is UnChecked"); // If switch is not checked
                }
            }
        });

        // CLick listener on the pickDate button of Date picker widget
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating an Object of the Calender
                final Calendar c = Calendar.getInstance();

                // We are receiving year, month and day
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // Initializing the DatePicker Dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year); // Format to be shown in the selectedDate textview.
                    }
                    // passing year, month and day for selected date in our Date Picker
                },year, month, day);
                // display Date Picker dialog
                datePickerDialog.show();
            }
        });

        // CLick listener on the pickTime button of Time picker widget
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating an Object of the Calender
                final Calendar c1 = Calendar.getInstance();

                // We are receiving hour and minute
                int hour = c1.get(Calendar.HOUR_OF_DAY);
                int minute = c1.get(Calendar.MINUTE);

                // Initializing the TimePicker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTime.setText(hourOfDay + ":" + minute); // Format to be shown in the selectedTime textview.
                    }
                    // passing hour and minute for selected time in our Date Picker
                },hour, minute, false);
                // display Date Picker dialog
                timePickerDialog.show();
            }
        });

        //setting 12 hour format for our text clock
        textClock.setFormat12Hour("hh:mm:ss a");
    }

    // Alert Dialog Box shows the Alert message and gives the answer in the form of yes or no. Display a warning message.
    // We need Title, Message area, and Action Button to create a Alert Dialog Box
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Alert !"); // display the Alert Dialog box Title
        builder.setMessage("Do you want to go back?"); // Display the message
        builder.setCancelable(false); // when the user clicks on the outside the Dialog Box then it will remain show

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes, app will close
            finish();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no, dialog box is canceled.
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create(); // Create Alert dialog box
        alertDialog.show(); // Show the Alert Dialog Box
    }
}