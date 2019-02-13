# アクティビティの呼び出し

```kotlin
class Activity1 : AppCompatActivity() {

    // ...

    fun callActivity2() {
        val activity2Intent = Intent(this, Activity2::class.java)
        startActivity(activity2Intent)
    } 

}
```

# アクティビティの結果を管理する

## 結果のコードのみ

```kotlin

const val REQUEST_CODE: Int = 1

class Activity1 : AppCompatActivity() {

    // ...

    fun callActivity2ForResult() {
        val activity2Intent = Intent(this, Activity2::class.java)
        startActivityForResult(activity2Intent, REQUEST_CODE)
    } 

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d("Activity1", "result is ok")
            } else {
                // from back button of device
                Log.d("Activity1", "result is not ok")
            }
        }
    }    
}

class Activity2 : AppCompatActivity() {

    // ...

    fun sendResultToCaller() {
        // Set the "resultCode" value
        setResult(Activity.RESULT_OK)
        // Finish the activity
        finish()
    }

}
```

## 結果コードとデータを戻す

```kotlin

const val REQUEST_CODE: Int = 1

class Activity1 : AppCompatActivity() {

    // ...

    fun callActivity2ForResult() {
        val activity2Intent = Intent(this, Activity2::class.java)
        startActivityForResult(activity2Intent, REQUEST_CODE)
    } 

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra("result") ?: ""
                Log.d("Activity1", "result is ok - result: $result")
            } else {
                // from back button of device
                Log.d("Activity1", "result is not ok")
            }
        }
    }    
}

class Activity2 : AppCompatActivity() {

    // ...

    fun sendResultToCallerWithData() {
        val data = Intent()
        data.putString("result", "bonjour")
        // Set the "resultCode" value and data
        setResult(Activity.RESULT_OK, data)
        // Finish the activity
        finish()
    }

}
```



# アクティビティへパラメタを渡す

```kotlin
class Activity1 : AppCompatActivity() {

    // ...

    fun sendParameterToActivity2() {
        val intent = Intent(this, Activity2::class.java)
        intent.putExtra("text", "test")
        startActivity(intent)
    }

}

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // ...

        // Read parameter
        val extras = this.intent.extras ?: Bundle()
        val text = extras.getString("text", "")
        Log.d("Activity2", "text: $text")
    }

}
```

# 外部アクティビティの呼び出し

アプリケーションの設定画面へ移動します。
```kotlin
class Activity1 : AppCompatActivity() {

    // ...

    fun callApplicationSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", this.packageName, null)
        intent.data = uri
        startActivity(intent)
    }

}
```
