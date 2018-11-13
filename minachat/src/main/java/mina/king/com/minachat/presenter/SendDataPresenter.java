package mina.king.com.minachat.presenter;


import mina.king.com.minachat.ClientMina;

/**
 * Created by king on 2017/11/17.
 *
 */

public class SendDataPresenter {

    private ClientMina mClientMina;
    public static SendDataPresenter init(){
        SendDataPresenter presenter = new SendDataPresenter();
        presenter.connectionService();
        return presenter;
    }

    public ClientMina getmClientMina() {
        return mClientMina;
    }

    private void connectionService() {
        closeMinaThread();
        if (mClientMina == null) {
            mClientMina = ClientMina.getIntrans();
            mClientMina.initialize();
            mClientMina.start();
        }
    }

    public void closeMinaThread() {
        if (mClientMina!=null){
            mClientMina.minaClose();
        }
    }


    public void sendOBDData(Object order) {
        if (mClientMina != null)
            mClientMina.writeData(order);
    }
}
