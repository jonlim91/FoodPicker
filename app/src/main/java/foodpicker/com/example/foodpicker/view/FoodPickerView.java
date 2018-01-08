package foodpicker.com.example.foodpicker.view;

public interface FoodPickerView {
    void showWinner(String winningPlayerDisplayLabel);
    void clearWinnerDisplay();
    void clearButtons();
    void setButtonText(int row, int col, String text);
}
