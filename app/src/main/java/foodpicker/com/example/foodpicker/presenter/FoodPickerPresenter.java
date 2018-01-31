package foodpicker.com.example.foodpicker.presenter;

import android.util.Log;

import foodpicker.com.example.foodpicker.model.Board;
import foodpicker.com.example.foodpicker.model.Player;
import foodpicker.com.example.foodpicker.view.FoodPickerView;

public class FoodPickerPresenter implements Presenter {

    private FoodPickerView view;
    private Board model;

    public FoodPickerPresenter(FoodPickerView view) {
        this.view = view;
        this.model = new Board();
    }

    @Override
    public void onCreate() {

        //model = new Board();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

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

    public void sayHi() {
        Log.v("FoodPickerPresenter", "hi");
    }


}
