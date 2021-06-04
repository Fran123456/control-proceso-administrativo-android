package com.example.controlprocesosadministrativos.TeacherActivities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.controlprocesosadministrativos.CycleActivities.RecyclerViewCycle;
import com.example.controlprocesosadministrativos.Models.Cycle;
import com.example.controlprocesosadministrativos.Models.Teacher;
import com.example.controlprocesosadministrativos.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecyclerViewTeacher extends RecyclerView.Adapter<RecyclerViewTeacher.ViewHolder> implements View.OnClickListener{
    public List<Teacher> menuList;
    private View.OnClickListener listener;

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private Button deletebtn;
        private Button editbtn;
        private TextView apellido;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.txttecher_name);
            deletebtn = (Button)itemView.findViewById(R.id.btnteacher_delete) ;
            image=(ImageView)itemView.findViewById(R.id.imgTeacher_img);
            editbtn=(Button)itemView.findViewById(R.id.btnteacher_edit);
            apellido=(TextView) itemView.findViewById(R.id.txtteacher_apellido);
        }
    }


    public RecyclerViewTeacher(List<Teacher> menuList) {
        this.menuList = menuList;
    }


    @Override
    public RecyclerViewTeacher.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_teacher, parent, false);
        RecyclerViewTeacher.ViewHolder viewHolder = new RecyclerViewTeacher.ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewTeacher.ViewHolder holder, int position) {




        holder.nombre.setText(menuList.get(position).getNombre() );
        holder.apellido.setText(menuList.get(position).getApellido());
        holder.image.setImageResource(R.drawable.teacher2);


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
