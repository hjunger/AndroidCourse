package henriquejunger.workoutexerc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.view.View;

/**
 * Created by HenriqueJunger on 02/09/17.
 *
 */

public class Settings {
    private static Settings instance;

    private Drawable imageDrawable;
    private int backgroundColor;
    private String title;

    private boolean darkTheme;
    private int colorDark;

    public boolean isDarkTheme() {
        return darkTheme;
    }

    public void setDarkTheme(boolean darkTheme) {
        this.darkTheme = darkTheme;
    }

    public Drawable getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    private int getBackgroundColor() {
        return darkTheme ? colorDark : backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBackgroundColorFromCode(String bgCodeColor){
        setBackgroundColor(Color.parseColor(bgCodeColor));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private Settings(){
        backgroundColor = Color.WHITE;
        colorDark = Color.DKGRAY;
        darkTheme = false;
    }

    public static Settings getInstance(){
         if(instance == null){
             instance = new Settings();
         }
         return instance;
    }

    public void changeBackgroundColor(Activity activity, int viewId){
        View view = activity.findViewById(viewId);
        view.setBackgroundColor(this.getBackgroundColor());
    }

}
