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
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Map;
import org.phss.hgis.utils.HgisDBUtils;
import org.phss.hgis.utils.OptionMenuActivity;
import org.phss.hgis.utils.PhssLocationProvider;
import org.phss.hgis.utils.PhssLocationProvider.OnLocationReceivedListener;
import org.phss.hgis.utils.StateLGAWard;

public class Settlement extends OptionMenuActivity {
    String _genarCode;
    String _id;
    Button btnContinue;
    Button btnLocation;
    CheckBox chksameAsBefore;
    Handler delayed;
    EditText editTextLgaCode;
    EditText editTextSettlementPopulation;
    EditText editTextStateCode;
    EditText editTextWardCode;
    EditText editeTextLatitude;
    EditText editeTextLongitude;
    EditText editeTextOtherName;
    EditText editeTextPhssTeamCode;
    EditText editeTextResoneForHardToReach;
    EditText editeTextSettlementHeadName;
    EditText editeTextSettlementHeadPhone;
    EditText editeTextSettlementName;
    InputFilter filter;
    Editor hgisDEditor;
    SharedPreferences hgisData;
    SharedPreferences initPref;
    Editor initPrefEditor;
    boolean initiat;
    boolean isEditTextSettlementNameEnabled;
    String lgaName;
    ProgressDialog loading;
    LocationManager locationManager;
    PhssLocationProvider pLocation;
    SharedPreferences runSetCodePref;
    Editor runSetCodePrefEditor;
    int runningSettlement;
    SharedPreferences setCodePref;
    Editor setCodePrefEditor;
    int settlementCode;
    String settlementCodeStr;
    ArrayList<Map<String, String>> settlementData;
    private Runnable sleeppy;
    Spinner spineerLGA;
    Spinner spineerSettlement;
    Spinner spineerState;
    Spinner spineerWard;
    Spinner spinnerHardToReach;
    Spinner spinnerLocation;
    Spinner spinnerReasoneHardToReach;
    Spinner spinnerRisk;
    Spinner spinnerType;
    StateLGAWard statLGAWard;
    String stateName;
    TextView textViewCurrentSettlementNumber;
    InputFilter uppderCaseFilter;
    String wardName;

    /* renamed from: org.phss.hgis.Settlement.1 */
    class C00321 implements Runnable {
        C00321() {
        }

        public void run() {
            Settlement.this.loading.cancel();
            Settlement.this.pLocation.stopLocationSearch();
            Settlement.this.showtoast();
        }
    }

    /* renamed from: org.phss.hgis.Settlement.2 */
    class C00332 implements InputFilter {
        C00332() {
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

    /* renamed from: org.phss.hgis.Settlement.3 */
    class C00343 implements InputFilter {
        C00343() {
        }

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            int i = start;
            while (i < end) {
                if (Character.isLetter(source.charAt(i)) && !Character.isUpperCase(source.charAt(i))) {
                    return Character.toString(Character.toUpperCase(source.charAt(i)));
                }
                i++;
            }
            return null;
        }
    }

    /* renamed from: org.phss.hgis.Settlement.4 */
    class C00354 implements OnClickListener {
        C00354() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Settlement.this.loading.cancel();
            Settlement.this.pLocation.stopLocationSearch();
        }
    }

    /* renamed from: org.phss.hgis.Settlement.5 */
    class C00375 implements TextWatcher {

        /* renamed from: org.phss.hgis.Settlement.5.1 */
        class C00361 implements OnClickListener {
            C00361() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Settlement.this.chksameAsBefore.setChecked(false);
                dialog.dismiss();
            }
        }

        C00375() {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count == 51) {
                AlertDialog ad = new Builder(Settlement.this).create();
                ad.setCancelable(false);
                ad.setMessage("Only 50 charecters can be entered.");
                ad.setButton("Ok", new C00361());
                ad.show();
                Settlement.this.editeTextSettlementName.setText(Settlement.this.editeTextSettlementName.getText().toString().substring(0, 50));
                Settlement.this.editeTextSettlementName.setSelection(Settlement.this.editeTextSettlementName.length());
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void afterTextChanged(Editable s) {
        }
    }

