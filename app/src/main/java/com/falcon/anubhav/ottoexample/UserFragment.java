package com.falcon.anubhav.ottoexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

/**
 * Created by anubhav on 28/09/17.
 */

public class UserFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = (Button) view.findViewById(R.id.submit);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        sendMessage();
    }

    private void sendMessage() {
        EditText edtText = (EditText) getView().findViewById(R.id.editText);
        Events.FragmentActivityMessage message = new Events.FragmentActivityMessage(edtText.getText().toString());
        GlobalBus.getBus().post(message);
    }

    @Subscribe
    public void getMessage(Events.ActivityFragmentMessage message) {
        TextView txtView = (TextView) getView().findViewById(R.id.message);

        String msgText = getResources().getString(R.string.message_received) + " " + message.getMessage();
        txtView.setText(msgText);

        Toast.makeText(getActivity(), msgText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }
}
