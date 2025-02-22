package interface_adapter.Profile;

import view.ViewManager;
import view.ViewModel;

import java.awt.*;

public class ProfileViewModel extends ViewModel {
    public final String NEW_USERNAME_LABEL_STRING = "Change Username  ";
    public final String NEW_USERNAME_BUTTON_STRING = "Proceed";
    public final String NEW_PASSWORD_LABEL_STRING = "Change Password  ";
    public final String NEW_PASSWORD_BUTTON_STRING = "Proceed";
    public final String BACK_BUTTON_STRING = "Back";
    public final String SUBMIT_BUTTON_STRING = "Submit";
    public Dimension NEW_FRAME_DIMENSION;
    private ProfileState state = new ProfileState();
    public ProfileViewModel(ViewManager viewManager) {
        super("Profile", viewManager);

        NEW_FRAME_DIMENSION = DEFAULT_SIZE;
    }

    public ProfileState getProfileState() {
        return state;
    }
}
