package dam.alejandro.p0_helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends LogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();
    }

    private void setUpUI(){
        Button btnextActivity = findViewById(R.id.btNextActivity);

        btnextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NextActivity.class));
            }
        });
    }

    public void launchNextActivity(View view) {
        startActivity(new Intent(this, NextActivity.class));
    }
}