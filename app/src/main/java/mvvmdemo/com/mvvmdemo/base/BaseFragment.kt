package mvvmdemo.com.mvvmdemo.base

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.layout_progress.view.*
import mvvmdemo.com.mvvmdemo.R


open class BaseFragment : Fragment() {

    lateinit var activity: Activity
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var alertDialog: AlertDialog? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            activity = (context as Activity)
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun showProgress(message: String) {
        val view = LayoutInflater.from(activity).inflate(R.layout.layout_progress, null)
        view.progressTitle.text = message
        val alertDialogBuilder = AlertDialog.Builder(activity).setView(view).setCancelable(false)
        alertDialog = alertDialogBuilder.show()
    }

    fun hideProgress() {
        alertDialog?.dismiss()
    }
}