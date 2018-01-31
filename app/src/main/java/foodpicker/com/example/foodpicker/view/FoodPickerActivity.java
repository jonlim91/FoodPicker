package foodpicker.com.example.foodpicker.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import foodpicker.com.example.foodpicker.MapsMarkerActivity;
import foodpicker.com.example.foodpicker.R;
import foodpicker.com.example.foodpicker.presenter.FoodPickerPresenter;

//import foodpicker.com.example.foodpicker.R;

public class FoodPickerActivity extends AppCompatActivity implements FoodPickerView, NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = FoodPickerActivity.class.getName();

    private Toolbar toolbar;

    private ViewGroup buttonGrid;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;

    FoodPickerPresenter presenter = new FoodPickerPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //actionBar.setTitle("title");
        setContentView(R.layout.activity_main);

        //Set title for Nav drawer's default action bar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(R.string.app_name);

/*        setContentView(R.layout.tictactoe);
        winnerPlayerLabel = (TextView) findViewById(R.id.winnerPlayerLabel);
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        buttonGrid = (ViewGroup) findViewById(R.id.buttonGrid);*/

        presenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tictactoe, menu);
        return true;
    }*/

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                presenter.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        //getMenuInflater().inflate(R.menu.main, menu);
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                /*mFirebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                mUsername = ANONYMOUS;
                startActivity(new Intent(this, SignInActivity.class));
                finish();*/
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            //Map marker activity
            Log.i(TAG, "Set location");

            Intent intent = new Intent(this, MapsMarkerActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            Log.i(TAG, "Second option");

        } else if (id == R.id.nav_slideshow) {
            //Auth activity

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            //If user clicks "Send" in nav drawer, send the invite
            //sendInvitation();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /* TicTacToe implementation */

    public void onCellClicked(View v) {

        Button button = (Button) v;
        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");

        presenter.onButtonSelected(row, col);

    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button btn = (Button) buttonGrid.findViewWithTag("" + row + col);
        if(btn != null) {
            btn.setText(text);
        }
    }

    public void clearButtons() {
        for( int i = 0; i < buttonGrid.getChildCount(); i++ ) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }

    public void showWinner(String winningPlayerDisplayLabel) {
        winnerPlayerLabel.setText(winningPlayerDisplayLabel);
        winnerPlayerViewGroup.setVisibility(View.VISIBLE);
    }

    public void clearWinnerDisplay() {
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");
    }

    public void test(){}
}
