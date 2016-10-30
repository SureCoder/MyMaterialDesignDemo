package sure.com.mymaterialdesigndemo.statusbarUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import sure.com.mymaterialdesigndemo.R;

/**
 * 描述：
 * 创建人：ShuoLi
 * 创建时间：2016/10/28 9:37
 */
public class StatusBabUtils {

    public static void transparentStatusBar(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0及以上
            View decorView = context.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            context.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4到5.0
            WindowManager.LayoutParams localLayoutParams = context.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        //addStatusBarView();//不推荐使用添加view的方式，所以注释掉此代码
    }

    public static void addStatusBarView(Activity context) {
        View view = new View(context);
        view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));//颜色自己指定
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(context));
        ViewGroup decorView = (ViewGroup) context.findViewById(android.R.id.content);
        decorView.addView(view, params);
    }

    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

}
