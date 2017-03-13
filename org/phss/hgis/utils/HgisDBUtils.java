package org.phss.hgis.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HgisDBUtils extends SQLiteOpenHelper {
    private static String DB_NAME;
    private static String DB_PATH;
    private final Context myContext;
    private SQLiteDatabase myDataBase;
    private Map<String, String> stnamecode;
    private long totalSelectedRow;
    private long totalSelectedSettlemnetRow;

    static {
        DB_PATH = "/data/data/org.phss.hgis/databases/";
        DB_NAME = "hgis.mp3";
    }

    public HgisDBUtils(Context context) {
        super(context, DB_NAME, null, 1);
        this.totalSelectedRow = 0;
        this.totalSelectedSettlemnetRow = 0;
        this.myContext = context;
    }

    public void createDataBase() throws IOException {
        if (!checkDataBase()) {
            getReadableDatabase();
            try {
                copyDataBase();
            } catch (Exception e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, 1);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        if (checkDB != null) {
            return true;
        }
        return false;
    }

    private void copyDataBase() {
        try {
            InputStream myInput = this.myContext.getAssets().open(DB_NAME);
            OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
            byte[] buffer = new byte[1024];
            while (true) {
                int length = myInput.read(buffer);
                if (length <= 0) {
                    myOutput.flush();
                    myOutput.close();
                    myInput.close();
                    return;
                }
                myOutput.write(buffer, 0, length);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void openDataBase() throws SQLException {
        this.myDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, 0);
    }

    public void closeDataBase() throws SQLException {
        this.myDataBase.close();
    }

    public boolean insertData() {
        try {
            SharedPreferences dbPref = this.myContext.getSharedPreferences("settlement", 0);
            ContentValues cv = new ContentValues();
            cv.put("state", dbPref.getString("stateName", ""));
            cv.put("scode", dbPref.getString("stateCode", ""));
            cv.put("lga", dbPref.getString("lgaName", ""));
            cv.put("lcode", dbPref.getString("lgaCode", ""));
            cv.put("ward", dbPref.getString("wardName", ""));
            cv.put("wcode", dbPref.getString("wardCode", ""));
            cv.put("stname", dbPref.getString("setName", ""));
            cv.put("stcode", dbPref.getString("setCode", ""));
            cv.put("otname", dbPref.getString("othName", ""));
            cv.put("tpopulation", dbPref.getString("setPopulation", ""));
            cv.put("loctype", dbPref.getString("locationName", ""));
            cv.put("loccode", dbPref.getString("locationCode", ""));
            cv.put("sttype", dbPref.getString("setType", ""));
            cv.put("sttypecode", dbPref.getString("setTypeCode", ""));
            cv.put("risk", dbPref.getString("risk", ""));
            cv.put("riskcode", dbPref.getString("riskCode", ""));
            cv.put("hdreach", dbPref.getString("hardToReach", ""));
            cv.put("hdreachcode", dbPref.getString("hardToReachCode", ""));
            cv.put("hdrresone", dbPref.getString("resoneHardToReach", ""));
            cv.put("hdrresonecode", dbPref.getString("resoneHardtoReachCode", ""));
            cv.put("stheadname", dbPref.getString("setHeadName", ""));
            cv.put("stheadphone", dbPref.getString("setHeadPhone", ""));
            cv.put("stlatitude", dbPref.getString("setlatti", ""));
            cv.put("stlongitiude", dbPref.getString("setlongi", ""));
            cv.put("phssteamcode", dbPref.getString("phssTeamCode", ""));
            cv.put("poigenre", dbPref.getString("poiGener", ""));
            cv.put("poigenrecode", dbPref.getString("poiCode", ""));
            cv.put("poitype", dbPref.getString("poiType", ""));
            cv.put("poitypecode", dbPref.getString("poiTypeCode", ""));
            cv.put("poiname", dbPref.getString("poiName", ""));
            cv.put("poilatitude", dbPref.getString("poiLatitute", ""));
            cv.put("poilongitiude", dbPref.getString("poiLongitute", ""));
            cv.put("date", dbPref.getString("date", ""));
            cv.put("issynced", Integer.valueOf(0));
            cv.put("timestamp", Long.valueOf(dbPref.getLong("tmstmp", 0)));
            this.myDataBase.insert("hgistbl", "_id", cv);
            if (this.myDataBase.isOpen()) {
                this.myDataBase.close();
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean insertSettlementData() {
        try {
            SharedPreferences dbPref = this.myContext.getSharedPreferences("settlement", 0);
            ContentValues cv = new ContentValues();
            cv.put("state", dbPref.getString("stateName", ""));
            cv.put("scode", dbPref.getString("stateCode", ""));
            cv.put("lga", dbPref.getString("lgaName", ""));
            cv.put("lcode", dbPref.getString("lgaCode", ""));
            cv.put("ward", dbPref.getString("wardName", ""));
            cv.put("wcode", dbPref.getString("wardCode", ""));
            cv.put("stname", dbPref.getString("setName", ""));
            cv.put("stcode", dbPref.getString("setCode", ""));
            cv.put("otname", dbPref.getString("othName", ""));
            cv.put("tpopulation", dbPref.getString("setPopulation", ""));
            cv.put("loctype", dbPref.getString("locationName", ""));
            cv.put("loccode", dbPref.getString("locationCode", ""));
            cv.put("sttype", dbPref.getString("setType", ""));
            cv.put("sttypecode", dbPref.getString("setTypeCode", ""));
            cv.put("risk", dbPref.getString("risk", ""));
            cv.put("riskcode", dbPref.getString("riskCode", ""));
            cv.put("hdreach", dbPref.getString("hardToReach", ""));
            cv.put("hdreachcode", dbPref.getString("hardToReachCode", ""));
            cv.put("hdrresone", dbPref.getString("resoneHardToReach", ""));
            cv.put("hdrresonecode", dbPref.getString("resoneHardtoReachCode", ""));
            cv.put("stheadname", dbPref.getString("setHeadName", ""));
            cv.put("stheadphone", dbPref.getString("setHeadPhone", ""));
            cv.put("stlatitude", dbPref.getString("setlatti", ""));
            cv.put("stlongitiude", dbPref.getString("setlongi", ""));
            cv.put("phssteamcode", dbPref.getString("phssTeamCode", ""));
            this.myDataBase.insert("hgis_settlement_tbl", "settlement_id", cv);
            if (this.myDataBase.isOpen()) {
                this.myDataBase.close();
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int hgisUpdate(Map<String, String> datam, long id) {
        ContentValues cv = new ContentValues();
        try {
            cv.put("state", (String) datam.get("state"));
            cv.put("scode", (String) datam.get("scode"));
            cv.put("lga", (String) datam.get("lga"));
            cv.put("lcode", (String) datam.get("lcode"));
            cv.put("ward", (String) datam.get("ward"));
            cv.put("wcode", (String) datam.get("wcode"));
            cv.put("stname", (String) datam.get("stname"));
            cv.put("stcode", (String) datam.get("stcode"));
            cv.put("otname", (String) datam.get("otname"));
            cv.put("tpopulation", (String) datam.get("tpopulation"));
            cv.put("loctype", (String) datam.get("loctype"));
            cv.put("loccode", (String) datam.get("loccode"));
            cv.put("sttype", (String) datam.get("sttype"));
            cv.put("sttypecode", (String) datam.get("sttypecode"));
            cv.put("risk", (String) datam.get("risk"));
            cv.put("riskcode", (String) datam.get("riskcode"));
            cv.put("hdreach", (String) datam.get("hdreach"));
            cv.put("hdreachcode", (String) datam.get("hdreachcode"));
            cv.put("hdrresone", (String) datam.get("hdrresone"));
            cv.put("hdrresonecode", (String) datam.get("hdrresonecode"));
            cv.put("stheadname", (String) datam.get("stheadname"));
            cv.put("stheadphone", (String) datam.get("stheadphone"));
            cv.put("stlatitude", (String) datam.get("slattitiude"));
            cv.put("stlongitiude", (String) datam.get("slongituide"));
            cv.put("phssteamcode", (String) datam.get("phssteamcode"));
            cv.put("poigenre", (String) datam.get("genre"));
            cv.put("poigenrecode", (String) datam.get("genrecode"));
            cv.put("poitype", (String) datam.get("type"));
            cv.put("poitypecode", (String) datam.get("typecode"));
            cv.put("poiname", (String) datam.get("name"));
            cv.put("poilatitude", (String) datam.get("plattitiude"));
            cv.put("poilongitiude", (String) datam.get("plongituide"));
            cv.put("date", (String) datam.get("date"));
            cv.put("issynced", Integer.valueOf(Integer.parseInt((String) datam.get("issynced"))));
            cv.put("timestamp", Long.valueOf(Long.parseLong((String) datam.get("timestamp"))));
            if (this.myDataBase == null) {
                openDataBase();
            }
            if (!this.myDataBase.isOpen()) {
                openDataBase();
            }
            this.myDataBase.update("hgistbl", cv, "_id =?", new String[]{String.valueOf(id)});
            if (this.myDataBase.isOpen()) {
                this.myDataBase.close();
            }
            return 1;
        } catch (Exception e) {
            if (this.myDataBase.isOpen()) {
                this.myDataBase.close();
            }
            return 0;
        }
    }

    public ArrayList<Map<String, String>> getAllNonSyncedRows() {
        ArrayList<Map<String, String>> data = new ArrayList();
        try {
            openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor c = this.myDataBase.rawQuery("SELECT * FROM hgistbl WHERE issynced=0", null);
        this.totalSelectedRow = (long) c.getCount();
        c.moveToFirst();
        if (this.totalSelectedRow > 0) {
            do {
                Map<String, String> datam = new HashMap();
                datam.put("_id", c.getString(0));
                datam.put("state", c.getString(1));
                datam.put("scode", c.getString(2));
                datam.put("lga", c.getString(3));
                datam.put("lcode", c.getString(4));
                datam.put("ward", c.getString(5));
                datam.put("wcode", c.getString(6));
                datam.put("stname", c.getString(7));
                datam.put("stcode", c.getString(8));
                datam.put("otname", c.getString(9));
                datam.put("tpopulation", c.getString(10));
                datam.put("loctype", c.getString(11));
                datam.put("loccode", c.getString(12));
                datam.put("sttype", c.getString(13));
                datam.put("sttypecode", c.getString(14));
                datam.put("risk", c.getString(15));
                datam.put("riskcode", c.getString(16));
                datam.put("hdreach", c.getString(17));
                datam.put("hdreachcode", c.getString(18));
                datam.put("hdrresone", c.getString(19));
                datam.put("hdrresonecode", c.getString(20));
                datam.put("stheadname", c.getString(21));
                datam.put("stheadphone", c.getString(22));
                datam.put("slattitiude", c.getString(23));
                datam.put("slongituide", c.getString(24));
                datam.put("phssteamcode", c.getString(25));
                datam.put("genre", c.getString(26));
                datam.put("genrecode", c.getString(27));
                datam.put("type", c.getString(28));
                datam.put("typecode", c.getString(29));
                datam.put("name", c.getString(30));
                datam.put("plattitiude", c.getString(31));
                datam.put("plongituide", c.getString(32));
                datam.put("date", c.getString(33));
                datam.put("timestamp", String.valueOf(c.getInt(34)));
                datam.put("issynced", String.valueOf(c.getInt(35)));
                data.add(datam);
            } while (c.moveToNext());
        }
        if (!(c == null || c.isClosed())) {
            c.close();
        }
        if (this.myDataBase.isOpen()) {
            this.myDataBase.close();
        }
        return data;
    }

    public void calculateSattlement(String query) {
        try {
            openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor c = this.myDataBase.rawQuery(query, null);
        this.totalSelectedRow = (long) c.getCount();
        if (!(c == null || c.isClosed())) {
            c.close();
        }
        if (this.myDataBase.isOpen()) {
            this.myDataBase.close();
        }
    }

    public ArrayList<Map<String, String>> getRows(String query) {
        ArrayList<Map<String, String>> data = new ArrayList();
        try {
            openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor c = this.myDataBase.rawQuery(query, null);
        this.totalSelectedRow = (long) c.getCount();
        c.moveToFirst();
        if (this.totalSelectedRow > 0) {
            do {
                Map<String, String> datam = new HashMap();
                datam.put("_id", c.getString(0));
                datam.put("state", c.getString(1));
                datam.put("scode", c.getString(2));
                datam.put("lga", c.getString(3));
                datam.put("lcode", c.getString(4));
                datam.put("ward", c.getString(5));
                datam.put("wcode", c.getString(6));
                datam.put("stname", c.getString(7));
                datam.put("stcode", c.getString(8));
                datam.put("otname", c.getString(9));
                datam.put("tpopulation", c.getString(10));
                datam.put("loctype", c.getString(11));
                datam.put("loccode", c.getString(12));
                datam.put("sttype", c.getString(13));
                datam.put("sttypecode", c.getString(14));
                datam.put("risk", c.getString(15));
                datam.put("riskcode", c.getString(16));
                datam.put("hdreach", c.getString(17));
                datam.put("hdreachcode", c.getString(18));
                datam.put("hdrresone", c.getString(19));
                datam.put("hdrresonecode", c.getString(20));
                datam.put("stheadname", c.getString(21));
                datam.put("stheadphone", c.getString(22));
                datam.put("slattitiude", c.getString(23));
                datam.put("slongituide", c.getString(24));
                datam.put("phssteamcode", c.getString(25));
                datam.put("genre", c.getString(26));
                datam.put("genrecode", c.getString(27));
                datam.put("type", c.getString(28));
                datam.put("typecode", c.getString(29));
                datam.put("name", c.getString(30));
                datam.put("plattitiude", c.getString(31));
                datam.put("plongituide", c.getString(32));
                datam.put("date", c.getString(33));
                datam.put("timestamp", String.valueOf(c.getInt(34)));
                datam.put("issynced", String.valueOf(c.getInt(35)));
                data.add(datam);
            } while (c.moveToNext());
        }
        if (!(c == null || c.isClosed())) {
            c.close();
        }
        if (this.myDataBase.isOpen()) {
            this.myDataBase.close();
        }
        return data;
    }

    public boolean isNewSettlement() {
        try {
            openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SharedPreferences dbPref = this.myContext.getSharedPreferences("settlement", 0);
        String stateCode = dbPref.getString("stateCode", "");
        String lgaCode = dbPref.getString("lgaCode", "");
        String wardCode = dbPref.getString("wardCode", "");
        Cursor c = this.myDataBase.rawQuery("SELECT * FROM hgis_settlement_tbl WHERE scode LIKE \"" + stateCode + "\" AND lcode LIKE \"" + lgaCode + "\" AND wcode LIKE \"" + wardCode + "\" AND stname LIKE \"" + dbPref.getString("setName", "") + "\"", null);
        this.totalSelectedRow = (long) c.getCount();
        if (!(c == null || c.isClosed())) {
            c.close();
        }
        if (this.myDataBase.isOpen()) {
            this.myDataBase.close();
        }
        if (this.totalSelectedRow == 0) {
            return true;
        }
        return false;
    }

    public long getTotalSettlement() {
        try {
            openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor c = this.myDataBase.rawQuery("SELECT * FROM hgis_settlement_tbl", null);
        this.totalSelectedRow = (long) c.getCount();
        if (!(c == null || c.isClosed())) {
            c.close();
        }
        if (this.myDataBase.isOpen()) {
            this.myDataBase.close();
        }
        return this.totalSelectedRow;
    }

    public long getTotalSelectedRow() {
        return this.totalSelectedRow;
    }

    public ArrayList<Map<String, String>> getSettlementRows(String wcode) {
        ArrayList<Map<String, String>> data = new ArrayList();
        String query = "SELECT * FROM settlementtbl WHERE wcode LIKE '" + wcode + "'";
        try {
            openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor c = this.myDataBase.rawQuery(query, null);
        this.totalSelectedSettlemnetRow = (long) c.getCount();
        c.moveToFirst();
        if (this.totalSelectedSettlemnetRow > 0) {
            do {
                String wardCode = c.getString(2);
                if (!wardCode.startsWith("0")) {
                    wardCode = "0" + wardCode;
                }
                String stcode = c.getString(4);
                if (!stcode.startsWith("0")) {
                    stcode = "0" + stcode;
                }
                Map<String, String> datam = new HashMap();
                datam.put("id", c.getString(0));
                datam.put("ward", c.getString(1));
                datam.put("wcode", wardCode);
                datam.put("settlement", c.getString(3));
                datam.put("stcode", stcode);
                datam.put("targetpopulation", c.getString(5));
                data.add(datam);
            } while (c.moveToNext());
        }
        if (!(c == null || c.isClosed())) {
            c.close();
        }
        if (this.myDataBase.isOpen()) {
            this.myDataBase.close();
        }
        return data;
    }

    public String[] getSettlementNames(ArrayList<Map<String, String>> data) {
        ArrayList<String> stList = new ArrayList();
        this.stnamecode = new HashMap();
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> datam = (Map) data.get(i);
            stList.add((String) datam.get("settlement"));
            this.stnamecode.put((String) datam.get("settlement"), (String) datam.get("stcode"));
        }
        stList.add("Other");
        return (String[]) stList.toArray(new String[stList.size()]);
    }

    public int getSettlementNamesPosition(ArrayList<Map<String, String>> data, String setName) {
        ArrayList<String> stList = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            stList.add((String) ((Map) data.get(i)).get("settlement"));
        }
        stList.add("Other");
        if (stList.indexOf(setName) != -1) {
            return stList.indexOf(setName);
        }
        return 0;
    }

    public String getSettlementCode(String stname) {
        if (this.totalSelectedSettlemnetRow != 0) {
            return (String) this.stnamecode.get(this.stnamecode);
        }
        return null;
    }

    public long getTotalSelectedSettlementRow() {
        return this.totalSelectedSettlemnetRow;
    }

    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
