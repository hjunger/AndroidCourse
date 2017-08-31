package henriquejunger.workoutexerc;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_ID_TITLE = "work.title";
    private static String TITLE_TEXT = "Title of this Screen";

    public static String EXTRA_ID_DRAWABLE = "work.image";
    public static int DRAWABLE_ID = 0;

    public static String EXTRA_ID_BACKGROUND = "work.background";
    public static int BACKGROUND_COLOR = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView;

        imageView = (ImageView) findViewById(R.id.image_weight);
        imageView.setTag(R.drawable.weight);
        imageView = (ImageView) findViewById(R.id.image_yoga);
        imageView.setTag(R.drawable.lotus);
        imageView = (ImageView) findViewById(R.id.image_cardio);
        imageView.setTag(R.drawable.heart);

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

    private void callNewView(View v){
        if(v instanceof ConstraintLayout){
            ConstraintLayout layout = (ConstraintLayout) v;
            int countChildren = layout.getChildCount();
            String title = TITLE_TEXT;
            int drawableId = DRAWABLE_ID;
            int backgroundColor = BACKGROUND_COLOR;

            for(int i = 0; i < countChildren; i++){
                View view = layout.getChildAt(i);

                if(view instanceof ImageView){
                    ImageView imageView = (ImageView)view;
                    drawableId = (int)imageView.getTag();
                }

                if(view instanceof TextView
                        && getResources().getResourceName(view.getId()).contains("title_")){
                    TextView textView = (TextView)view;
                    title = textView.getText().toString();
                }
            }

            ColorStateList colorStateList = layout.getBackgroundTintList();
            backgroundColor = colorStateList.getDefaultColor();

            Intent intent = new Intent(MainActivity.this, WorkActivity.class);
            intent.putExtra(EXTRA_ID_TITLE, title);
            intent.putExtra(EXTRA_ID_DRAWABLE, drawableId);
            intent.putExtra(EXTRA_ID_BACKGROUND, backgroundColor);
            startActivity(intent);
        }
    }
}
