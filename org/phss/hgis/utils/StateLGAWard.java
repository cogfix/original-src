package org.phss.hgis.utils;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StateLGAWard {
    private static Map<String, String> lga;
    private static Map<String, ArrayList<String>> lgaArray;
    private static Map<String, Map<String, String>> lgaWardMap;
    private static Map<String, String> state;
    private static Map<String, Map<String, String>> stateLgaMap;
    private static Map<String, Map<String, Map<String, String>>> stateLgaWordMap;
    private static Map<String, String> ward;
    private static Map<String, ArrayList<String>> wardArray;
    private AssetManager aMgr;
    private InputStream is;
    private ArrayList<String> lgaList;
    private String temLGA;
    private String temLGACode;
    private String temState;
    private String temStateCode;
    private String temWard;
    private String temWardCode;
    private ArrayList<String> wardList;

    static {
        state = new HashMap();
        lgaArray = new HashMap();
        wardArray = new HashMap();
        stateLgaWordMap = new HashMap();
        stateLgaMap = new HashMap();
    }

    public StateLGAWard(Context ctx) {
        this.temState = "";
        this.temStateCode = "";
        this.temLGA = "";
        this.temLGACode = "";
        this.temWard = "";
        this.temWardCode = "";
        this.aMgr = ctx.getAssets();
        SetStateLGAWard();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void SetStateLGAWard() {
        /*
        r12 = this;
        r4 = "";
        r2 = "";
        r8 = r12.aMgr;	 Catch:{ IOException -> 0x01d3 }
        r9 = "state_lga_word.csv";
        r8 = r8.open(r9);	 Catch:{ IOException -> 0x01d3 }
        r12.is = r8;	 Catch:{ IOException -> 0x01d3 }
        r6 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x01d3 }
        r8 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x01d3 }
        r9 = r12.is;	 Catch:{ IOException -> 0x01d3 }
        r8.<init>(r9);	 Catch:{ IOException -> 0x01d3 }
        r6.<init>(r8);	 Catch:{ IOException -> 0x01d3 }
    L_0x001a:
        r3 = r6.readLine();	 Catch:{ Exception -> 0x01cd }
        if (r3 != 0) goto L_0x005f;
    L_0x0020:
        r8 = stateLgaMap;	 Catch:{ Exception -> 0x01cd }
        r9 = r12.temState;	 Catch:{ Exception -> 0x01cd }
        r10 = lga;	 Catch:{ Exception -> 0x01cd }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x01cd }
        r8 = lgaWardMap;	 Catch:{ Exception -> 0x01cd }
        r9 = r12.temLGA;	 Catch:{ Exception -> 0x01cd }
        r10 = ward;	 Catch:{ Exception -> 0x01cd }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x01cd }
        r8 = stateLgaWordMap;	 Catch:{ Exception -> 0x01cd }
        r9 = r12.temState;	 Catch:{ Exception -> 0x01cd }
        r10 = lgaWardMap;	 Catch:{ Exception -> 0x01cd }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x01cd }
        r9 = lgaArray;	 Catch:{ Exception -> 0x01cd }
        r10 = r12.temState;	 Catch:{ Exception -> 0x01cd }
        r8 = r12.lgaList;	 Catch:{ Exception -> 0x01cd }
        r8 = r8.clone();	 Catch:{ Exception -> 0x01cd }
        r8 = (java.util.ArrayList) r8;	 Catch:{ Exception -> 0x01cd }
        r9.put(r10, r8);	 Catch:{ Exception -> 0x01cd }
        r9 = wardArray;	 Catch:{ Exception -> 0x01cd }
        r10 = r12.temLGA;	 Catch:{ Exception -> 0x01cd }
        r8 = r12.wardList;	 Catch:{ Exception -> 0x01cd }
        r8 = r8.clone();	 Catch:{ Exception -> 0x01cd }
        r8 = (java.util.ArrayList) r8;	 Catch:{ Exception -> 0x01cd }
        r9.put(r10, r8);	 Catch:{ Exception -> 0x01cd }
    L_0x0059:
        r8 = r12.is;	 Catch:{ IOException -> 0x01e8 }
        r8.close();	 Catch:{ IOException -> 0x01e8 }
    L_0x005e:
        return;
    L_0x005f:
        r8 = "";
        r8 = r4.equals(r8);	 Catch:{ Exception -> 0x01cd }
        if (r8 == 0) goto L_0x006a;
    L_0x0067:
        r4 = "t";
        goto L_0x001a;
    L_0x006a:
        r8 = ",";
        r5 = r3.split(r8);	 Catch:{ Exception -> 0x01cd }
        r8 = r12.temState;	 Catch:{ Exception -> 0x01cd }
        r9 = 0;
        r9 = r5[r9];	 Catch:{ Exception -> 0x01cd }
        r9 = r9.trim();	 Catch:{ Exception -> 0x01cd }
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x01cd }
        if (r8 != 0) goto L_0x00ea;
    L_0x007f:
        r8 = r12.temState;	 Catch:{ Exception -> 0x01cd }
        r9 = "";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x01cd }
        if (r8 != 0) goto L_0x00ac;
    L_0x0089:
        r9 = lgaArray;	 Catch:{ Exception -> 0x01cd }
        r10 = r12.temState;	 Catch:{ Exception -> 0x01cd }
        r8 = r12.lgaList;	 Catch:{ Exception -> 0x01cd }
        r8 = r8.clone();	 Catch:{ Exception -> 0x01cd }
        r8 = (java.util.ArrayList) r8;	 Catch:{ Exception -> 0x01cd }
        r9.put(r10, r8);	 Catch:{ Exception -> 0x01cd }
        r8 = stateLgaMap;	 Catch:{ Exception -> 0x01cd }
        r9 = r12.temState;	 Catch:{ Exception -> 0x01cd }
        r10 = lga;	 Catch:{ Exception -> 0x01cd }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x01cd }
        r8 = stateLgaWordMap;	 Catch:{ Exception -> 0x01cd }
        r9 = r12.temState;	 Catch:{ Exception -> 0x01cd }
        r10 = lgaWardMap;	 Catch:{ Exception -> 0x01cd }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x01cd }
        r2 = r12.temState;	 Catch:{ Exception -> 0x01cd }
    L_0x00ac:
        r8 = 0;
        r8 = r5[r8];	 Catch:{ Exception -> 0x01cd }
        r8 = r8.trim();	 Catch:{ Exception -> 0x01cd }
        r12.temState = r8;	 Catch:{ Exception -> 0x01cd }
        r8 = 1;
        r8 = r5[r8];	 Catch:{ Exception -> 0x01cd }
        r8 = r8.trim();	 Catch:{ Exception -> 0x01cd }
        r9 = 3;
        r10 = 1;
        r10 = r5[r10];	 Catch:{ Exception -> 0x01cd }
        r10 = r10.length();	 Catch:{ Exception -> 0x01cd }
        r10 = r10 + -1;
        r8 = r8.substring(r9, r10);	 Catch:{ Exception -> 0x01cd }
        r12.temStateCode = r8;	 Catch:{ Exception -> 0x01cd }
        r8 = state;	 Catch:{ Exception -> 0x01cd }
        r9 = r12.temState;	 Catch:{ Exception -> 0x01cd }
        r10 = r12.temStateCode;	 Catch:{ Exception -> 0x01cd }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x01cd }
        r8 = new java.util.ArrayList;	 Catch:{ Exception -> 0x01cd }
        r8.<init>();	 Catch:{ Exception -> 0x01cd }
        r12.lgaList = r8;	 Catch:{ Exception -> 0x01cd }
        r8 = new java.util.HashMap;	 Catch:{ Exception -> 0x01cd }
        r8.<init>();	 Catch:{ Exception -> 0x01cd }
        lga = r8;	 Catch:{ Exception -> 0x01cd }
        r8 = new java.util.HashMap;	 Catch:{ Exception -> 0x01cd }
        r8.<init>();	 Catch:{ Exception -> 0x01cd }
        lgaWardMap = r8;	 Catch:{ Exception -> 0x01cd }
    L_0x00ea:
        r8 = r12.temLGA;	 Catch:{ Exception -> 0x01cd }
        r9 = 2;
        r9 = r5[r9];	 Catch:{ Exception -> 0x01cd }
        r9 = r9.trim();	 Catch:{ Exception -> 0x01cd }
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x01cd }
        if (r8 != 0) goto L_0x019a;
    L_0x00f9:
        r8 = r12.temLGA;	 Catch:{ Exception -> 0x01cd }
        r9 = "YANKWASHI";
        r8.equals(r9);	 Catch:{ Exception -> 0x01cd }
        r8 = r12.temLGA;	 Catch:{ Exception -> 0x01cd }
        r9 = "";
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x01cd }
        if (r8 != 0) goto L_0x0149;
    L_0x010a:
        r9 = wardArray;	 Catch:{ Exception -> 0x01cd }
        r10 = r12.temLGA;	 Catch:{ Exception -> 0x01cd }
        r8 = r12.wardList;	 Catch:{ Exception -> 0x01cd }
        r8 = r8.clone();	 Catch:{ Exception -> 0x01cd }
        r8 = (java.util.ArrayList) r8;	 Catch:{ Exception -> 0x01cd }
        r9.put(r10, r8);	 Catch:{ Exception -> 0x01cd }
        r8 = lgaWardMap;	 Catch:{ Exception -> 0x01cd }
        r9 = r12.temLGA;	 Catch:{ Exception -> 0x01cd }
        r10 = ward;	 Catch:{ Exception -> 0x01cd }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x01cd }
        r8 = r12.temState;	 Catch:{ Exception -> 0x01cd }
        r8 = r2.equals(r8);	 Catch:{ Exception -> 0x01cd }
        if (r8 != 0) goto L_0x0149;
    L_0x012a:
        r8 = "";
        if (r2 == r8) goto L_0x0149;
    L_0x012e:
        r8 = stateLgaWordMap;	 Catch:{ Exception -> 0x01cd }
        r7 = r8.get(r2);	 Catch:{ Exception -> 0x01cd }
        r7 = (java.util.Map) r7;	 Catch:{ Exception -> 0x01cd }
        r8 = r12.temLGA;	 Catch:{ Exception -> 0x01cd }
        r9 = ward;	 Catch:{ Exception -> 0x01cd }
        r7.put(r8, r9);	 Catch:{ Exception -> 0x01cd }
        r8 = stateLgaWordMap;	 Catch:{ Exception -> 0x01cd }
        r8.put(r2, r7);	 Catch:{ Exception -> 0x01cd }
        r2 = r12.temState;	 Catch:{ Exception -> 0x01cd }
        r8 = lgaWardMap;	 Catch:{ Exception -> 0x01cd }
        r8.clear();	 Catch:{ Exception -> 0x01cd }
    L_0x0149:
        r8 = 2;
        r8 = r5[r8];	 Catch:{ Exception -> 0x01cd }
        r8 = r8.trim();	 Catch:{ Exception -> 0x01cd }
        r12.temLGA = r8;	 Catch:{ Exception -> 0x01cd }
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01cd }
        r9 = "0";
        r8.<init>(r9);	 Catch:{ Exception -> 0x01cd }
        r9 = 4;
        r9 = r5[r9];	 Catch:{ Exception -> 0x01cd }
        r9 = r9.trim();	 Catch:{ Exception -> 0x01cd }
        r10 = 0;
        r11 = 4;
        r11 = r5[r11];	 Catch:{ Exception -> 0x01cd }
        r11 = r11.trim();	 Catch:{ Exception -> 0x01cd }
        r11 = r11.length();	 Catch:{ Exception -> 0x01cd }
        r11 = r11 + -2;
        r9 = r9.substring(r10, r11);	 Catch:{ Exception -> 0x01cd }
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x01cd }
        r8 = r8.toString();	 Catch:{ Exception -> 0x01cd }
        r12.temLGACode = r8;	 Catch:{ Exception -> 0x01cd }
        r8 = lga;	 Catch:{ Exception -> 0x01cd }
        r9 = r12.temLGA;	 Catch:{ Exception -> 0x01cd }
        r10 = r12.temLGACode;	 Catch:{ Exception -> 0x01cd }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x01cd }
        r8 = new java.util.ArrayList;	 Catch:{ Exception -> 0x01cd }
        r8.<init>();	 Catch:{ Exception -> 0x01cd }
        r12.wardList = r8;	 Catch:{ Exception -> 0x01cd }
        r8 = r12.lgaList;	 Catch:{ Exception -> 0x01cd }
        r9 = r12.temLGA;	 Catch:{ Exception -> 0x01cd }
        r8.add(r9);	 Catch:{ Exception -> 0x01cd }
        r8 = new java.util.HashMap;	 Catch:{ Exception -> 0x01cd }
        r8.<init>();	 Catch:{ Exception -> 0x01cd }
        ward = r8;	 Catch:{ Exception -> 0x01cd }
    L_0x019a:
        r8 = 3;
        r8 = r5[r8];	 Catch:{ Exception -> 0x01cd }
        r8 = r8.trim();	 Catch:{ Exception -> 0x01cd }
        r12.temWard = r8;	 Catch:{ Exception -> 0x01cd }
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01cd }
        r9 = "0";
        r8.<init>(r9);	 Catch:{ Exception -> 0x01cd }
        r9 = 4;
        r9 = r5[r9];	 Catch:{ Exception -> 0x01cd }
        r9 = r9.trim();	 Catch:{ Exception -> 0x01cd }
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x01cd }
        r8 = r8.toString();	 Catch:{ Exception -> 0x01cd }
        r12.temWardCode = r8;	 Catch:{ Exception -> 0x01cd }
        r8 = r12.wardList;	 Catch:{ Exception -> 0x01cd }
        r9 = r12.temWard;	 Catch:{ Exception -> 0x01cd }
        r8.add(r9);	 Catch:{ Exception -> 0x01cd }
        r8 = ward;	 Catch:{ Exception -> 0x01cd }
        r9 = r12.temWard;	 Catch:{ Exception -> 0x01cd }
        r10 = r12.temWardCode;	 Catch:{ Exception -> 0x01cd }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x01cd }
        goto L_0x001a;
    L_0x01cd:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ IOException -> 0x01d3 }
        goto L_0x0059;
    L_0x01d3:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x01e1 }
        r8 = r12.is;	 Catch:{ IOException -> 0x01de }
        r8.close();	 Catch:{ IOException -> 0x01de }
        goto L_0x005e;
    L_0x01de:
        r8 = move-exception;
        goto L_0x005e;
    L_0x01e1:
        r8 = move-exception;
        r9 = r12.is;	 Catch:{ IOException -> 0x01eb }
        r9.close();	 Catch:{ IOException -> 0x01eb }
    L_0x01e7:
        throw r8;
    L_0x01e8:
        r8 = move-exception;
        goto L_0x005e;
    L_0x01eb:
        r9 = move-exception;
        goto L_0x01e7;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.phss.hgis.utils.StateLGAWard.SetStateLGAWard():void");
    }

    public static String GetStateCode(String stateName) {
        return (String) state.get(stateName);
    }

    public static String GetLGACode(String stateName, String lgaName) {
        lga = (Map) stateLgaMap.get(stateName);
        return (String) lga.get(lgaName);
    }

    public static String GetWardCode(String stateName, String lgaName, String wardName) {
        lgaWardMap = (Map) stateLgaWordMap.get(stateName);
        ward = (Map) lgaWardMap.get(lgaName);
        return (String) ward.get(wardName);
    }

    public static String[] GetLGAs(String name) {
        return (String[]) ((ArrayList) lgaArray.get(name)).toArray(new String[((ArrayList) lgaArray.get(name)).size()]);
    }

    public static String[] GetWards(String name) {
        return (String[]) ((ArrayList) wardArray.get(name)).toArray(new String[((ArrayList) wardArray.get(name)).size()]);
    }

    public static int getStatePosition(String name) {
        ArrayList<String> stArray = new ArrayList();
        stArray.add("BORNO");
        stArray.add("JIGAWA");
        stArray.add("KANO");
        stArray.add("KATSINA");
        stArray.add("KEBBI");
        stArray.add("SOKOTO");
        stArray.add("YOBE");
        stArray.add("ZAMFARA");
        return stArray.indexOf(name) != -1 ? stArray.indexOf(name) : 0;
    }

    public static int getLgaPosition(String stateName, String lgaName) {
        ArrayList<String> tempList = (ArrayList) lgaArray.get(stateName);
        return tempList.indexOf(lgaName) != -1 ? tempList.indexOf(lgaName) : 0;
    }

    public static int getWardPosition(String lgaName, String wardName) {
        ArrayList<String> tempList = (ArrayList) wardArray.get(lgaName);
        return tempList.indexOf(wardName) != -1 ? tempList.indexOf(wardName) : 0;
    }

    public static int getStateLocPosition(String name) {
        ArrayList<String> stLocArray = new ArrayList();
        stLocArray.add("URBAN");
        stLocArray.add("RURAL");
        stLocArray.add("SEMI URBAN");
        stLocArray.add("HAMLET");
        stLocArray.add("MIGRANT");
        return stLocArray.indexOf(name) != -1 ? stLocArray.indexOf(name) : 0;
    }

    public static int getStateLocTypePosition(String name) {
        ArrayList<String> stLocTypeArray = new ArrayList();
        stLocTypeArray.add("NEW");
        stLocTypeArray.add("OLD MICROPLAN");
        return stLocTypeArray.indexOf(name) != -1 ? stLocTypeArray.indexOf(name) : 0;
    }

    public static int getRiskPosition(String name) {
        ArrayList<String> stRiskArray = new ArrayList();
        stRiskArray.add("VERY HIGH RISK");
        stRiskArray.add("HIGH RISK");
        stRiskArray.add("LOW RISK");
        return stRiskArray.indexOf(name) != -1 ? stRiskArray.indexOf(name) : 0;
    }

    public static int getHard2ReachPosition(String name) {
        ArrayList<String> stHard2ReachArray = new ArrayList();
        stHard2ReachArray.add("YES");
        stHard2ReachArray.add("NO");
        return stHard2ReachArray.indexOf(name) != -1 ? stHard2ReachArray.indexOf(name) : 0;
    }

    public static int getHard2ReachResonePosition(String name) {
        ArrayList<String> stHard2ReachResone = new ArrayList();
        stHard2ReachResone.add("SANDY");
        stHard2ReachResone.add("ROCKY");
        stHard2ReachResone.add("LIMITED ACCESS DURING RAINY SEASON");
        stHard2ReachResone.add("OTHERS");
        return stHard2ReachResone.indexOf(name) != -1 ? stHard2ReachResone.indexOf(name) : 3;
    }

    public static int getSchoolTypePosition(String name) {
        ArrayList<String> schoolType = new ArrayList();
        schoolType.add("PRIMARY");
        schoolType.add("SECONDARY");
        schoolType.add("COLLAGE");
        schoolType.add("UNIVERSITY");
        schoolType.add("MADRASSA");
        schoolType.add("QURANIC SCHOOL");
        schoolType.add("SCHOOL OF TECHNOLOGY");
        schoolType.add("SCHOOL OF NURSING");
        schoolType.add("OTHERS");
        return schoolType.indexOf(name) != -1 ? schoolType.indexOf(name) : 0;
    }

    public static int getHealthTypePosition(String name) {
        ArrayList<String> healthType = new ArrayList();
        healthType.add("HOSPITAL");
        healthType.add("PRIMARY HEALTH CARE CENTER (PHC)");
        healthType.add("HEALTH POST (HP)");
        healthType.add("COMPREHENSIVE HEALTH CENTER (CHC)");
        healthType.add("HEALTH CENTER(HC)");
        healthType.add("MATERNITY CENTER (MC)");
        healthType.add("FEDERAL MEDICAL CENTER");
        healthType.add("PRIVATE CLINIC");
        healthType.add("PRIVATE HOSPITAL");
        healthType.add("PRIVATE LAB/DIAGNOSTIC CENTER");
        healthType.add("OTHERS");
        return healthType.indexOf(name) != -1 ? healthType.indexOf(name) : 0;
    }

    public static int getMejorBuildingTypePosition(String name) {
        ArrayList<String> mejorBuildingType = new ArrayList();
        mejorBuildingType.add("EMIR'S PALACE");
        mejorBuildingType.add("LGA SECRETARIAT");
        mejorBuildingType.add("GOVERNOR'S HOUSE");
        mejorBuildingType.add("STATE SECRETARIAT");
        mejorBuildingType.add("VILLAGE GATE");
        mejorBuildingType.add("HOTEL");
        mejorBuildingType.add("STATE VACCINE STORE");
        mejorBuildingType.add("LGA VACCINE STORE");
        mejorBuildingType.add("SUPERMARKET");
        mejorBuildingType.add("TV/RADIO STATION");
        mejorBuildingType.add("POST OFFICE");
        mejorBuildingType.add("CITY GATE");
        mejorBuildingType.add("MOBILE PHONE TOWER");
        mejorBuildingType.add("BANK");
        mejorBuildingType.add("OTHERS");
        return mejorBuildingType.indexOf(name) != -1 ? mejorBuildingType.indexOf(name) : 0;
    }

    public static int getWaterPointTypePosition(String name) {
        ArrayList<String> waterPointType = new ArrayList();
        waterPointType.add("BORE HOLE");
        waterPointType.add("WELL");
        waterPointType.add("OPEN RESERVOIR");
        waterPointType.add("LAKE");
        waterPointType.add("RIVER");
        waterPointType.add("WATER TOWER");
        waterPointType.add("OTHERS");
        return waterPointType.indexOf(name) != -1 ? waterPointType.indexOf(name) : 0;
    }
}
