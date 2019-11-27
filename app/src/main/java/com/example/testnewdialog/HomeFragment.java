package com.example.testnewdialog;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        Button button = view.findViewById(R.id.button);

        RecyclerView propertiesRV = view.findViewById(R.id.propertiesRv);
        PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
        propertiesAdapter.size = 4;
        propertiesRV.setAdapter(propertiesAdapter);

        RecyclerView galleryRV = view.findViewById(R.id.galleryRv);
        galleryRV.setNestedScrollingEnabled(false);
        PropertiesAdapter galleryAdapter = new PropertiesAdapter();
        galleryAdapter.size = 10;
        galleryRV.setAdapter(galleryAdapter);


        final ConstraintLayout llBottomSheet = view.findViewById(R.id.bottom_sheet);

       /* CoordinatorLayout coordinatorLayout = view.findViewById(R.id.coordinator);

        BottomSheetBehavior bottomSheetBehavior = new BottomSheetBehavior();
        CoordinatorLayout.LayoutParams params =
                (CoordinatorLayout.LayoutParams) llBottomSheet.getLayoutParams();
        params.setBehavior(bottomSheetBehavior);
        llBottomSheet.requestLayout();*/
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
        bottomSheetBehavior.setHalfExpandedRatio(0.4f);
        // настройка возможности скрыть элемент при свайпе вниз
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setSaveFlags(BottomSheetBehavior.SAVE_ALL);
        bottomSheetBehavior.setFitToContents(false);
        bottomSheetBehavior.setPeekHeight(20);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llBottomSheet.setVisibility(View.INVISIBLE);
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
                llBottomSheet.startAnimation(animation);

            }
        });

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llBottomSheet.getVisibility() == View.INVISIBLE)
                    llBottomSheet.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
                llBottomSheet.startAnimation(animation);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
            }
        });
/*
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        state = BottomSheetBehavior.STATE_COLLAPSED;
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        state = BottomSheetBehavior.STATE_EXPANDED;
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        if (state != BottomSheetBehavior.STATE_HALF_EXPANDED) {
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                        }
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        state = BottomSheetBehavior.STATE_HALF_EXPANDED;
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });*/
        Log.e(TAG, "onCreateView: "+ getContext().getExternalFilesDirs(null)[0].getAbsolutePath() );
        Log.e(TAG, "onCreateView: "+ Environment.getExternalStorageDirectory());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Navigation.findNavController(view).navigate(R.id.coordinateDialog);
    }

    int state = BottomSheetBehavior.STATE_COLLAPSED;
    private static final String TAG = "HomeFragment";
}
