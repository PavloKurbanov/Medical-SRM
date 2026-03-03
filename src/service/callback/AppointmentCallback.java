package service.callback;

public interface AppointmentCallback {
    void onSuccess();

    void onError(String error);
}
