# RecyclerView ビュー

`RecyclerView` ビューがビューの一覧を表示します。

## dependencies

```groovy
def version = ''
implementation "androidx.recyclerview:recyclerview:$version"
```

## 明細

Recyclerviewは3つの部分で構成します。

- ビュー： `RecyclerView` (クラス)
- レイアウトマネージャー： `RecyclerView.LayoutManager` (abstractクラス)  
一覧のアイテムの表示の順番を管理します。  
`RecyclerView`ライブラリがデフォルトで３つのレイアウトマネージャーを定義します。
    - `androidx.recyclerview.widget.LinearLayoutManager`： よこ一覧か立って一覧の順番でアイテムを表示します。
    - `androidx.recyclerview.widget.GridLayoutManager`：グリッドの順でアイテムを表示します。
    - `androidx.recyclerview.widget.StaggeredGridLayoutManager`：グリッドと同じが、アイテムのサイズが違うできる。

- アダプター： `RecyclerView.Adapter` (abstractクラス)  
データのアダプタです。

## RecyclerView.Adapter<VH: RecyclerView.ViewHolder>

アダプタはデータタイプから`ビューホルダー`オブジェクトを作成し、データからビューホルダーをバインドします。

下記の関数を実装します。

- `abstract fun getItemCount(): Int`  
表示するデータ一覧の件数を戻す
- `open fun getItemViewType(position: Int): Int`  
データからビュータイプを戻す。  
※アイテムの一覧が同じの場合はこの関数を実装が不要です。
- `@NonNull abstract fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): VH`  
`viewType`からビューホルダーを作成します。  
- `abstract fun onBindViewHolder(@NonNull holder: VH, position: Int): Unit`  
ビューホルダーをバインドする。  
この関数にデータからアイテムのビューを設定します。

### サンプル

```kotlin
import androidx.recyclerview.widget.RecyclerView

/**
 * アダプター
 *
 * @constructor コンストラクタ
 * @param myDataset データ一覧
 */
class MyAdapter(
    private val myDataset: List<String>
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    /**
     * ビューホルダー
     */
    class MyViewHolder(
        val textView: TextView
    ) : RecyclerView.ViewHolder

    override fun getItemCount() = this.myDataset.size

    /**
     * 新規ビューを作成する（レイアウトマネージャーに呼び出される）
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.MyViewHolder {
        // レイアウトから新規ビューを作成する
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_text_view, parent, false) as TextView
        
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    /**
     * ビューのコンテンツを変更する（レイアウトマネージャーに呼び出される）
     */
    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        // データからビューのテキストを変更する
        holder.textView.text = this.myDataset[position]
    }

}
```

## RecyclerViewの設定

レイアウト

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- A RecyclerView with some commonly used attributes -->
<android.support.v7.widget.RecyclerView
    android:id="@+id/my_recycler_view"
    android:scrollbars="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

アクティビティ

```kotlin
class MyActivity : Activity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_activity)

        // データ
        val myDataSet = listOf("ichi", "ni", "san", "yon")

        this.recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view)?.apply {
            // RecyclerViewのサイズが変わらない場合は下記のコードを使ってください
            setHasFixedSize(true)

            // レイアウトマネージャーを設定する
            layoutManager = LinearLayoutManager(this)

            // アダプターを設定する
            adapter = MyAdapter(myDataset)
        }
    }
    // ...
}
```
