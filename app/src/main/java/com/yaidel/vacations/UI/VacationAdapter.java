package com.yaidel.vacations.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yaidel.vacations.R;
import com.yaidel.vacations.entity.Vacation;

import java.util.ArrayList;
import java.util.List;

// NOTE.. Adapters put stuff in the recycler view
public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.VacationViewHolder> {

    private List<Vacation> mVacations;
    private List<Vacation> mVacationsFull;  // Copy of the full list for filtering
    private final Context context;
    private final LayoutInflater mInflater;

    public VacationAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    // Automaticaly added all this methods
    public class VacationViewHolder extends RecyclerView.ViewHolder {

        private final TextView vacationItemView;

        public VacationViewHolder(@NonNull View itemView) {
            super(itemView);
            vacationItemView = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Vacation current = mVacations.get(position);

                    //part b-2
                    //passing data to vacation and excursion details view
                    Intent intent = new Intent(context, VacationDetails.class);
                    intent.putExtra("id", current.getId());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("hotel", current.getHotel());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public VacationAdapter.VacationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.vacation_list_item, parent, false);
        return new VacationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VacationAdapter.VacationViewHolder holder, int position) {
        if (mVacations != null) {
            Vacation current = mVacations.get(position);
            String title = current.getTitle();
            holder.vacationItemView.setText(title);
        } else {
            String noVacation = "No Vacation";
            holder.vacationItemView.setText(noVacation);
        }
    }

    @Override
    public int getItemCount() {
        if (mVacations != null) {
            return mVacations.size();
        } else {
            return 0;
        }
    }

    public void SetVacation(List<Vacation> vacations) {
        mVacations = vacations;
        mVacationsFull = new ArrayList<>(vacations);  // Keep a full copy of the original list for filtering
        notifyDataSetChanged();
    }

    // Method to filter the list based on search query
    public void filter(String query) {
        if (query == null || query.isEmpty()) {
            mVacations = new ArrayList<>(mVacationsFull);  // Reset to full list when query is empty
        } else {
            List<Vacation> filteredList = new ArrayList<>();
            for (Vacation vacation : mVacationsFull) {
                if (vacation.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(vacation);  // Add matching vacations to the filtered list
                }
            }
            mVacations = filteredList;  // Update the list to show filtered results
        }
        notifyDataSetChanged();  // Refresh the RecyclerView with the new data
    }

}
