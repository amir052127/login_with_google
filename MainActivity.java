package com.example.loginwithsql;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
        int signCode;
        Button login;

        GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this,gso);
        login=findViewById(R.id.btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signin();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account =GoogleSignIn.getLastSignedInAccount(this);
if (account!= null){
    sendfile(account);

}else {



}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==signCode){
            Task<GoogleSignInAccount> googleSignInAccountTask=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount googleSignInAccount=googleSignInAccountTask.getResult(ApiException.class);
                sendfile(googleSignInAccount);

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }


    }
    public void sendfile(GoogleSignInAccount account){
        Intent intent = new Intent(MainActivity.this,signInActivity.class) ;
        intent.putExtra("name",account);
    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);

startActivity(intent);
finish();
    }
    public  void Signin(){
        Intent SignInintent = googleSignInClient.getSignInIntent();
        startActivityForResult(SignInintent,signCode);
    }
}