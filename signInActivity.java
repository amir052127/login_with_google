package com.example.loginwithsql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class signInActivity extends AppCompatActivity {
   TextView  email,name;
    Button exit;
    GoogleSignInOptions gso;
    GoogleSignInAccount account;
        GoogleApiClient googleApiClient;
    @Override
    protected void onStart() {
        super.onStart();
         gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

            googleApiClient=new GoogleApiClient.Builder(this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                    .build();
        googleApiClient.connect();
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signing_activity);

        email=findViewById(R.id.emailaccont);
        name=findViewById(R.id.emailaccont);
        exit=findViewById(R.id.btn_exit) ;
            account=    getIntent().getParcelableExtra("name");
            email.setText(account.getEmail());
            name.setText(account.getDisplayName());

    exit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {

                }
            });
        }
    });
    }

    public void signout(){
        Intent intent=new Intent(signInActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();



    }
}
