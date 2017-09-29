package com.falcon.anubhav.ottoexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment();

        btnSendMessage = (Button) findViewById(R.id.sendMessageToFragment);
        btnSendMessage.setOnClickListener(this);

    }

    private void addFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new UserFragment()).commit();
    }

    public void sendMessage() {
        EditText edtText = (EditText) findViewById(R.id.activityData);
        Events.ActivityFragmentMessage message = new
                Events.ActivityFragmentMessage(edtText.getText().toString());

        GlobalBus.getBus().post(message);
    }

    @Override
    public void onClick(View v) {
        sendMessage();
    }

    @Subscribe
    public void getMessage(Events.FragmentActivityMessage message) {
        TextView txtView = (TextView) findViewById(R.id.message);
        String msgToDisplay = getResources().getString(R.string.message_received) + " " + message.getMessage();
        txtView.setText(msgToDisplay);

        Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }
}
