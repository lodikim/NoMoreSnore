package com.example.pj4test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pj4test.ProjectConfiguration
import com.example.pj4test.audioInference.SnapClassifier
import com.example.pj4test.databinding.FragmentAudioBinding

// packages for alarm ring
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.pj4test.StopAlarm
import com.example.pj4test.R

class AudioFragment: Fragment(), SnapClassifier.DetectorListener {
    private val TAG = "AudioFragment"

    private var _fragmentAudioBinding: FragmentAudioBinding? = null

    private val fragmentAudioBinding
        get() = _fragmentAudioBinding!!

    // classifiers
    lateinit var snapClassifier: SnapClassifier

    // views
    lateinit var snapView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentAudioBinding = FragmentAudioBinding.inflate(inflater, container, false)

        return fragmentAudioBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snapView = fragmentAudioBinding.SnapView

        snapClassifier = SnapClassifier()
        snapClassifier.initialize(requireContext())
        snapClassifier.setDetectorListener(this)
    }

    override fun onPause() {
        super.onPause()
        snapClassifier.stopInferencing()
    }

    override fun onResume() {
        super.onResume()
        snapClassifier.startInferencing()
    }

    var mediaPlayer: MediaPlayer? = null
    var isAlarmRinging: Boolean = false
    //var personDetected: Boolean = false
    private val viewModel: StopAlarm by activityViewModels()
    var personDetected : Boolean = false

    override fun onResults(score: Float) {
        activity?.runOnUiThread {
            if (score > SnapClassifier.THRESHOLD) {
                Log.d(TAG, "Snoring Detected")
                snapView.text = "SNORING!"       /**snoring Txt*/
                snapView.setBackgroundColor(ProjectConfiguration.activeBackgroundColor)
                snapView.setTextColor(ProjectConfiguration.activeTextColor)

                // Call the function to make the alarm ring
                makeAlarmRing()
                viewModel.stopAlarm(false)
            } else {
                snapView.text = "NO SNORING"   /** No snoring Txt*/
                snapView.setBackgroundColor(ProjectConfiguration.idleBackgroundColor)
                snapView.setTextColor(ProjectConfiguration.idleTextColor)

                // Call the function to make the alarm stop ringing
                viewModel.stopAlarm.observe(viewLifecycleOwner, Observer { newBool ->
                    personDetected = newBool
                })
                if (personDetected == true){
                    Log.d(TAG, "variable personDetected : true")
                    isAlarmRinging = false
                    mediaPlayer?.release() // Stop the alarm by releasing the MediaPlayer
                    mediaPlayer = null
                }
                else{
                    Log.d(TAG, "variable personDetected : false")
                }
            }
        }
    }

    private fun makeAlarmRing() {
        Log.d(TAG, "Enters makeAlarmRing()")

        if (!isAlarmRinging) {
            isAlarmRinging = true
            mediaPlayer?.release() // Release any previously allocated MediaPlayer

            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.alarm_sound)
            mediaPlayer?.isLooping = true // Set looping to true

            mediaPlayer?.setOnCompletionListener {
                // The media file has finished playing
                // Restart the playback
                mediaPlayer?.start()
            }

            mediaPlayer?.start()

            // You can also show a toast message to indicate that the alarm is ringing
            Toast.makeText(requireContext(), "Alarm is ringing!", Toast.LENGTH_SHORT)
        }
    }
}