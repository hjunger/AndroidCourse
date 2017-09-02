package henriquejunger.workoutexerc;

import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WorkActivity extends AppCompatActivity {
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        TextView titleView = (TextView) findViewById(R.id.title_work);
        ImageView imageView = (ImageView)findViewById(R.id.image_work);
        settings = Settings.getInstance();

        titleView.setText(settings.getTitle());

        Drawable drawable = settings.getImageDrawable();
        if(drawable != null){
            imageView.setImageDrawable(drawable);
        }

        settings.changeBackgroundColor(this, R.id.weigth_main_layout);
    }
}
