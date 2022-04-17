package com.example.recyclerviewsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.example.recyclerviewsample.common.MainNavigator
import com.example.recyclerviewsample.databinding.ActivityMainBinding
import com.example.recyclerviewsample.ui.filesList.FilesListFragment

class MainActivity : AppCompatActivity(), MainNavigator {

    private lateinit var binding: ActivityMainBinding

    /**
     * Контейнер, в котором открываются все корневые контейнеры
     */
    private lateinit var fragmentsContainer: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        fragmentsContainer = binding.fragmentConatinier
        setContentView(binding.root)
        openFilesListFragment()
    }

    override fun openFilesListFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(fragmentsContainer.id, FilesListFragment()).commit()
        }
    }
}
