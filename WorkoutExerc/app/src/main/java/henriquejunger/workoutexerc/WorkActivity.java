package henriquejunger.workoutexerc;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WorkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        Intent intent = getIntent();
        TextView titleView = (TextView) findViewById(R.id.title_work);
        ImageView imageView = (ImageView)findViewById(R.id.image_work);
        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.weigth_main_layout);

        String title = intent.getStringExtra(MainActivity.EXTRA_ID_TITLE);
        titleView.setText(title);

        int drawableId = intent.getIntExtra(MainActivity.EXTRA_ID_DRAWABLE, MainActivity.DRAWABLE_ID);
        if(drawableId != MainActivity.DRAWABLE_ID){
            imageView.setImageResource(drawableId);
        }

        layout.setBackgroundColor(intent.getIntExtra(MainActivity.EXTRA_ID_BACKGROUND,
                MainActivity.BACKGROUND_COLOR));
    }
}
