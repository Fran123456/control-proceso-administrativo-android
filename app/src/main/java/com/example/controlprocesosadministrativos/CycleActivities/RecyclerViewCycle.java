package com.example.controlprocesosadministrativos.CycleActivities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.controlprocesosadministrativos.Models.Cycle;
import com.example.controlprocesosadministrativos.R;

import java.text.SimpleDateFormat;
import java.util.List;

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
            inicio = (TextView)itemView.findViewById(R.id.txtcycle_i);
            deletebtn = (Button)itemView.findViewById(R.id.btncycle_delete) ;
            image=(ImageView)itemView.findViewById(R.id.imgCycle_img);
            editbtn=(Button)itemView.findViewById(R.id.btncycle_edit);
            fin=(TextView) itemView.findViewById(R.id.txtcycle_f);
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
                //

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
