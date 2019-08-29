package io.github.jfyoteau.androidtutorial.recyclerview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.jfyoteau.androidtutorial.databinding.RecyclerViewFragmentBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class RecyclerViewFragment : Fragment() {

    private lateinit var binding: RecyclerViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        this.binding = RecyclerViewFragmentBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // データを定義する
        val data: MutableList<String> = ArrayList(50)
        for (i in 1..50) {
            data.add("item $i")
        }

        // アダプタを作成する
        val myAdapter = MyRecyclerViewAdapter(data)

        binding.recyclerview.apply {
            // RecyclerViewのレイアウトを設定する
            layoutManager = LinearLayoutManager(this@RecyclerViewFragment.requireContext())

            // RecyclerViewのアダプターを設定する
            adapter = myAdapter
        }
    }

}
