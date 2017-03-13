package org.phss.hgis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import org.phss.hgis.utils.CustomImageButton;
import org.phss.hgis.utils.HgisDBUtils;
import org.phss.hgis.utils.OptionMenuActivity;

public class PoiList extends OptionMenuActivity {
    CustomImageButton buildingBtn;
    CustomImageButton charchBtn;
    CustomImageButton cityPointBtn;
    CustomImageButton dHeadHouseBtn;
    DisplayMetrics dMatrix;
    int dWidth;
    Handler delayed;
    int dheight;
    CustomImageButton healthBtn;
    Intent intent;
    CustomImageButton marketBtn;
    CustomImageButton mosqueBtn;
    CustomImageButton motorParkBtn;
    CustomImageButton othersBtn;
    CustomImageButton pharmacyBtn;
    CustomImageButton playBtn;
    SharedPreferences poiId;
    Editor poiIdEditor;
    CustomImageButton railCrossingBtn;
    CustomImageButton restaurantsBtn;
    CustomImageButton roadBtn;
    SharedPreferences runSetCodePref;
    CustomImageButton schoolBtn;
    CustomImageButton shopsBtn;
    private Runnable sleeppy;
    CustomImageButton stadiumBtn;
    CustomImageButton streetBtn;
    CustomImageButton suparMarketBtn;
    TextView textViewCurrentSettlementNumber;
    CustomImageButton tvRedioStationBtn;
    CustomImageButton uniBtl;
    CustomImageButton vheadHouseBtn;
    CustomImageButton wHeadBtn;
    CustomImageButton waterBtn;

    /* renamed from: org.phss.hgis.PoiList.1 */
    class C00141 implements Runnable {
        C00141() {
        }

        public void run() {
            PoiList.this.uniBtl.setDefaultColor();
            PoiList.this.uniBtl.setBackgroundColor(PoiList.this.uniBtl.getBackgroundColor());
        }
    }

    /* renamed from: org.phss.hgis.PoiList.2 */
    class C00152 implements OnClickListener {
        C00152() {
        }

        public void onClick(View v) {
            PoiList.this.wHeadBtn.setColor();
            PoiList.this.wHeadBtn.setBackgroundColor(PoiList.this.wHeadBtn.getBackgroundColor());
            PoiList.this.uniBtl = PoiList.this.wHeadBtn;
            PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
            PoiList.this.intent = new Intent(PoiList.this, Poi.class);
            PoiList.this.poiIdEditor.putInt("ID", 1);
            PoiList.this.poiIdEditor.commit();
            PoiList.this.startNewActivities();
        }
    }

    /* renamed from: org.phss.hgis.PoiList.3 */
    class C00163 implements OnClickListener {
        C00163() {
        }

        public void onClick(View v) {
            PoiList.this.vheadHouseBtn.setColor();
            PoiList.this.vheadHouseBtn.setBackgroundColor(PoiList.this.vheadHouseBtn.getBackgroundColor());
            PoiList.this.uniBtl = PoiList.this.vheadHouseBtn;
            PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
            PoiList.this.intent = new Intent(PoiList.this, Poi.class);
            PoiList.this.poiIdEditor.putInt("ID", 2);
            PoiList.this.poiIdEditor.commit();
            PoiList.this.startNewActivities();
        }
    }

    /* renamed from: org.phss.hgis.PoiList.4 */
    class C00174 implements OnClickListener {
        C00174() {
        }

        public void onClick(View v) {
            PoiList.this.mosqueBtn.setColor();
            PoiList.this.mosqueBtn.setBackgroundColor(PoiList.this.mosqueBtn.getBackgroundColor());
            PoiList.this.uniBtl = PoiList.this.mosqueBtn;
            PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
            PoiList.this.intent = new Intent(PoiList.this, Poi.class);
            PoiList.this.poiIdEditor.putInt("ID", 3);
            PoiList.this.poiIdEditor.commit();
            PoiList.this.startNewActivities();
        }
    }

    /* renamed from: org.phss.hgis.PoiList.5 */
    class C00185 implements OnClickListener {
        C00185() {
        }

        public void onClick(View v) {
            PoiList.this.schoolBtn.setColor();
            PoiList.this.schoolBtn.setBackgroundColor(PoiList.this.schoolBtn.getBackgroundColor());
            PoiList.this.uniBtl = PoiList.this.schoolBtn;
            PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
            PoiList.this.intent = new Intent(PoiList.this, Poi.class);
            PoiList.this.poiIdEditor.putInt("ID", 4);
            PoiList.this.poiIdEditor.commit();
            PoiList.this.startNewActivities();
        }
    }

