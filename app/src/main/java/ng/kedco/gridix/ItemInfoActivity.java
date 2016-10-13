package ng.kedco.gridix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import ng.kedco.gridix.helpers.Holder;
import ng.kedco.gridix.models.Station;
import ng.kedco.gridix.models.TransmissionStation;

public class ItemInfoActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView itemImage;
    TextView itemName;
    LinearLayout itemPropertiesLayout;
    Holder holder = Holder.getInstance();
    Station selectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar();
        itemImage = (ImageView) findViewById(R.id.item_info_image);
        itemName = (TextView) findViewById(R.id.item_info_name);
        itemPropertiesLayout = (LinearLayout) findViewById(R.id.item_info_others);
        Intent itemClickedIntent = getIntent();
        int position = itemClickedIntent.getIntExtra("clicked_item",-1);
        String frm = itemClickedIntent.getStringExtra("from");

        Toast.makeText(this,frm+" "+position,Toast.LENGTH_SHORT).show();
        if(position!=-1 && frm.equals("trans")){
            selectedItem = holder.getTransmissionStations().get(position);
            itemImage.setBackgroundResource(R.drawable.trans_sample);
            itemName.setText(selectedItem.getName());
            setTitle(selectedItem.getName());

            TextView vratio = new TextView(this);
            vratio.setText("voltage Ratio: "+selectedItem.getVoltageRatio().toString());
            itemPropertiesLayout.addView(vratio);

            TextView id = new TextView(this);
            id.setText("id: "+selectedItem.getId());
            itemPropertiesLayout.addView(id);

            TextView splid = new TextView(this);
            splid.setText("source power ilne id: "+selectedItem.getSourcePowerLineId());
            itemPropertiesLayout.addView(splid);

            TextView type = new TextView(this);
            type.setText("type :"+Station.getStationTypefromClass(selectedItem.getClass()).toString());
            itemPropertiesLayout.addView(type);

            TextView isPublic = new TextView(this);
            isPublic.setText("Public: "+selectedItem.isPublic());
            itemPropertiesLayout.addView(isPublic);

            TextView addressStreet = new TextView(this);
            addressStreet.setText("Address street: "+selectedItem.getAddressStreet());
            itemPropertiesLayout.addView(addressStreet);

            TextView addressTown = new TextView(this);
            addressTown.setText("Address Town: "+selectedItem.getAddressTown());
            itemPropertiesLayout.addView(addressTown);

            TextView addressRaw = new TextView(this);
            addressRaw.setText("Address Raw: "+selectedItem.getAddressRaw());
            itemPropertiesLayout.addView(addressRaw);

            TextView postalCode = new TextView(this);
            postalCode.setText("PostalCode: "+selectedItem.getPostalCode());
            itemPropertiesLayout.addView(postalCode);

            TextView addressStateId = new TextView(this);
            addressStateId.setText("Address State Id: "+selectedItem.getAddressState());
            itemPropertiesLayout.addView(addressStateId);

            TextView code = new TextView(this);
            code.setText("Code: "+selectedItem.getCode());
            itemPropertiesLayout.addView(code);

            TextView altCode = new TextView(this);
            altCode.setText("AltCode: "+selectedItem.getAltCode());
            itemPropertiesLayout.addView(altCode);

            TextView dateCreated = new TextView(this);
            dateCreated.setText("Date Created: "+selectedItem.getDateCreated());
            itemPropertiesLayout.addView(dateCreated);

            TextView lastUpdated = new TextView(this);
            lastUpdated.setText("Last Updated: "+selectedItem.getLastUpdated());
            itemPropertiesLayout.addView(lastUpdated);

            TextView dateCommissioned = new TextView(this);
            dateCommissioned.setText("Date Commissioned: "+selectedItem.getDateCommissioned());
            itemPropertiesLayout.addView(dateCommissioned);
        }







    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_info_settings_menu,menu);
        return true;
    }
}
