package io.github.jfyoteau.androidtutorial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import io.github.jfyoteau.androidtutorial.databinding.MainActivityBinding

open class MainActivity : BaseActivity() {

    lateinit var dataBinding: MainActivityBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.dataBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        // モデルを定義する
        this.viewModel = MainViewModel(this)

        this.dataBinding.viewModel = this.viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("AndroidTutorial", "requestCode: $requestCode - resultCode: $resultCode")
    }

    fun startOtherActivity() {
        val intent = Intent(this, OtherActivity::class.java)
        startActivity(intent)
    }

    fun startActivityWithResult() {
        val intent = Intent(this, ResultActivity::class.java)
        startActivityForResult(intent, 1)
    }

    fun startFragmentActivity() {
        // MyFragmentActivityアクティブティを呼び出します
        val intent = Intent(this, MyFragmentActivity::class.java)
        startActivity(intent)
    }

}
