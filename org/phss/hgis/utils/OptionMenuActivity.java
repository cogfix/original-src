package org.phss.hgis.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.phss.hgis.C0029R;
import org.phss.hgis.HgisListView;
import org.phss.hgis.Summary;

public class OptionMenuActivity extends Activity {
    ArrayList<Map<String, String>> data;
    HgisDBUtils hgisDb;
    AsyncTask<HttpUils, Integer, Boolean> httpTask;
    HttpUils httpUtils;
    ProgressDialog loading;
    PowerManager pm;
    WakeLock wl;

    /* renamed from: org.phss.hgis.utils.OptionMenuActivity.1 */
    class C00471 implements OnClickListener {
        C00471() {
        }

        public void onClick(DialogInterface dialog, int which) {
            OptionMenuActivity.this.loading.cancel();
            OptionMenuActivity.this.httpTask.cancel(true);
        }
    }

    private class HttpTask extends AsyncTask<HttpUils, Integer, Boolean> {
        int datasize;
        FileUtils fUtils;
        HttpUils hUtils;
        long localSyncCount;
        long remoteSyncCount;
        boolean tem;
        long timeMiliStamp;

        /* renamed from: org.phss.hgis.utils.OptionMenuActivity.HttpTask.1 */
        class C00481 implements OnClickListener {
            C00481() {
            }

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }

        /* renamed from: org.phss.hgis.utils.OptionMenuActivity.HttpTask.2 */
        class C00492 implements OnClickListener {
            C00492() {
            }

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }

        private HttpTask() {
            this.tem = false;
            this.hUtils = new HttpUils();
            this.fUtils = new FileUtils(OptionMenuActivity.this);
            this.datasize = OptionMenuActivity.this.data.size();
            this.localSyncCount = 0;
            this.remoteSyncCount = 0;
            this.timeMiliStamp = System.currentTimeMillis();
        }

        protected Boolean doInBackground(HttpUils... httpclient) {
            this.hUtils = httpclient[0];
            boolean result = false;
            if (this.datasize > 0) {
                int i;
                Map<String, String> datam;
                for (i = 0; i < this.datasize; i++) {
                    datam = (Map) OptionMenuActivity.this.data.get(i);
                    datam.put("submittime", String.valueOf(this.timeMiliStamp));
                    this.tem = this.hUtils.dataPost(datam);
                    datam.remove("submittime");
                    if (this.tem) {
                        publishProgress(new Integer[]{Integer.valueOf(i + 1)});
                        datam.put("issynced", String.valueOf("1"));
                        OptionMenuActivity.this.hgisDb.hgisUpdate(datam, Long.parseLong((String) datam.get("_id")));
                        this.localSyncCount++;
                        if (!result) {
                            result = true;
                        }
                    }
                }
                if (result) {
                    OptionMenuActivity.this.data.clear();
                    OptionMenuActivity.this.data = OptionMenuActivity.this.hgisDb.getRows("SELECT * FROM hgistbl");
                    for (i = 0; i < OptionMenuActivity.this.data.size(); i++) {
                        try {
                            datam = (Map) OptionMenuActivity.this.data.get(i);
                            publishProgress(new Integer[]{Integer.valueOf(i + 1)});
                            datam.put("issynced", String.valueOf("1"));
                            this.fUtils.UpdateCSV(this.fUtils.Map2CsvString(datam));
                            if (!result) {
                                result = true;
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            } else {
                result = false;
            }
            this.hUtils = new HttpUils("http://www.imeddic.com/android/hgiscount.php", OptionMenuActivity.this);
            Map<String, String> submitMiliTimeStamp = new HashMap();
            submitMiliTimeStamp.put("submittime", String.valueOf(this.timeMiliStamp));
            this.hUtils.dataPost(submitMiliTimeStamp);
            String respose = this.hUtils.getHttpResponseString();
            respose = respose.substring((respose.indexOf("\"record\":") + "\"record\":".length()) + 1);
            this.remoteSyncCount = Long.parseLong(respose.substring(0, respose.indexOf("\"}")).trim());
            return Boolean.valueOf(result);
        }

        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            OptionMenuActivity.this.loading.setProgress(progress[0].intValue());
            if (progress[0].intValue() == OptionMenuActivity.this.data.size()) {
                OptionMenuActivity.this.loading.setMessage("Updating CSV please waite ...");
                OptionMenuActivity.this.loading.setMax((int) OptionMenuActivity.this.hgisDb.getTotalSelectedRow());
                OptionMenuActivity.this.loading.setProgress(0);
            }
        }

        protected void onPostExecute(Boolean result) {
            OptionMenuActivity.this.loading.dismiss();
            OptionMenuActivity.this.wl.release();
            AlertDialog ad;
            if (result.booleanValue()) {
                this.fUtils.deleteFile();
                this.fUtils.renameFile();
                ad = new Builder(OptionMenuActivity.this).create();
                ad.setCancelable(false);
                if (this.localSyncCount == ((long) this.datasize) && this.localSyncCount == this.remoteSyncCount) {
                    ad.setMessage("Data synced completed.\nTotal " + String.valueOf(this.remoteSyncCount) + "/" + String.valueOf(this.datasize) + "\nrecords has been synced");
                } else {
                    ad.setMessage("Data synced completed partially.\nTotal " + String.valueOf(this.remoteSyncCount) + "/" + String.valueOf(this.datasize) + "\nrecords has been synced");
                }
                ad.setButton("Ok", new C00481());
                ad.show();
                return;
            }
            ad = new Builder(OptionMenuActivity.this).create();
            ad.setCancelable(false);
            ad.setMessage("No unsynced data is found.");
            ad.setButton("Ok", new C00492());
            ad.show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0029R.menu.optmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case C0029R.id.sync:
                this.hgisDb = new HgisDBUtils(this);
                this.data = this.hgisDb.getAllNonSyncedRows();
                if (this.data.size() <= 0) {
                    Toast.makeText(this, "No unsynced data is found.", 1).show();
                    break;
                }
                this.httpUtils = new HttpUils("http://www.imeddic.com/android/hgis.php", this);
                if (!this.httpUtils.isInternetAvailable()) {
                    Toast.makeText(this, "Internet connection is disabled.", 1).show();
                    break;
                }
                this.pm = (PowerManager) getSystemService("power");
                this.wl = this.pm.newWakeLock(1, "My Tag");
                this.wl.acquire();
                this.httpTask = new HttpTask();
                this.httpTask.execute(new HttpUils[]{this.httpUtils});
                this.loading = new ProgressDialog(this);
                this.loading.setProgressStyle(1);
                this.loading.setMessage("Transmiting data...");
                this.loading.setCancelable(false);
                this.loading.setMax((int) this.hgisDb.getTotalSelectedRow());
                this.loading.setProgress(0);
                this.loading.show();
                this.loading.setButton("Stop", new C00471());
                break;
            case C0029R.id.view_edit:
                startActivity(new Intent(this, HgisListView.class));
                break;
            case C0029R.id.summaryMenu:
                startActivity(new Intent(this, Summary.class));
                break;
            default:
                super.onOptionsItemSelected(item);
                break;
        }
        return true;
    }
}
