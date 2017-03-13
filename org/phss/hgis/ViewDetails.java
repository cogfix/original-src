package org.phss.hgis;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Map;
import org.phss.hgis.utils.HgisDBUtils;

public class ViewDetails extends Activity {
    ArrayList<Map<String, String>> data;
    HgisDBUtils hdb;
    SharedPreferences runSetCodePref;
    TextView textViewCurrentSettlementNumber;

    /* renamed from: org.phss.hgis.ViewDetails.1 */
    class C00461 implements OnClickListener {
        C00461() {
        }

        public void onClick(View v) {
            ViewDetails.this.finish();
        }
    }

    void printCurrentRunningSettlement() {
        this.textViewCurrentSettlementNumber.setText("Current running settlement: " + String.valueOf((int) (new HgisDBUtils(this).getTotalSettlement() + 1)));
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Details vew");
        setContentView(C0029R.layout.view_details);
        String _id = getIntent().getStringExtra("_id");
        this.hdb = new HgisDBUtils(this);
        this.data = this.hdb.getRows("SELECT * FROM hgistbl WHERE _id=" + _id);
        this.textViewCurrentSettlementNumber = (TextView) findViewById(C0029R.id.textView101);
        printCurrentRunningSettlement();
        Button back = (Button) findViewById(C0029R.id.buttonBack);
        Map<String, String> datam = (Map) this.data.get(0);
        ((EditText) findViewById(C0029R.id.edittextState)).setText((CharSequence) datam.get("state"));
        ((EditText) findViewById(C0029R.id.editTextStateCode)).setText((CharSequence) datam.get("scode"));
        ((EditText) findViewById(C0029R.id.editetextLga)).setText((CharSequence) datam.get("lga"));
        ((EditText) findViewById(C0029R.id.edittextLgaCode)).setText((CharSequence) datam.get("lcode"));
        ((EditText) findViewById(C0029R.id.edittextWard)).setText((CharSequence) datam.get("ward"));
        ((EditText) findViewById(C0029R.id.edittextWardCode)).setText((CharSequence) datam.get("wcode"));
        ((EditText) findViewById(C0029R.id.edittextSettlementName)).setText((CharSequence) datam.get("stname"));
        ((EditText) findViewById(C0029R.id.edittextSettlementOtherName)).setText((CharSequence) datam.get("otname"));
        ((EditText) findViewById(C0029R.id.editTextSettlementCode)).setText((CharSequence) datam.get("stcode"));
        ((EditText) findViewById(C0029R.id.editextSettlementPopulation)).setText((CharSequence) datam.get("tpopulation"));
        ((EditText) findViewById(C0029R.id.edittextLocation)).setText((CharSequence) datam.get("loctype"));
        ((EditText) findViewById(C0029R.id.edittextType)).setText((CharSequence) datam.get("sttype"));
        ((EditText) findViewById(C0029R.id.edittextRisk)).setText((CharSequence) datam.get("risk"));
        ((EditText) findViewById(C0029R.id.edittextHardToReach)).setText((CharSequence) datam.get("hdreach"));
        ((EditText) findViewById(C0029R.id.edittextReasonHardToReach)).setText((CharSequence) datam.get("hdrresone"));
        ((EditText) findViewById(C0029R.id.edittextSettlementHeadName)).setText((CharSequence) datam.get("stheadname"));
        ((EditText) findViewById(C0029R.id.edittextSettlementHeadPhone)).setText((CharSequence) datam.get("stheadphone"));
        ((EditText) findViewById(C0029R.id.edittextSettlementLatitude)).setText((CharSequence) datam.get("slattitiude"));
        ((EditText) findViewById(C0029R.id.edittextSettlementLongitude)).setText((CharSequence) datam.get("slongituide"));
        ((EditText) findViewById(C0029R.id.edittextPhssTeamCode)).setText((CharSequence) datam.get("phssteamcode"));
        ((EditText) findViewById(C0029R.id.edittextPoiGenre)).setText((CharSequence) datam.get("genre"));
        ((EditText) findViewById(C0029R.id.edittextPoiGenreCode)).setText((CharSequence) datam.get("genrecode"));
        ((EditText) findViewById(C0029R.id.edittextPoiType)).setText((CharSequence) datam.get("type"));
        ((EditText) findViewById(C0029R.id.edittextPoiTypeCode)).setText((CharSequence) datam.get("typecode"));
        ((EditText) findViewById(C0029R.id.edittextPoiName)).setText((CharSequence) datam.get("name"));
        ((EditText) findViewById(C0029R.id.edittextPoiLatitiude)).setText((CharSequence) datam.get("plattitiude"));
        ((EditText) findViewById(C0029R.id.edittextPoiLongititude)).setText((CharSequence) datam.get("plongituide"));
        back.setOnClickListener(new C00461());
    }
}
