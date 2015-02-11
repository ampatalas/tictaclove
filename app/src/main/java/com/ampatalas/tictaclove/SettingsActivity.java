package com.ampatalas.tictaclove;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

/**
 * Created by A. M. Patalas on 2015-02-11.
 */
public class SettingsActivity extends Activity {

    /* Shared preferences */
    public static final String PREFERENCES = "PREFERENCES";
    public static final String PREF_SYMBOL_KEY = "PREF_SYMBOL_KEY";

    public static final int SYMBOL_NONE = 0;
    public static final int SYMBOL_HEARTS = 1;
    public static final int SYMBOL_KISSES = 2;

    private SharedPreferences sharedPreferences;

    /* UI */
    private Button save;
    private RadioButton hearts;
    private RadioButton kisses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        findViews();
        setRadioButton(sharedPreferences);
        attachListeners();
    }

    private void findViews() {
        save = (Button) findViewById(R.id.buttonSave);
        hearts = (RadioButton) findViewById(R.id.radioButtonHeart);
        kisses = (RadioButton) findViewById(R.id.radioButtonKiss);
    }

    private void setRadioButton(SharedPreferences sharedPreferences) {
        if (sharedPreferences.contains(PREF_SYMBOL_KEY)) {
            int symbol = sharedPreferences.getInt(PREF_SYMBOL_KEY, 0);
            if (symbol == SYMBOL_NONE) {
                hearts.setChecked(false);
                kisses.setChecked(false);
            }
            if (symbol == SYMBOL_HEARTS) {
                hearts.setChecked(true);
            }
            if (symbol == SYMBOL_KISSES) {
                kisses.setChecked(true);
            }
        }
    }

    private void attachListeners() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (hearts.isChecked()) {
                    editor.putInt(PREF_SYMBOL_KEY, SYMBOL_HEARTS);
                    editor.commit();
                }
                if (kisses.isChecked()) {
                    editor.putInt(PREF_SYMBOL_KEY, SYMBOL_KISSES);
                    editor.commit();
                }
                finish();
            }
        });
    }


}
