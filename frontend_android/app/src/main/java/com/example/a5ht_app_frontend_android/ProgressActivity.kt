package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.math.roundToInt
import kotlin.random.Random

// Clase para datos simulados
data class MockEntry(
    val dayIndex: Int,
    val mood: Int,
    val energy: Int,
    val stress: Int,
    val anxiety: Int
)

class ProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        setupNavigation()

        // 1. Generar Datos
        val last30Days = generateMockData()

        // 2. Calcular y Mostrar Promedios
        calculateAndShowAverages(last30Days)

        // 3. Pintar Gráfico
        val chart = findViewById<LineChart>(R.id.chartProgress)
        setupChart(chart, last30Days)
    }

    private fun generateMockData(): List<MockEntry> {
        val list = ArrayList<MockEntry>()
        for (i in 1..30) {
            list.add(
                MockEntry(
                    dayIndex = i,
                    mood = Random.nextInt(2, 6),
                    energy = Random.nextInt(2, 6),
                    stress = Random.nextInt(1, 4),
                    anxiety = Random.nextInt(1, 4)
                )
            )
        }
        return list
    }

    private fun calculateAndShowAverages(data: List<MockEntry>) {
        val avgMood = data.map { it.mood }.average()
        val avgEnergy = data.map { it.energy }.average()
        val avgStress = data.map { it.stress }.average()
        val avgAnxiety = data.map { it.anxiety }.average()

        // Referencias UI (Ahora incluimos los Value TextViews)
        val tvMoodEmoji = findViewById<TextView>(R.id.tvMoodEmoji)
        val tvMoodValue = findViewById<TextView>(R.id.tvMoodValue) // Nuevo
        val tvMoodLabel = findViewById<TextView>(R.id.tvMoodLabel)

        val tvEnergyEmoji = findViewById<TextView>(R.id.tvEnergyEmoji)
        val tvEnergyValue = findViewById<TextView>(R.id.tvEnergyValue) // Nuevo
        val tvEnergyLabel = findViewById<TextView>(R.id.tvEnergyLabel)

        val tvStressEmoji = findViewById<TextView>(R.id.tvStressEmoji)
        val tvStressValue = findViewById<TextView>(R.id.tvStressValue) // Nuevo
        val tvStressLabel = findViewById<TextView>(R.id.tvStressLabel)

        val tvAnxietyEmoji = findViewById<TextView>(R.id.tvAnxietyEmoji)
        val tvAnxietyValue = findViewById<TextView>(R.id.tvAnxietyValue) // Nuevo
        val tvAnxietyLabel = findViewById<TextView>(R.id.tvAnxietyLabel)

        // Actualizar UI
        updateCardUI(avgMood, tvMoodEmoji, tvMoodValue, tvMoodLabel, isPositive = true)
        updateCardUI(avgEnergy, tvEnergyEmoji, tvEnergyValue, tvEnergyLabel, isPositive = true)

        updateCardUI(avgStress, tvStressEmoji, tvStressValue, tvStressLabel, isPositive = false)
        updateCardUI(avgAnxiety, tvAnxietyEmoji, tvAnxietyValue, tvAnxietyLabel, isPositive = false)
    }

    private fun updateCardUI(average: Double, tvEmoji: TextView, tvValue: TextView, tvLabel: TextView, isPositive: Boolean) {
        val roundedValue = average.roundToInt().coerceIn(1, 5)

        // Formato numérico "3.8/5"
        val formattedText = String.format("%.1f/5", average)
        tvValue.text = formattedText

        if (isPositive) {
            when (roundedValue) {
                1 -> { tvEmoji.text = "😫"; tvLabel.text = "Bajo" }
                2 -> { tvEmoji.text = "🙁"; tvLabel.text = "Regular" }
                3 -> { tvEmoji.text = "😐"; tvLabel.text = "Neutral" }
                4 -> { tvEmoji.text = "🙂"; tvLabel.text = "Bien" }
                5 -> { tvEmoji.text = "😄"; tvLabel.text = "Excelente" }
            }
        } else {
            when (roundedValue) {
                1 -> { tvEmoji.text = "😌"; tvLabel.text = "Muy Bajo" }
                2 -> { tvEmoji.text = "🙂"; tvLabel.text = "Bajo" }
                3 -> { tvEmoji.text = "😐"; tvLabel.text = "Moderado" }
                4 -> { tvEmoji.text = "😰"; tvLabel.text = "Alto" }
                5 -> { tvEmoji.text = "🤯"; tvLabel.text = "Crítico" }
            }
        }
    }

    private fun setupChart(chart: LineChart, data: List<MockEntry>) {
        val entries = ArrayList<Entry>()
        data.forEachIndexed { index, mockEntry ->
            entries.add(Entry(index.toFloat(), mockEntry.mood.toFloat()))
        }

        val dataSet = LineDataSet(entries, "Ánimo")
        dataSet.color = Color.parseColor("#B5B5E8")
        dataSet.lineWidth = 2f
        dataSet.setDrawCircles(false)
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.setDrawFilled(true)
        dataSet.fillColor = Color.parseColor("#B5B5E8")
        dataSet.fillAlpha = 50
        dataSet.setDrawValues(false)

        val lineData = LineData(dataSet)
        chart.data = lineData

        chart.description.isEnabled = false
        chart.legend.isEnabled = false
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.xAxis.setDrawGridLines(false)
        chart.axisRight.isEnabled = false
        chart.axisLeft.axisMinimum = 1f
        chart.axisLeft.axisMaximum = 5f

        chart.invalidate()
    }

    private fun setupNavigation() {
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navResources = findViewById<LinearLayout>(R.id.navResources)
        val navSettings = findViewById<LinearLayout>(R.id.navSettings)

        navHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
        navResources.setOnClickListener {
            val intent = Intent(this, ResourcesActivity::class.java)
            startActivity(intent)
            finish()
        }
        navSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}