    /* renamed from: org.phss.hgis.PoiList.6 */
    class C00196 implements OnClickListener {
        C00196() {
        }

        public void onClick(View v) {
            PoiList.this.charchBtn.setColor(-16776961);
            PoiList.this.charchBtn.setBackgroundColor(PoiList.this.charchBtn.getBackgroundColor());
            PoiList.this.uniBtl = PoiList.this.charchBtn;
            PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
            PoiList.this.intent = new Intent(PoiList.this, Poi.class);
            PoiList.this.poiIdEditor.putInt("ID", 5);
            PoiList.this.poiIdEditor.commit();
            PoiList.this.startNewActivities();
        }
    }

    /* renamed from: org.phss.hgis.PoiList.7 */
    class C00207 implements OnClickListener {
        C00207() {
        }

        public void onClick(View v) {
            PoiList.this.healthBtn.setColor();
            PoiList.this.healthBtn.setBackgroundColor(PoiList.this.healthBtn.getBackgroundColor());
            PoiList.this.uniBtl = PoiList.this.healthBtn;
            PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
            PoiList.this.intent = new Intent(PoiList.this, Poi.class);
            PoiList.this.poiIdEditor.putInt("ID", 6);
            PoiList.this.poiIdEditor.commit();
            PoiList.this.startNewActivities();
        }
    }

    /* renamed from: org.phss.hgis.PoiList.8 */
    class C00218 implements OnClickListener {
        C00218() {
        }

        public void onClick(View v) {
            PoiList.this.marketBtn.setColor();
            PoiList.this.marketBtn.setBackgroundColor(PoiList.this.marketBtn.getBackgroundColor());
            PoiList.this.uniBtl = PoiList.this.marketBtn;
            PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
            PoiList.this.intent = new Intent(PoiList.this, Poi.class);
            PoiList.this.poiIdEditor.putInt("ID", 8);
            PoiList.this.poiIdEditor.commit();
            PoiList.this.startNewActivities();
        }
    }

    /* renamed from: org.phss.hgis.PoiList.9 */
    class C00229 implements OnClickListener {
        C00229() {
        }

        public void onClick(View v) {
            PoiList.this.waterBtn.setColor();
            PoiList.this.waterBtn.setBackgroundColor(PoiList.this.waterBtn.getBackgroundColor());
            PoiList.this.uniBtl = PoiList.this.waterBtn;
            PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
            PoiList.this.intent = new Intent(PoiList.this, Poi.class);
            PoiList.this.poiIdEditor.putInt("ID", 9);
            PoiList.this.poiIdEditor.commit();
            PoiList.this.startNewActivities();
        }
    }

    public PoiList() {
        this.delayed = new Handler();
        this.dWidth = 0;
        this.dheight = 0;
        this.sleeppy = new C00141();
    }

