package com.project.sasmito.wonderlabsbusiness;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnNext, btnBef;
    private MyViewPagerAdapter MyViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnBef = (Button) findViewById(R.id.btn_bef);


        layouts = new int[]{
                R.layout.activity_card1,
                R.layout.activity_card2,
                R.layout.activity_card3,
                R.layout.activity_card4
        };

        addBottomDots(0);

        changeStatusBarColor();

        btnBef.setVisibility(View.GONE);

        MyViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(MyViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnBef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(-1);
                if (current < layouts.length) {
                    viewPager.setCurrentItem(current);
                } else {

                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(+1);
                if (current < layouts.length) {
                    viewPager.setCurrentItem(current);
                } else {

                }
            }
        });

    }

    public void location(View view){
        String uriw = "https://goo.gl/maps/jNC9JPm1Eis";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uriw));
        startActivity(intent);
    }

    public void facebook(View view){
        String uriw = "http://facebook.com/wonderlabs.io";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uriw));
        startActivity(intent);
    }

    public void twitter(View view){
        String uriw = "http://twitter.com/wonderlabshq";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uriw));
        startActivity(intent);
    }

    public void linkedin(View view){
        String uriw = "http://linkedin.com/company/wonderlabs.io";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uriw));
        startActivity(intent);
    }

    public void infous(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FirstActivity.this);
        alertDialog.setTitle("About Me");
        alertDialog.setMessage(getString(R.string.created));
        alertDialog.setPositiveButton( "My Profile",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String uriw = "https://www.facebook.com/muhammad.sasmito.adi.wibowo";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(uriw));
                        startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton( "Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    public void addBottomDots(int currentPage){
        dots = new TextView[layouts.length];

        int[] colorActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i=0; i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length>0){
            dots[currentPage].setTextColor(colorActive[currentPage]);
        }

    }

    private int getItem(int i){
        return viewPager.getCurrentItem()+i;
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == 0)
            {
                btnBef.setVisibility(View.GONE);
            } else if (position == layouts.length - 1) {
                btnNext.setVisibility(View.GONE);
                btnBef.setVisibility(View.VISIBLE);
            } else{
                btnNext.setVisibility(View.VISIBLE);
                btnBef.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
    };

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View Pager Adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
