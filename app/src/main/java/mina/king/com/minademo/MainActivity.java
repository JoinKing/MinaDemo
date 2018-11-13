package mina.king.com.minademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mina.king.com.minachat.ClientMina;
import mina.king.com.minachat.beans.ChatDialogueBean;
import mina.king.com.minachat.contract.ChatScreenContract;
import mina.king.com.minachat.presenter.ChatScreenPresenter;

public class MainActivity extends AppCompatActivity implements ChatScreenContract.View{

    protected ChatScreenPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMina();
    }
    public void sendText(View view){
        presenter.sendTextMsg("0001","TEXT","000","第一次测试聊天服务");
    }
    private void initMina() {
        presenter = ChatScreenPresenter.getInstans(this);
        ClientMina.getIntrans();
    }

    @Override
    public void msgSuccessStatus(Object message) {

    }

    @Override
    public void receivedMsg(ChatDialogueBean bean) {

    }
}
