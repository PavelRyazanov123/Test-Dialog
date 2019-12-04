package com.example.testnewdialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private DialogViewPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        Button button = view.findViewById(R.id.button);


        final ConstraintLayout llBottomSheet = view.findViewById(R.id.bottom_sheet);

       /* CoordinatorLayout coordinatorLayout = view.findViewById(R.id.coordinator);

        BottomSheetBehavior bottomSheetBehavior = new BottomSheetBehavior();
        CoordinatorLayout.LayoutParams params =
                (CoordinatorLayout.LayoutParams) llBottomSheet.getLayoutParams();
        params.setBehavior(bottomSheetBehavior);
        llBottomSheet.requestLayout();*/
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
        // настройка возможности скрыть элемент при свайпе вниз
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setSaveFlags(BottomSheetBehavior.SAVE_ALL);
        bottomSheetBehavior.setPeekHeight((int) convertDpToPx(getContext(), 205));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "adsda", Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(0);
                pagerAdapter.disabel = true;
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
        Log.e(TAG, "onCreateView: " + getContext().getExternalFilesDirs(null)[0].getAbsolutePath());
        Log.e(TAG, "onCreateView: " + Environment.getExternalStorageDirectory());
        setupViewPager(view);
        final ViewSwitcher view_switcher = view.findViewById(R.id.view_switcher);
        view_switcher.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        view.findViewById(R.id.materialButton4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_switcher.showNext();
            }
        });
        view.findViewById(R.id.materialButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_switcher.showPrevious();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    int state = BottomSheetBehavior.STATE_COLLAPSED;
    private static final String TAG = "HomeFragment";

    public float convertDpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public class SnapHelperOneByOne extends LinearSnapHelper {

        @Override
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {

            if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
                return RecyclerView.NO_POSITION;
            }

            final View currentView = findSnapView(layoutManager);

            if (currentView == null) {
                return RecyclerView.NO_POSITION;
            }

            LinearLayoutManager myLayoutManager = (LinearLayoutManager) layoutManager;

            int position1 = myLayoutManager.findFirstVisibleItemPosition();
            int position2 = myLayoutManager.findLastVisibleItemPosition();

            int currentPosition = layoutManager.getPosition(currentView);

            if (velocityX > 400) {
                currentPosition = position2;
            } else if (velocityX < 400) {
                currentPosition = position1;
            }

            if (currentPosition == RecyclerView.NO_POSITION) {
                return RecyclerView.NO_POSITION;
            }

            return currentPosition;
        }
    }


    private void setupViewPager(View view) {
        /*viewPager = view.findViewById(R.id.dialog_layers_fragment_viewpager);
        TabLayout tabLayout = view.findViewById(R.id.dialog_layers_fragment_tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        pagerAdapter = new DialogViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new tab1(), "Свойства");
        pagerAdapter.addFragment(new tab2(), "Измерения");
        viewPager.setAdapter(pagerAdapter);

        ViewSwitcher viewSwitcher;
        viewSwitcher.show();*/

    }

    public class DialogViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private boolean disabel = false;

        public DialogViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }
}
