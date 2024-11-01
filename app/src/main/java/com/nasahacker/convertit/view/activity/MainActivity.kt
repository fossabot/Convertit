package com.nasahacker.convertit.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.nasahacker.convertit.R
import com.nasahacker.convertit.databinding.ActivityMainBinding
import com.nasahacker.convertit.util.AppUtils.handleNotificationPermission
import com.nasahacker.convertit.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
                        or WindowInsetsCompat.Type.displayCutout()
            )
            v.updatePadding(
                left = bars.left,
                top = bars.top,
                right = bars.right,
                bottom = bars.bottom,
            )
            WindowInsetsCompat.CONSUMED
        }

        setupNavigation()
        handleNotificationPermission(this)
        setupToolbarMenu()

        // Observe ViewModel for update status messages
        mainViewModel.updateStatus.observe(this) { message ->
            Snackbar.make(binding.main, message, Snackbar.LENGTH_SHORT).show()
        }

        mainViewModel.showRestartSnackbar.observe(this) { show ->
            if (show) {
                Snackbar.make(binding.main, "Update downloaded. Restart to complete.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Restart") {
                        mainViewModel.completeUpdate()
                    }
                    .show()
            }
        }

        // Trigger update check
        mainViewModel.checkForUpdate(activityResultLauncher)
    }

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result: ActivityResult ->
            val message = if (result.resultCode == RESULT_OK) {
                "Update completed successfully!"
            } else {
                "Update canceled or failed. Try again later."
            }
            Snackbar.make(binding.main, message, Snackbar.LENGTH_SHORT).show()
        }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        navHostFragment?.navController?.let { navController ->
            binding.bottomNav.setupWithNavController(navController)
        }
    }

    private fun setupToolbarMenu() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.about -> startActivity(Intent(application, AboutActivity::class.java))
            }
            true
        }
    }
}
