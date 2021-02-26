package com.diwixis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.diwixis.filmlibrary.custom_view.databinding.FragmentCustomViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class CustomViewFragment : Fragment() {

    private lateinit var binding: FragmentCustomViewBinding
    private lateinit var equalizerTicker: Job
    private lateinit var numberViewTicker: Job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.customView.init(BARS_COUNT)
    }

    override fun onStart() {
        super.onStart()

        startEqualizerTicker()
        startNumberViewTicker()
    }

    override fun onStop() {
        super.onStop()
        equalizerTicker.cancel()
        numberViewTicker.cancel()
    }

    private fun startEqualizerTicker() {
        equalizerTicker = lifecycleScope.doTick(700) {
            binding.customView.updateBarsRelatively(makeFakeEqualizerData())
        }
    }

    private fun makeFakeEqualizerData(count: Int = BARS_COUNT) = (0 until count).map {
        Random.nextFloat() * 0.8f
    }

    private fun startNumberViewTicker() {
        numberViewTicker = lifecycleScope.doTick(1200) {
            binding.numberView.update((0..9).random())
        }
    }

    private fun CoroutineScope.doTick(
        repeatMillis: Long,
        action: () -> Unit
    ) = this.launch {
        if (repeatMillis > 0) {
            while (true) {
                action()
                delay(repeatMillis)
            }
        } else {
            action()
        }
    }

    companion object {
        const val BARS_COUNT = 50
    }
}