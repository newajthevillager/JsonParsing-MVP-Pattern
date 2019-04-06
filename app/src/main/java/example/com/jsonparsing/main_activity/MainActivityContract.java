package example.com.jsonparsing.main_activity;

import java.util.List;

import example.com.jsonparsing.model.User;

public interface MainActivityContract {

    interface IView {
        void showProgressbar();
        void hideProgressbar();
        void showTryAgainButton();
        void hideTryAgainButton();
        void showUsers(List<User> users);
        void showError(String error);

        interface ItemClickListener {
            void navigateToDetailsActivity(User user);
        }
    }

    interface IPresenter {
        void onNetworkCheck();
        void onDestroy();
    }

    interface IIntractor {

        interface OnNetworkListener {
            void onAvailable();
            void onNoNetwork();
        }
        void getNetworkState(OnNetworkListener onNetworkListener);

        interface OnGetUsersListener {
            void onSuccess(List<User> list);
            void onFailure(String message);
        }
        void getUsersList(OnGetUsersListener onGetUsersListener);

    }

}
