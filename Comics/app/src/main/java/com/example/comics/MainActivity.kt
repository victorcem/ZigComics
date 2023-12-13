package com.example.comics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.comics.databinding.ActivityMainBinding
import com.example.comics.ui.ZigComicFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ZigComicFragment())
                .commitNow()
        }
    }
}