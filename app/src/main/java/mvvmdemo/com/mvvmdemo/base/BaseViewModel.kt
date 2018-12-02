package mvvmdemo.com.mvvmdemo.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

open class BaseViewModel : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()


    protected val isLoadingSubject = BehaviorSubject.create<Boolean>()
    val isLoading = isLoadingSubject.hide()

    protected val errorSubject = PublishSubject.create<String>()
    val error = errorSubject.hide()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}