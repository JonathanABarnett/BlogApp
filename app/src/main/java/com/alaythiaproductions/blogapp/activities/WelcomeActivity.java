package com.alaythiaproductions.blogapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alaythiaproductions.blogapp.R;
import com.alaythiaproductions.blogapp.adapters.WelcomeItemAdapter;
import com.alaythiaproductions.blogapp.models.WelcomeItem;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private WelcomeItemAdapter welcomeItemAdapter;
    private LinearLayout layoutWelcomeIndicator;
    private MaterialButton welcomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        layoutWelcomeIndicator = findViewById(R.id.layout_welcome_indicator);
        welcomeButton = findViewById(R.id.welcome_next_button);

        setupWelcomeItems();

        final ViewPager2 welcomeViewPager = findViewById(R.id.welcome_viewpager2);
        welcomeViewPager.setAdapter(welcomeItemAdapter);

        setupWelcomeIndicators();
        setCurrentWelcomeIndicator(0);

        welcomeViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentWelcomeIndicator(position);
            }
        });

        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (welcomeViewPager.getCurrentItem() + 1 < welcomeItemAdapter.getItemCount()) {
                    welcomeViewPager.setCurrentItem(welcomeViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });
    }

    private void setupWelcomeItems() {
        List<WelcomeItem> welcomeItems = new ArrayList<>();
        WelcomeItem welcomeItem1 = new WelcomeItem(R.drawable.raptor, "Welcome to the World", "Don't let the dinosaurs get you.");
        WelcomeItem welcomeItem2 = new WelcomeItem(R.drawable.indo, "Don't forget to wash your hands", "Animals can taste the fear on your palms");
        WelcomeItem welcomeItem3 = new WelcomeItem(R.drawable.rex, "Need anything else?", "It's way too late now. Just kidding. Go ahead and leave if you must.");

        welcomeItems.add(welcomeItem1);
        welcomeItems.add(welcomeItem2);
        welcomeItems.add(welcomeItem3);

        welcomeItemAdapter = new WelcomeItemAdapter(welcomeItems);
    }

    private void setupWelcomeIndicators() {
        ImageView[] indicators = new ImageView[welcomeItemAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.welcome_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutWelcomeIndicator.addView(indicators[i]);
        }
    }

    private void setCurrentWelcomeIndicator(int index) {
        int childCount = layoutWelcomeIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutWelcomeIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.welcome_indicator_active
                        ));
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.welcome_indicator_inactive
                        ));
            }
        }
        if (index == welcomeItemAdapter.getItemCount() - 1) {
            welcomeButton.setText("Start");
        } else {
            welcomeButton.setText("Next");
        }
    }
}
