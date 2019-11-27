package com.example.testnewdialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class DialogColorPicker extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_color_picker, container, false);
        final ColorPickerView colorPicker = view.findViewById(R.id.colorPicker);
        colorPicker.setColorListener(new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                Log.e(TAG, "onColorSelected: "+colorPicker.getSelectedPoint().x+" "+colorPicker.getSelectedPoint().y );
            }
        });
        colorPicker.setPureColor(16711680);
        return view;
    }

    private static final String TAG = "DialogColorPicker";
}
