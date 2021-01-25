package com.kryptkode.droidarch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kryptkode.commonandroid.viewbinding.viewBinding
import com.kryptkode.core.remote.mediator.ApiServiceType
import com.kryptkode.droidarch.databinding.ActivitySelectionBinding

class SelectionActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySelectionBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mockBtn.setOnClickListener {
            ApiServiceType.useMock = true
            startActivity(MainActivity.createIntent(this))
        }

        binding.realBtn.setOnClickListener {
            ApiServiceType.useMock = false
            startActivity(MainActivity.createIntent(this))
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SelectionActivity::class.java)
        }
    }
}
