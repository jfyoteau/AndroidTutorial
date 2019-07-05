package io.github.jfyoteau.androidtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.github.jfyoteau.androidtutorial.fragment.MenuFragment
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
        val isMenuVisibleRequested = screen?.isBottomMenuVisible ?: false

        val fm = this.supportFragmentManager

        // 現在のメニューの表示の状態を取得する
        val fragmentInMenuContainer = fm.findFragmentById(R.id.menu_container)
        val isMenuVisible = fragmentInMenuContainer?.isVisible ?: false

        fm.beginTransaction().apply {
            setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            replace(R.id.fragment_container, fragment)

            // メニューを追加・削除する
            if (!isMenuVisibleRequested && isMenuVisible) {
                // メニューを削除する
                fragmentInMenuContainer?.let {
                    remove(it)
                }
            } else if (isMenuVisibleRequested && !isMenuVisible) {
                // メニューを表示する
                val menuFragment = fragmentInMenuContainer ?: MenuFragment()
                add(R.id.menu_container, menuFragment)
            }

            addToBackStack(null)
        }.commit()
    }

}
