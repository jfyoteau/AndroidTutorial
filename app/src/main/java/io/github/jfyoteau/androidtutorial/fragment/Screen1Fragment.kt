package io.github.jfyoteau.androidtutorial.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import io.github.jfyoteau.androidtutorial.MyFragmentActivity
import io.github.jfyoteau.androidtutorial.R

class Screen1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_screen1, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.screen1_button)?.apply {
            setOnClickListener {
                val activity = this@Screen1Fragment.activity as? MyFragmentActivity ?: return@setOnClickListener
                activity.presentFragment(Screen1Fragment())
            }
        }

        view.findViewById<Button>(R.id.screen2_button)?.apply {
            setOnClickListener {
                val activity = this@Screen1Fragment.activity as? MyFragmentActivity ?: return@setOnClickListener
                activity.presentFragment(Screen2Fragment())
            }
        }
    }

}
