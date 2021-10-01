package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    // DECLARATION VARIABLE
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // INITIALIZATION VARIABLE
        this.registerButton = (Button) findViewById(R.id.registerButton);

        // EVENT ONCLICK LISTENER
        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.label_msg_registrer_1, Toast.LENGTH_LONG).show();
            }
        });
    }
}
