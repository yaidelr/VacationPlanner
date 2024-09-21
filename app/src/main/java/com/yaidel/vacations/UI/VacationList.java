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

public class VacationList extends AppCompatActivity {

    private Repository repository;

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
        });//end addFab
        RecyclerView recyclerView = findViewById(R.id.VacationListRecyclerView);

        repository = new Repository(getApplication());
        List<Vacation> allvacations = repository.getAllVacations();
        final VacationAdapter VacationAdapter = new VacationAdapter(this);
        recyclerView.setAdapter(VacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        VacationAdapter.SetVacation(allvacations);


    }  //end onCreate


    // menu item  inflator  and back buton navegation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vacation_list, menu);
        return true;
    }//end onCreateOptionsMenu

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
            Excursion hawaii_snorkling = new Excursion(0,1,"Snorkling","9/16/2023");
            Excursion hawaii_fishing = new Excursion(0,1,"Fishing","9/17/2023");
            Excursion hawaii_hiking = new Excursion(0,1,"Hiking","9/18/2023");
            Excursion hawaii_swimming = new Excursion(0,1,"Swimming","9/19/2023");


            Vacation cuba = new Vacation(0,"Cuba Getaway", "Cuba 5 star","9/15/2023","9/20/2023");
            //cuba excursions
            Excursion cuba_snorkling = new Excursion(0,2,"Snorkling","9/16/2023");
            Excursion cuba_fishing = new Excursion(0,2,"Fishing","9/17/2023");
            Excursion cuba_hiking = new Excursion(0,2,"Hiking","9/18/2023");


            Vacation japan = new Vacation(0,"Japan Getaway", "Tokyo 5 star","9/15/2023","9/20/2023");
            //japan excursions
            Excursion japan_snorkling = new Excursion(0,3,"Snorkling","9/16/2023");
            Excursion japan_fishing = new Excursion(0,3,"Fishing","9/17/2023");


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

    }//end onOptionsItemSelected

    // on resume  when going back to vew update database
    @Override
    protected void onResume() {
        super.onResume();
        List<Vacation> allVacations = repository.getAllVacations();
        RecyclerView recyclerView = findViewById(R.id.VacationListRecyclerView);
        final VacationAdapter VacationAdapter = new VacationAdapter(this);
        recyclerView.setAdapter(VacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        VacationAdapter.SetVacation(allVacations);

    }//end onResume



}//end activity