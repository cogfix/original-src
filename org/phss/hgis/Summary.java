package org.phss.hgis;

import android.os.Bundle;
import android.widget.TextView;
import org.phss.hgis.utils.HgisDBUtils;
import org.phss.hgis.utils.OptionMenuActivity;

public class Summary extends OptionMenuActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0029R.layout.summary);
        setTitle("Summary");
        HgisDBUtils hgisDb = new HgisDBUtils(this);
        hgisDb.getRows("SELECT * FROM hgistbl");
        long totalRecords = hgisDb.getTotalSelectedRow();
        long totalSettlement = (long) ((int) hgisDb.getTotalSettlement());
        hgisDb.getRows("SELECT * FROM hgistbl WHERE issynced=1");
        long totalSyncedRecords = hgisDb.getTotalSelectedRow();
        hgisDb.getAllNonSyncedRows();
        long totalUnSyncedRecords = hgisDb.getTotalSelectedRow();
        ((TextView) findViewById(C0029R.id.textViewTitle)).setText("Summary:");
        ((TextView) findViewById(C0029R.id.textViewTotalRows)).setText("Total record(s): " + String.valueOf(totalRecords));
        ((TextView) findViewById(C0029R.id.textViewTotalSettlement)).setText("Total settlement(s): " + String.valueOf(totalSettlement));
        ((TextView) findViewById(C0029R.id.textViewTotalSyncedlRows)).setText("Total synced record(s): " + String.valueOf(totalSyncedRecords) + " out of " + String.valueOf(totalRecords));
        ((TextView) findViewById(C0029R.id.textViewTotalUnSyncedlRows)).setText("Total unsynced record(s): " + String.valueOf(totalUnSyncedRecords) + " out of " + String.valueOf(totalRecords));
    }
}
