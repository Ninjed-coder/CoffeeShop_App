package com.example.brewtiful_cafe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class register_screen extends AppCompatActivity {

    private TextInputEditText nameInput,passwordInput,retypePassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_screen);

        nameInput = findViewById(R.id.nameInput);
        passwordInput = findViewById(R.id.passwordInput);
        retypePassword = findViewById(R.id.retypePassword);
        Button register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                String retype = retypePassword.getText().toString().trim();

                if(name.isEmpty()){
                    nameInput.setError("Name is required");
                } else if(!password.equals(retype)){
                    retypePassword.setError("Passwords do not match!");
                }else{
                    registerUser(name,password);
                }


            }

        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
    public void registerUser(final String name, final String password){
        String URL = "http://10.0.2.2/brewtifulCafe/register.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL,
                response ->{
                    Toast.makeText(register_screen.this,"Server" + response, Toast.LENGTH_LONG).show();
                },
                error -> {
                    Toast.makeText(register_screen.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                }) {
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("name",name);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}