    /* renamed from: org.phss.hgis.Settlement.6 */
    class C00386 implements OnFocusChangeListener {
        C00386() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
            }
        }
    }

    /* renamed from: org.phss.hgis.Settlement.7 */
    class C00407 implements TextWatcher {

        /* renamed from: org.phss.hgis.Settlement.7.1 */
        class C00391 implements OnClickListener {
            C00391() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Settlement.this.chksameAsBefore.setChecked(false);
                dialog.dismiss();
            }
        }

        C00407() {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count == 21) {
                AlertDialog ad = new Builder(Settlement.this).create();
                ad.setCancelable(false);
                ad.setMessage("Only 20 charecters can be entered.");
                ad.setButton("Ok", new C00391());
                ad.show();
                Settlement.this.editeTextOtherName.setText(Settlement.this.editeTextOtherName.getText().toString().substring(0, 20));
                Settlement.this.editeTextOtherName.setSelection(Settlement.this.editeTextOtherName.length());
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void afterTextChanged(Editable s) {
        }
    }

    /* renamed from: org.phss.hgis.Settlement.8 */
    class C00428 implements TextWatcher {

        /* renamed from: org.phss.hgis.Settlement.8.1 */
        class C00411 implements OnClickListener {
            C00411() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Settlement.this.chksameAsBefore.setChecked(false);
                dialog.dismiss();
            }
        }

        C00428() {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count == 21) {
                AlertDialog ad = new Builder(Settlement.this).create();
                ad.setCancelable(false);
                ad.setMessage("Only 20 charecters can be entered.");
                ad.setButton("Ok", new C00411());
                ad.show();
                Settlement.this.editeTextSettlementHeadName.setText(Settlement.this.editeTextSettlementHeadName.getText().toString().substring(0, 20));
                Settlement.this.editeTextSettlementHeadName.setSelection(Settlement.this.editeTextSettlementHeadName.length());
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void afterTextChanged(Editable s) {
        }
    }

    /* renamed from: org.phss.hgis.Settlement.9 */
    class C00449 implements TextWatcher {

        /* renamed from: org.phss.hgis.Settlement.9.1 */
        class C00431 implements OnClickListener {
            C00431() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Settlement.this.chksameAsBefore.setChecked(false);
                dialog.dismiss();
            }
        }

        C00449() {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count == 12) {
                AlertDialog ad = new Builder(Settlement.this).create();
                ad.setCancelable(false);
                ad.setMessage("Only 11 digit can be entered.");
                ad.setButton("Ok", new C00431());
                ad.show();
                Settlement.this.editeTextSettlementHeadPhone.setText(Settlement.this.editeTextSettlementHeadPhone.getText().toString().substring(0, 11));
                Settlement.this.editeTextSettlementHeadPhone.setSelection(Settlement.this.editeTextSettlementHeadPhone.length());
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void afterTextChanged(Editable s) {
        }
    }

    public class HeardToReachOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            if (pos == 1) {
                Settlement.this.spinnerReasoneHardToReach.setEnabled(false);
            } else {
                Settlement.this.spinnerReasoneHardToReach.setEnabled(true);
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class LgaOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            Settlement.this.lgaName = Settlement.this.spineerLGA.getSelectedItem().toString();
            Settlement.this.editTextLgaCode.setText(StateLGAWard.GetLGACode(Settlement.this.stateName, Settlement.this.lgaName));
            String[] wardList = StateLGAWard.GetWards(Settlement.this.lgaName);
            Settlement.this.spineerWard = (Spinner) Settlement.this.findViewById(C0029R.id.spinnerWard);
            ArrayAdapter<CharSequence> wardAdapter = new ArrayAdapter(Settlement.this, 17367048, wardList);
            wardAdapter.setDropDownViewResource(17367049);
            Settlement.this.spineerWard.setAdapter(wardAdapter);
            Settlement.this.spineerWard.setOnItemSelectedListener(new WardOnItemSelectedListener());
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class LocationOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class ReasoneHardToReachOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            switch (pos) {
                case 3:
                    Settlement.this.editeTextResoneForHardToReach.setEnabled(true);
                default:
                    Settlement.this.editeTextResoneForHardToReach.setEnabled(false);
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class RiskOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class SettlementOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            String _stname_ = Settlement.this.spineerSettlement.getSelectedItem().toString();
            Settlement.this.printCurrentRunningSettlement();
            if (_stname_.equals("Other")) {
                Settlement.this.editeTextSettlementName.setEnabled(true);
                Settlement.this.isEditTextSettlementNameEnabled = true;
                Settlement.this.editeTextSettlementName.setFilters(new InputFilter[]{Settlement.this.filter});
                Settlement.this.editeTextSettlementName.setText("");
                Settlement.this.settlementCodeStr = null;
                return;
            }
            Settlement.this.editeTextSettlementName.setFilters(new InputFilter[0]);
            Settlement.this.editeTextSettlementName.setEnabled(false);
            Settlement.this.isEditTextSettlementNameEnabled = false;
            Settlement.this.editeTextSettlementName.setText(_stname_.toUpperCase());
            HgisDBUtils hgisDbUtils = new HgisDBUtils(Settlement.this);
            hgisDbUtils.getSettlementNames(Settlement.this.settlementData);
            Settlement.this.settlementCodeStr = hgisDbUtils.getSettlementCode(_stname_);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class StateOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            Settlement.this.stateName = Settlement.this.spineerState.getSelectedItem().toString();
            Settlement.this.editTextStateCode.setText(StateLGAWard.GetStateCode(Settlement.this.stateName));
            String[] lgaList = StateLGAWard.GetLGAs(Settlement.this.stateName);
            Settlement.this.spineerLGA = (Spinner) Settlement.this.findViewById(C0029R.id.spinnerLga);
            ArrayAdapter<CharSequence> lgaAdapter = new ArrayAdapter(Settlement.this, 17367048, lgaList);
            lgaAdapter.setDropDownViewResource(17367049);
            Settlement.this.spineerLGA.setAdapter(lgaAdapter);
            Settlement.this.spineerLGA.setOnItemSelectedListener(new LgaOnItemSelectedListener());
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class TypeOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class WardOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
            Settlement.this.wardName = Settlement.this.spineerWard.getSelectedItem().toString();
            String wcode = StateLGAWard.GetWardCode(Settlement.this.stateName, Settlement.this.lgaName, Settlement.this.wardName);
            if (Settlement.this.stateName.equals("BORNO") || Settlement.this.stateName.equals("SOKOTO") || Settlement.this.stateName.equals("ZAMFARA")) {
                wcode = wcode.substring(1, wcode.length());
            }
            Settlement.this.editTextWardCode.setText(wcode);
            HgisDBUtils hgisdbUtils = new HgisDBUtils(Settlement.this);
            Settlement.this.settlementData = hgisdbUtils.getSettlementRows(wcode);
            String[] settlmentList = hgisdbUtils.getSettlementNames(Settlement.this.settlementData);
            Settlement.this.spineerSettlement = (Spinner) Settlement.this.findViewById(C0029R.id.spinnerSettlement);
            ArrayAdapter<CharSequence> settlementAdapter = new ArrayAdapter(Settlement.this, 17367048, settlmentList);
            settlementAdapter.setDropDownViewResource(17367049);
            Settlement.this.spineerSettlement.setAdapter(settlementAdapter);
            Settlement.this.spineerSettlement.setOnItemSelectedListener(new SettlementOnItemSelectedListener());
            if (!Settlement.this.initPref.getBoolean("setInit", false)) {
                Settlement.this.initPrefEditor.putBoolean("setInit", true);
                Settlement.this.initPrefEditor.commit();
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public Settlement() {
        this.stateName = "";
        this.lgaName = "";
        this.wardName = "";
        this.delayed = new Handler();
        this._genarCode = "";
        this.runningSettlement = 0;
        this.isEditTextSettlementNameEnabled = true;
        this.sleeppy = new C00321();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0029R.layout.settlement);
        this.filter = new C00332();
        this.uppderCaseFilter = new C00343();
        this.hgisData = getSharedPreferences("settlement", 0);
        this.hgisDEditor = this.hgisData.edit();
        this.initPref = getSharedPreferences("initRecord", 0);
        this.initPrefEditor = this.initPref.edit();
        this.setCodePref = getSharedPreferences("settlementCode", 0);
        this.setCodePrefEditor = this.setCodePref.edit();
        this.runSetCodePref = getSharedPreferences("runsett", 0);
        this.runSetCodePrefEditor = this.setCodePref.edit();
        this.pLocation = new PhssLocationProvider(this);
        this.btnLocation = (Button) findViewById(C0029R.id.buttonLocation);
        this.btnContinue = (Button) findViewById(C0029R.id.buttonContinue);
        this.locationManager = (LocationManager) getSystemService("location");
        this.pLocation.setLocationMgr(this.locationManager);
        this.spineerState = (Spinner) findViewById(C0029R.id.spinnerState);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, C0029R.array.phss_state_array, 17367048);
        adapter.setDropDownViewResource(17367049);
        this.spineerState.setAdapter(adapter);
        this.spineerState.setOnItemSelectedListener(new StateOnItemSelectedListener());
        this.chksameAsBefore = (CheckBox) findViewById(C0029R.id.chksameAsBefore);
        this.editeTextPhssTeamCode = (EditText) findViewById(C0029R.id.editTextPhssTeamCode);
        this.editTextStateCode = (EditText) findViewById(C0029R.id.editTextStateCode);
        this.editTextLgaCode = (EditText) findViewById(C0029R.id.editTextLgaCode);
        this.editTextWardCode = (EditText) findViewById(C0029R.id.editTextWardCode);
        this.editeTextSettlementName = (EditText) findViewById(C0029R.id.editTextSettlementName);
        this.editeTextOtherName = (EditText) findViewById(C0029R.id.editTextSettlementOtherName);
        this.editeTextOtherName.setFilters(new InputFilter[]{this.filter});
        this.editTextSettlementPopulation = (EditText) findViewById(C0029R.id.editTextSettlementPopulation);
        this.editeTextResoneForHardToReach = (EditText) findViewById(C0029R.id.editTextSpacifyReasonHardToReach);
        this.editeTextResoneForHardToReach.setFilters(new InputFilter[]{this.filter});
        this.editeTextSettlementHeadName = (EditText) findViewById(C0029R.id.editTextSettlementHeadName);
        this.editeTextSettlementHeadName.setFilters(new InputFilter[]{this.filter});
        this.editeTextSettlementHeadPhone = (EditText) findViewById(C0029R.id.editTextSettlementHeadPhone);
        this.editeTextLatitude = (EditText) findViewById(C0029R.id.editTextSettlementLatitude);
        this.editeTextLongitude = (EditText) findViewById(C0029R.id.editTextSettlementLongitude);
        this.spinnerLocation = (Spinner) findViewById(C0029R.id.spinnerLocation);
        ArrayAdapter<CharSequence> locAdapter = ArrayAdapter.createFromResource(this, C0029R.array.phss_state_location_array, 17367048);
        locAdapter.setDropDownViewResource(17367049);
        this.spinnerLocation.setAdapter(locAdapter);
        this.spinnerLocation.setOnItemSelectedListener(new LocationOnItemSelectedListener());
        this.spinnerType = (Spinner) findViewById(C0029R.id.spinnerType);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, C0029R.array.phss_state_type_array, 17367048);
        typeAdapter.setDropDownViewResource(17367049);
        this.spinnerType.setAdapter(typeAdapter);
        this.spinnerType.setOnItemSelectedListener(new TypeOnItemSelectedListener());
        this.spinnerRisk = (Spinner) findViewById(C0029R.id.spinnerRisk);
        ArrayAdapter<CharSequence> riskAdapter = ArrayAdapter.createFromResource(this, C0029R.array.phss_risk_array, 17367048);
        riskAdapter.setDropDownViewResource(17367049);
        this.spinnerRisk.setAdapter(riskAdapter);
        this.spinnerRisk.setOnItemSelectedListener(new RiskOnItemSelectedListener());
        this.spinnerHardToReach = (Spinner) findViewById(C0029R.id.spinnerHardToReach);
        ArrayAdapter<CharSequence> heardToReachAdapter = ArrayAdapter.createFromResource(this, C0029R.array.phss_state_hardtoreach_array, 17367048);
        heardToReachAdapter.setDropDownViewResource(17367049);
        this.spinnerHardToReach.setAdapter(heardToReachAdapter);
        this.spinnerHardToReach.setOnItemSelectedListener(new HeardToReachOnItemSelectedListener());
        this.spinnerReasoneHardToReach = (Spinner) findViewById(C0029R.id.spinnerReasoneHardToReach);
        ArrayAdapter<CharSequence> reasoneHardToReachAdapter = ArrayAdapter.createFromResource(this, C0029R.array.phss_state_reason_hardtoreach_array, 17367048);
        reasoneHardToReachAdapter.setDropDownViewResource(C0029R.layout.multiline_spinner_dropdown_item);
        this.spinnerReasoneHardToReach.setAdapter(reasoneHardToReachAdapter);
        this.spinnerReasoneHardToReach.setOnItemSelectedListener(new ReasoneHardToReachOnItemSelectedListener());
        this._id = getIntent().getStringExtra("_id");
        if (!(this._id == null || this._id == "")) {
            Map<String, String> datam = (Map) new HgisDBUtils(this).getRows("SELECT * FROM hgistbl WHERE _id=" + this._id).get(0);
            this._genarCode = (String) datam.get("genrecode");
            setScreen(datam);
        }
        this.loading = new ProgressDialog(this);
        this.loading.setProgressStyle(0);
        this.loading.setMessage("Searching location...");
        this.loading.setCancelable(false);
        this.loading.setButton("Stop search", new C00354());
        this.editeTextSettlementName.addTextChangedListener(new C00375());
        this.editeTextSettlementName.setOnFocusChangeListener(new C00386());
        this.editeTextOtherName.addTextChangedListener(new C00407());
        this.editeTextSettlementHeadName.addTextChangedListener(new C00428());
        this.editeTextSettlementHeadPhone.addTextChangedListener(new C00449());
        this.textViewCurrentSettlementNumber = (TextView) findViewById(C0029R.id.textView101);
        this.btnLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (Settlement.this.pLocation.isGpsOn()) {
                        Settlement.this.pLocation.getCurrentLocation(Settlement.this.locationManager);
                        Settlement.this.loading.show();
                        Settlement.this.delayed.postDelayed(Settlement.this.sleeppy, 180000);
                    }
                } catch (Exception e) {
                }
            }
        });
        this.chksameAsBefore.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            /* renamed from: org.phss.hgis.Settlement.11.1 */
            class C00301 implements OnClickListener {
                C00301() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Settlement.this.chksameAsBefore.setChecked(false);
                    dialog.dismiss();
                }
            }

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    return;
                }
                if (Settlement.this.hgisData.getString("setName", "").equals("")) {
                    AlertDialog ad = new Builder(Settlement.this).create();
                    ad.setCancelable(false);
                    ad.setMessage("No previous data found.\nPlease add.");
                    ad.setButton("Ok", new C00301());
                    ad.show();
                    return;
                }
                Settlement.this.startActivityForResult(new Intent(Settlement.this, PoiList.class), 1);
            }
        });
        this.btnContinue.setOnClickListener(new View.OnClickListener() {

            /* renamed from: org.phss.hgis.Settlement.12.1 */
            class C00311 implements OnClickListener {
                C00311() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Settlement.this.chksameAsBefore.setChecked(false);
                    dialog.dismiss();
                }
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r40) {
                /*
                r39 = this;
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editeTextPhssTeamCode;
                r36 = r0;
                r36 = r36.getText();
                r13 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spineerState;
                r36 = r0;
                r36 = r36.getSelectedItem();
                r32 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editTextStateCode;
                r36 = r0;
                r36 = r36.getText();
                r31 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spineerLGA;
                r36 = r0;
                r36 = r36.getSelectedItem();
                r9 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editTextLgaCode;
                r36 = r0;
                r36 = r36.getText();
                r8 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spineerWard;
                r36 = r0;
                r36 = r36.getSelectedItem();
                r35 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editTextWardCode;
                r36 = r0;
                r36 = r36.getText();
                r34 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editeTextSettlementName;
                r36 = r0;
                r36 = r36.getText();
                r23 = r36.toString();
                r20 = "";
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.setCodePref;
                r36 = r0;
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r37 = r0;
                r0 = r37;
                r0 = r0.wardName;
                r37 = r0;
                r38 = 0;
                r33 = r36.getInt(r37, r38);
                if (r23 == 0) goto L_0x06a1;
            L_0x00c2:
                r36 = "";
                r0 = r23;
                r1 = r36;
                if (r0 == r1) goto L_0x06a1;
            L_0x00ca:
                if (r33 == 0) goto L_0x0691;
            L_0x00cc:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r37 = "stcode";
                r38 = 0;
                r29 = r36.getSharedPreferences(r37, r38);
                r30 = r29.edit();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r37 = 0;
                r0 = r29;
                r1 = r23;
                r2 = r37;
                r37 = r0.getInt(r1, r2);
                r0 = r37;
                r1 = r36;
                r1.settlementCode = r0;
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.settlementCode;
                r36 = r0;
                if (r36 != 0) goto L_0x067b;
            L_0x0104:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r37 = r33 + 1;
                r0 = r37;
                r1 = r36;
                r1.settlementCode = r0;
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.settlementCode;
                r36 = r0;
                r0 = r30;
                r1 = r23;
                r2 = r36;
                r0.putInt(r1, r2);
                r30.commit();
            L_0x012a:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.setCodePrefEditor;
                r36 = r0;
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r37 = r0;
                r0 = r37;
                r0 = r0.wardName;
                r37 = r0;
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r38 = r0;
                r0 = r38;
                r0 = r0.settlementCode;
                r38 = r0;
                r36.putInt(r37, r38);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.setCodePrefEditor;
                r36 = r0;
                r36.commit();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r37 = r0;
                r0 = r37;
                r0 = r0.settlementCode;
                r37 = r0;
                r0 = r36;
                r1 = r34;
                r2 = r37;
                r20 = r0.getSetCode(r1, r2);
            L_0x017c:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editeTextOtherName;
                r36 = r0;
                r36 = r36.getText();
                r12 = r36.toString();
                r36 = "";
                r0 = r36;
                r36 = r12.equals(r0);
                if (r36 == 0) goto L_0x019c;
            L_0x019a:
                r12 = "NONE";
            L_0x019c:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editTextSettlementPopulation;
                r36 = r0;
                r36 = r36.getText();
                r24 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spinnerLocation;
                r36 = r0;
                r36 = r36.getSelectedItem();
                r11 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spinnerLocation;
                r36 = r0;
                r36 = r36.getSelectedItemPosition();
                r36 = r36 + 1;
                r10 = java.lang.String.valueOf(r36);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spinnerType;
                r36 = r0;
                r36 = r36.getSelectedItem();
                r25 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spinnerType;
                r36 = r0;
                r36 = r36.getSelectedItemPosition();
                r36 = r36 + 1;
                r26 = java.lang.String.valueOf(r36);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spinnerRisk;
                r36 = r0;
                r36 = r36.getSelectedItem();
                r18 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spinnerRisk;
                r36 = r0;
                r36 = r36.getSelectedItemPosition();
                r36 = r36 + 1;
                r19 = java.lang.String.valueOf(r36);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spinnerHardToReach;
                r36 = r0;
                r36 = r36.getSelectedItem();
                r5 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spinnerHardToReach;
                r36 = r0;
                r36 = r36.getSelectedItemPosition();
                r36 = r36 + 1;
                r6 = java.lang.String.valueOf(r36);
                r16 = "NONE";
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spinnerReasoneHardToReach;
                r36 = r0;
                r36 = r36.isEnabled();
                if (r36 == 0) goto L_0x0280;
            L_0x026c:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spinnerReasoneHardToReach;
                r36 = r0;
                r36 = r36.getSelectedItem();
                r16 = r36.toString();
            L_0x0280:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.spinnerReasoneHardToReach;
                r36 = r0;
                r36 = r36.getSelectedItemPosition();
                r36 = r36 + 1;
                r17 = java.lang.String.valueOf(r36);
                r36 = "OTHERS";
                r0 = r16;
                r1 = r36;
                r36 = r0.equals(r1);
                if (r36 == 0) goto L_0x02b8;
            L_0x02a2:
                r17 = "7";
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editeTextResoneForHardToReach;
                r36 = r0;
                r36 = r36.getText();
                r16 = r36.toString();
            L_0x02b8:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editeTextSettlementHeadName;
                r36 = r0;
                r36 = r36.getText();
                r21 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editeTextSettlementHeadPhone;
                r36 = r0;
                r36 = r36.getText();
                r22 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editeTextLatitude;
                r36 = r0;
                r36 = r36.getText();
                r27 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.editeTextLongitude;
                r36 = r0;
                r36 = r36.getText();
                r28 = r36.toString();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "phssTeamCode";
                r0 = r36;
                r1 = r37;
                r0.putString(r1, r13);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "stateName";
                r0 = r36;
                r1 = r37;
                r2 = r32;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "stateCode";
                r0 = r36;
                r1 = r37;
                r2 = r31;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "lgaName";
                r0 = r36;
                r1 = r37;
                r0.putString(r1, r9);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "lgaCode";
                r0 = r36;
                r1 = r37;
                r0.putString(r1, r8);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "wardName";
                r0 = r36;
                r1 = r37;
                r2 = r35;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "wardCode";
                r0 = r36;
                r1 = r37;
                r2 = r34;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "setName";
                r0 = r36;
                r1 = r37;
                r2 = r23;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "setCode";
                r0 = r36;
                r1 = r37;
                r2 = r20;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "othName";
                r0 = r36;
                r1 = r37;
                r0.putString(r1, r12);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "setPopulation";
                r0 = r36;
                r1 = r37;
                r2 = r24;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "locationName";
                r0 = r36;
                r1 = r37;
                r0.putString(r1, r11);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "locationCode";
                r0 = r36;
                r1 = r37;
                r0.putString(r1, r10);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "setType";
                r0 = r36;
                r1 = r37;
                r2 = r25;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "setTypeCode";
                r0 = r36;
                r1 = r37;
                r2 = r26;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "risk";
                r0 = r36;
                r1 = r37;
                r2 = r18;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "riskCode";
                r0 = r36;
                r1 = r37;
                r2 = r19;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "hardToReach";
                r0 = r36;
                r1 = r37;
                r0.putString(r1, r5);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "hardToReachCode";
                r0 = r36;
                r1 = r37;
                r0.putString(r1, r6);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "resoneHardToReach";
                r0 = r36;
                r1 = r37;
                r2 = r16;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "resoneHardtoReachCode";
                r0 = r36;
                r1 = r37;
                r2 = r17;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "setHeadName";
                r0 = r36;
                r1 = r37;
                r2 = r21;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "setHeadPhone";
                r0 = r36;
                r1 = r37;
                r2 = r22;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "setlatti";
                r0 = r36;
                r1 = r37;
                r2 = r27;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r37 = "setlongi";
                r0 = r36;
                r1 = r37;
                r2 = r28;
                r0.putString(r1, r2);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.hgisDEditor;
                r36 = r0;
                r36.commit();
                r4 = "";
                r36 = "";
                r0 = r23;
                r1 = r36;
                r36 = r0.equals(r1);
                if (r36 == 0) goto L_0x0556;
            L_0x0554:
                r4 = "Please enter a Settlement name.\n";
            L_0x0556:
                r36 = "";
                r0 = r21;
                r1 = r36;
                r36 = r0.equals(r1);
                if (r36 == 0) goto L_0x0575;
            L_0x0562:
                r36 = new java.lang.StringBuilder;
                r37 = java.lang.String.valueOf(r4);
                r36.<init>(r37);
                r37 = "Please enter a Settlement head name.\n";
                r36 = r36.append(r37);
                r4 = r36.toString();
            L_0x0575:
                r36 = "";
                r0 = r22;
                r1 = r36;
                r36 = r0.equals(r1);
                if (r36 == 0) goto L_0x0594;
            L_0x0581:
                r36 = new java.lang.StringBuilder;
                r37 = java.lang.String.valueOf(r4);
                r36.<init>(r37);
                r37 = "Please enter a Settlement head phone.";
                r36 = r36.append(r37);
                r4 = r36.toString();
            L_0x0594:
                r36 = "";
                r0 = r24;
                r1 = r36;
                r36 = r0.equals(r1);
                if (r36 == 0) goto L_0x05b3;
            L_0x05a0:
                r36 = new java.lang.StringBuilder;
                r37 = java.lang.String.valueOf(r4);
                r36.<init>(r37);
                r37 = "Please enter a Target population.";
                r36 = r36.append(r37);
                r4 = r36.toString();
            L_0x05b3:
                r36 = "";
                r0 = r27;
                r1 = r36;
                r36 = r0.equals(r1);
                if (r36 != 0) goto L_0x05cb;
            L_0x05bf:
                r36 = "";
                r0 = r28;
                r1 = r36;
                r36 = r0.equals(r1);
                if (r36 == 0) goto L_0x05de;
            L_0x05cb:
                r36 = new java.lang.StringBuilder;
                r37 = java.lang.String.valueOf(r4);
                r36.<init>(r37);
                r37 = "Please collect location lattitiude & logititude.";
                r36 = r36.append(r37);
                r4 = r36.toString();
            L_0x05de:
                r36 = r23.length();
                r37 = 50;
                r0 = r36;
                r1 = r37;
                if (r0 <= r1) goto L_0x05fd;
            L_0x05ea:
                r36 = new java.lang.StringBuilder;
                r37 = java.lang.String.valueOf(r4);
                r36.<init>(r37);
                r37 = "Only 50 charectes can be entered for settlement name\n";
                r36 = r36.append(r37);
                r4 = r36.toString();
            L_0x05fd:
                r36 = r21.length();
                r37 = 20;
                r0 = r36;
                r1 = r37;
                if (r0 <= r1) goto L_0x061c;
            L_0x0609:
                r36 = new java.lang.StringBuilder;
                r37 = java.lang.String.valueOf(r4);
                r36.<init>(r37);
                r37 = "Only 20 charectes can be entered for settlement head name.\n";
                r36 = r36.append(r37);
                r4 = r36.toString();
            L_0x061c:
                r36 = r22.length();
                r37 = 11;
                r0 = r36;
                r1 = r37;
                if (r0 <= r1) goto L_0x063b;
            L_0x0628:
                r36 = new java.lang.StringBuilder;
                r37 = java.lang.String.valueOf(r4);
                r36.<init>(r37);
                r37 = "Only 11 digit can be entered for settlement head phone number.\n";
                r36 = r36.append(r37);
                r4 = r36.toString();
            L_0x063b:
                r36 = "";
                r0 = r36;
                r36 = r4.equals(r0);
                if (r36 != 0) goto L_0x06a5;
            L_0x0645:
                r36 = new android.app.AlertDialog$Builder;
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r37 = r0;
                r36.<init>(r37);
                r3 = r36.create();
                r36 = 0;
                r0 = r36;
                r3.setCancelable(r0);
                r36 = "Error";
                r0 = r36;
                r3.setTitle(r0);
                r3.setMessage(r4);
                r36 = "Ok";
                r37 = new org.phss.hgis.Settlement$12$1;
                r0 = r37;
                r1 = r39;
                r0.<init>();
                r0 = r36;
                r1 = r37;
                r3.setButton(r0, r1);
                r3.show();
            L_0x067a:
                return;
            L_0x067b:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.settlementCode;
                r37 = r0;
                r37 = r37 + 1;
                r0 = r37;
                r1 = r36;
                r1.settlementCode = r0;
                goto L_0x012a;
            L_0x0691:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r37 = r33 + 1;
                r0 = r37;
                r1 = r36;
                r1.settlementCode = r0;
                goto L_0x012a;
            L_0x06a1:
                r20 = "0";
                goto L_0x017c;
            L_0x06a5:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0._id;
                r36 = r0;
                if (r36 == 0) goto L_0x0758;
            L_0x06b3:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0._id;
                r36 = r0;
                r37 = "";
                r0 = r36;
                r1 = r37;
                if (r0 == r1) goto L_0x0758;
            L_0x06c7:
                r7 = new android.content.Intent;
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r37 = org.phss.hgis.Poi.class;
                r0 = r36;
                r1 = r37;
                r7.<init>(r0, r1);
                r36 = "_id";
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r37 = r0;
                r0 = r37;
                r0 = r0._id;
                r37 = r0;
                r0 = r36;
                r1 = r37;
                r7.putExtra(r0, r1);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r37 = "poiIdInt";
                r38 = 0;
                r14 = r36.getSharedPreferences(r37, r38);
                r15 = r14.edit();
                r36 = "ID";
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r37 = r0;
                r0 = r37;
                r0 = r0._genarCode;
                r37 = r0;
                r37 = java.lang.Integer.parseInt(r37);
                r0 = r36;
                r1 = r37;
                r15.putInt(r0, r1);
                r15.commit();
            L_0x071b:
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.runSetCodePrefEditor;
                r36 = r0;
                r37 = "runsetnum";
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r38 = r0;
                r0 = r38;
                r0 = r0.runningSettlement;
                r38 = r0;
                r36.putInt(r37, r38);
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r0 = r36;
                r0 = r0.runSetCodePrefEditor;
                r36 = r0;
                r36.commit();
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r37 = 1;
                r0 = r36;
                r1 = r37;
                r0.startActivityForResult(r7, r1);
                goto L_0x067a;
            L_0x0758:
                r7 = new android.content.Intent;
                r0 = r39;
                r0 = org.phss.hgis.Settlement.this;
                r36 = r0;
                r37 = org.phss.hgis.PoiList.class;
                r0 = r36;
                r1 = r37;
                r7.<init>(r0, r1);
                goto L_0x071b;
                */
                throw new UnsupportedOperationException("Method not decompiled: org.phss.hgis.Settlement.12.onClick(android.view.View):void");
            }
        });
        this.pLocation.setOnLocationReceivedListener(new OnLocationReceivedListener() {
            public void onLocationReceived(Location location) {
                Settlement.this.editeTextLatitude.setText(String.format("%1s", new Object[]{Double.valueOf(location.getLatitude())}));
                Settlement.this.editeTextLongitude.setText(String.format("%1s", new Object[]{Double.valueOf(location.getLongitude())}));
                Settlement.this.loading.cancel();
                Settlement.this.pLocation.stopLocationSearch();
                Settlement.this.delayed.removeCallbacks(Settlement.this.sleeppy);
            }
        });
    }

    public void showtoast() {
        Toast.makeText(this, "Location can not be retrive.\nPlease try again.", 1).show();
    }

    private String getSetCode(String wcode, int stcode) {
        if (this.settlementCodeStr != null) {
            return this.settlementCodeStr;
        }
        return new StringBuilder(String.valueOf(wcode)).append(String.format("%04d", new Object[]{Integer.valueOf(stcode)})).toString();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 1) {
            printCurrentRunningSettlement();
            this.chksameAsBefore.setChecked(false);
            this.editeTextPhssTeamCode.setText("");
            this.spineerState.setSelection(0);
            this.spineerLGA.setSelection(0);
            this.spineerWard.setSelection(0);
            this.spineerSettlement.setSelection(0);
            this.editeTextSettlementName.setText(this.spineerSettlement.getSelectedItem().toString());
            this.editeTextOtherName.setText("");
            this.editTextSettlementPopulation.setText("");
            this.spinnerLocation.setSelection(0);
            this.spinnerType.setSelection(0);
            this.spinnerRisk.setSelection(0);
            this.spinnerHardToReach.setSelection(0);
            this.spinnerReasoneHardToReach.setSelection(0);
            this.editeTextResoneForHardToReach.setText("");
            this.editeTextSettlementHeadName.setText("");
            this.editeTextSettlementHeadPhone.setText("");
            this.editeTextLatitude.setText("");
            this.editeTextLongitude.setText("");
            this.editeTextPhssTeamCode.setSelected(true);
        } else if (resultCode == 0 && requestCode == 1 && this._id != null && this._id != "") {
            finish();
        }
    }

    private void setScreen(Map<String, String> datam) {
        this.chksameAsBefore.setChecked(false);
        this.editeTextPhssTeamCode.setText((CharSequence) datam.get("phssteamcode"));
        this.spineerState.setSelection(StateLGAWard.getStatePosition((String) datam.get("state")));
        String[] lgaList = StateLGAWard.GetLGAs((String) datam.get("state"));
        this.spineerLGA = (Spinner) findViewById(C0029R.id.spinnerLga);
        ArrayAdapter<CharSequence> lgaAdapter = new ArrayAdapter(this, 17367048, lgaList);
        lgaAdapter.setDropDownViewResource(17367049);
        this.spineerLGA.setAdapter(lgaAdapter);
        this.spineerLGA.setOnItemSelectedListener(new LgaOnItemSelectedListener());
        this.spineerLGA.setSelection(StateLGAWard.getLgaPosition((String) datam.get("state"), (String) datam.get("lga")));
        String[] wardList = StateLGAWard.GetWards((String) datam.get("lga"));
        this.spineerWard = (Spinner) findViewById(C0029R.id.spinnerWard);
        ArrayAdapter<CharSequence> wardAdapter = new ArrayAdapter(this, 17367048, wardList);
        wardAdapter.setDropDownViewResource(17367049);
        this.spineerWard.setAdapter(wardAdapter);
        this.spineerWard.setOnItemSelectedListener(new WardOnItemSelectedListener());
        this.spineerWard.setSelection(StateLGAWard.getWardPosition((String) datam.get("lga"), (String) datam.get("ward")));
        HgisDBUtils hgisDBUtils = new HgisDBUtils(this);
        this.settlementData = hgisDBUtils.getSettlementRows((String) datam.get("wcode"));
        String[] setList = hgisDBUtils.getSettlementNames(this.settlementData);
        this.spineerSettlement = (Spinner) findViewById(C0029R.id.spinnerSettlement);
        ArrayAdapter<CharSequence> settlementAdapter = new ArrayAdapter(this, 17367048, setList);
        settlementAdapter.setDropDownViewResource(17367049);
        this.spineerSettlement.setAdapter(settlementAdapter);
        this.spineerSettlement.setOnItemSelectedListener(new SettlementOnItemSelectedListener());
        this.spineerSettlement.setSelection(hgisDBUtils.getSettlementNamesPosition(this.settlementData, (String) datam.get("stname")));
        this.editeTextSettlementName.setText((CharSequence) datam.get("stname"));
        this.editeTextSettlementName.setEnabled(false);
        this.editeTextOtherName.setText((CharSequence) datam.get("otname"));
        this.editTextSettlementPopulation.setText((CharSequence) datam.get("tpopulation"));
        this.spinnerLocation.setSelection(StateLGAWard.getStateLocPosition((String) datam.get("loctype")));
        this.spinnerType.setSelection(StateLGAWard.getStateLocTypePosition((String) datam.get("sttype")));
        this.spinnerRisk.setSelection(StateLGAWard.getRiskPosition((String) datam.get("risk")));
        this.spinnerHardToReach.setSelection(StateLGAWard.getHard2ReachPosition((String) datam.get("hdreach")));
        this.spinnerReasoneHardToReach.setSelection(StateLGAWard.getHard2ReachResonePosition((String) datam.get("hdrresone")));
        if (this.spinnerReasoneHardToReach.getSelectedItemPosition() == 3) {
            this.editeTextResoneForHardToReach.setEnabled(true);
            this.editeTextResoneForHardToReach.setText((CharSequence) datam.get("hdrresone"));
        }
        this.editeTextSettlementHeadName.setText((CharSequence) datam.get("stheadname"));
        this.editeTextSettlementHeadPhone.setText((CharSequence) datam.get("stheadphone"));
        this.editeTextLatitude.setText((CharSequence) datam.get("slattitiude"));
        this.editeTextLongitude.setText((CharSequence) datam.get("slongituide"));
        this.editeTextPhssTeamCode.setSelected(true);
    }

    void printCurrentRunningSettlement() {
        this.runningSettlement = (int) (new HgisDBUtils(this).getTotalSettlement() + 1);
        this.textViewCurrentSettlementNumber.setText("Current running settlement: " + String.valueOf(this.runningSettlement));
    }
}
