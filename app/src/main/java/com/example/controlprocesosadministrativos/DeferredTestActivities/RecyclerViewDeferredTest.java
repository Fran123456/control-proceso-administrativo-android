package com.example.controlprocesosadministrativos.DeferredTestActivities;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.controlprocesosadministrativos.CourseActivities.RecyclerViewCourse;
import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Models.Career;
import com.example.controlprocesosadministrativos.Models.Course;
import com.example.controlprocesosadministrativos.Models.Local;
import com.example.controlprocesosadministrativos.Models.Student;
import com.example.controlprocesosadministrativos.Models.TestDiferred;
import com.example.controlprocesosadministrativos.R;

import java.util.List;

public class RecyclerViewDeferredTest extends RecyclerView.Adapter<RecyclerViewDeferredTest.ViewHolder> implements View.OnClickListener {
    public List<TestDiferred> menuList;
    private View.OnClickListener listener;
    private DataBaseHelper DB;


    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView solTxt;
        private TextView studentTxt;
        private TextView carnetTxt;
        private TextView courseTxt;
        private TextView placeTxt;
        private TextView timeTxt;
        private TextView dateTxt;
        private Button deletebtn;

        public ViewHolder(View itemView) {
            super(itemView);
            solTxt = (TextView)itemView.findViewById(R.id.soldeferred_list);
            studentTxt = (TextView) itemView.findViewById(R.id.studentdeferred_list) ;
            carnetTxt = (TextView) itemView.findViewById(R.id.carnetdeferred_list) ;
            courseTxt = (TextView) itemView.findViewById(R.id.coursedeferred_list) ;
            placeTxt = (TextView) itemView.findViewById(R.id.palcedeferred_list) ;
            timeTxt = (TextView) itemView.findViewById(R.id.timecedeferred_list) ;
            dateTxt = (TextView) itemView.findViewById(R.id.datecedeferred_list) ;
            deletebtn = (Button) itemView.findViewById(R.id.deleteDiferred_btn);
        }
    }


    public RecyclerViewDeferredTest(List<TestDiferred> menuList) {
        this.menuList = menuList;
    }


    @Override
    public RecyclerViewDeferredTest.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_deferred_list, parent, false);
            RecyclerViewDeferredTest.ViewHolder viewHolder = new RecyclerViewDeferredTest.ViewHolder(view);
        view.setOnClickListener(this);
        DB = new DataBaseHelper (parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewDeferredTest.ViewHolder holder, int position) {
        Course course=DB.getCourse(  String.valueOf(menuList.get(position).getCourseId())  );
        Student student = DB.getStudent(String.valueOf(menuList.get(position).getStudentId()));
        Career career = DB.getCareer(String.valueOf(course.getId()));
        Local local =DB.getLocal(  String.valueOf(menuList.get(position).getLocalId())  );

        holder.solTxt.setText("Solicitud de diferido Numero: "+ String.valueOf(menuList.get(position).getId())   );
        holder.studentTxt.setText(student.getName());
        holder.carnetTxt.setText(student.getCarnet());
        holder.placeTxt.setText("Local: "+ local.getLocal());
        holder.dateTxt.setText("Hora: "+menuList.get(position).getTime() );
        holder.timeTxt.setText(  "Fecha: "+menuList.get(position).getDate());

        holder.courseTxt.setText(course.getCourse() + " (" + course.getCodeCourse() + ") ");

     /*   holder.title.setText("(" + menuList.get(position).getCodeCourse().toUpperCase() + ") " + menuList.get(position).getCourse());
        holder.careertxt.setText( c.getCareer()  );
        holder.image.setImageResource(R.drawable.carrera);*/

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                DB = new DataBaseHelper(view.getContext());
                String message= DB.deleteDiferredTest(menuList.get(position).getId());
                menuList.clear();
                menuList.addAll(DB.getTests());
                notifyDataSetChanged();
                Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
            }
        });

/*
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
        });*/
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

}