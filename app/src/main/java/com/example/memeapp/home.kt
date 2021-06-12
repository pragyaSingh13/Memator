package com.example.memeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class home() : AppCompatActivity(){
    val url = "https://meme-api.herokuapp.com/gimme"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val share:Button  = findViewById(R.id.button)
        val nextbtn:Button  = findViewById(R.id.button2)

        val builder = AlertDialog.Builder(this)
        builder.setTitle(" 18+ WARNING")
        builder.setMessage("This contains some memes which might not be suitable for minors")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->

        }


        builder.show()

        loadMeme()

    }
    private fun loadMeme(){// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        val iv:ImageView = findViewById(R.id.memeimage)


        val jsonobject = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener{ response ->

                    val url = response.getString("url")

                    Glide.with(this).load(url).into(iv)
                },
                Response.ErrorListener {
                    Toast.makeText(this,"Please check your internet connection",Toast.LENGTH_SHORT)
                })


        queue.add(jsonobject)
    }


    fun shareMeme(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Check out this meme LOL -> $url")
        val chooser = Intent.createChooser(intent, "Share via...")
        startActivity(chooser)

    }


    fun nextMeme(view: View) {
        loadMeme()
    }

}