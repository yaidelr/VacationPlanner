package com.yaidel.vacations.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yaidel.vacations.R;
import com.yaidel.vacations.database.Repository;
import com.yaidel.vacations.entity.Excursion;
import com.yaidel.vacations.entity.Vacation;
import java.util.List;
import androidx.appcompat.widget.SearchView;

public class VacationList extends AppCompatActivity {

    private Repository repository;
    private VacationAdapter vacationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vacation_list);

        //testing passing data
        String yaitest = getIntent().getStringExtra("yaitest");
        System.out.println(yaitest);

        //add floating action button
        FloatingActionButton addFab = findViewById(R.id.addfloatingActionButton);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VacationList.this, VacationDetails.class);
                startActivity(intent);
            }
        }); //end addFab
        RecyclerView recyclerView = findViewById(R.id.VacationListRecyclerView);

        repository = new Repository(getApplication());
        List<Vacation> allvacations = repository.getAllVacations();
        vacationAdapter = new VacationAdapter(this);  // Using as a field
        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter.SetVacation(allvacations);

        // add SearchView to filter the vacations
        SearchView searchView = findViewById(R.id.searchView);  // Assuming the SearchView is already in the layout
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;  // No action on submit, search happens on text change
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                vacationAdapter.filter(newText);  // Call filter method on the adapter
                return true;
            }
        });
    }  //end onCreate

    // menu item inflator and back button navigation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vacation_list, menu);
        return true;
    } //end onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //menu item selected
        if (id == R.id.add_sample_data) {
            repository = new Repository(getApplication());
            Toast.makeText(this, "Adding sample Data", Toast.LENGTH_SHORT).show();

            //sample data to be added

            Vacation hawaii = new Vacation(0,"Hawaii Getaway", "Hanallu 5 star","9/15/2023","9/20/2023");
            //hawaii excursions
            Excursion hawaii_snorkling = new Excursion(0,1,"Snorkling","9/16/2024");
            Excursion hawaii_fishing = new Excursion(0,1,"Fishing","9/17/2024");
            Excursion hawaii_hiking = new Excursion(0,1,"Hiking","9/18/2024");
            Excursion hawaii_swimming = new Excursion(0,1,"Swimming","9/19/2024");

            Vacation cuba = new Vacation(0,"Cuba Getaway", "Cuba 5 star","9/15/2024","9/20/2024");
            //cuba excursions
            Excursion cuba_snorkling = new Excursion(0,2,"Snorkling","9/16/2024");
            Excursion cuba_fishing = new Excursion(0,2,"Fishing","9/17/2024");
            Excursion cuba_hiking = new Excursion(0,2,"Hiking","9/18/2024");

            Vacation japan = new Vacation(0,"Japan Getaway", "Tokyo 5 star","9/15/2024","9/20/2024");
            //japan excursions
            Excursion japan_snorkling = new Excursion(0,3,"Snorkling","9/16/2024");
            Excursion japan_fishing = new Excursion(0,3,"Fishing","9/17/2024");

            //hawaii excursions
            repository.insert(hawaii);
            repository.insert(hawaii_snorkling);
            repository.insert(hawaii_fishing);
            repository.insert(hawaii_hiking);
            repository.insert(hawaii_swimming);

            //cuba excursions
            repository.insert(cuba);
            repository.insert(cuba_snorkling);
            repository.insert(cuba_fishing);
            repository.insert(cuba_hiking);

            //japan excursions
            repository.insert(japan);
            repository.insert(japan_snorkling);
            repository.insert(japan_fishing);

            Toast.makeText(this, "Sample Data Added!", Toast.LENGTH_SHORT).show();
            return true;
        }

        // if back button pressed
        if (id == android.R.id.home) {

            //** note to self intent to another view can be added here!!
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    } //end onOptionsItemSelected

    // on resume when going back to view update database
    @Override
    protected void onResume() {
        super.onResume();
        List<Vacation> allVacations = repository.getAllVacations();
        RecyclerView recyclerView = findViewById(R.id.VacationListRecyclerView);
        vacationAdapter = new VacationAdapter(this);  // Make sure we're using the same adapter for the search
        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter.SetVacation(allVacations);
    } //end onResume
} //end activity
