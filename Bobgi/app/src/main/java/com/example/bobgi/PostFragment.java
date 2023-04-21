package com.example.bobgi;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.bobgi.databinding.ActivityPostBinding;

import java.util.Calendar;


public class PostFragment extends Fragment {
    private ActivityPostBinding binding;
    private Fragment fragment = new FragmentDeliver();
    private Fragment fragment1 = new FragmentRestaurant();

    DatePickerDialog datePickerDialog;

    TimePickerDialog timePickerDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getChildFragmentManager().beginTransaction().replace(R.id.post_container,fragment).commit();

        binding = ActivityPostBinding.inflate(inflater,container,false);

        binding.haksik.setOnClickListener(view -> {
            if(binding.haksik.isChecked()){
                binding.postContainer.setVisibility(View.VISIBLE);
                getChildFragmentManager().beginTransaction().replace(R.id.post_container,fragment).commit();
            }
        });
        binding.local.setOnClickListener(view -> {
            if(binding.local.isChecked()){
                binding.postContainer.setVisibility(View.VISIBLE);
                getChildFragmentManager().beginTransaction().replace(R.id.post_container,fragment1).commit();
            }
        });

        binding.btnRegister.setOnClickListener(view-> {
            ((MainActivity) requireActivity()).moveTo(new ListFragment());
        });
        binding.datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int pYear = calendar.get(Calendar.YEAR);
                int pMonth = calendar.get(Calendar.MONTH);
                int pDay = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month +1;
                        String date = year + "/" + month + "/" + day;

                        binding.dateInformation.setText(date);
                    }
                },pYear,pMonth,pDay);
                datePickerDialog.show();
            }
        });

        binding.timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int pHour = calendar.get(Calendar.HOUR);
                int pMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + "시" + minute +"분";
                        binding.timeInformation.setText(time);
                    }
                },pHour,pMinute,false);
                timePickerDialog.show();
            }
        });


        return binding.getRoot();
    }
}
