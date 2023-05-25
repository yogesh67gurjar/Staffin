package com.example.staffin;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.staffin.Interface.ApiInterface;
import com.example.staffin.Response.LoginResponse;
import com.example.staffin.Retrofit.RetrofitServices;
import com.example.staffin.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    ActivityLoginBinding binding;
    ApiInterface apiInterface;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    private static final int SIGN_IN_CODE = 9001;


    private FirebaseAuth firebaseAuth;


    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        firebaseAuth = FirebaseAuth.getInstance();


        sharedPreferences = getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getAll().containsKey("login")) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        binding.alreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                finish();
            }
        });

        binding.forgotTv.setOnClickListener(v -> {

                startActivity(new Intent(getApplicationContext(), ForgotActivity.class));
                finish();


        });
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.phoneEt.getText().toString().trim().isEmpty()) {
                    binding.phoneEt.setError("Enter Email");
                    binding.phoneEt.requestFocus();
                }
//                else if (binding.phoneEt.getText().toString().trim().length() < 10) {
//                    binding.phoneEt.setError("Enter Correct Mobile Number");
//                    binding.phoneEt.requestFocus();
//                }
                else if (binding.passwordEt.getText().toString().trim().isEmpty()) {
                    binding.passwordEt.setError("Enter Password");
                    binding.passwordEt.requestFocus();
                } else if (binding.passwordEt.getText().toString().trim().length() < 4) {
                    binding.passwordEt.setError("Enter password min 4 words");
                    binding.passwordEt.requestFocus();
                } else {

                    String number = binding.phoneEt.getText().toString();
                    String password = binding.passwordEt.getText().toString();

//                    Call<LoginResponse> call = apiInterface.postLoginResponse(number, password);
//                    call.enqueue(new Callback<LoginResponse>() {
//                        @Override
//                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                            if (response.isSuccessful()) {
//                                Toast.makeText(LoginActivity.this, "Welcome...", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                finish();
//                            } else {
//                                Toast.makeText(LoginActivity.this, "Response Error", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<LoginResponse> call, Throwable t) {
//                           // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                            Log.d("Message karo", t.getMessage());
//                          //  Toast.makeText(LoginActivity.this, "Enter Correct Details ", Toast.LENGTH_SHORT).show();
//                        }
//                    });


                    if (isNetworkAvailable()) {
                        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                        progressDialog.setMessage("please wait...");
                        progressDialog.show();
                        Call<LoginResponse> call = apiInterface.postLoginResponse(number, password);
                        call.enqueue(new Callback<>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    editor.putString("login", "yes");
                                    editor.apply();
                                    progressDialog.dismiss();
                                    finish();
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Please Enter Correct Details", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("Message karo", t.getMessage());
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Enter Correct Details", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(LoginActivity.this, "Check Your Internet Connection...", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        binding.googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, SIGN_IN_CODE);
                // firebase auth se create user with google
            }
        });


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == SIGN_IN_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            editor.putString("login", "yes");
                            editor.putString("firebase", "yes");
                            editor.apply();
                            finish();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }
}