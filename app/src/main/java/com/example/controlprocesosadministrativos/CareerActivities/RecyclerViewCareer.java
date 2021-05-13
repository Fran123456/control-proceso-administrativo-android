package com.example.controlprocesosadministrativos.CareerActivities;

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
import com.example.controlprocesosadministrativos.R;
import com.example.controlprocesosadministrativos.Utility.Menu;

import java.util.List;

public class RecyclerViewCareer extends RecyclerView.Adapter<RecyclerViewCareer.ViewHolder> implements View.OnClickListener {
    public List<Career> menuList;
    private View.OnClickListener listener;
    private DataBaseHelper DB;
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private Button deletebtn;
        private Button editbtn;
        ImageView image;

        public ViewHolder( View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.careerTxt_text);
            deletebtn = (Button)itemView.findViewById(R.id.deleteCareer_btn) ;
            image=(ImageView)itemView.findViewById(R.id.imgItemCareer_img);
            editbtn=(Button)itemView.findViewById(R.id.editCareer_btn);
        }
    }


    public RecyclerViewCareer(List<Career> menuList){
        this.menuList = menuList;
    }


    @Override
    public RecyclerViewCareer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_career_list, parent,false);
        RecyclerViewCareer.ViewHolder viewHolder = new RecyclerViewCareer.ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewCareer.ViewHolder holder, int position) {
        holder.title.setText("("+menuList.get(position).getCodeCareer().toUpperCase() +  ") "+ menuList.get(position).getCareer());
        holder.image.setImageResource( R.drawable.carrera);

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                DB = new DataBaseHelper(view.getContext());
                String message= DB.deleteCareer(menuList.get(position).getId());
                menuList.clear();
                menuList.addAll(DB.getCareers());
                notifyDataSetChanged();
                Toast.makeText( view.getContext() ,message, Toast.LENGTH_LONG ).show();
               /* try{
                    Class<?> clase=Class.forName("com.example.controlprocesosadministrativos.CareerActivities.CareerDeleteActivity");
                    Intent inte = new Intent(view.getContext(), clase);
                    inte.putExtra("id",  menuList.get(position).getId()  );
                    view.getContext().startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }*/
            }
        });


        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Class<?> clase=Class.forName("com.example.controlprocesosadministrativos.CareerActivities.CareerEditActivity");
                    Intent inte = new Intent(view.getContext(), clase);
                    inte.putExtra("id", String.valueOf( menuList.get(position).getId() ) );
                    view.getContext().startActivity(inte);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }


}
