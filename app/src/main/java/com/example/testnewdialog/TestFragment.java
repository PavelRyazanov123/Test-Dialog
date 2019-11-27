package com.example.testnewdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.tinkoff.decoro.Mask;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class TestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmetn_test, container, false);
        EditText editText = view.findViewById(R.id.editText);

        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots("___,___°");
        MaskImpl terminated = MaskImpl.createTerminated(slots);
        terminated.setShowingEmptySlots(false);
        terminated.setHideHardcodedHead(false);
        FormatWatcher formatWatcher = new MaskFormatWatcher(terminated);
        formatWatcher.installOn(editText); // install on any TextView

        return view;
    }
}
