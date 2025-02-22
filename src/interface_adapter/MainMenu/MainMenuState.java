package interface_adapter.MainMenu;

public class MainMenuState {
    String buttonName = "";
    String username = "";

    public MainMenuState() {}

    public MainMenuState(MainMenuState copy)
    {
        this.buttonName = copy.buttonName;
        this.username = copy.username;
    }

    public String getButtonName() { return buttonName; }

    public void setButtonName(String buttonName) { this.buttonName = buttonName; }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
