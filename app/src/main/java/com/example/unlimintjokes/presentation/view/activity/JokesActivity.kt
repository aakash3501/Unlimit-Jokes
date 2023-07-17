package com.example.unlimintjokes.presentation.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unlimintjokes.databinding.ActivityJokesBinding
import com.example.unlimintjokes.domain.model.JokeModel
import com.example.unlimintjokes.presentation.adapter.JokesAdapter
import com.example.unlimintjokes.presentation.viewmodel.JokesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokesActivity : AppCompatActivity() {
    lateinit var binding: ActivityJokesBinding
    private val viewModel: JokesViewModel by viewModel()
    private lateinit var adapter: JokesAdapter
    private val jokeList: MutableList<JokeModel> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        observeLiveData()
        binding.run {
            adapter = JokesAdapter(jokeList)
            rvJokes.adapter = adapter
        }
        viewModel.getSavedJokes()
    }

    private fun observeLiveData() {
        viewModel.jokeLiveData.observe(this) { joke ->
            if (jokeList.size == 10) {
                jokeList.removeAt(0)
                adapter.notifyItemRemoved(0)
            }
            joke?.let {
                jokeList.add(it)
                adapter.notifyItemInserted(jokeList.size - 1)
            }
        }

        viewModel.jokeListLiveData.observe(this) { jokes ->
            jokeList.clear()
            jokeList.addAll(jokes)
            adapter.notifyDataSetChanged()
        }

        viewModel.errorMsgLiveData.observe(this) { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.startFetchingJokes()
    }

    override fun onStop() {
        viewModel.stopFetchingJokes()
        viewModel.saveJokesInDB(jokeList)
        super.onStop()
    }
}
