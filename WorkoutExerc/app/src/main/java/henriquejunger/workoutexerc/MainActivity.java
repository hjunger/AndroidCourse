package henriquejunger.workoutexerc;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Settings settings;
    private static int backgroundDefaulColor = Color.WHITE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSettings();
        setBackgroundColor();
        setListenerToConstraintLayout();
        setListenerToButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setBackgroundColor();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setBackgroundColor();
    }

    private void setListenerToConstraintLayout(){
        ConstraintLayout main = (ConstraintLayout) findViewById(R.id.main_layout);
        int countViews = main.getChildCount();
        for(int i = 0; i < countViews; i++){
            View view = main.getChildAt(i);
            if(view instanceof ConstraintLayout){
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callNewView(v);
                    }
                });
            }
        }
    }

    private void setListenerToButtons(){
        ImageButton settingBtn = (ImageButton)findViewById(R.id.settings_button);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setBackgroundColor(){
        if(settings == null)
            settings = Settings.getInstance();
        settings.setBackgroundColor(backgroundDefaulColor);
        settings.changeBackgroundColor(this, R.id.main_layout);
    }

    private void initSettings(){
        // Initiate Settings
        settings = Settings.getInstance();
        ImageView imageView = (ImageView) findViewById(R.id.image_weight);
        settings.setImageDrawable(imageView.getDrawable());
        TextView textView = (TextView)findViewById(R.id.title_weight);
        settings.setTitle(textView.getText().toString());
    }

    private void callNewView(View v){
        if(v instanceof ConstraintLayout){
            ConstraintLayout layout = (ConstraintLayout) v;
            int countChildren = layout.getChildCount();

            for(int i = 0; i < countChildren; i++){
                View view = layout.getChildAt(i);

                if(view instanceof ImageView){
                    ImageView imageView = (ImageView)view;
                    settings.setImageDrawable(imageView.getDrawable());
                }

                if(view instanceof TextView
                        && getResources().getResourceName(view.getId()).contains("title_")){
                    TextView textView = (TextView)view;
                    settings.setTitle(textView.getText().toString());
                }
            }

            ColorStateList colorStateList = layout.getBackgroundTintList();
            settings.setBackgroundColor(colorStateList.getDefaultColor());

            Intent intent = new Intent(MainActivity.this, WorkActivity.class);
            startActivity(intent);
        }
    }
}
