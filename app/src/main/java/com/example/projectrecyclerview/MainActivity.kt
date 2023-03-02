package com.example.projectrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFood: RecyclerView
    private var list: ArrayList<Food> = arrayListOf()
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFood = findViewById(R.id.rv_hero)
        rvFood.setHasFixedSize(true)

        list.addAll(FoodData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvFood.layoutManager = LinearLayoutManager(this)
        val listFoodAdapter = ListFoodAdapter(list)
        rvFood.adapter = listFoodAdapter

        listFoodAdapter.setOnItemClickCallBack(object : ListFoodAdapter.OnItemCLickCallBack {
            override fun onItemClicked(data: Food) {
                showSelectedFood(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return  super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                showRecyclerList()
                title = "Mode List"
            }
            R.id.action_grid -> {
                showRecyclerGrid()
                title = "Mode Grid"
            }
            R.id.action_cardview -> {
                showReCyclerCardView()
                title = "Mode CardView"
            }
        }
    }

    private fun showRecyclerGrid() {
        rvFood.layoutManager = GridLayoutManager(this, 2)
        val gridFoodAdapter = GridFoodAdapter(list)
        rvFood.adapter = gridFoodAdapter

        gridFoodAdapter.setOnItemCLickCallBack(object : GridFoodAdapter.OnItemCLickCallBack {
            override fun onItemCLicked(food: Food) {
                showSelectedFood(food)
            }
        })
    }

    private fun showReCyclerCardView() {
        rvFood.layoutManager = LinearLayoutManager(this)
        val cardViewFoodAdapter = CardViewFoodAdapter(list)
        rvFood.adapter = cardViewFoodAdapter
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }

    private fun showSelectedFood(food: Food) {
        Toast.makeText(this, "Kamu memilih " + food.nama, Toast.LENGTH_SHORT).show()
    }
}
