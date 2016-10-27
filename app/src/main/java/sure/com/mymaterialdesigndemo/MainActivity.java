package sure.com.mymaterialdesigndemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TabLayout tabLayout;
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initToolbar();
        initTab();
        initContentScrimColor();
        collapsingToolbarLayout.setTitleEnabled(false);
        vp.setAdapter(new ViewPagAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(vp);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("测试");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initTab() {
        tabLayout.addTab( tabLayout.newTab());
        tabLayout.addTab( tabLayout.newTab());
        tabLayout.addTab( tabLayout.newTab());
        initIcon();
    }

    private void findView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        vp = (ViewPager) findViewById(R.id.vp);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collaps);

    }

    private void initContentScrimColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.iv);
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int color = palette.getDominantColor(Color.parseColor("#545454"));
                collapsingToolbarLayout.setContentScrimColor(color);
            }
        });
    }

    private void initIcon() {
       TabLayout.Tab tab =  tabLayout.getTabAt(0);
        tab.setCustomView(R.layout.tabitem);
        TabLayout.Tab tab1 =  tabLayout.getTabAt(1);
        tab1.setCustomView(R.layout.tabitem);
        TabLayout.Tab tab2 =  tabLayout.getTabAt(2);
        tab2.setCustomView(R.layout.tabitem);
    }
}
