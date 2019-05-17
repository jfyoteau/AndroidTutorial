package io.github.jfyoteau.androidtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.github.jfyoteau.androidtutorial.fragment.Screen
import io.github.jfyoteau.androidtutorial.fragment.SplashFragment

class MyFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_fragment)

        if (savedInstanceState == null) {
            showSplash()
        }
    }

    private fun showSplash() {
        val fm = this.supportFragmentManager
        fm.beginTransaction().apply {
            val fragment = SplashFragment()
            add(R.id.fragment_container, fragment) // `R.id.fragment_container`コンテナーに`fragmentフラグメントを`追加する
        }.commitNow()
    }

    fun presentFragment(fragment: Fragment) {
        val screen = fragment as? Screen
        val isMenuVisibled = screen?.isMenuVisibled ?: false

        val fm = this.supportFragmentManager
        fm.beginTransaction().apply {
            setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

}
