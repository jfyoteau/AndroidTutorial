package io.github.jfyoteau.androidtutorial.room.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.jfyoteau.androidtutorial.R
import io.github.jfyoteau.androidtutorial.databinding.RoomFragmentBinding
import io.github.jfyoteau.androidtutorial.room.data.database.DatabaseFactory
import io.github.jfyoteau.androidtutorial.room.data.repository.DefaultWordRepository
import io.github.jfyoteau.androidtutorial.room.domain.DefaultWordDomain

class RoomFragment : Fragment() {

    private lateinit var viewDataBinding: RoomFragmentBinding

    private val viewModel by viewModels<RoomViewModel> {
        getViewModelFactory()
    }

    private lateinit var listAdapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.viewDataBinding = RoomFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = this@RoomFragment.viewModel
        }
        return this.viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // LiveDataを管理する為
        this.viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        // RecyclerViewを設定する
        setupListAdapter()
    }

    private fun getViewModelFactory(): ViewModelFactory {
        val db = DatabaseFactory.getDatabase(this.requireContext())
        val dao = db.wordDao()
        val repository = DefaultWordRepository(dao)
        val domain = DefaultWordDomain(repository)
        return ViewModelFactory(domain)
    }

    private fun setupListAdapter() {
        val viewModel = this.viewDataBinding.viewModel ?: return

        this.listAdapter = WordAdapter(viewModel)
        this.viewDataBinding.wordList.adapter = this.listAdapter
    }

}