    void printCurrentRunningSettlement() {
        this.textViewCurrentSettlementNumber.setText("Current running settlement: " + String.valueOf((int) (new HgisDBUtils(this).getTotalSettlement() + 1)));
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Poi List");
        this.poiId = getSharedPreferences("poiIdInt", 0);
        this.poiIdEditor = this.poiId.edit();
        this.dMatrix = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(this.dMatrix);
        this.dheight = this.dMatrix.heightPixels;
        this.dWidth = this.dMatrix.widthPixels;
        this.textViewCurrentSettlementNumber = new TextView(this);
        printCurrentRunningSettlement();
        this.wHeadBtn = new CustomImageButton(this, C0029R.drawable.ic_ward_head, "Ward head", this.dWidth, this.dheight);
        this.vheadHouseBtn = new CustomImageButton(this, C0029R.drawable.ic_village_head_house, "V. Head house", this.dWidth, this.dheight);
        this.mosqueBtn = new CustomImageButton(this, C0029R.drawable.ic_mosque, "Mosque", this.dWidth, this.dheight);
        this.schoolBtn = new CustomImageButton(this, C0029R.drawable.ic_school, "School", this.dWidth, this.dheight);
        this.charchBtn = new CustomImageButton(this, C0029R.drawable.ic_church, "Charch", this.dWidth, this.dheight);
        this.healthBtn = new CustomImageButton(this, C0029R.drawable.ic_health_facilty, "Health facilities", this.dWidth, this.dheight);
        this.marketBtn = new CustomImageButton(this, C0029R.drawable.ic_market_place, "Market place", this.dWidth, this.dheight);
        this.waterBtn = new CustomImageButton(this, C0029R.drawable.ic_water_point, "Water point", this.dWidth, this.dheight);
        this.motorParkBtn = new CustomImageButton(this, C0029R.drawable.ic_motor_park, "Motor park", this.dWidth, this.dheight);
        this.dHeadHouseBtn = new CustomImageButton(this, C0029R.drawable.ic_district_head_house, "D.Head house", this.dWidth, this.dheight);
        this.cityPointBtn = new CustomImageButton(this, C0029R.drawable.ic_city_point, "City point", this.dWidth, this.dheight);
        this.roadBtn = new CustomImageButton(this, C0029R.drawable.ic_divider, "Road", this.dWidth, this.dheight);
        this.railCrossingBtn = new CustomImageButton(this, C0029R.drawable.ic_rail_crossing, "Rail crossing", this.dWidth, this.dheight);
        this.playBtn = new CustomImageButton(this, C0029R.drawable.ic_playground, "Play ground", this.dWidth, this.dheight);
        this.stadiumBtn = new CustomImageButton(this, C0029R.drawable.ic_stadium, "Stadium", this.dWidth, this.dheight);
        this.buildingBtn = new CustomImageButton(this, C0029R.drawable.ic_major_building, "Major building", this.dWidth, this.dheight);
        this.streetBtn = new CustomImageButton(this, C0029R.drawable.ic_street, "Street", this.dWidth, this.dheight);
        this.tvRedioStationBtn = new CustomImageButton(this, C0029R.drawable.ic_television, "Tv/Redio", this.dWidth, this.dheight);
        this.pharmacyBtn = new CustomImageButton(this, C0029R.drawable.ic_chemist, "Pharmacy", this.dWidth, this.dheight);
        this.suparMarketBtn = new CustomImageButton(this, C0029R.drawable.ic_market, "Super market", this.dWidth, this.dheight);
        this.shopsBtn = new CustomImageButton(this, C0029R.drawable.ic_shops, "Shops", this.dWidth, this.dheight);
        this.restaurantsBtn = new CustomImageButton(this, C0029R.drawable.ic_restaurant, "Restaurants", this.dWidth, this.dheight);
        this.othersBtn = new CustomImageButton(this, C0029R.drawable.ic_other, "Others", this.dWidth, this.dheight);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        this.textViewCurrentSettlementNumber.setId(101);
        this.textViewCurrentSettlementNumber.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 101);
        layoutParams.addRule(9);
        layoutParams.leftMargin = 20;
        layoutParams.topMargin = 20;
        this.wHeadBtn.setId(1);
        this.wHeadBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        layoutParams.topMargin = 20;
        this.vheadHouseBtn.setId(2);
        this.vheadHouseBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        layoutParams.rightMargin = 20;
        layoutParams.topMargin = 20;
        this.mosqueBtn.setId(3);
        this.mosqueBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 3);
        layoutParams.addRule(9);
        layoutParams.leftMargin = 20;
        layoutParams.topMargin = 20;
        this.schoolBtn.setId(4);
        this.schoolBtn.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams chParams = new RelativeLayout.LayoutParams(-2, -2);
        chParams.addRule(3, 3);
        chParams.addRule(14);
        chParams.topMargin = 20;
        this.charchBtn.setId(5);
        this.charchBtn.setLayoutParams(chParams);
        RelativeLayout.LayoutParams heathParams = new RelativeLayout.LayoutParams(-2, -2);
        heathParams.addRule(3, 3);
        heathParams.addRule(11);
        heathParams.rightMargin = 20;
        heathParams.topMargin = 20;
        this.healthBtn.setId(6);
        this.healthBtn.setLayoutParams(heathParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 6);
        layoutParams.addRule(9);
        layoutParams.leftMargin = 20;
        layoutParams.topMargin = 20;
        this.marketBtn.setId(7);
        this.marketBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 6);
        layoutParams.addRule(14);
        layoutParams.topMargin = 20;
        this.waterBtn.setId(9);
        this.waterBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 6);
        layoutParams.addRule(11);
        layoutParams.rightMargin = 20;
        layoutParams.topMargin = 20;
        this.motorParkBtn.setId(10);
        this.motorParkBtn.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams dHeadParams = new RelativeLayout.LayoutParams(-2, -2);
        dHeadParams.addRule(3, 10);
        dHeadParams.addRule(9);
        dHeadParams.leftMargin = 20;
        dHeadParams.topMargin = 20;
        this.dHeadHouseBtn.setId(11);
        this.dHeadHouseBtn.setLayoutParams(dHeadParams);
        RelativeLayout.LayoutParams cityParams = new RelativeLayout.LayoutParams(-2, -2);
        cityParams.addRule(3, 10);
        cityParams.addRule(14);
        cityParams.topMargin = 20;
        this.cityPointBtn.setId(12);
        this.cityPointBtn.setLayoutParams(cityParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 10);
        layoutParams.addRule(11);
        layoutParams.rightMargin = 20;
        layoutParams.topMargin = 20;
        this.roadBtn.setId(13);
        this.roadBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 13);
        layoutParams.addRule(9);
        layoutParams.leftMargin = 20;
        layoutParams.topMargin = 20;
        this.railCrossingBtn.setId(14);
        this.railCrossingBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 13);
        layoutParams.addRule(14);
        layoutParams.topMargin = 20;
        this.playBtn.setId(15);
        this.playBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 13);
        layoutParams.addRule(11);
        layoutParams.rightMargin = 20;
        layoutParams.topMargin = 20;
        this.stadiumBtn.setId(17);
        this.stadiumBtn.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams buildingParams = new RelativeLayout.LayoutParams(-2, -2);
        buildingParams.addRule(3, 17);
        buildingParams.addRule(9);
        buildingParams.leftMargin = 20;
        buildingParams.topMargin = 20;
        this.buildingBtn.setId(18);
        this.buildingBtn.setLayoutParams(buildingParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 17);
        layoutParams.addRule(14);
        layoutParams.topMargin = 20;
        this.streetBtn.setId(19);
        this.streetBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 17);
        layoutParams.addRule(11);
        layoutParams.topMargin = 20;
        layoutParams.rightMargin = 20;
        this.tvRedioStationBtn.setId(20);
        this.tvRedioStationBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 20);
        layoutParams.addRule(9);
        layoutParams.leftMargin = 20;
        layoutParams.topMargin = 20;
        this.pharmacyBtn.setId(21);
        this.pharmacyBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 20);
        layoutParams.addRule(14);
        layoutParams.topMargin = 20;
        this.suparMarketBtn.setId(22);
        this.suparMarketBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 20);
        layoutParams.addRule(11);
        layoutParams.topMargin = 20;
        layoutParams.rightMargin = 20;
        this.shopsBtn.setId(23);
        this.shopsBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 23);
        layoutParams.addRule(9);
        layoutParams.leftMargin = 20;
        layoutParams.topMargin = 20;
        this.restaurantsBtn.setId(21);
        this.restaurantsBtn.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 23);
        layoutParams.addRule(14);
        layoutParams.topMargin = 20;
        this.othersBtn.setId(7);
        this.othersBtn.setLayoutParams(layoutParams);
        RelativeLayout layout1 = new RelativeLayout(this);
        layout1.setLayoutParams(new LayoutParams(-1, -1));
        layout1.addView(this.wHeadBtn);
        layout1.addView(this.vheadHouseBtn);
        layout1.addView(this.mosqueBtn);
        layout1.addView(this.schoolBtn);
        layout1.addView(this.charchBtn);
        layout1.addView(this.healthBtn);
        layout1.addView(this.marketBtn);
        layout1.addView(this.waterBtn);
        layout1.addView(this.motorParkBtn);
        layout1.addView(this.dHeadHouseBtn);
        layout1.addView(this.cityPointBtn);
        layout1.addView(this.roadBtn);
        layout1.addView(this.railCrossingBtn);
        layout1.addView(this.playBtn);
        layout1.addView(this.stadiumBtn);
        layout1.addView(this.buildingBtn);
        layout1.addView(this.streetBtn);
        layout1.addView(this.tvRedioStationBtn);
        layout1.addView(this.pharmacyBtn);
        layout1.addView(this.suparMarketBtn);
        layout1.addView(this.shopsBtn);
        layout1.addView(this.restaurantsBtn);
        layout1.addView(this.othersBtn);
        layout1.setScrollbarFadingEnabled(true);
        LinearLayout linearLayoutMain = new LinearLayout(this);
        linearLayoutMain.setLayoutParams(new LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LayoutParams(-2, -2));
        relativeLayout.addView(this.textViewCurrentSettlementNumber);
        relativeLayout = new ScrollView(this);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        relativeLayout.addView(layout1);
        linearLayout.addView(relativeLayout);
        linearLayoutMain.setOrientation(1);
        linearLayoutMain.addView(relativeLayout);
        linearLayoutMain.addView(linearLayout);
        setContentView(linearLayoutMain);
        this.wHeadBtn.setOnClickListener(new C00152());
        this.vheadHouseBtn.setOnClickListener(new C00163());
        this.mosqueBtn.setOnClickListener(new C00174());
        this.schoolBtn.setOnClickListener(new C00185());
        this.charchBtn.setOnClickListener(new C00196());
        this.healthBtn.setOnClickListener(new C00207());
        this.marketBtn.setOnClickListener(new C00218());
        this.waterBtn.setOnClickListener(new C00229());
        this.motorParkBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.motorParkBtn.setColor();
                PoiList.this.motorParkBtn.setBackgroundColor(PoiList.this.motorParkBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.motorParkBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 10);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.dHeadHouseBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.dHeadHouseBtn.setColor();
                PoiList.this.dHeadHouseBtn.setBackgroundColor(PoiList.this.dHeadHouseBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.dHeadHouseBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 11);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.cityPointBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.cityPointBtn.setColor();
                PoiList.this.cityPointBtn.setBackgroundColor(PoiList.this.cityPointBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.cityPointBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 12);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.roadBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.roadBtn.setColor();
                PoiList.this.roadBtn.setBackgroundColor(PoiList.this.roadBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.roadBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 13);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.railCrossingBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.railCrossingBtn.setColor();
                PoiList.this.railCrossingBtn.setBackgroundColor(PoiList.this.railCrossingBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.railCrossingBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 14);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.playBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.playBtn.setColor();
                PoiList.this.playBtn.setBackgroundColor(PoiList.this.playBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.playBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 15);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.stadiumBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.stadiumBtn.setColor();
                PoiList.this.stadiumBtn.setBackgroundColor(PoiList.this.stadiumBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.stadiumBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 16);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.buildingBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.buildingBtn.setColor();
                PoiList.this.buildingBtn.setBackgroundColor(PoiList.this.buildingBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.buildingBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 17);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.streetBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.streetBtn.setColor();
                PoiList.this.streetBtn.setBackgroundColor(PoiList.this.streetBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.streetBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 18);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.tvRedioStationBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.tvRedioStationBtn.setColor();
                PoiList.this.tvRedioStationBtn.setBackgroundColor(PoiList.this.tvRedioStationBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.tvRedioStationBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 19);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.pharmacyBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.pharmacyBtn.setColor();
                PoiList.this.pharmacyBtn.setBackgroundColor(PoiList.this.pharmacyBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.pharmacyBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 20);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.suparMarketBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.suparMarketBtn.setColor();
                PoiList.this.suparMarketBtn.setBackgroundColor(PoiList.this.suparMarketBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.suparMarketBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 21);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.shopsBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.shopsBtn.setColor();
                PoiList.this.shopsBtn.setBackgroundColor(PoiList.this.shopsBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.shopsBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 22);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.restaurantsBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.restaurantsBtn.setColor();
                PoiList.this.restaurantsBtn.setBackgroundColor(PoiList.this.restaurantsBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.restaurantsBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 23);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
        this.othersBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                PoiList.this.othersBtn.setColor();
                PoiList.this.othersBtn.setBackgroundColor(PoiList.this.othersBtn.getBackgroundColor());
                PoiList.this.uniBtl = PoiList.this.othersBtn;
                PoiList.this.delayed.postDelayed(PoiList.this.sleeppy, 200);
                PoiList.this.intent = new Intent(PoiList.this, Poi.class);
                PoiList.this.poiIdEditor.putInt("ID", 7);
                PoiList.this.poiIdEditor.commit();
                PoiList.this.startNewActivities();
            }
        });
    }

    private void startNewActivities() {
        this.intent = new Intent(this, Poi.class);
        startActivityForResult(this.intent, 2);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 2) {
            setResult(-1);
            finish();
        }
    }
}
