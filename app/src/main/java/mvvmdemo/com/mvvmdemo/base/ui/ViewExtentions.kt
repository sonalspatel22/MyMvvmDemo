package mvvmdemo.com.mvvmdemo.base.ui

import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

fun View.throttleClicks(): Observable<Unit> {
    return clicks().throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
}

/**
 * Sets the drawable level of this view's background.
 *
 * @param view  view whose background's level will be set
 * @param level level position (starting at 0)
 */
fun View.setDrawableLevel(level: Int) {
    if (this.background == null) return
    this.background.level = level
}