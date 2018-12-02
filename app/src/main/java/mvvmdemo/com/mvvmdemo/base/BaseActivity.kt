package mvvmdemo.com.mvvmdemo.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.layout_progress.view.*
import mvvmdemo.com.mvvmdemo.R
import mvvmdemo.com.mvvmdemo.di.AppComponent


open class BaseActivity : AppCompatActivity() {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        AppComponent.component.inject(this)
    }

    fun showProgress(message: String) {
        val view = LayoutInflater.from(this).inflate(R.layout.layout_progress, null)
        view.progressTitle.text = message
        val alertDialogBuilder = AlertDialog.Builder(this).setView(view).setCancelable(false)
        alertDialog = alertDialogBuilder.show()
    }

    fun hideProgress() {
        alertDialog?.dismiss()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}