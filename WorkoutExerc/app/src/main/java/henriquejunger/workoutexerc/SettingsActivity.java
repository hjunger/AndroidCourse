package henriquejunger.workoutexerc;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class SettingsActivity extends AppCompatActivity {
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        startSettings();
    }

    private void startSettings(){
        settings = Settings.getInstance();
        settings.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color
                .colorPrimaryDark, null));
        settings.changeBackgroundColor(this, R.id.settings_main_layout);
        CheckBox checkBox = (CheckBox)findViewById(R.id.themeChk);
        checkBox.setChecked(settings.isDarkTheme());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDarkTheme();
            }
        });
    }

    private void setDarkTheme(){
        CheckBox cb = (CheckBox)findViewById(R.id.themeChk);
        settings.setDarkTheme(cb.isChecked());
        settings.changeBackgroundColor(this, R.id.settings_main_layout);
    }
}
