package pucminas.br.agenda;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Agenda extends AppCompatActivity {

    private ViewPager mViewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        setupActivity();
        setupToolbar();
        setupTabs();
    }

    private void setupTabs() {
        String[] titles = {"CADASTRAR", "LISTAR", "ATUALIZAR"};

        AgendaAdapter agendaAdapter = new AgendaAdapter(getSupportFragmentManager(), getApplicationContext(), titles);
        mViewPager.setAdapter(agendaAdapter);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setupActivity() {
        mViewPager = findViewById(R.id.mViewPager);
        tabLayout = findViewById(R.id.mTabs);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_finish);
        toolbar.setTitle("Agenda");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
