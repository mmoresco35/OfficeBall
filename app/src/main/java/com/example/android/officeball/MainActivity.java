package com.example.android.officeball;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer bossPlayer;
    int pointsA = 0;
    int cBossA = 0;
    int pointsB = 0;
    int cBossB = 0;

    /**
     * Fragment's state saved.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pointsA", pointsA);
        outState.putInt("pointsB", pointsB);
        outState.putInt("bossA", cBossA);
        outState.putInt("bossB", cBossB);
    }

    /**
     * Fragment's state restore.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pointsA = savedInstanceState.getInt("pointsA");
        pointsB = savedInstanceState.getInt("pointsB");
        cBossA = savedInstanceState.getInt("bossA");
        cBossB = savedInstanceState.getInt("bossB");
        setData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //"inflamos" el menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        showInstructions();

        return true;
    }

    public void showInstructions(){
        DiaInstructions diaInstructions = new DiaInstructions();
        diaInstructions.show(getFragmentManager(), getString(R.string.instructions));
    }


    private void setData() {
        TextView textViewA = (TextView) findViewById(R.id.tvpointsA);
        textViewA.setText(Integer.toString(pointsA));

        TextView textViewB = (TextView) findViewById(R.id.tvpointsb);
        textViewB.setText(Integer.toString(pointsB));

        TextView textViewBA = (TextView) findViewById(R.id.tvbcaugthA);
        textViewBA.setText(Integer.toString(cBossA));

        TextView textViewBB = (TextView) findViewById(R.id.tvbcaugthb);
        textViewBB.setText(Integer.toString(cBossB));

    }

    public void incrasePointsA(View view) {
        pointsA++;
        setData();
    }

    public void incrasePointsB(View view) {
        pointsB++;
        setData();
    }

    public void pointBossA(View view) {
        pointsA += 3;
        setData();
    }

    public void pointBossB(View view) {
        pointsB += 3;
        setData();
    }

    public void reset(View view) {
        pointsA = 0;
        pointsB = 0;
        cBossA = 0;
        cBossB = 0;
        LinearLayout layout = (LinearLayout) findViewById(R.id.data);
        layout.setVisibility(View.VISIBLE);
        LinearLayout lineatLayout = (LinearLayout) findViewById(R.id.activity_main);
        lineatLayout.setBackgroundResource(R.drawable.papelera);
        setData();
    }

    public void caugthA(View view) {
        pointsA -= 5;
        cBossA++;

        if (cBossA == 3) {
            boss();
        }
        if (pointsA < 0) {
            pointsA = 0;
        }

        setData();
    }

    public void caugthB(View view) {
        pointsB -= 5;
        cBossB++;

        if (cBossB == 3) {
            boss();
        }
        if (pointsB < 0) {
            pointsB = 0;
        }

        setData();
    }

    public void boss() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.data);
        layout.setVisibility(View.INVISIBLE);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        linearLayout.setBackgroundResource(R.drawable.boss);
        bossPlayer = MediaPlayer.create(this, R.raw.boss);
        bossPlayer.start();


    }
}
