package org.phss.hgis;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import java.io.IOException;
import org.phss.hgis.utils.HgisDBUtils;
import org.phss.hgis.utils.StateLGAWard;

public class HGIS extends Activity {
    HgisDBUtils hgisDb;
    Intent intent;
    StateLGAWard slw;

    private class ParseStateLgaWard extends AsyncTask<StateLGAWard, Integer, Integer> {
        private ParseStateLgaWard() {
        }

        protected Integer doInBackground(StateLGAWard... stateLgaWard) {
            try {
                HGIS.this.hgisDb.createDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HGIS.this.slw = stateLgaWard[0];
            HGIS.this.slw = new StateLGAWard(HGIS.this);
            return Integer.valueOf(0);
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Integer result) {
            HGIS.this.intent = new Intent(HGIS.this, Settlement.class);
            HGIS.this.startActivity(HGIS.this.intent);
            HGIS.this.finish();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0029R.layout.main);
        this.hgisDb = new HgisDBUtils(this);
        new ParseStateLgaWard().execute(new StateLGAWard[]{this.slw});
    }
}
