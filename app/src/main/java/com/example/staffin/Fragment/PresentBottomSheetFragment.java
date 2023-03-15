package com.example.staffin.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.staffin.R;
import com.example.staffin.databinding.FragmentPresentBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class PresentBottomSheetFragment extends BottomSheetDialogFragment {
    FragmentPresentBottomSheetBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPresentBottomSheetBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }
}