package org.phss.hgis;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.phss.hgis.utils.FileUtils;
import org.phss.hgis.utils.HgisDBUtils;

public class Preview extends Activity {
    String _id;
    AsyncTask<String, Integer, Boolean> csvUpdate;
    ArrayList<Map<String, String>> data;
    HgisDBUtils hgisdb;
    Editor initPrefEditor;
    ProgressDialog loading;
    SharedPreferences previewPref;
    SharedPreferences runSetCodePref;
    TextView textViewCurrentSettlementNumber;

    /* renamed from: org.phss.hgis.Preview.1 */
    class C00251 implements OnClickListener {

        /* renamed from: org.phss.hgis.Preview.1.1 */
        class C00231 implements DialogInterface.OnClickListener {
            C00231() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Preview.this, HgisListView.class);
                intent.putExtra("where", "WHERE stname LIKE \"" + Preview.this.previewPref.getString("setName", "") + "\"");
                Preview.this.startActivityForResult(intent, 6);
                dialog.dismiss();
            }
        }

        /* renamed from: org.phss.hgis.Preview.1.2 */
        class C00242 implements DialogInterface.OnClickListener {
            C00242() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Intent intent = Preview.this.getIntent();
                intent.putExtra("isEdit", false);
                Preview.this.setResult(0, intent);
                Preview.this.finish();
                dialog.dismiss();
            }
        }

        C00251() {
        }

        public void onClick(View v) {
            Preview.this.initPrefEditor.putBoolean("setInit", false);
            Preview.this.initPrefEditor.commit();
            Preview.this.hgisdb = new HgisDBUtils(Preview.this);
            if (Preview.this._id == null || Preview.this._id == "") {
                try {
                    Preview.this.hgisdb.createDataBase();
                    try {
                        Preview.this.hgisdb.openDataBase();
                    } catch (SQLException e) {
                    }
                    Preview.this.hgisdb.insertData();
                    if (Preview.this.hgisdb.isNewSettlement()) {
                        Preview.this.hgisdb.createDataBase();
                        try {
                            Preview.this.hgisdb.openDataBase();
                        } catch (SQLException e2) {
                        }
                        Preview.this.hgisdb.insertSettlementData();
                    }
                } catch (IOException e3) {
                }
                FileUtils fUtils = new FileUtils(Preview.this);
                fUtils.SaveCSV(Preview.this.GetNormalizeStr());
                fUtils.SaveSettlementCSV(Preview.this.GetNormalizeSettlementStr());
                AlertDialog ad = new Builder(Preview.this).create();
                ad.setCancelable(false);
                ad.setMessage("Successfully saved record.");
                ad.setButton("New settlement", new C00231());
                ad.setButton2("Continue poi", new C00242());
                ad.show();
                return;
            }
            Preview.this.hgisdb.hgisUpdate(Preview.this.update(), Long.parseLong(Preview.this._id));
            Preview.this.data = Preview.this.hgisdb.getRows("SELECT * FROM hgistbl");
            Preview.this.csvUpdate = new CsvUpateTask(null);
            Preview.this.csvUpdate.execute(new String[]{Preview.this._id});
            Preview.this.loading = new ProgressDialog(Preview.this);
            Preview.this.loading.setProgressStyle(1);
            Preview.this.loading.setMessage("Updating csv data...");
            Preview.this.loading.setCancelable(false);
            Preview.this.loading.setMax((int) Preview.this.hgisdb.getTotalSelectedRow());
            Preview.this.loading.setProgress(0);
            Preview.this.loading.show();
        }
    }

    /* renamed from: org.phss.hgis.Preview.2 */
    class C00262 implements OnClickListener {
        C00262() {
        }

        public void onClick(View v) {
            Intent intent = Preview.this.getIntent();
            intent.putExtra("isEdit", true);
            Preview.this.setResult(0, intent);
            Preview.this.finish();
        }
    }

    private class CsvUpateTask extends AsyncTask<String, Integer, Boolean> {
        FileUtils fUtils;

        /* renamed from: org.phss.hgis.Preview.CsvUpateTask.1 */
        class C00271 implements DialogInterface.OnClickListener {
            C00271() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Preview.this, HgisListView.class);
                intent.putExtra("where", "WHERE stname LIKE \"" + Preview.this.previewPref.getString("setName", "") + "\"");
                Preview.this.startActivityForResult(intent, 6);
                dialog.dismiss();
            }
        }

        /* renamed from: org.phss.hgis.Preview.CsvUpateTask.2 */
        class C00282 implements DialogInterface.OnClickListener {
            C00282() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Intent intent = Preview.this.getIntent();
                intent.putExtra("isEdit", false);
                Preview.this.setResult(0, intent);
                Preview.this.finish();
                dialog.dismiss();
            }
        }

        private CsvUpateTask() {
            this.fUtils = new FileUtils(Preview.this);
        }

        protected Boolean doInBackground(String... arg0) {
            for (int i = 0; i < Preview.this.data.size(); i++) {
                this.fUtils.UpdateCSV(this.fUtils.Map2CsvString((Map) Preview.this.data.get(i)));
                publishProgress(new Integer[]{Integer.valueOf(i + 1)});
            }
            return Boolean.valueOf(true);
        }

        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            Preview.this.loading.setProgress(progress[0].intValue());
        }

        protected void onPostExecute(Boolean result) {
            Preview.this.loading.dismiss();
            this.fUtils.deleteFile();
            this.fUtils.renameFile();
            AlertDialog ad = new Builder(Preview.this).create();
            ad.setCancelable(false);
            ad.setMessage("Successfully update record.");
            ad.setButton("New settlement", new C00271());
            ad.setButton2("Continue view/edit", new C00282());
            ad.show();
        }
    }

    public Preview() {
        this._id = "";
    }

    void printCurrentRunningSettlement() {
        this.textViewCurrentSettlementNumber.setText("Current running settlement: " + String.valueOf((int) (new HgisDBUtils(this).getTotalSettlement() + 1)));
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0029R.layout.preview);
        this.textViewCurrentSettlementNumber = (TextView) findViewById(C0029R.id.textView101);
        printCurrentRunningSettlement();
        this.previewPref = getSharedPreferences("settlement", 0);
        this.initPrefEditor = getSharedPreferences("initRecord", 0).edit();
        this._id = getIntent().getStringExtra("_id");
        ((EditText) findViewById(C0029R.id.edittextState)).setText(this.previewPref.getString("stateName", ""));
        ((EditText) findViewById(C0029R.id.editTextStateCode)).setText(this.previewPref.getString("stateCode", ""));
        ((EditText) findViewById(C0029R.id.editetextLga)).setText(this.previewPref.getString("lgaName", ""));
        ((EditText) findViewById(C0029R.id.edittextLgaCode)).setText(this.previewPref.getString("lgaCode", ""));
        ((EditText) findViewById(C0029R.id.edittextWard)).setText(this.previewPref.getString("wardName", ""));
        ((EditText) findViewById(C0029R.id.edittextWardCode)).setText(this.previewPref.getString("wardCode", ""));
        ((EditText) findViewById(C0029R.id.edittextSettlementName)).setText(this.previewPref.getString("setName", ""));
        ((EditText) findViewById(C0029R.id.edittextSettlementOtherName)).setText(this.previewPref.getString("othName", ""));
        ((EditText) findViewById(C0029R.id.editTextSettlementCode)).setText(this.previewPref.getString("setCode", ""));
        ((EditText) findViewById(C0029R.id.editextSettlementPopulation)).setText(this.previewPref.getString("setPopulation", ""));
        ((EditText) findViewById(C0029R.id.edittextLocation)).setText(this.previewPref.getString("locationName", ""));
        ((EditText) findViewById(C0029R.id.edittextType)).setText(this.previewPref.getString("setType", ""));
        ((EditText) findViewById(C0029R.id.edittextRisk)).setText(this.previewPref.getString("risk", ""));
        ((EditText) findViewById(C0029R.id.edittextHardToReach)).setText(this.previewPref.getString("hardToReach", ""));
        ((EditText) findViewById(C0029R.id.edittextReasonHardToReach)).setText(this.previewPref.getString("resoneHardToReach", ""));
        ((EditText) findViewById(C0029R.id.edittextSettlementHeadName)).setText(this.previewPref.getString("setHeadName", ""));
        ((EditText) findViewById(C0029R.id.edittextSettlementHeadPhone)).setText(this.previewPref.getString("setHeadPhone", ""));
        ((EditText) findViewById(C0029R.id.edittextSettlementLatitude)).setText(this.previewPref.getString("setlatti", ""));
        ((EditText) findViewById(C0029R.id.edittextSettlementLongitude)).setText(this.previewPref.getString("setlongi", ""));
        ((EditText) findViewById(C0029R.id.edittextPhssTeamCode)).setText(this.previewPref.getString("phssTeamCode", ""));
        ((EditText) findViewById(C0029R.id.edittextPoiGenre)).setText(this.previewPref.getString("poiGener", ""));
        ((EditText) findViewById(C0029R.id.edittextPoiGenreCode)).setText(this.previewPref.getString("poiCode", ""));
        ((EditText) findViewById(C0029R.id.edittextPoiType)).setText(this.previewPref.getString("poiType", ""));
        ((EditText) findViewById(C0029R.id.edittextPoiTypeCode)).setText(this.previewPref.getString("poiTypeCode", ""));
        ((EditText) findViewById(C0029R.id.edittextPoiName)).setText(this.previewPref.getString("poiName", ""));
        ((EditText) findViewById(C0029R.id.edittextPoiLatitiude)).setText(this.previewPref.getString("poiLatitute", ""));
        ((EditText) findViewById(C0029R.id.edittextPoiLongititude)).setText(this.previewPref.getString("poiLongitute", ""));
        long tmstmp = System.currentTimeMillis() / 1000;
        Editor previewPrefEditor = this.previewPref.edit();
        previewPrefEditor.putLong("tmstmp", tmstmp);
        previewPrefEditor.commit();
        ((Button) findViewById(C0029R.id.buttonConfirm)).setOnClickListener(new C00251());
        ((Button) findViewById(C0029R.id.buttonEdit)).setOnClickListener(new C00262());
    }

    private String GetNormalizeSettlementStr() {
        return "\"" + this.previewPref.getString("stateName", "") + "\",\"" + this.previewPref.getString("stateCode", "") + "\",\"" + this.previewPref.getString("lgaName", "") + "\",\"" + this.previewPref.getString("lgaCode", "") + "\",\"" + this.previewPref.getString("wardName", "") + "\",\"" + this.previewPref.getString("wardCode", "") + "\",\"" + this.previewPref.getString("setName", "") + "\",\"" + this.previewPref.getString("setCode", "") + "\",\"" + this.previewPref.getString("othName", "") + "\",\"" + this.previewPref.getString("setPopulation", "") + "\",\"" + this.previewPref.getString("locationName", "") + "\",\"" + this.previewPref.getString("locationCode", "") + "\",\"" + this.previewPref.getString("setType", "") + "\",\"" + this.previewPref.getString("setTypeCode", "") + "\",\"" + this.previewPref.getString("risk", "") + "\",\"" + this.previewPref.getString("riskCode", "") + "\",\"" + this.previewPref.getString("hardToReach", "") + "\",\"" + this.previewPref.getString("hardToReachCode", "") + "\",\"" + this.previewPref.getString("resoneHardToReach", "") + "\",\"" + this.previewPref.getString("resoneHardtoReachCode", "") + "\",\"" + this.previewPref.getString("setHeadName", "") + "\",\"" + this.previewPref.getString("setHeadPhone", "") + "\",\"" + this.previewPref.getString("setlatti", "") + "\",\"" + this.previewPref.getString("setlongi", "") + "\",\"" + this.previewPref.getString("phssTeamCode", "") + "\"";
    }

    private String GetNormalizeStr() {
        return "\"" + this.previewPref.getString("stateName", "") + "\",\"" + this.previewPref.getString("stateCode", "") + "\",\"" + this.previewPref.getString("lgaName", "") + "\",\"" + this.previewPref.getString("lgaCode", "") + "\",\"" + this.previewPref.getString("wardName", "") + "\",\"" + this.previewPref.getString("wardCode", "") + "\",\"" + this.previewPref.getString("setName", "") + "\",\"" + this.previewPref.getString("setCode", "") + "\",\"" + this.previewPref.getString("othName", "") + "\",\"" + this.previewPref.getString("setPopulation", "") + "\",\"" + this.previewPref.getString("locationName", "") + "\",\"" + this.previewPref.getString("locationCode", "") + "\",\"" + this.previewPref.getString("setType", "") + "\",\"" + this.previewPref.getString("setTypeCode", "") + "\",\"" + this.previewPref.getString("risk", "") + "\",\"" + this.previewPref.getString("riskCode", "") + "\",\"" + this.previewPref.getString("hardToReach", "") + "\",\"" + this.previewPref.getString("hardToReachCode", "") + "\",\"" + this.previewPref.getString("resoneHardToReach", "") + "\",\"" + this.previewPref.getString("resoneHardtoReachCode", "") + "\",\"" + this.previewPref.getString("setHeadName", "") + "\",\"" + this.previewPref.getString("setHeadPhone", "") + "\",\"" + this.previewPref.getString("setlatti", "") + "\",\"" + this.previewPref.getString("setlongi", "") + "\",\"" + this.previewPref.getString("phssTeamCode", "") + "\",\"" + this.previewPref.getString("poiGener", "") + "\",\"" + this.previewPref.getString("poiCode", "") + "\",\"" + this.previewPref.getString("poiType", "") + "\",\"" + this.previewPref.getString("poiTypeCode", "") + "\",\"" + this.previewPref.getString("poiName", "") + "\",\"" + this.previewPref.getString("poiLatitute", "") + "\",\"" + this.previewPref.getString("poiLongitute", "") + "\",\"" + this.previewPref.getString("date", "") + "\",\"" + String.valueOf(this.previewPref.getLong("tmstmp", 0)) + "\",\"" + "0\"";
    }

    private Map<String, String> update() {
        Map<String, String> datam = new HashMap();
        datam.put("state", this.previewPref.getString("stateName", ""));
        datam.put("scode", this.previewPref.getString("stateCode", ""));
        datam.put("lga", this.previewPref.getString("lgaName", ""));
        datam.put("lcode", this.previewPref.getString("lgaCode", ""));
        datam.put("ward", this.previewPref.getString("wardName", ""));
        datam.put("wcode", this.previewPref.getString("wardCode", ""));
        datam.put("stname", this.previewPref.getString("setName", ""));
        datam.put("stcode", this.previewPref.getString("setCode", ""));
        datam.put("otname", this.previewPref.getString("othName", ""));
        datam.put("tpopulation", this.previewPref.getString("setPopulation", ""));
        datam.put("loctype", this.previewPref.getString("locationName", ""));
        datam.put("loccode", this.previewPref.getString("locationCode", ""));
        datam.put("sttype", this.previewPref.getString("setType", ""));
        datam.put("sttypecode", this.previewPref.getString("setTypeCode", ""));
        datam.put("risk", this.previewPref.getString("risk", ""));
        datam.put("riskcode", this.previewPref.getString("riskCode", ""));
        datam.put("hdreach", this.previewPref.getString("hardToReach", ""));
        datam.put("hdreachcode", this.previewPref.getString("hardToReachCode", ""));
        datam.put("hdrresone", this.previewPref.getString("resoneHardToReach", ""));
        datam.put("hdrresonecode", this.previewPref.getString("resoneHardtoReachCode", ""));
        datam.put("stheadname", this.previewPref.getString("setHeadName", ""));
        datam.put("stheadphone", this.previewPref.getString("setHeadPhone", ""));
        datam.put("slattitiude", this.previewPref.getString("setlatti", ""));
        datam.put("slongituide", this.previewPref.getString("setlongi", ""));
        datam.put("phssteamcode", this.previewPref.getString("phssTeamCode", ""));
        datam.put("genre", this.previewPref.getString("poiGener", ""));
        datam.put("genrecode", this.previewPref.getString("poiCode", ""));
        datam.put("type", this.previewPref.getString("poiType", ""));
        datam.put("typecode", this.previewPref.getString("poiTypeCode", ""));
        datam.put("name", this.previewPref.getString("poiName", ""));
        datam.put("plattitiude", this.previewPref.getString("poiLatitute", ""));
        datam.put("plongituide", this.previewPref.getString("poiLongitute", ""));
        datam.put("date", this.previewPref.getString("date", ""));
        datam.put("timestamp", String.valueOf(this.previewPref.getLong("tmstmp", 0)));
        datam.put("issynced", "0");
        return datam;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 6) {
            setResult(-1);
            finish();
            return;
        }
        finish();
    }
}
