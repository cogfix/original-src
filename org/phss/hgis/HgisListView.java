package org.phss.hgis;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Map;
import org.phss.hgis.utils.HgisDBUtils;

public class HgisListView extends Activity {
    String _id;
    ArrayList<Map<String, String>> data;
    HgisDBUtils hdb;
    boolean mutex;
    boolean pass;
    String[] poinamse;
    SharedPreferences runSetCodePref;
    TextView textViewCurrentSettlementNumber;

    /* renamed from: org.phss.hgis.HgisListView.1 */
    class C00001 implements OnClickListener {
        C00001() {
        }

        public void onClick(View v) {
            HgisListView.this.setResult(-1);
            HgisListView.this.finish();
        }
    }

    /* renamed from: org.phss.hgis.HgisListView.2 */
    class C00012 implements OnItemClickListener {
        private final /* synthetic */ ListView val$lv;

        C00012(ListView listView) {
            this.val$lv = listView;
        }

        public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long arg3) {
            try {
                HgisListView.this._id = HgisListView.this.getID(arg2);
            } catch (Exception e) {
            }
            this.val$lv.showContextMenuForChild(arg1);
        }
    }

    /* renamed from: org.phss.hgis.HgisListView.3 */
    class C00023 implements DialogInterface.OnClickListener {
        C00023() {
        }

        public void onClick(DialogInterface dialog, int whichButton) {
        }
    }

    /* renamed from: org.phss.hgis.HgisListView.4 */
    class C00034 implements DialogInterface.OnClickListener {
        private final /* synthetic */ Builder val$alert;
        private final /* synthetic */ EditText val$input;

        C00034(EditText editText, Builder builder) {
            this.val$input = editText;
            this.val$alert = builder;
        }

        public void onClick(DialogInterface dialog, int whichButton) {
            if (this.val$input.getText().toString().equals("zakir@1234")) {
                Intent intent1 = new Intent(HgisListView.this, Settlement.class);
                intent1.putExtra("_id", HgisListView.this._id);
                HgisListView.this.startActivity(intent1);
                return;
            }
            this.val$alert.show();
        }
    }

    /* renamed from: org.phss.hgis.HgisListView.5 */
    class C00045 implements DialogInterface.OnClickListener {
        C00045() {
        }

        public void onClick(DialogInterface dialog, int whichButton) {
        }
    }

    class IconicAdapter extends ArrayAdapter<String> {
        public IconicAdapter() {
            super(HgisListView.this, C0029R.layout.row, C0029R.id.label, HgisListView.this.poinamse);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);
            ((ImageView) row.findViewById(C0029R.id.iconr)).setImageResource(HgisListView.this.getIcon(position));
            return row;
        }
    }

    public HgisListView() {
        this._id = "";
        this.pass = false;
        this.mutex = false;
    }

    void printCurrentRunningSettlement() {
        this.textViewCurrentSettlementNumber.setText("Current running settlement: " + String.valueOf((int) (new HgisDBUtils(this).getTotalSettlement() + 1)));
    }

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(C0029R.layout.view);
        String whereClaus = getIntent().getStringExtra("where");
        if (whereClaus == null) {
            whereClaus = "";
        }
        this.hdb = new HgisDBUtils(this);
        this.data = this.hdb.getRows("SELECT * FROM hgistbl " + whereClaus + " ORDER BY timestamp DESC");
        nameArray();
        this.textViewCurrentSettlementNumber = (TextView) findViewById(C0029R.id.textView101);
        printCurrentRunningSettlement();
        ((TextView) findViewById(C0029R.id.total)).setText("Total POI: " + String.valueOf(this.data.size()));
        Button btnBack = (Button) findViewById(C0029R.id.buttonBack);
        if (!whereClaus.equals("")) {
            btnBack.setText("Back to settlement.");
        }
        btnBack.setOnClickListener(new C00001());
        ListView lv = (ListView) findViewById(C0029R.id.list);
        lv.setAdapter(new IconicAdapter());
        registerForContextMenu(lv);
        lv.setOnItemClickListener(new C00012(lv));
    }

    public void onBackPressed() {
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        this.mutex = true;
        getMenuInflater().inflate(C0029R.menu.contextmenu, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case C0029R.id.view:
                Intent intent = new Intent(this, ViewDetails.class);
                intent.putExtra("_id", this._id);
                startActivity(intent);
                break;
            case C0029R.id.edit:
                String passWord = "zakir@1234";
                EditText input = new EditText(this);
                input.setSingleLine();
                input.setTransformationMethod(PasswordTransformationMethod.getInstance());
                Builder alert = new Builder(this);
                alert.setMessage("Wrong password");
                alert.setPositiveButton("Ok", new C00023());
                new Builder(this).setTitle("Athentication form").setMessage("Password:").setView(input).setPositiveButton("Ok", new C00034(input, alert)).setNegativeButton("Cancel", new C00045()).show();
                break;
            case C0029R.id.back:
                setResult(-1);
                finish();
                break;
        }
        return true;
    }

    private void nameArray() {
        this.poinamse = new String[this.data.size()];
        for (int i = 0; i < this.data.size(); i++) {
            this.poinamse[i] = (String) ((Map) this.data.get(i)).get("name");
        }
    }

    private String getID(int position) {
        return (String) ((Map) this.data.get(position)).get("_id");
    }

    private int getPoiIconId(int typeCode) {
        switch (typeCode) {
            case 1:
                return C0029R.drawable.ic_ward_head;
            case 2:
                return C0029R.drawable.ic_village_head_house;
            case 3:
                return C0029R.drawable.ic_mosque;
            case 4:
                return C0029R.drawable.ic_school;
            case 5:
                return C0029R.drawable.ic_church;
            case 6:
                return C0029R.drawable.ic_health_facilty;
            case 7:
                return C0029R.drawable.ic_other;
            case 8:
                return C0029R.drawable.ic_market_place;
            case 9:
                return C0029R.drawable.ic_water_point;
            case 10:
                return C0029R.drawable.ic_motor_park;
            case 11:
                return C0029R.drawable.ic_district_head_house;
            case 12:
                return C0029R.drawable.ic_city_point;
            case 13:
                return C0029R.drawable.ic_divider;
            case 14:
                return C0029R.drawable.ic_rail_crossing;
            case 15:
                return C0029R.drawable.ic_playground;
            case 16:
                return C0029R.drawable.ic_stadium;
            case 17:
                return C0029R.drawable.ic_major_building;
            case 18:
                return C0029R.drawable.ic_street;
            case 19:
                return C0029R.drawable.ic_television;
            case 20:
                return C0029R.drawable.ic_chemist;
            case 21:
                return C0029R.drawable.ic_market;
            case 22:
                return C0029R.drawable.ic_shops;
            case 23:
                return C0029R.drawable.ic_restaurant;
            default:
                return 0;
        }
    }

    private int getIcon(int position) {
        return getPoiIconId(Integer.parseInt((String) ((Map) this.data.get(position)).get("genrecode")));
    }
}
