package br.com.ufpb.ittalopessoa.t_mind.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by rapha on 16/04/2016.
 */
public class RobotoMedium extends TextView {

    public RobotoMedium(Context context) {
        super(context);
        setTypeface(context);
    }

    public RobotoMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public RobotoMedium(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RobotoMedium(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setTypeface(context);
    }

    private void setTypeface(Context context){
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/roboto_medium.ttf"));
    }
}
