package com.example.mytodoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytodoapp.db.DatabaseBuilder
import com.example.mytodoapp.db.DbHelper
import com.example.mytodoapp.db.DbHelperImpl
import com.example.mytodoapp.model.TodoItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    companion object {
        const val ADD_TODO_REQUEST_CODE = 100
        const val TAG = "MainActivity"
    }

    lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupView()
        fabAdd.setOnClickListener {
            startActivityForResult(
                Intent(this@MainActivity, AddTodoItemActivity::class.java),
                ADD_TODO_REQUEST_CODE
            )
        }
    }

    private fun setupView() {
        itemAdapter = ItemAdapter(mutableListOf())
        rvTodoItems.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
//            val items = mutableListOf<com.example.mytodoapp.db.entity.TodoItem>(
//                TodoItem("test1", "Want to Explore Kotlin", 1, "Android"),
//                TodoItem("test2", "Want to Explore Kotlin", 1, "Android"),
//                TodoItem("test3", "Want to Explore Kotlin", 1, "Android"),
//                TodoItem("test4", "Want to Explore Kotlin", 1, "Android")
//            )
            adapter = itemAdapter

        }
        loadTodoItems()
    }

    private fun loadTodoItems(){
        val dbHelper = DbHelperImpl(DatabaseBuilder.getInstance(this))
        //get todoitem list using coroutine lifecycle scope
        lifecycleScope.launch {
            try{
                withContext(Dispatchers.IO) {
                     val items = dbHelper.getTodoItems()
                    itemAdapter.updateItems(items)
                }

            }catch ( exception : Exception){
                Log.d(TAG, exception.localizedMessage)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {


            if (requestCode == ADD_TODO_REQUEST_CODE) {
                loadTodoItems()
                Toast.makeText(applicationContext, "Note Added", Toast.LENGTH_SHORT).show()
            }


        }
    }
    }