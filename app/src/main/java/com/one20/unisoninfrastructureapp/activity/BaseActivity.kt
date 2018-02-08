package com.one20.unisoninfrastructureapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.one20.unisoninfrastructure.logging.UnisonFileLogger
import com.one20.unisoninfrastructure.logging.UnisonLogger
import com.one20.unisoninfrastructureapp.R
import kotlinx.android.synthetic.main.base_activity.*
import java.util.*

/**
 * Created by ryanmord on 1/30/18.
 */
class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)

        button.setOnClickListener {
            val dir = filesDir.toString() + "/" + Calendar.getInstance().timeInMillis + "_keepalives.txt"
            var v = UnisonFileLogger(dir)

            var i = 0
            while(i < 10) {
                i ++
                v.log(UnisonLogger.LEVEL_DEBUG, "Hello")
            }
        }
    }
}