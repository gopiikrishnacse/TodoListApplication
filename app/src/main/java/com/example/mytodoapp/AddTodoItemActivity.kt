package com.example.mytodoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mytodoapp.db.DatabaseBuilder
import com.example.mytodoapp.db.DbHelperImpl
import com.example.mytodoapp.db.entity.TodoItem
import kotlinx.android.synthetic.main.activity_add_todo_item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddTodoItemActivity : AppCompatActivity() {

    companion object{
        private val categories = listOf("Android", "Kotlin","Tech","General")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo_item)
        setupView()
    }

    private fun setupView() {
        spinnerCategory.adapter = ArrayAdapter<String>(this,android. R.layout.simple_spinner_item,
            categories).apply {
            setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        }
        btnAddTodo.setOnClickListener {
            val item = TodoItem(name = edName.text.toString(),description = edDescription.text.toString(),category = spinnerCategory.selectedItem.toString())
            val dbHelper = DbHelperImpl(DatabaseBuilder.getInstance(this))
            //get todoitem list using coroutine lifecycle scope
            lifecycleScope.launch {
                try{
                    withContext(Dispatchers.IO) {
                        dbHelper.insertTodoItem(item)
                    }
                        val intent = Intent()
                        intent.putExtra("added", true)
                        setResult(Activity.RESULT_OK, intent)
                        finish()

                }catch ( exception : Exception){
                    Log.d(MainActivity.TAG, exception.localizedMessage)
                }
            }

        }
    }
}