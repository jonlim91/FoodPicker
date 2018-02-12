package foodpicker.com.example.foodpicker.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import foodpicker.com.example.foodpicker.auth.AuthUiActivity;
import foodpicker.com.example.foodpicker.model.Board;
import foodpicker.com.example.foodpicker.model.Player;
import foodpicker.com.example.foodpicker.view.FoodPickerActivity;
import foodpicker.com.example.foodpicker.view.FoodPickerView;

public class FoodPickerPresenter extends AppCompatActivity implements Presenter  {

    private static String TAG = FoodPickerActivity.class.getName();
    private FoodPickerView view;
    private Board model;

    public static final String ANONYMOUS = "anonymous";
    private GoogleApiClient mGoogleApiClient;

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    //Signed in user info
    private String mUsername;
    private String mUserEmail;
    private String mPhotoUrl;

    public FoodPickerPresenter(FoodPickerView view) {
        this.view = view;
        this.model = new Board();
    }

    public FoodPickerPresenter(){
        //include constructor so manifest file stops complaining
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.foodpickerpresenter_main);
        // Set default username is anonymous.
        mUsername = ANONYMOUS;
        /*setContentView(R.layout.foodpickerpresenter_main);

        Intent intent = getIntent();

        String message = intent.getStringExtra(FoodPickerActivity.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.textView);
        textView.setText(message);*/

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            // Not signed in, launch the Sign In activity
            Log.d("FoodPresenterActivity", "user is null, launching sign in activity");

            Intent intent = new Intent(this, AuthUiActivity.class);
            startActivity(intent);

            //startSignInActivity();

            /*Bundle extras = getIntent().getExtras();
            if(extras.containsKey("username")){
                TextView user_name = (TextView) findViewById(R.id.username);
                user_name.setText(extras.getString("username"));
            }*/

            //finish();
            return;
        } else {
            Log.d("MainActivity", "User not null, looks good so far");

            //setContentView(R.layout.auth_ui_layout);
            //setContentView(R.layout.)

            //Do stuff with the signed in user's info
            //model = new Board();
        }
    }

    private void startSignInActivity() {
        Log.d("FoodPresenterJava", "Starting sign in activity");
        //Intent intent = new Intent(this, AuthUiActivity.class);

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onButtonSelected(int row, int col) {
        Player playerThatMoved = model.mark(row, col);

        if(playerThatMoved != null) {
            view.setButtonText(row, col, playerThatMoved.toString());

            if (model.getWinner() != null) {
                view.showWinner(playerThatMoved.toString());
            }
        }
    }

    public void onResetSelected() {
        view.clearWinnerDisplay();
        view.clearButtons();
        model.restart();
    }

    //Testing Presenter-View integration
    public void sayHi() {
        Log.d("FoodPickerPresenter", "hi");
    }

    //Check if user is currently signed in
    public boolean isUserSignedIn(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) { return false;}
        else return true;
    }

    //Sign in the user via AuthUiActivity
    public void signInUser(){
        Intent intent = new Intent(this, AuthUiActivity.class);
        startActivity(intent);
    }

    public void signOutUser(){
        Log.d(TAG, "Signing out the user, is currently: " + mFirebaseAuth.getCurrentUser().getDisplayName().toString());
        mFirebaseAuth.signOut();
        Log.d(TAG, "User should now be null, is: " + mFirebaseAuth.getCurrentUser());
        //Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        mUsername = ANONYMOUS;
        Intent intent = new Intent(this, AuthUiActivity.class);
        //startActivity(intent);
        /*intent.setClassName("foodpicker.com.example.foodpicker", "foodpicker.com.example.foodpicker.view.FoodPickerActivity");
        startActivity(intent);*/
        finish();
    }


}
