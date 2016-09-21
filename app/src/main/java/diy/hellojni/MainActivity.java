package diy.hellojni;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    static {
        System.loadLibrary("hellojni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null)
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    about();
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            about();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void about() {
        int v = getVersion();
        Log.d(TAG, String.format("version = %08x", v));

        String version = String.format(Locale.US, "%d.%d.%d %s"
                , (v & 0xff000000) >> 24
                , (v & 0x00ff0000) >> 16
                , (v & 0x0000ff00) >> 8
                , ((v & 0x000000ff) > 0 ? "debug" : "release"));
        String message = getString(R.string.about_dialog_message);
        message = String.format(Locale.US, message, version);
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_android_black_24dp)
                .setTitle(R.string.about_dialog_caption)
                .setMessage(message)
                .setNeutralButton(R.string.about_dialog_neutral, null)
                .show();
    }

    private native int getVersion();

}
