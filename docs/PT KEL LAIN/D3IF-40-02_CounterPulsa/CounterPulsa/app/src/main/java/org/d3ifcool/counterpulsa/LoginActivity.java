package org.d3ifcool.counterpulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    // DECLARATION VARIABLE
    private Button loginButton, registerButton;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // INITIALIZATION VARIABLE
        this.loginButton = (Button) findViewById(R.id.loginButton);
        this.registerButton = (Button) findViewById(R.id.registerButtonJump);
        this.database = new Database(this);

        // EVENT ONCLICK LISTENER
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DUMMY LOGIN
                ArrayList<String> table_user_column = new ArrayList<>();
                table_user_column.add("NO_KTP");
                table_user_column.add("NAMA_LENGKAP");
                table_user_column.add("NO_HP");
                table_user_column.add("EMAIL");
                table_user_column.add("USERNAME");
                table_user_column.add("PASSWORD");
                ArrayList<String> table_user_value = new ArrayList<>();
                table_user_value.add("1");
                table_user_value.add("1");
                table_user_value.add("1");
                table_user_value.add("1");
                table_user_value.add("1");
                table_user_value.add("1");
                boolean login = database.insertData("user_table", table_user_column, table_user_value);
                if(login == true){
                    Toast.makeText(getApplicationContext(), R.string.label_msg_login_1, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), R.string.label_msg_login_1, Toast.LENGTH_SHORT).show();
                }
                // DUMMY LOGIN
            }
        });
        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
