package org.phss.hgis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettlementStart extends Activity {

    /* renamed from: org.phss.hgis.SettlementStart.1 */
    class C00451 implements OnClickListener {
        private final /* synthetic */ Intent val$intent;

        C00451(Intent intent) {
            this.val$intent = intent;
        }

        public void onClick(View v) {
            SettlementStart.this.startActivity(this.val$intent);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0029R.layout.settlementstart);
        ((Button) findViewById(C0029R.id.button1)).setOnClickListener(new C00451(new Intent(this, Settlement.class)));
    }
}
