package io.github.jfyoteau.androidtutorial.recyclerview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

}
