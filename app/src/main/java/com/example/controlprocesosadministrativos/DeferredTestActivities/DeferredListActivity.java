package com.example.controlprocesosadministrativos.DeferredTestActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import com.example.controlprocesosadministrativos.C0471R;
import com.example.controlprocesosadministrativos.DataBaseHelper;
import com.example.controlprocesosadministrativos.Help;
import com.example.controlprocesosadministrativos.MainActivity;
import com.example.controlprocesosadministrativos.Models.TestDiferred;
import com.example.controlprocesosadministrativos.R;

import java.util.ArrayList;
import java.util.List;

public class DeferredListActivity extends AppCompatActivity {

    /* renamed from: DB */
    private DataBaseHelper f84DB;
    private RecyclerViewDeferredTest adapter;
    private RecyclerView recyclerView;
    private List<TestDiferred> testList;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_deferred_list);
        setTitle("solicitudes de diferido");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f84DB = new DataBaseHelper(this);
        this.testList = new ArrayList();
        this.testList = this.f84DB.getTests();
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recyclerDeferred);
        this.recyclerView = recyclerView2;
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewDeferredTest recyclerViewDeferredTest = new RecyclerViewDeferredTest(this.testList);
        this.adapter = recyclerViewDeferredTest;
        this.recyclerView.setAdapter(recyclerViewDeferredTest);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 2131230995) {
            Help.saveFile("login.txt", "0", this);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
