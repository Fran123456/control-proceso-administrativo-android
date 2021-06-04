package com.example.controlprocesosadministrativos.DeferredTestActivities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.controlprocesosadministrativos.R;
import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Models.Course;
import com.example.controlprocesosadministrativos.Models.Local;
import com.example.controlprocesosadministrativos.Models.Student;
import com.example.controlprocesosadministrativos.Models.TestDiferred;
import java.util.List;

public class RecyclerViewDeferredTest extends RecyclerView.Adapter<RecyclerViewDeferredTest.ViewHolder> implements View.OnClickListener {
    /* access modifiers changed from: private */

    /* renamed from: DB */
    public DataBaseHelper f87DB;
    private View.OnClickListener listener;
    public List<TestDiferred> menuList;

    public void onClick(View view) {
        View.OnClickListener onClickListener = this.listener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public TextView carnetTxt;
        /* access modifiers changed from: private */
        public TextView courseTxt;
        /* access modifiers changed from: private */
        public TextView dateTxt;
        /* access modifiers changed from: private */
        public Button deletebtn;
        /* access modifiers changed from: private */
        public TextView placeTxt;
        /* access modifiers changed from: private */
        public TextView solTxt;
        /* access modifiers changed from: private */
        public TextView studentTxt;
        /* access modifiers changed from: private */
        public TextView timeTxt;

        public ViewHolder(View view) {
            super(view);
            this.solTxt = (TextView) view.findViewById(R.id.soldeferred_list);
            this.studentTxt = (TextView) view.findViewById(R.id.studentdeferred_list);
            this.carnetTxt = (TextView) view.findViewById(R.id.carnetdeferred_list);
            this.courseTxt = (TextView) view.findViewById(R.id.coursedeferred_list);
            this.placeTxt = (TextView) view.findViewById(R.id.palcedeferred_list);
            this.timeTxt = (TextView) view.findViewById(R.id.timecedeferred_list);
            this.dateTxt = (TextView) view.findViewById(R.id.datecedeferred_list);
            this.deletebtn = (Button) view.findViewById(R.id.deleteDiferred_btn);
        }
    }

    public RecyclerViewDeferredTest(List<TestDiferred> list) {
        this.menuList = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_deferred_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        inflate.setOnClickListener(this);
        this.f87DB = new DataBaseHelper(viewGroup.getContext());
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        Course course = this.f87DB.getCourse(String.valueOf(this.menuList.get(i).getCourseId()));
        Student student = this.f87DB.getStudent(String.valueOf(this.menuList.get(i).getStudentId()));
        this.f87DB.getCareer(String.valueOf(course.getId()));
        Local local = this.f87DB.getLocal(String.valueOf(this.menuList.get(i).getLocalId()));
        TextView access$000 = viewHolder.solTxt;
        access$000.setText("Solicitud de diferido Numero: " + String.valueOf(this.menuList.get(i).getId()));
        viewHolder.studentTxt.setText(student.getName());
        viewHolder.carnetTxt.setText(student.getCarnet());
        TextView access$300 = viewHolder.placeTxt;
        access$300.setText("Local: " + local.getLocal());
        TextView access$400 = viewHolder.dateTxt;
        access$400.setText("Hora: " + this.menuList.get(i).getTime());
        TextView access$500 = viewHolder.timeTxt;
        access$500.setText("Fecha: " + this.menuList.get(i).getDate());
        TextView access$600 = viewHolder.courseTxt;
        access$600.setText(course.getCourse() + " (" + course.getCodeCourse() + ") ");
        viewHolder.deletebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DataBaseHelper unused = RecyclerViewDeferredTest.this.f87DB = new DataBaseHelper(view.getContext());
                String deleteDiferredTest = RecyclerViewDeferredTest.this.f87DB.deleteDiferredTest(RecyclerViewDeferredTest.this.menuList.get(i).getId());
                RecyclerViewDeferredTest.this.menuList.clear();
                RecyclerViewDeferredTest.this.menuList.addAll(RecyclerViewDeferredTest.this.f87DB.getTests());
                RecyclerViewDeferredTest.this.notifyDataSetChanged();
                Toast.makeText(view.getContext(), deleteDiferredTest, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.listener = onClickListener;
    }

    public int getItemCount() {
        return this.menuList.size();
    }
}
