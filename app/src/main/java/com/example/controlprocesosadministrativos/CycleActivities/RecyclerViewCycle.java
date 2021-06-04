package com.example.controlprocesosadministrativos.CycleActivities;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controlprocesosadministrativos.DeferredTestActivities.RecyclerViewDeferredTest;
import com.example.controlprocesosadministrativos.Models.Cycle;
import com.example.controlprocesosadministrativos.Models.CycleApi;
import com.example.controlprocesosadministrativos.Models.Success;
import com.example.controlprocesosadministrativos.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerViewCycle extends RecyclerView.Adapter<RecyclerViewCycle.ViewHolder> implements View.OnClickListener{

    public List<Cycle> menuList;
    private View.OnClickListener listener;

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fin;
        private Button deletebtn;
        private Button editbtn;
        private TextView inicio;
        private TextView titulo;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            inicio = (TextView)itemView.findViewById(R.id.txttecher_name);
            deletebtn = (Button)itemView.findViewById(R.id.btnteacher_delete) ;
            image=(ImageView)itemView.findViewById(R.id.imgTeacher_img);
            editbtn=(Button)itemView.findViewById(R.id.btnteacher_edit);
            fin=(TextView) itemView.findViewById(R.id.txtteacher_apellido);
            titulo=(TextView) itemView.findViewById(R.id.txtcycle_cicle);
        }
    }

    public RecyclerViewCycle(List<Cycle> menuList) {
        this.menuList = menuList;
    }


    @Override
    public RecyclerViewCycle.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_cycle, parent, false);
        RecyclerViewCycle.ViewHolder viewHolder = new RecyclerViewCycle.ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewCycle.ViewHolder holder, int position) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");



        holder.inicio.setText("Inicio: "+sdf.format(menuList.get(position).getFecha_inicio() ));
        holder.fin.setText("Fin: "+ sdf.format(menuList.get(position).getFecha_fin()));
        holder.image.setImageResource(R.drawable.date);
        holder.titulo.setText(menuList.get(position).getNombre());


        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://nh16001.000webhostapp.com/servicios-web-pdm/").addConverterFactory(GsonConverterFactory.create()).build();
                CycleApi cycleApi = retrofit.create(CycleApi.class);
                Call<Success> call = cycleApi.eliminar(RecyclerViewCycle.this.menuList.get(position).getId());
                Success success = new Success();
                call.enqueue(new Callback<Success>() {
                    @Override
                    public void onResponse(Call<Success> call, Response<Success> response) {
                        try{
                            if(response.isSuccessful()){
                                 //Toast.makeText(view.getContext(), response.body().getSuccess() , Toast.LENGTH_LONG).show();
                                 RecyclerViewCycle.this.menuList.clear();

                                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://nh16001.000webhostapp.com/servicios-web-pdm/").addConverterFactory(GsonConverterFactory.create()).build();
                                CycleApi cycleApi = retrofit.create(CycleApi.class);
                                Call<List<Cycle>> call2 = cycleApi.contenido();
                                List<Cycle> list = new ArrayList<>();
                                call2.enqueue(new Callback<List<Cycle>>() {
                                    @Override
                                    public void onResponse(Call<List<Cycle>> call, Response<List<Cycle>> response) {
                                        try{
                                            if(response.isSuccessful()){
                                              //  list.addAll(response.body());
                                                RecyclerViewCycle.this.menuList.clear();
                                                RecyclerViewCycle.this.menuList.addAll(response.body());
                                                RecyclerViewCycle.this.notifyDataSetChanged();
                                                Toast.makeText(view.getContext(), "Eliminado correctamente", Toast.LENGTH_LONG).show();
                                            }
                                        }catch(Exception ex){
                                            Toast.makeText(view.getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<Cycle>> call, Throwable t) {
                                        Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });

                            }
                        }catch(Exception ex){
                            Toast.makeText(view.getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Success> call, Throwable t) {
                        Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* try {
                    Class<?> clase = Class.forName("com.example.controlprocesosadministrativos.CourseActivities.CourseEditActivity");
                    Intent inte = new Intent(view.getContext(), clase);
                    inte.putExtra("id", String.valueOf( menuList.get(position).getId() ) );
                    view.getContext().startActivity(inte);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }*/
            }
        });
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

}
