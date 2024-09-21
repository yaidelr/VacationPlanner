package com.yaidel.vacations.UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yaidel.vacations.R;
import com.yaidel.vacations.database.Repository;
import com.yaidel.vacations.entity.Excursion;
import com.yaidel.vacations.entity.Vacation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


//-----------------vacation and excursion details view--------------------------------

public class VacationDetails extends AppCompatActivity {

    EditText editTitle;
    EditText editHotel;
    EditText editStartDate;
    EditText editEndDate;

    int vacationId;
    String title;
    String hotel;
    String startDate;
    String endDate;

    Repository repository;
    ExcursionAdapter excursionAdapter;

    // New SimpleDateFormat for validation and alarm setting
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vacation_details);

        //Bug here to fix
        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);

        //edit text fields in vacation details
        vacationId = getIntent().getIntExtra("id", -1);
        editTitle = findViewById(R.id.editTitle);
        editHotel = findViewById(R.id.editHotel);
        editStartDate = findViewById(R.id.editStartDate);
        editEndDate = findViewById(R.id.editEndDate);

        //get data from vacation edit text fields
        vacationId = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        hotel = getIntent().getStringExtra("hotel");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");

        //set data to vacation edit text fields
        editTitle.setText(title);
        editHotel.setText(hotel);
        editStartDate.setText(startDate);
        editEndDate.setText(endDate);

        // Add DatePicker for Start Date
        editStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(VacationDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        editStartDate.setText( (selectedMonth + 1) + "/" +selectedDay  + "/" + selectedYear);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        // Add DatePicker for End Date
        editEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(VacationDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        editEndDate.setText( (selectedMonth + 1) + "/" +selectedDay  + "/" + selectedYear);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VacationDetails.this, ExcursionDetails.class);
                intent.putExtra("vacationId", vacationId);
                intent.putExtra("vacationTitle", title);
                intent.putExtra("vacationHotel", hotel);
                intent.putExtra("vacationStartDate", startDate);
                intent.putExtra("vacationEndDate", endDate);
                startActivity(intent);

                System.out.println("---- floatingActionButton2 -----(OnClick)-------(vacation and excursion detail) passing to (excursion details)-------------");
                System.out.println("vacationId "+vacationId);
                System.out.println("vacationTitle "+title);
                System.out.println("vacationHotel "+hotel);
                System.out.println("vacationStartDate "+startDate);
                System.out.println("vacationEndDate "+endDate);

            }
        });

        RecyclerView recyclerView = findViewById(R.id.VacationDetailsRecyclerView);
        repository = new Repository(getApplication());
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this, startDate, endDate);
        recyclerView.setAdapter(excursionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //filter excursions by vacation id
        List<Excursion> filteredExcursions = new ArrayList<>();
        for (Excursion e : repository.getAllExcursions()) {
            if (e.getVacationId() == vacationId) {
                filteredExcursions.add(e);
            }
        }
        excursionAdapter.setExcursions(filteredExcursions);

    }

    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.VacationDetailsRecyclerView);
        repository = new Repository(getApplication());
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this, startDate, endDate);
        recyclerView.setAdapter(excursionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //filter excursions by vacation id
        List<Excursion> filteredExcursions = new ArrayList<>();
        for (Excursion e : repository.getAllExcursions()) {
            if (e.getVacationId() == vacationId) {
                filteredExcursions.add(e);
            }
        }
        excursionAdapter.setExcursions(filteredExcursions);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vacationdetails, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.vacation_save) {

            // Add validation for end date after start date
            try {
                Date startDate = sdf.parse(editStartDate.getText().toString());
                Date endDate = sdf.parse(editEndDate.getText().toString());


                if (endDate != null && endDate.before(startDate)) {
                    Toast.makeText(this, "End date cannot be before start date!", Toast.LENGTH_SHORT).show();
                    return false; // Stop saving if validation fails
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Vacation vacation;

            //if defauult prduct id ie new product
            if (vacationId == -1) {
                if (repository.getAllVacations().isEmpty()) vacationId = 1;
                else vacationId = repository.getAllVacations().get(repository.getAllVacations().size() - 1).getId() + 1;
                vacation = new Vacation(vacationId, editTitle.getText().toString(), editHotel.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
                repository.insert(vacation);

                // Set alarm after saving the vacation
                setAlarmForDates(vacation);

                System.out.println("new vacation  added");
                Toast.makeText(this, "New Vacation Added", Toast.LENGTH_SHORT).show();
                this.finish();
            }

            //if not new product just modify
            else {
                vacation = new Vacation(vacationId, editTitle.getText().toString(), editHotel.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
                repository.update(vacation);

                // set alarm after updating the vacations
                setAlarmForDates(vacation);

                System.out.println("vacation updated");
                Toast.makeText(this, "Vacation Updated", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }

        // delete vacation
        else if (item.getItemId() == R.id.vacation_delete) {
            List<Excursion> associatedExcursions = repository.getAssociatedExcursions(vacationId);

            // check if the vacation has associated excursions
            if (associatedExcursions != null && !associatedExcursions.isEmpty()) {
                Toast.makeText(this, "Cannot delete vacation. There are associated Excursions", Toast.LENGTH_SHORT).show();
            } else {
                Vacation vacation = new Vacation(vacationId, editTitle.getText().toString(), editHotel.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
                repository.delete(vacation);

                System.out.println("Vacation deleted");
                Toast.makeText(this, "Vacation Deleted", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }

        // Add sharing option
        else if (item.getItemId() == R.id.vacation_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String vacationDetails = "Vacation: " + title + "\nHotel: " + hotel + "\nStart Date: " + startDate + "\nEnd Date: " + endDate;
            shareIntent.putExtra(Intent.EXTRA_TEXT, vacationDetails);
            startActivity(Intent.createChooser(shareIntent, "Share Vacation Details"));
        }

        // Add setting alarm option
        else if (item.getItemId() == R.id.set_alarm) {
            // Retrieve vacation details
            Vacation vacation = new Vacation(vacationId, editTitle.getText().toString(), editHotel.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());

            // Set alarm for the vacation
            setAlarmForDates(vacation);
            Toast.makeText(this, "Alarm Set for vacation start and end dates", Toast.LENGTH_SHORT).show();
        }


        return true;
    }

    // Set alarm for start and end dates
    private void setAlarmForDates(Vacation vacation) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        try {
            // set start date alarm for strating
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(sdf.parse(vacation.getStartDate()));

            Intent startIntent = new Intent(this, VacationAlarmReceiver.class);
            startIntent.putExtra(" vacationTitle", vacation.getTitle());
            startIntent.putExtra("status", "starting");

            PendingIntent startPendingIntent = PendingIntent.getBroadcast(this, 0, startIntent, PendingIntent.FLAG_IMMUTABLE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, startCalendar.getTimeInMillis(), startPendingIntent);

            // set end date alarm for ending
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(sdf.parse(vacation.getEndDate()));

            Intent endIntent = new Intent(this, VacationAlarmReceiver.class);
            endIntent.putExtra("vacationTitle", vacation.getTitle());
            endIntent.putExtra("status", "ending");

            PendingIntent endPendingIntent = PendingIntent.getBroadcast(this, 1, endIntent, PendingIntent.FLAG_IMMUTABLE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, endCalendar.getTimeInMillis(), endPendingIntent);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}// end class
