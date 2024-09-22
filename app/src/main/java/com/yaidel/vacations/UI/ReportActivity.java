package com.yaidel.vacations.UI;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yaidel.vacations.R;
import com.yaidel.vacations.UI.ReportAdapter;
import com.yaidel.vacations.database.Repository;
import com.yaidel.vacations.entity.Vacation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ReportActivity extends AppCompatActivity {

    private Repository repository;
    private RecyclerView reportRecyclerView;
    private ReportAdapter reportAdapter;
    private Button startDateButton, endDateButton;
    private Calendar startDateCalendar, endDateCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        // Initialize UI components
        startDateButton = findViewById(R.id.startDateButton);
        endDateButton = findViewById(R.id.endDateButton);
        reportRecyclerView = findViewById(R.id.reportRecyclerView);
        reportRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        repository = new Repository(getApplication());

        // Initialize date pickers for start and end dates
        startDateCalendar = Calendar.getInstance();
        endDateCalendar = Calendar.getInstance();

        // Set onClickListeners for the DatePicker buttons
        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(startDateCalendar, startDateButton);
            }
        });

        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(endDateCalendar, endDateButton);
            }
        });
    }

    // Show DatePicker dialog and update the button text
    private void showDatePickerDialog(final Calendar calendar, final Button button) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);

                        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy", Locale.US);
                        button.setText(sdf.format(calendar.getTime()));

                        // Generate report if both start and end dates are set
                        if (!startDateButton.getText().toString().equals("Select Start Date") &&
                                !endDateButton.getText().toString().equals("Select End Date")) {
                            generateReport();
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    //generate report based on selected start and end dates
    private void generateReport() {
        String startDate = startDateButton.getText().toString();
        String endDate = endDateButton.getText().toString();

        // Log the selected dates
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);

        //get vacations within the date range
        List<Vacation> reportData = repository.getVacationsByDateRange(startDate, endDate);

        // Display the report in the RecyclerView
        System.out.println("reportData test: " + reportData);
        if (reportData != null && !reportData.isEmpty()) {
            reportAdapter = new ReportAdapter(this);
            reportAdapter.setReportData(reportData);
            reportRecyclerView.setAdapter(reportAdapter);
        } else {
            System.out.println("No data found for the selected date range.");
        }
    }
}
