package timrapp.breweries.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timrapp.breweries.R
import timrapp.breweries.data.Brewery
import timrapp.breweries.data.BreweryApiService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var recyclerView: RecyclerView
    private lateinit var job: Job
    private var breweryList = ArrayList<Brewery>()
    private val breweryService = BreweryApiService.get()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = Job()

        recyclerView = findViewById(R.id.recyclerView)

        val manager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        launch {
            getData()
            recyclerView.adapter = BreweryAdapter(breweryList)
        }
    }

    private suspend fun getData() {
        withContext(Dispatchers.Default) {
            val list = breweryService.breweryList()
            breweryList.addAll(list)
        }
    }
}

