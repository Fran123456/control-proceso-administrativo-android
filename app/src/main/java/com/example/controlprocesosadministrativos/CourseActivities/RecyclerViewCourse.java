package com.example.controlprocesosadministrativos.CourseActivities;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.Models.Course;
import com.example.controlprocesosadministrativos.R;

import java.util.List;

public class RecyclerViewCourse extends RecyclerView.Adapter<RecyclerViewCourse.ViewHolder> implements View.OnClickListener {
    public List<Course> menuList;
    private View.OnClickListener listener;
    private DataBaseHelper DB;

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView career;
        private Button deletebtn;
        private Button editbtn;
        private TextView careertxt;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.txtCourseList_list);
            deletebtn = (Button)itemView.findViewById(R.id.btnCourseDelete_list) ;
            image=(ImageView)itemView.findViewById(R.id.imgCourseList_list);
            editbtn=(Button)itemView.findViewById(R.id.btnCourseEdit_list);
            careertxt=(TextView) itemView.findViewById(R.id.txtCourseCareerList_list);

        }
    }


    public RecyclerViewCourse(List<Course> menuList) {
        this.menuList = menuList;
    }


    @Override
    public RecyclerViewCourse.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_course_list, parent, false);
        RecyclerViewCourse.ViewHolder viewHolder = new RecyclerViewCourse.ViewHolder(view);
        view.setOnClickListener(this);
        DB = new DataBaseHelper (parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewCourse.ViewHolder holder, int position) {

        Career c= DB.getCareer(  String.valueOf(menuList.get(position).getCarrerId())    );
        holder.title.setText("(" + menuList.get(position).getCodeCourse().toUpperCase() + ") " + menuList.get(position).getCourse());
        holder.careertxt.setText( c.getCareer()  );
        holder.image.setImageResource(R.drawable.carrera);

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                DB = new DataBaseHelper(view.getContext());
                String message= DB.deleteCourse(menuList.get(position).getId());
                menuList.clear();
                menuList.addAll(DB.getCourses());
                notifyDataSetChanged();
                Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
            }
        });


        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Class<?> clase = Class.forName("com.example.controlprocesosadministrativos.CourseActivities.CourseEditActivity");
                       Intent inte = new Intent(view.getContext(), clase);
                       inte.putExtra("id", String.valueOf( menuList.get(position).getId() ) );
                       view.getContext().startActivity(inte);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
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