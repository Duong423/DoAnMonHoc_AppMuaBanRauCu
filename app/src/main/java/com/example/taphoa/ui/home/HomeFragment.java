package com.example.taphoa.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taphoa.R;
//import com.example.taphoa.adapters.PopularAdapters;
import com.example.taphoa.adapters.HomeAdapter;
import com.example.taphoa.adapters.PopularAdapters;
import com.example.taphoa.adapters.RecommenedeAdapter;
import com.example.taphoa.databinding.FragmentHomeBinding;
import com.example.taphoa.models.HomeCategory;
import com.example.taphoa.models.PopularModel;
import com.example.taphoa.models.RecommendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView popularRec, homeCatRec, recommendedRec;
    ScrollView scrollView;
    ProgressBar progressBar;
    FirebaseFirestore db;


    //sanpham pho bien
    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;

    //sanphammoi
    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;
    //sanphamdexuat
    List<RecommendedModel> recommendedModelList;
    RecommenedeAdapter recommenedeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        popularRec = root.findViewById(R.id.pop_rec);
        homeCatRec = root.findViewById(R.id.explore_rec);
        recommendedRec = root.findViewById(R.id.recommended_rec);
        scrollView = root.findViewById(R.id.scroll_view);
        progressBar = root.findViewById(R.id.progressbar);
        db = FirebaseFirestore.getInstance();

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        //popularitem------------------------------------------------------------
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapters);
                     //--------------read data----------//
        db.collection("SanPhamPhoBien")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapters.notifyDataSetChanged();

                                scrollView.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);



                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //sanpham moi------------------------------------------------------------
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),categoryList);
        homeCatRec.setAdapter(homeAdapter);
                     //------------------read data-------------------
        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategory homeCategory = document.toObject(HomeCategory.class);//model sanpham moi
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();



                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
         //sanphamdexuat------------------------------------------------------------
        recommendedRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedModelList = new ArrayList<>();
        recommenedeAdapter = new RecommenedeAdapter(getActivity(),recommendedModelList);
        recommendedRec.setAdapter(recommenedeAdapter);
        //--------------read data----------//
        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommenedeAdapter.notifyDataSetChanged();



                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }
}