package com.example.tutorassistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterClass extends RecyclerView.Adapter<MyAdapterClass.MyViewHolder> {

    ArrayList<TuitionModelClass> objectmodelclass ;

    public MyAdapterClass(ArrayList<TuitionModelClass> objectmodelclass) {
        this.objectmodelclass = objectmodelclass;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       View singleRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_layout,viewGroup,false) ;
       return new MyViewHolder(singleRow) ;


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        TuitionModelClass objmodelclass = objectmodelclass.get(position) ;
        holder.NameTextView.setText(objmodelclass.getName());
        holder.DaysTextView.setText(objmodelclass.getClasses());





    }

    @Override
    public int getItemCount() {
        return objectmodelclass.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView NameTextView,DateTextview,DaysTextView,AjkeKiBarTextView ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            NameTextView = itemView.findViewById(R.id.text_name) ;
            DateTextview= itemView.findViewById(R.id.datefor) ;
            DaysTextView = itemView.findViewById(R.id.total_days) ;
            AjkeKiBarTextView = itemView.findViewById(R.id.name_of_day) ;

        }
    }
}
