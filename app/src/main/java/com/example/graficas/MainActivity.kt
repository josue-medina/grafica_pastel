package com.example.graficas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.PieChart
import com.example.graficas.databinding.ActivityMainBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var myChart: PieChart = binding.pieChart1
        var dataList: ArrayList<PieEntry> = arrayListOf()

        for (i in 0 until 5)
            dataList.add(PieEntry((Math.random() * 5 + 5).toFloat()))

        var dataSet = PieDataSet(dataList,"resultados")

        //colores para la grafica
        val colors: ArrayList<Int> = ArrayList()
        for (c in ColorTemplate.VORDIPLOM_COLORS){
            colors.add(c)
        }
        dataSet.colors = colors

        var data= PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(20f)
        data.setValueTextColor(Color.BLACK)
        myChart.setData(data)

        binding.btnUpdate.setOnClickListener{
            dataList.clear()

            val texto = binding.etValues.text
            val test = Arrays.asList(texto.split(","))
            for (value in test) {
                for(number in value){
                    dataList.add(PieEntry(number.toFloat()))
                }


            }
            dataSet = PieDataSet(dataList,"resultados")
            dataSet.colors = colors
            data = PieData(dataSet)
            //Para que no se ajuste numeros al actualizar
            data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(20f)
            data.setValueTextColor(Color.BLACK)
            myChart.setData(data)
            setContentView(view)
        }


    }
}