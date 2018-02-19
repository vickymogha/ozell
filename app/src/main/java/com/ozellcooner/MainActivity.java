package com.ozellcooner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ozellcooner.fragment.MenuFragmentAdapter;
import com.ozellcooner.fragment.MyFragment;
import com.ozellcooner.fragment.model.Product;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MenuFragmentAdapter pageAdapter;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        pager = (ViewPager)findViewById(R.id.viewPager);



        final View menuContainer = findViewById(R.id.menu_container);
        pager.post(new Runnable() {
            @Override
            public void run() {
                int menu_container_width = pager.getWidth();
                int menu_container_height = pager.getHeight();

                List<Fragment> fragments = getFragments(menu_container_height);
                pageAdapter = new MenuFragmentAdapter(getSupportFragmentManager(), fragments);
                CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
                ViewPager pager =
                        (ViewPager)findViewById(R.id.viewPager);

                pager.setAdapter(pageAdapter);
                indicator.setViewPager(pager);
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private List<Fragment> getFragments(int menu_height) {
        List<Fragment> fList = new ArrayList<Fragment>();


        ArrayList<Product> list = getProductList(menu_height);
        List<List<Product>> list1 = getChunkList(list,9);
        for(int i=0;i<list1.size();i++)
        {
            Fragment f = MyFragment.newInstance("Fragment",new ArrayList<Product>(list1.get(i)));
            fList.add(f);
        }


        return fList;
    }

    private <T> List<List<T>> getChunkList(List<T> largeList , int chunkSize) {
        List<List<T>> chunkList = new ArrayList<>();
        for (int i = 0 ; i <  largeList.size() ; i += chunkSize) {
            chunkList.add(largeList.subList(i , i + chunkSize >= largeList.size() ? largeList.size() : i + chunkSize));
        }
        return chunkList;
    }

    public ArrayList<Product> getProductList(int menuheight)
    {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.ds_why_choose, "CHOOSE A COLOR",CHOOSE_A_COLOR,menuheight));
        productList.add(new Product(R.drawable.ds_select_your_color, "Select Your Color",SELECT_YOUR_COLOR,menuheight));
        productList.add(new Product(R.drawable.ds_select_right_sheen, "Select the Right Sheen",SELECT_YOUR_RIGHT_SHEEN,menuheight));
        productList.add(new Product(R.drawable.ds_live_paint_visual, "Live Paint Visualizer",LIVE_PAINT_VISUALIZER,menuheight));

        productList.add(new Product(R.drawable.ds_color_picker, "Color Picker",COLOR_PICKER,menuheight));
        productList.add(new Product(R.drawable.ds_color_previ, "Color Preview",COLOR_PREVIEW,menuheight));
        productList.add(new Product(R.drawable.ds_color_combination, "Color Combination",COLOR_COMBINATION,menuheight));
        productList.add(new Product(R.drawable.ds_our_product, "Product Range",PRODUCT_RANGE,menuheight));
        productList.add(new Product(R.drawable.ds_select_right, "Select the Right Paint",SELECT_RIGHT_PAINT,menuheight));

        productList.add(new Product(R.drawable.ds_color_trend, "Color Trends",COLOR_TRENDS,menuheight));
        productList.add(new Product(R.drawable.ds_how_to_choose_color, "How To Choose Color",HOW_TO_CHOOSE_COLOR,menuheight));
        productList.add(new Product(R.drawable.ds_color_theory, "Color Theory",COLOR_THEORY,menuheight));

        productList.add(new Product(R.drawable.ds_painting_styles, "Paint Styles",PAINT_STYLING,menuheight));
        productList.add(new Product(R.drawable.ds_how_to_paint, "How to Paint",HOW_TO_PAINT,menuheight));
        productList.add(new Product(R.drawable.ds_paint_calculator, "Paint Calculator",PAINT_CALCULATOR,menuheight));
        productList.add(new Product(R.drawable.ds_benefit_of_latex, "Benefit of Latex Paints",BENEFIT_OF_LATEX_PAINTS,menuheight));
        productList.add(new Product(R.drawable.ds_vastu_tips, "Vastu Tips",VASTU_TIPS,menuheight));
        productList.add(new Product(R.drawable.ds_product_combination, "Product Camparsion",PRODUCT_COMPARSION,menuheight));
        productList.add(new Product(R.drawable.ds_dealer_locator, "Dealer Locator",DEARLER_LOCATOR,menuheight));
        productList.add(new Product(R.drawable.ds_social_netowrk, "Social Networks",SOCAIL_NETWORKS,menuheight));
        productList.add(new Product(R.drawable.ds_gallery_photo, "Gallery (Photo & Video)",GALLERY_PHOTO,menuheight));
        productList.add(new Product(R.drawable.ds_customer_care, "Customer Care",CUSTOMER_CARE,menuheight));
        productList.add(new Product(R.drawable.ds_feedback_form, "Feeback Form",FEEDBACK_FORM,menuheight));
        productList.add(new Product(R.drawable.ds_paint_problem, "Paint Problem & Solution",PAINT_PROBLEM_SOLUTION,menuheight));

        return  productList;
    }


    public final static int CHOOSE_A_COLOR = 1;
    public final static int SELECT_YOUR_COLOR = 2;
    public final static int SELECT_YOUR_RIGHT_SHEEN = 3;

    public final static int LIVE_PAINT_VISUALIZER = 4;
    public final static int COLOR_PICKER = 5;
    public final static int COLOR_PREVIEW = 6;


    public final static int COLOR_COMBINATION = 7;
    public final static int PRODUCT_RANGE = 8;
    public final static int SELECT_RIGHT_PAINT = 9;

    public final static int COLOR_TRENDS = 10;
    public final static int HOW_TO_CHOOSE_COLOR = 11;
    public final static int COLOR_THEORY = 12;

    public final static int PAINT_STYLING = 13;
    public final static int HOW_TO_PAINT = 14;
    public final static int PAINT_CALCULATOR = 15;

    public final static int BENEFIT_OF_LATEX_PAINTS = 16;
    public final static int VASTU_TIPS = 17;
    public final static int PRODUCT_COMPARSION = 18;

    public final static int DEARLER_LOCATOR = 19;
    public final static int SOCAIL_NETWORKS = 20;
    public final static int GALLERY_PHOTO = 21;

    public final static int CUSTOMER_CARE = 22;
    public final static int FEEDBACK_FORM = 23;
    public final static int PAINT_PROBLEM_SOLUTION= 24;




}
