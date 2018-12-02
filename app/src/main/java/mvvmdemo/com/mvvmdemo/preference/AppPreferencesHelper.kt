package mvvmdemo.com.mvvmdemo.preference

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import mvvmdemo.com.mvvmdemo.di.PreferenceInfo
import javax.inject.Inject

class AppPreferencesHelper @Inject
constructor(
    context: Context, @PreferenceInfo prefFileName: String,
    override var Id: String,
    override var Detail: String
) : PreferencesHelper {


    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    private val defaultPreference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override var fcmToken: String
        get() = mPrefs.getString(PREF_FCMTOKEN, "") ?: ""
        set(fcmToken) = mPrefs.edit().putString(PREF_FCMTOKEN, fcmToken).apply()


    override var loginToken: String
        get() = mPrefs.getString(PREF_LOGINTOKEN, "") ?: ""
        set(loginToken) = mPrefs.edit().putString(PREF_LOGINTOKEN, loginToken).apply()

    fun clear() {
        mPrefs.edit().clear().apply()
    }

    companion object {
        private val PREF_FCMTOKEN = "PREF_FCMTOKEN"
        private val PREF_RJID = "PREF_RJID"
        private val PREF_RJDETAIL = "PREF_RJDETAIL"
        private val PREF_LOGINTOKEN = "PREF_LOGINTOKEN"
    }
}