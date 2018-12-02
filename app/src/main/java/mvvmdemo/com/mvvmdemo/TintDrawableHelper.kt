package mvvmdemo.com.mvvmdemo

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.drawable.DrawableCompat


object TintDrawableHelper {

    fun getTintedResource(context: Context, drawableId: Int, colorId: Int): Drawable? {
        var drawable: Drawable? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = context.getResources().getDrawable(R.drawable.abc_ic_ab_back_material, context.getTheme())
        } else {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
            drawable = context.getResources().getDrawable(R.drawable.abc_ic_ab_back_material)
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            DrawableCompat.setTint(drawable, context.getResources().getColor(colorId, context.getTheme()))
        } else {
            DrawableCompat.setTint(drawable, context.getResources().getColor(colorId))
        }

        return drawable
    }
}
