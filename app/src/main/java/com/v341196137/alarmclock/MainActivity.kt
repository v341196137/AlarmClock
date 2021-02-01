package com.v341196137.alarmclock
// Kotlin libraries

// Android libraries
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import java.util.LinkedList

// do most of our work in here
// main hub for changing layouts and views and stuff
// buttononclick events to change layouts when clicked on
class MainActivity : AppCompatActivity() {
    private var stopwatchRunning: Boolean = false
    private var stopwatchInitiated: Boolean = false
    private var timeDifference: Long = 0
    private var lastTime: Long = 0
    //private lateinit var stopwatch: Chronometer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variables
        var alarmList = LinkedList<AlarmData>()

        initiateAlarmViewButtons()

    }

    /**
     * Hi Sherwin you should javadoc this :P
     * @return something something
     */
    private fun generateList() : LinkedList<AlarmData> {
        var alarmList = LinkedList<AlarmData>()
        alarmList.add(AlarmData("12:00")) // change "12:00" to string where time is specified

        return alarmList
    }

    /**
     * Sets the context view to the passed in view
     * @param view the Int ID for the view
     */
    private fun switchToView(view: Int){
        setContentView(view)
    }

    /**
     * Initiates the three buttons at the bottom necessary for navigating
     */
    private fun initiateNavButtons(){
        val alarmButton: Button = findViewById(R.id.alarm_button)
        val timerButton: Button = findViewById(R.id.timer_button)
        val stopwatchButton: Button = findViewById(R.id.stopwatch_button)
        //button listeners
        // TODO: figure out if to use .setContentView or .addView
        // Button listeners on acitivty_main.xml
        alarmButton.setOnClickListener() {
            Toast.makeText(this, "alarm button pressed", Toast.LENGTH_SHORT).show()
            switchToView(R.layout.activity_main)
            initiateAlarmViewButtons()
        } //currently operating under the assumption main view is alarm view
        timerButton.setOnClickListener() {
            Toast.makeText(this, "timer button pressed", Toast.LENGTH_SHORT).show()
            switchToView(R.layout.timer_view)
            initiateTimerViewButtons()
        }
        stopwatchButton.setOnClickListener() {
            Toast.makeText(this, "stopwatch button pressed", Toast.LENGTH_SHORT).show()
            switchToView(R.layout.stopwatch_view)
            initiateStopwatch()
        }
    }

    /**
     * Does all the button initializations necessary when switching to alarm view
     */
    private fun initiateAlarmViewButtons(){
        initiateNavButtons()
        val addAlarmButton: Button = findViewById(R.id.add_alarm_button)
        addAlarmButton.setOnClickListener(){
            Toast.makeText(this, "add alarm button pressed", Toast.LENGTH_SHORT).show()
            switchToView(R.layout.add_alarm_view)
            initiateAddAlarmButtons()
        }
    }

    /**
     * Does all button initialization necessary when switching to timer view
     */
    private fun initiateTimerViewButtons(){
        initiateNavButtons()
    }

    /**
     * Does all necessary button and task initialization for when switching to stopwatch view
     */
    private fun initiateStopwatch(){
        var stopwatch: Chronometer = findViewById(R.id.stopwatch_chronometer) //TODO: delete this later after figuring out how to make chronometer work properly
        if(!stopwatchRunning){
            stopwatch.base = SystemClock.elapsedRealtime() - timeDifference
        }else{
            stopwatch.base = SystemClock.elapsedRealtime() - lastTime
            stopwatch.start()
            stopwatchRunning = true
        }
        initiateNavButtons()
        val startButton: Button = findViewById(R.id.start_button)
        val stopButton: Button = findViewById(R.id.stop_button)
        val resetButton: Button = findViewById(R.id.reset_button)
        startButton.setOnClickListener(){
            if(!stopwatchRunning){
                stopwatch.base = SystemClock.elapsedRealtime() - timeDifference
                stopwatchRunning = true
                stopwatch.start()
            }
        }
        stopButton.setOnClickListener(){
            if(stopwatchRunning){
                timeDifference = SystemClock.elapsedRealtime() - stopwatch.base
                stopwatchRunning = false
                stopwatch.stop()
            }
        }
        resetButton.setOnClickListener(){
            timeDifference = 0
            lastTime = 0
            stopwatch.base = SystemClock.elapsedRealtime()
        }
        stopwatch.setOnChronometerTickListener {
            if(stopwatchRunning){
                lastTime = SystemClock.elapsedRealtime() - stopwatch.base
            }
        }
    }

    /**
     * Initiates the buttons for add alarm
     */
    private fun initiateAddAlarmButtons(){

    }

}