package io.github.jfyoteau.androidtutorial.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import io.github.jfyoteau.androidtutorial.MyFragmentActivity

import io.github.jfyoteau.androidtutorial.R

/**
 * A simple [Fragment] subclass.
 *
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_splash, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.screen1_button)?.apply {
            setOnClickListener {
                val activity = this@SplashFragment.activity as? MyFragmentActivity ?: return@setOnClickListener
                activity.presentFragment(Screen1Fragment())
            }
        }
    }
}
