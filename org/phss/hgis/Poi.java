package org.phss.hgis;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.phss.hgis.utils.HgisDBUtils;
import org.phss.hgis.utils.OptionMenuActivity;
import org.phss.hgis.utils.PhssLocationProvider;
import org.phss.hgis.utils.PhssLocationProvider.OnLocationReceivedListener;
import org.phss.hgis.utils.StateLGAWard;

public class Poi extends OptionMenuActivity {
    String _id;
    ArrayAdapter<CharSequence> adapter;
    int apiLevel;
    private Button btnLocation;
    private Button btnSave;
    Handler delayed;
    EditText eTextpTypeCode;
    InputFilter filter;
    String latitute;
    protected ProgressDialog loading;
    protected LocationManager locationManager;
    String longitute;
    boolean mExternalStorageAvailable;
    boolean mExternalStorageWriteable;
    private PhssLocationProvider pLocation;
    String poiCode;
    String poiGener;
    SharedPreferences poiId;
    String poiName;
    Editor poiPrefEditor;
    String poiType;
    String poiTypeCode;
    SharedPreferences runSetCodePref;
    private Runnable sleeppy;
    Spinner spineer;
    String state;
    TextView textViewCurrentSettlementNumber;
    String wordCode;
    String wordName;

    /* renamed from: org.phss.hgis.Poi.1 */
    class C00051 implements Runnable {
        C00051() {
        }

        public void run() {
            Poi.this.loading.cancel();
            Poi.this.pLocation.stopLocationSearch();
            Poi.this.showtoast();
        }
    }

