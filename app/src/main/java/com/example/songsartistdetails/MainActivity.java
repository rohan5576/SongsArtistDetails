package com.example.songsartistdetails;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songsartistdetails.adapter.ArtistAdapter;
import com.example.songsartistdetails.model.Artist;
import com.example.songsartistdetails.presenter.MainPresenter;
import com.example.songsartistdetails.view.MainActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

import static com.example.songsartistdetails.service.ApiClient.getAPIClientInstance;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    @BindView(R.id.search_ET)
    EditText searchET;
    @BindView(R.id.artist_search_RV)
    RecyclerView artistSearchRV;
    ArtistAdapter artistAdapter;
    MainPresenter mainPresenter;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * Initialize view.
     */
    private void initView() {
        progressdialog = new ProgressDialog(this);
        mainPresenter = new MainPresenter(this, getAPIClientInstance());
        artistSearchRV.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
    }

    @OnEditorAction(R.id.search_ET)
    public boolean setOnEditorActionListener(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            mainPresenter.performSearch(searchET.getText().toString());
            return true;
        }
        return false;
    }

    @OnClick(R.id.send_FL)
    public void onSendButtonClick() {
        mainPresenter.performSearch(searchET.getText().toString());
    }

    @Override
    public void showList(List<Artist> articles) {
        artistAdapter = new ArtistAdapter(this, articles);
        artistSearchRV.setAdapter(artistAdapter);
    }

    @Override
    public void showProgress() {
        progressdialog.setMessage("Please Wait....");
        progressdialog.show();
    }

    @Override
    public void hideProgress() {
        progressdialog.dismiss();
    }
}