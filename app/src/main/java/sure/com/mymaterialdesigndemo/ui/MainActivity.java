package sure.com.mymaterialdesigndemo.ui;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;

import jp.wasabeef.glide.transformations.BlurTransformation;
import sure.com.mymaterialdesigndemo.R;
import sure.com.mymaterialdesigndemo.adapter.ViewPagAdapter;
import sure.com.mymaterialdesigndemo.statusbarUtils.StatusBabUtils;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TabLayout tabLayout;
    ViewPager vp;
    AppBarLayout appBarLayout;
    private TabLayout.Tab tab;
    private TabLayout.Tab tab1;
    private TabLayout.Tab tab2;
    private TextView textView;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBabUtils.transparentStatusBar(this);
        findView();
        initToolbar();
        initTab();
        initContentScrimColor();
        collapsingToolbarLayout.setTitleEnabled(false);
        vp.setAdapter(new ViewPagAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(vp);
        initIcon();
        initAppbar();
        //毛玻璃效果
        Glide.with(MainActivity.this).load(R.mipmap.iviv).override(600,400).bitmapTransform(new BlurTransformation(MainActivity.this,25)).into(iv);



    }
    float tvStartX ;
    float tvStartY ;
    private void initAppbar() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset < 0){
                    final float percent = (float) (1.0*Math.abs(verticalOffset))/appBarLayout.getTotalScrollRange();
//                    ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(),Color.parseColor("#00000000"),Color.parseColor("#3F51B5"));
//                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                        @Override
//                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                           int color = (int) valueAnimator.getAnimatedValue();
//                           toolbar.setBackgroundColor(color);
//                        }
//                    });
//                    animator.setInterpolator(new Interpolator() {
//                        @Override
//                        public float getInterpolation(float v) {
//                            return percent;
//                        }
//                    });
//                    animator.setDuration(0);
                    ArgbEvaluator argbEvaluator =new ArgbEvaluator();
                    //本来考虑使用属性动画实现，感觉实现不了，直接使用API提供的估值器就可以了
                    int color = (int) argbEvaluator.evaluate(percent,Color.parseColor("#00000000"),Color.parseColor("#3F51B5"));
                    collapsingToolbarLayout.setContentScrimColor(color);
                    //根据收缩的百分比，移动textview到右上角的位置

                    Log.e("tvStartX",""+tvStartX+"        "+tvStartY);
                    if (tvStartX == 0){
                        tvStartX= textView.getX();
                        tvStartY = textView.getY();
                        Log.e("tvStartX",""+tvStartX+"        "+tvStartY);
                    }
                    float tvFinalY = 40 + toolbar.getHeight()/2-textView.getHeight()/2;
                    float tvFinalX = toolbar.getWidth()-textView.getWidth()-45;//45是距离右边界的像素值
                    float tvCurrX  = percent*(tvFinalX-tvStartX)+tvStartX;
                    float tvCurrY  = percent*(tvFinalY-tvStartY)+tvStartY;
//                    Log.e("tvStartX",""+tvStartX+"        "+tvStartY);
//                    Log.e("tvCurrX"," tvFinalY:"+tvFinalY+"      tvFinalX"+tvFinalX+"   tvCurrX"+tvCurrX+"  tvCurrY"+tvCurrY);
//                    textView.setX(tvCurrX);
//                    textView.setY(tvCurrY);


                }
            }
        });
    }


    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("测试");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initTab() {
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

    }

    private void findView() {
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        vp = (ViewPager) findViewById(R.id.vp);
        iv = (ImageView) findViewById(R.id.imageview);
        textView = (TextView) findViewById(R.id.text);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collaps);

    }

    private void initContentScrimColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.iv);
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int color = palette.getDominantColor(Color.parseColor("#545454"));
                //这个回调可以在获取图片后设置collapsingToolbarLayout的填充颜色setContentScrimColor
//                collapsingToolbarLayout.setContentScrimColor(color);
            }
        });
    }

    private void initIcon() {
        tab = tabLayout.getTabAt(0);
        tab.setCustomView(R.layout.tabitem);
        tab1 = tabLayout.getTabAt(1);
        tab1.setCustomView(R.layout.tabitem);
        tab2 = tabLayout.getTabAt(2);
        tab2.setCustomView(R.layout.tabitem);
    }
}