    /* renamed from: org.phss.hgis.Poi.2 */
    class C00062 implements InputFilter {
        C00062() {
        }

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            int i = start;
            while (i < end) {
                if (!Character.isLetter(source.charAt(i)) && !Character.isWhitespace(source.charAt(i)) && !Character.isDigit(source.charAt(i))) {
                    return "";
                }
                if (Character.isLetter(source.charAt(i)) && !Character.isUpperCase(source.charAt(i)) && !Character.isWhitespace(source.charAt(i))) {
                    return Character.toString(Character.toUpperCase(source.charAt(i)));
                }
                i++;
            }
            return null;
        }
    }

    /* renamed from: org.phss.hgis.Poi.3 */
    class C00083 implements TextWatcher {
        private final /* synthetic */ EditText val$eTextpName;

        /* renamed from: org.phss.hgis.Poi.3.1 */
        class C00071 implements OnClickListener {
            C00071() {
            }

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }

        C00083(EditText editText) {
            this.val$eTextpName = editText;
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count == 21) {
                AlertDialog ad = new Builder(Poi.this).create();
                ad.setCancelable(false);
                ad.setMessage("Only 20 charecters can be entered.");
                ad.setButton("Ok", new C00071());
                ad.show();
                this.val$eTextpName.setText(this.val$eTextpName.getText().toString().substring(0, 20));
                this.val$eTextpName.setSelection(this.val$eTextpName.length());
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void afterTextChanged(Editable s) {
        }
    }

    /* renamed from: org.phss.hgis.Poi.4 */
    class C00094 implements OnClickListener {
        C00094() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Poi.this.loading.cancel();
            Poi.this.pLocation.stopLocationSearch();
        }
    }

    /* renamed from: org.phss.hgis.Poi.5 */
    class C00115 implements View.OnClickListener {
        private final /* synthetic */ EditText val$eTextLati;
        private final /* synthetic */ EditText val$eTextLongi;
        private final /* synthetic */ EditText val$eTextpName;

        /* renamed from: org.phss.hgis.Poi.5.1 */
        class C00101 implements OnClickListener {
            C00101() {
            }

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }

        C00115(EditText editText, EditText editText2, EditText editText3) {
            this.val$eTextpName = editText;
            this.val$eTextLati = editText2;
            this.val$eTextLongi = editText3;
        }

        public void onClick(View v) {
            Poi.this.poiName = this.val$eTextpName.getText().toString();
            if (Poi.this.spineer != null) {
                Poi.this.poiType = Poi.this.spineer.getSelectedItem().toString();
            } else {
                Poi.this.poiType = "NONE";
            }
            Poi.this.poiTypeCode = Poi.this.eTextpTypeCode.getText().toString();
            Poi.this.latitute = this.val$eTextLati.getText().toString();
            Poi.this.longitute = this.val$eTextLongi.getText().toString();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Poi.this.poiPrefEditor.putString("poiGener", Poi.this.poiGener);
            Poi.this.poiPrefEditor.putString("poiCode", Poi.this.poiCode);
            Poi.this.poiPrefEditor.putString("poiName", Poi.this.poiName);
            Poi.this.poiPrefEditor.putString("poiType", Poi.this.poiType);
            Poi.this.poiPrefEditor.putString("poiTypeCode", Poi.this.poiTypeCode);
            Poi.this.poiPrefEditor.putString("poiLatitute", Poi.this.latitute);
            Poi.this.poiPrefEditor.putString("poiLongitute", Poi.this.longitute);
            Poi.this.poiPrefEditor.putString("date", date);
            Poi.this.poiPrefEditor.commit();
            String error = "";
            if (Poi.this.poiName.equals("")) {
                error = "Please enter a Poi name.\n";
            }
            if (Poi.this.latitute.equals("") || Poi.this.longitute.equals("")) {
                error = new StringBuilder(String.valueOf(error)).append("Please collect location lattitiude & logititude.").toString();
            }
            if (error.equals("")) {
                Intent intent = new Intent(Poi.this, Preview.class);
                if (!(Poi.this._id == null || Poi.this._id == "")) {
                    intent.putExtra("_id", Poi.this._id);
                }
                Poi.this.startActivityForResult(intent, 3);
                return;
            }
            AlertDialog ad = new Builder(Poi.this).create();
            ad.setCancelable(false);
            ad.setTitle("Error");
            ad.setMessage(error);
            ad.setButton("Ok", new C00101());
            ad.show();
        }
    }

    /* renamed from: org.phss.hgis.Poi.6 */
    class C00126 implements View.OnClickListener {
        C00126() {
        }

        public void onClick(View v) {
            if (Poi.this.pLocation.isGpsOn()) {
                Poi.this.pLocation.getCurrentLocation(Poi.this.locationManager);
                Poi.this.loading.show();
                Poi.this.delayed.postDelayed(Poi.this.sleeppy, 180000);
            }
        }
    }

    /* renamed from: org.phss.hgis.Poi.7 */
    class C00137 implements OnLocationReceivedListener {
        private final /* synthetic */ EditText val$eTextLati;
        private final /* synthetic */ EditText val$eTextLongi;

        C00137(EditText editText, EditText editText2) {
            this.val$eTextLati = editText;
            this.val$eTextLongi = editText2;
        }

        public void onLocationReceived(Location location) {
            this.val$eTextLati.setText(String.format("%1s", new Object[]{Double.valueOf(location.getLatitude())}));
            this.val$eTextLongi.setText(String.format("%1s", new Object[]{Double.valueOf(location.getLongitude())}));
            Poi.this.loading.cancel();
            Poi.this.pLocation.stopLocationSearch();
            Poi.this.delayed.removeCallbacks(Poi.this.sleeppy);
        }
    }

    public class BuildingsOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            switch (pos) {
                case 0:
                    Poi.this.eTextpTypeCode.setText("1");
                case 1:
                    Poi.this.eTextpTypeCode.setText("2");
                case 2:
                    Poi.this.eTextpTypeCode.setText("3");
                case 3:
                    Poi.this.eTextpTypeCode.setText("4");
                case 4:
                    Poi.this.eTextpTypeCode.setText("5");
                case 5:
                    Poi.this.eTextpTypeCode.setText("6");
                case 6:
                    Poi.this.eTextpTypeCode.setText("8");
                case 7:
                    Poi.this.eTextpTypeCode.setText("9");
                case 8:
                    Poi.this.eTextpTypeCode.setText("10");
                case 9:
                    Poi.this.eTextpTypeCode.setText("11");
                case 10:
                    Poi.this.eTextpTypeCode.setText("12");
                case 11:
                    Poi.this.eTextpTypeCode.setText("13");
                case 12:
                    Poi.this.eTextpTypeCode.setText("14");
                case 13:
                    Poi.this.eTextpTypeCode.setText("15");
                case 14:
                    Poi.this.eTextpTypeCode.setText("7");
                default:
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class HealthOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            switch (pos) {
                case 0:
                    Poi.this.eTextpTypeCode.setText("1");
                case 1:
                    Poi.this.eTextpTypeCode.setText("2");
                case 2:
                    Poi.this.eTextpTypeCode.setText("3");
                case 3:
                    Poi.this.eTextpTypeCode.setText("4");
                case 4:
                    Poi.this.eTextpTypeCode.setText("5");
                case 5:
                    Poi.this.eTextpTypeCode.setText("6");
                case 6:
                    Poi.this.eTextpTypeCode.setText("8");
                case 7:
                    Poi.this.eTextpTypeCode.setText("9");
                case 8:
                    Poi.this.eTextpTypeCode.setText("10");
                case 9:
                    Poi.this.eTextpTypeCode.setText("11");
                case 10:
                    Poi.this.eTextpTypeCode.setText("7");
                default:
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class SchoolOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            switch (pos) {
                case 0:
                    Poi.this.eTextpTypeCode.setText("1");
                case 1:
                    Poi.this.eTextpTypeCode.setText("2");
                case 2:
                    Poi.this.eTextpTypeCode.setText("3");
                case 3:
                    Poi.this.eTextpTypeCode.setText("4");
                case 4:
                    Poi.this.eTextpTypeCode.setText("5");
                case 5:
                    Poi.this.eTextpTypeCode.setText("6");
                case 6:
                    Poi.this.eTextpTypeCode.setText("8");
                case 7:
                    Poi.this.eTextpTypeCode.setText("9");
                case 8:
                    Poi.this.eTextpTypeCode.setText("7");
                default:
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class WaterOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            switch (pos) {
                case 0:
                    Poi.this.eTextpTypeCode.setText("1");
                case 1:
                    Poi.this.eTextpTypeCode.setText("2");
                case 2:
                    Poi.this.eTextpTypeCode.setText("3");
                case 3:
                    Poi.this.eTextpTypeCode.setText("4");
                case 4:
                    Poi.this.eTextpTypeCode.setText("5");
                case 5:
                    Poi.this.eTextpTypeCode.setText("6");
                case 6:
                    Poi.this.eTextpTypeCode.setText("7");
                default:
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public Poi() {
        this.mExternalStorageAvailable = false;
        this.mExternalStorageWriteable = false;
        this.state = "";
        this.apiLevel = 0;
        this.poiCode = "";
        this.poiGener = "";
        this.wordName = "";
        this.wordCode = "";
        this.poiName = "";
        this.poiType = "";
        this.poiTypeCode = "9";
        this.latitute = "";
        this.longitute = "";
        this.delayed = new Handler();
        this._id = "";
        this.sleeppy = new C00051();
    }

    void printCurrentRunningSettlement() {
        this.textViewCurrentSettlementNumber.setText("Current running settlement: " + String.valueOf((int) (new HgisDBUtils(this).getTotalSettlement() + 1)));
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this._id = getIntent().getStringExtra("_id");
        this.filter = new C00062();
        this.poiPrefEditor = getSharedPreferences("settlement", 0).edit();
        this.poiId = getSharedPreferences("poiIdInt", 0);
        int id = this.poiId.getInt("ID", 0);
        switch (id) {
            case 1:
                setTitle(C0029R.string.wHead);
                setContentView(C0029R.layout.whead);
                break;
            case 2:
                setTitle(C0029R.string.vHeadHouse);
                setContentView(C0029R.layout.vheadhouse);
                break;
            case 3:
                setTitle(C0029R.string.mosque);
                setContentView(C0029R.layout.mosque);
                break;
            case 4:
                setTitle(C0029R.string.school);
                setContentView(C0029R.layout.school);
                break;
            case 5:
                setTitle(C0029R.string.charch);
                setContentView(C0029R.layout.charch);
                break;
            case 6:
                setTitle(C0029R.string.healthFacilities);
                setContentView(C0029R.layout.helth);
                break;
            case 7:
                setTitle(C0029R.string.others);
                setContentView(C0029R.layout.others);
                break;
            case 8:
                setTitle(C0029R.string.markatePlace);
                setContentView(C0029R.layout.market);
                break;
            case 9:
                setTitle(C0029R.string.waterPoint);
                setContentView(C0029R.layout.water);
                break;
            case 10:
                setTitle(C0029R.string.motorPark);
                setContentView(C0029R.layout.motorpark);
                break;
            case 11:
                setTitle(C0029R.string.dHeadHouse);
                setContentView(C0029R.layout.dheadhouse);
                break;
            case 12:
                setTitle(C0029R.string.cityPoint);
                setContentView(C0029R.layout.citypoint);
                break;
            case 13:
                setTitle(C0029R.string.roadSetrat);
                setContentView(C0029R.layout.roadstreet);
                break;
            case 14:
                setTitle(C0029R.string.railCrossing);
                setContentView(C0029R.layout.railcrossing);
                break;
            case 15:
                setTitle(C0029R.string.playGround);
                setContentView(C0029R.layout.play_ground);
                break;
            case 16:
                setTitle(C0029R.string.stadium);
                setContentView(C0029R.layout.stadium);
                break;
            case 17:
                setTitle(C0029R.string.majorBuildings);
                setContentView(C0029R.layout.building);
                break;
            case 18:
                setTitle(C0029R.string.street);
                setContentView(C0029R.layout.street);
                break;
            case 19:
                setTitle(C0029R.string.tvRedioStation);
                setContentView(C0029R.layout.tvstation);
                break;
            case 20:
                setTitle(C0029R.string.pharmacy);
                setContentView(C0029R.layout.pharmacy);
                break;
            case 21:
                setTitle(C0029R.string.suparMarket);
                setContentView(C0029R.layout.suparmarket);
                break;
            case 22:
                setTitle(C0029R.string.shops);
                setContentView(C0029R.layout.shops);
                break;
            case 23:
                setTitle(C0029R.string.restaurant);
                setContentView(C0029R.layout.restaurants);
                break;
        }
        this.pLocation = new PhssLocationProvider(this);
        this.textViewCurrentSettlementNumber = (TextView) findViewById(C0029R.id.textView101);
        printCurrentRunningSettlement();
        EditText eTextpName = (EditText) findViewById(C0029R.id.editTextPoiName);
        InputFilter[] inputFilterArr = new InputFilter[1];
        inputFilterArr[0] = this.filter;
        eTextpName.setFilters(inputFilterArr);
        eTextpName.addTextChangedListener(new C00083(eTextpName));
        this.eTextpTypeCode = (EditText) findViewById(C0029R.id.editTextPoiType);
        EditText eTextLati = (EditText) findViewById(C0029R.id.editTextLatitute);
        eTextLati.setEnabled(false);
        EditText eTextLongi = (EditText) findViewById(C0029R.id.editTextLongitute);
        eTextLongi.setEnabled(false);
        this.btnLocation = (Button) findViewById(C0029R.id.buttonLocation);
        this.btnSave = (Button) findViewById(C0029R.id.buttonSave);
        this.locationManager = (LocationManager) getSystemService("location");
        this.pLocation.setLocationMgr(this.locationManager);
        this.state = Environment.getExternalStorageState();
        if ("mounted".equals(this.state)) {
            this.mExternalStorageWriteable = true;
            this.mExternalStorageAvailable = true;
        } else {
            if ("mounted_ro".equals(this.state)) {
                this.mExternalStorageAvailable = true;
                this.mExternalStorageWriteable = false;
            } else {
                this.mExternalStorageWriteable = false;
                this.mExternalStorageAvailable = false;
            }
        }
        this.loading = new ProgressDialog(this);
        this.loading.setProgressStyle(0);
        this.loading.setMessage("Searching location...");
        this.loading.setCancelable(false);
        this.loading.setButton("Stop search", new C00094());
        this.btnSave.setOnClickListener(new C00115(eTextpName, eTextLati, eTextLongi));
        this.btnLocation.setOnClickListener(new C00126());
        this.pLocation.setOnLocationReceivedListener(new C00137(eTextLati, eTextLongi));
        Map<String, String> datam = null;
        if (this._id != null) {
            String str = this._id;
            if (r0 != "") {
                datam = (Map) new HgisDBUtils(this).getRows("SELECT * FROM hgistbl WHERE _id=" + this._id).get(0);
                String pLtti = (String) datam.get("plattitiude");
                String pLongi = (String) datam.get("plongituide");
                eTextpName.setText((String) datam.get("name"));
                eTextLati.setText(pLtti);
                eTextLongi.setText(pLongi);
                this.btnSave.setText("Update");
            }
        }
        switch (id) {
            case 1:
                this.poiCode = "1";
                this.poiGener = "Ward head";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 2:
                this.poiCode = "2";
                this.poiGener = "Village Head House";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 3:
                this.poiCode = "3";
                this.poiGener = "Mosque";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 4:
                this.spineer = (Spinner) findViewById(C0029R.id.spinnerPoi);
                this.adapter = ArrayAdapter.createFromResource(this, C0029R.array.phss_schoo_array, 17367048);
                this.adapter.setDropDownViewResource(17367049);
                this.spineer.setAdapter(this.adapter);
                this.spineer.setOnItemSelectedListener(new SchoolOnItemSelectedListener());
                if (this._id != null) {
                    str = this._id;
                    if (r0 != "") {
                        int position = StateLGAWard.getSchoolTypePosition((String) datam.get("type"));
                        this.spineer.setSelection(position);
                    }
                }
                this.poiCode = "4";
                this.poiGener = "School";
                this.eTextpTypeCode.setText("1");
                this.eTextpTypeCode.setEnabled(false);
            case 5:
                this.poiCode = "5";
                this.poiGener = "Charch";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 6:
                this.spineer = (Spinner) findViewById(C0029R.id.spinnerPoi);
                this.adapter = ArrayAdapter.createFromResource(this, C0029R.array.phss_health_array, 17367048);
                this.adapter.setDropDownViewResource(17367049);
                this.spineer.setAdapter(this.adapter);
                this.spineer.setOnItemSelectedListener(new HealthOnItemSelectedListener());
                if (this._id != null) {
                    str = this._id;
                    if (r0 != "") {
                        this.spineer.setSelection(StateLGAWard.getHealthTypePosition((String) datam.get("type")));
                    }
                }
                this.poiCode = "6";
                this.poiGener = "Health Facilities";
                this.eTextpTypeCode.setText("1");
                this.eTextpTypeCode.setEnabled(false);
                setTitle(C0029R.string.school);
            case 7:
                this.poiCode = "7";
                this.poiGener = "Others";
                this.eTextpTypeCode.setText("7");
                this.eTextpTypeCode.setEnabled(false);
            case 8:
                this.poiCode = "8";
                this.poiGener = "Market place";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 9:
                this.poiCode = "9";
                this.poiGener = "Water point";
                this.eTextpTypeCode.setText("1");
                this.eTextpTypeCode.setEnabled(false);
                this.spineer = (Spinner) findViewById(C0029R.id.spinnerPoi);
                this.adapter = ArrayAdapter.createFromResource(this, C0029R.array.phss_waterpoint_array, 17367048);
                this.adapter.setDropDownViewResource(17367049);
                this.spineer.setAdapter(this.adapter);
                this.spineer.setOnItemSelectedListener(new WaterOnItemSelectedListener());
                if (this._id != null) {
                    str = this._id;
                    if (r0 != "") {
                        this.spineer.setSelection(StateLGAWard.getWaterPointTypePosition((String) datam.get("type")));
                    }
                }
            case 10:
                this.poiCode = "10";
                this.poiGener = "Motor park";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 11:
                this.poiCode = "11";
                this.poiGener = "District Head House";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 12:
                this.poiCode = "12";
                this.poiGener = "City point";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 13:
                this.poiCode = "13";
                this.poiGener = "Road";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 14:
                this.poiCode = "14";
                this.poiGener = "Rail Crossing";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 15:
                this.poiCode = "15";
                this.poiGener = "Play Ground";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 16:
                this.poiCode = "16";
                this.poiGener = "Stadium";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 17:
                this.poiCode = "17";
                this.poiGener = "Major Buildings";
                this.eTextpTypeCode.setText("1");
                this.eTextpTypeCode.setEnabled(false);
                Spinner spineer = (Spinner) findViewById(C0029R.id.spinnerPoi);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, C0029R.array.phss_buildings_array, 17367048);
                adapter.setDropDownViewResource(17367049);
                spineer.setAdapter(adapter);
                spineer.setOnItemSelectedListener(new BuildingsOnItemSelectedListener());
                if (this._id != null) {
                    str = this._id;
                    if (r0 != "") {
                        spineer.setSelection(StateLGAWard.getMejorBuildingTypePosition((String) datam.get("type")));
                    }
                }
            case 18:
                this.poiCode = "18";
                this.poiGener = "Street";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 19:
                this.poiCode = "19";
                this.poiGener = "Tv/Redio station";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 20:
                this.poiCode = "20";
                this.poiGener = "Pharmacy";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 21:
                this.poiCode = "21";
                this.poiGener = "Supermarket";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 22:
                this.poiCode = "22";
                this.poiGener = "Shops";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            case 23:
                this.poiCode = "23";
                this.poiGener = "Restaurant";
                this.eTextpTypeCode.setText("9");
                this.eTextpTypeCode.setEnabled(false);
            default:
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 3) {
            setResult(-1);
            finish();
        } else if (resultCode == 0 && requestCode == 3) {
            boolean isEdit = false;
            try {
                data.getBooleanExtra("isEdit", false);
            } catch (Exception e) {
                isEdit = false;
            }
            if (!isEdit) {
                if (!(this._id == null || this._id == "")) {
                    setResult(0);
                }
                finish();
            }
        }
    }

    public void showtoast() {
        Toast.makeText(this, "Location can not be retrive.\nPlease try again.", 1).show();
    }
}
