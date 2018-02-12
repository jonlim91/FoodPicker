package foodpicker.com.example.foodpicker.presenter;


import android.os.Bundle;

public interface Presenter {

    void onCreate(Bundle savedInstanceState);
    void onPause();
    void onResume();
    void onDestroy();

}
