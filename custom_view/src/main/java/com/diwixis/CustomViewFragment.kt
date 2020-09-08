package com.diwixis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.diwixis.filmlibrary.custom_view.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_custom_view.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class CustomViewFragment : Fragment(R.layout.fragment_custom_view) {

    private var compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customView.init(BARS_COUNT)
    }

    override fun onStart() {
        super.onStart()

        val equalizer = Observable.interval(700, TimeUnit.MILLISECONDS)
            .map {
                (0 until BARS_COUNT).map {
                    Random.nextFloat() * 0.8f
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data -> customView.updateBarsRelatively(data) }

        val numbers = Observable.interval(1200, TimeUnit.MILLISECONDS)
            .map { Random.nextInt(0, 9) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                numberView.update(data)
            }

        compositeDisposable.addAll(equalizer, numbers)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }

    companion object {
        const val BARS_COUNT = 50
    }
}