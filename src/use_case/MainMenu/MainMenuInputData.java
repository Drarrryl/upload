package use_case.MainMenu;

public class MainMenuInputData {
    final private String buttonName;
    final private String username;

    public MainMenuInputData(String buttonName, String username) {
        this.buttonName = buttonName;
        this.username = username;
    }

    String getButtonName() {
        return buttonName;
    }
    String getUsername() {
        return username;
    }
}
