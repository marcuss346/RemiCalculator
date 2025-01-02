package com.example.remicalculator.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remicalculator.Data.Translation
import com.example.remicalculator.network.Api
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class RemiCalculatorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RemiCalculatorUIState())
    val uiState: StateFlow<RemiCalculatorUIState> = _uiState.asStateFlow()

    init {
        resetRemiCalc()
    }

    private fun resetRemiCalc() {
        _uiState.value = RemiCalculatorUIState()
    }

    fun updateTextLanguage(){
        viewModelScope.launch {
            try {
                val translation = Translation("auto", "en", _uiState.value.rulesSlovenian)
                val newQuote = Api.retrofitService.getTranslation(translation)
                _uiState.update {
                    currentState ->
                    newQuote.body()?.let { currentState.copy(rulesSlovenian = it.trans) }!!
                }
                Log.i("API", "Quote retrieved successfully + $newQuote")
            } catch (e: IOException) {
                Log.e("API", "Error retrieving the quote - IO Exception: " + e.message)
            } catch (e: HttpException) {
                Log.e("API", "Error retrieving the quote - HTTP Exception: " + e.message)
            }


        }
    }



}