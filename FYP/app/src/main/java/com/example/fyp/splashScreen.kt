package com.example.fyp

import android.app.ActivityOptions
import android.app.TaskStackBuilder.create
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Pair.create
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.net.URI.create
import android.util.*;


class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()
        setContentView(R.layout.activity_splash_screen)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        var image = findViewById<ImageView>(R.id.imageView)
        var logo = findViewById<TextView>(R.id.textView);
        var slogan = findViewById<TextView>(R.id.textView2)

        //Animations
        var topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        var bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        Handler(Looper.getMainLooper()).postDelayed({
            var intent: Intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }, 5000)

        Handler().postDelayed({
            // Call next screen
            val intent = Intent(this@splashScreen, BaseLogin::class.java)
            // Attach all the elements those you want to animate in design
            val pairs = arrayOf<Pair<View, String>>(
                Pair.create(image, "logo_image"),
                Pair.create(logo, "logo_text")
            )
            // Wrap the call in API level 21 or higher
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val options = ActivityOptions.makeSceneTransitionAnimation(this@splashScreen, *pairs)
                startActivity(intent, options.toBundle())
            }
        }, 5000)

    }
}