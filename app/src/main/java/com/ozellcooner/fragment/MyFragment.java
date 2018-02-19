package com.ozellcooner.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ozellcooner.BenefitOfLatexActivity;
import com.ozellcooner.ColorCombinationActivity;
import com.ozellcooner.ColorTheoryActivity;
import com.ozellcooner.ColorTrendsActivity;
import com.ozellcooner.MainActivity;
import com.ozellcooner.R;
import com.ozellcooner.SelectYourColorActivity;
import com.ozellcooner.SelectYourSheenActivity;
import com.ozellcooner.adapter.ProductAdapter;
import com.ozellcooner.fragment.model.Product;
import com.ozellcooner.fragment.model.utils.LayoutUtils;

import java.util.ArrayList;

public class MyFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String MENU_LIST = "MENU";

    ArrayList<Product> productList;
    GridView gridview;

    public static final MyFragment newInstance(String message, ArrayList<Product> list1) {
        MyFragment f = new MyFragment();
        Bundle bdl = new Bundle();
        bdl.putString(EXTRA_MESSAGE, message);
        bdl.putParcelableArrayList(MENU_LIST, list1);
        f.setArguments(bdl);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String message = getArguments().getString(EXTRA_MESSAGE);
        View v = inflater.inflate(R.layout.menu_fragment, container, false);
        productList = getArguments().getParcelableArrayList(MENU_LIST);
        gridview = (GridView) v.findViewById(R.id.gridview_product);
        LayoutUtils.setContext(getActivity());
        ProductAdapter adpt = new ProductAdapter(getActivity(), productList);
        gridview.setAdapter(adpt);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeScreen(position);
            }
        });
        return v;
    }

    public void changeScreen(int positon) {
        switch (productList.get(positon).getId())
        {
            case MainActivity.COLOR_THEORY:
                Intent in1 =  new Intent(getActivity(), ColorTheoryActivity.class);
                startActivity(in1);
            break;
            case MainActivity.BENEFIT_OF_LATEX_PAINTS:
                Intent in2 =  new Intent(getActivity(), BenefitOfLatexActivity.class);
                startActivity(in2);
                break;
            case MainActivity.SELECT_YOUR_RIGHT_SHEEN:
                Intent in3 =  new Intent(getActivity(), SelectYourSheenActivity.class);
                startActivity(in3);
                break;
            case MainActivity.COLOR_TRENDS:
                Intent in4 =  new Intent(getActivity(), ColorTrendsActivity.class);
                startActivity(in4);
                break;
            case MainActivity.SELECT_YOUR_COLOR:
                Intent in5 =  new Intent(getActivity(), SelectYourColorActivity.class);
                startActivity(in5);
                break;
            case MainActivity.COLOR_COMBINATION:
                Intent in6 =  new Intent(getActivity(), ColorCombinationActivity.class);
                startActivity(in6);
                break;


        }
    }
}