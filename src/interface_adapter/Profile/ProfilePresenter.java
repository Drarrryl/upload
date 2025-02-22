package interface_adapter.Profile;

import use_case.Profile.ProfileOutputBoundary;
import view.ViewManager;
import view.ViewModel;

public class ProfilePresenter implements ProfileOutputBoundary {
    private ViewManager viewManager;
    private ProfileViewModel profileViewModel;

    public ProfilePresenter(ViewManager viewManager, ProfileViewModel profileViewModel) {
        this.viewManager = viewManager;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void prepareBackView() {
        ViewModel viewModel = profileViewModel.getViewManager().getLastViewModel();
        viewManager.switchToView(viewModel.getName());
        viewManager.setResolution(viewModel.DEFAULT_SIZE);
        profileViewModel.getProfileState().setStatus(false);
    }
}
