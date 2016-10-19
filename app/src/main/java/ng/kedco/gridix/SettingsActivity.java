package ng.kedco.gridix;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ng.kedco.gridix.dialogs.UrlDialog;

public class SettingsActivity extends AppCompatActivity {
    Toolbar toolbar;
    RelativeLayout urlContainer;
    TextView urlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.url_sp),MODE_PRIVATE);
        String url = sp.getString(getResources().getString(R.string.stored_url),getResources().getString(R.string.default_api_root_url));
        urlContainer = (RelativeLayout) findViewById(R.id.settings_url_container);
        urlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UrlDialog urlDialog = new UrlDialog();
                Bundle args = new Bundle();
                args.putString("url",urlView.getText().toString());
                urlDialog.setArguments(args);
                urlDialog.show(getSupportFragmentManager(),"");
            }
        });
        urlView = (TextView) findViewById(R.id.settings_url_view);
        urlView.setText(url);
        setupToolbar();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Settings");

    }

    public void setUrl(String url){
        urlView.setText(url);
        SharedPreferences sp = getSharedPreferences(getResources().getString(R.string.url_sp),MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(getResources().getString(R.string.stored_url),url);
        editor.commit();
    }
}
