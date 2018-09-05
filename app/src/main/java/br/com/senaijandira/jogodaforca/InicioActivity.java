package br.com.senaijandira.jogodaforca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by 17259195 on 13/08/2018.
 */

public class InicioActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inicio);
    }

    public void inicarJogo(View v){
        Intent intent = new Intent (this, MainActivty.class);
        startActivity(intent);
        finish();
    }
}
