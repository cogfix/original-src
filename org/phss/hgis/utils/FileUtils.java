package org.phss.hgis.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileUtils {
    Context ctx;

    public FileUtils(Context cntx) {
        this.ctx = cntx;
    }

    public void SaveCSV(String str) {
        File fileDir = new File(Environment.getExternalStorageDirectory(), "/external_sd");
        if (fileDir.exists() && fileDir.canWrite()) {
            fileDir = new File(fileDir, "/hgis/");
        } else {
            fileDir = new File(Environment.getExternalStorageDirectory(), "/hgis/");
        }
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        File file = new File(fileDir, "hgis.csv");
        try {
            BufferedWriter bWriter;
            if (!file.exists()) {
                bWriter = new BufferedWriter(new FileWriter(file, true));
                bWriter.append("state,statecode,lga,lgacode,ward,wardcode,settlementname,settlementcode,othername,targetpopulation,locationtype,locationtypecode,settlementtype,settlementtypecode,risk,riskcode,hardtoreach,hardtoreachcode,hardtoreachresone,hardtoreachresonecode,settlementheadname,settlementheadphone,settlementlattiude,settlementlongitiude,phssteamcode,poigenre,poigenrecode,poitype,poitypecode,poiname,poilattitiude,poilongituide,date,timestamp,issynced");
                bWriter.newLine();
                bWriter.close();
            }
            bWriter = new BufferedWriter(new FileWriter(file, true));
            bWriter.append(str);
            bWriter.newLine();
            bWriter.close();
            Toast.makeText(this.ctx, "Saved", 1).show();
        } catch (IOException e) {
            Toast.makeText(this.ctx, e.getMessage(), 1).show();
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
    }

    public void SaveSettlementCSV(String str) {
        File fileDir = new File(Environment.getExternalStorageDirectory(), "/external_sd");
        if (fileDir.exists() && fileDir.canWrite()) {
            fileDir = new File(fileDir, "/hgis/");
        } else {
            fileDir = new File(Environment.getExternalStorageDirectory(), "/hgis/");
        }
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        File file = new File(fileDir, "settlement.csv");
        try {
            BufferedWriter bWriter;
            if (!file.exists()) {
                bWriter = new BufferedWriter(new FileWriter(file, true));
                bWriter.append("state,statecode,lga,lgacode,ward,wardcode,settlementname,settlementcode,othername,targetpopulation,locationtype,locationtypecode,settlementtype,settlementtypecode,risk,riskcode,hardtoreach,hardtoreachcode,hardtoreachresone,hardtoreachresonecode,settlementheadname,settlementheadphone,settlementlattiude,settlementlongitiude,phssteamcode");
                bWriter.newLine();
                bWriter.close();
            }
            bWriter = new BufferedWriter(new FileWriter(file, true));
            bWriter.append(str);
            bWriter.newLine();
            bWriter.close();
        } catch (IOException e) {
            Toast.makeText(this.ctx, e.getMessage(), 1).show();
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
    }

    public void UpdateCSV(String str) {
        File fileDir = new File(Environment.getExternalStorageDirectory(), "/external_sd");
        if (fileDir.exists() && fileDir.canWrite()) {
            fileDir = new File(fileDir, "/hgis/");
        } else {
            fileDir = new File(Environment.getExternalStorageDirectory(), "/hgis/");
        }
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        File file = new File(fileDir, "tem_hgis.csv");
        try {
            BufferedWriter bWriter;
            if (!file.exists()) {
                bWriter = new BufferedWriter(new FileWriter(file, true));
                bWriter.append("state,statecode,lga,lgacode,ward,wardcode,settlementname,settlementcode,othername,targetpopulation,locationtype,locationtypecode,settlementtype,settlementtypecode,risk,riskcode,hardtoreach,hardtoreachcode,hardtoreachresone,hardtoreachresonecode,settlementheadname,settlementheadphone,settlementlattiude,settlementlongitiude,phssteamcode,poigenre,poigenrecode,poitype,poitypecode,poiname,poilattitiude,poilongituide,date,timestamp,issynced");
                bWriter.newLine();
                bWriter.close();
            }
            bWriter = new BufferedWriter(new FileWriter(file, true));
            bWriter.append(str);
            bWriter.newLine();
            bWriter.close();
        } catch (IOException e) {
        }
    }

    public boolean deleteFile() {
        File fileDir = new File(Environment.getExternalStorageDirectory(), "/external_sd");
        if (fileDir.exists() && fileDir.canWrite()) {
            fileDir = new File(fileDir, "/hgis/");
        } else {
            fileDir = new File(Environment.getExternalStorageDirectory(), "/hgis/");
        }
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        return new File(fileDir, "hgis.csv").delete();
    }

    public boolean renameFile() {
        File fileDir = new File(Environment.getExternalStorageDirectory(), "/external_sd");
        if (fileDir.exists() && fileDir.canWrite()) {
            fileDir = new File(fileDir, "/hgis/");
        } else {
            fileDir = new File(Environment.getExternalStorageDirectory(), "/hgis/");
        }
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        return new File(fileDir, "tem_hgis.csv").renameTo(new File(fileDir, "hgis.csv"));
    }

    public String Map2CsvString(Map<String, String> datam) {
        return new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("\"" + ((String) datam.get("state")) + "\",\"")).append((String) datam.get("scode")).append("\",\"").toString())).append((String) datam.get("lga")).append("\",\"").toString())).append((String) datam.get("lcode")).append("\",\"").toString())).append((String) datam.get("ward")).append("\",\"").toString())).append((String) datam.get("wcode")).append("\",\"").toString())).append((String) datam.get("stname")).append("\",\"").toString())).append((String) datam.get("stcode")).append("\",\"").toString())).append((String) datam.get("otname")).append("\",\"").toString())).append((String) datam.get("tpopulation")).append("\",\"").toString())).append((String) datam.get("loctype")).append("\",\"").toString())).append((String) datam.get("loccode")).append("\",\"").toString())).append((String) datam.get("sttype")).append("\",\"").toString())).append((String) datam.get("sttypecode")).append("\",\"").toString())).append((String) datam.get("risk")).append("\",\"").toString())).append((String) datam.get("riskcode")).append("\",\"").toString())).append((String) datam.get("hdreach")).append("\",\"").toString())).append((String) datam.get("hdreachcode")).append("\",\"").toString())).append((String) datam.get("hdrresone")).append("\",\"").toString())).append((String) datam.get("hdrresonecode")).append("\",\"").toString())).append((String) datam.get("stheadname")).append("\",\"").toString())).append((String) datam.get("stheadphone")).append("\",\"").toString())).append((String) datam.get("slattitiude")).append("\",\"").toString())).append((String) datam.get("slongituide")).append("\",\"").toString())).append((String) datam.get("phssteamcode")).append("\",\"").toString())).append((String) datam.get("genre")).append("\",\"").toString())).append((String) datam.get("genrecode")).append("\",\"").toString())).append((String) datam.get("type")).append("\",\"").toString())).append((String) datam.get("typecode")).append("\",\"").toString())).append((String) datam.get("name")).append("\",\"").toString())).append((String) datam.get("plattitiude")).append("\",\"").toString())).append((String) datam.get("plongituide")).append("\",\"").toString())).append((String) datam.get("date")).append("\",\"").toString())).append((String) datam.get("timestamp")).append("\",\"").toString())).append((String) datam.get("issynced")).append("\"").toString();
    }
}
