package com.example.tthreads;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   public String equat(int a, int b, int c){
    int dis = b*b - 4*a*c;
    if (dis == 0) {
        return "one solution\nx =" +((float) -b/(2*a) );
    }
    else if (dis < 0) {
        return getString(R.string.mT_no_solutions);
    }
    else {
        return ("two solutions\nx1 ="+((float) -b+Math.sqrt(dis)/(2*a))+"\nx2 = "+((float) -b-Math.sqrt(dis)/(2*a)) );
    }
}
    Button solve;
    EditText ac;
    EditText bc;
    EditText cc;
    EditText dc;
    TextView solveTV;
    TextView solveTV2;
    String s1 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solve = findViewById(R.id.solve);
        solveTV = findViewById(R.id.solveTV);
        solveTV2 = findViewById(R.id.solveTV2);
        ac = findViewById(R.id.ac);
        bc = findViewById(R.id.bc);
        cc = findViewById(R.id.cc);
        dc = findViewById(R.id.dc);


        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(ac.getText().toString());
                    int b = Integer.parseInt(bc.getText().toString());
                    int c = Integer.parseInt(cc.getText().toString());
                    int d = Integer.parseInt(dc.getText().toString());
                    solveTV.setText("mainThread: "+equat(b,c,d));

                    Thread addThread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            s1 = "addThread"+equat(a,b,c);

                        }
                    });

                    addThread.start();
                    solveTV2.setText(s1);


                }
                catch (Exception e){
                    solveTV.setText(getString(R.string.exception));
                }

            }
        });
    }
}