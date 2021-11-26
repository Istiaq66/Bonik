package com.istiaq66.bonik;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;



public class Home_frag extends Fragment {


  RelativeLayout btn;
  ExpandableRelativeLayout exrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

       btn = root.findViewById(R.id.morebtn);
       exrl = root.findViewById(R.id.exrl);


       btn.setOnClickListener(v -> showmore());
       btn.performClick();

        return root;
    }
    public  void showmore(){

      exrl.toggle();

    }
}