package com.devvailonge.flip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun updateState(state: CategoryListState){
        when(state){
            is CategoryListState.CategoryList -> {
                state.list
            }
            is CategoryListState.ErrorMessage -> {
                state.message
            }
            is CategoryListState.Loading -> {
                state.isLoading
            }
            CategoryListState.Empty -> {

            }
        }
    }

}

sealed class CategoryListState {

    data class CategoryList(val list: List<String>) : CategoryListState()
    data class ErrorMessage(val message : String) : CategoryListState()
    data class Loading(val isLoading : Boolean) : CategoryListState()
    object Empty : CategoryListState()
}

