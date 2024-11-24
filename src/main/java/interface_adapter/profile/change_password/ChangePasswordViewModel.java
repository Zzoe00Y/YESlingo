package interface_adapter.profile.change_password;

import interface_adapter.ViewModel;

public class ChangePasswordViewModel extends ViewModel<ChangePasswordState> {

    public ChangePasswordViewModel() {
        super("changePassword");
        setState(new ChangePasswordState());
    }
}
