package example.com.jsonparsing.main_activity;

import java.util.List;

import example.com.jsonparsing.model.User;
import example.com.jsonparsing.util.MyAppUtil;

public class MainActivityPresenterImpl implements MainActivityContract.IPresenter,
        MainActivityContract.IIntractor.OnNetworkListener,
        MainActivityContract.IIntractor.OnGetUsersListener {

    MainActivityContract.IView iView;
    MainActivityContract.IIntractor iIntractor;

    public MainActivityPresenterImpl(MainActivityContract.IView iView, MyAppUtil myAppUtil) {
        this.iView = iView;
        iIntractor = new MainActivityIntractorImpl(myAppUtil);
    }

    @Override
    public void onNetworkCheck() {
        if (iView != null) {
            iIntractor.getNetworkState(this);
        }
    }

    @Override
    public void onDestroy() {
        if (iView != null) {
            iView = null;
        }
    }

    @Override
    public void onAvailable() {
        if (iView != null) {
            iView.hideTryAgainButton();
            iView.showProgressbar();
            iIntractor.getUsersList(this);
        }
    }

    @Override
    public void onNoNetwork() {
        if (iView != null) {
            iView.showTryAgainButton();
        }
    }


    @Override
    public void onSuccess(List<User> list) {
        if (iView != null) {
            iView.hideProgressbar();
            iView.showUsers(list);
        }
    }

    @Override
    public void onFailure(String message) {
        if (iView != null) {
            iView.hideProgressbar();
            iView.showError(message);
        }
    }
}
