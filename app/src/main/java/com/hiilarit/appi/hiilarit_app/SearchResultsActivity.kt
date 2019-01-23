package com.hiilarit.appi.hiilarit_app

import android.app.SearchManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class SearchResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)
        handleIntent(intent)

    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val queue = Volley.newRequestQueue(this)
            val query = intent.getStringExtra(SearchManager.QUERY)
            val url = "https://fineli.fi/fineli/api/v1/foods?q="
            val textView = findViewById(R.id.textView2) as TextView

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                "$url$query",
                null,
                Response.Listener { response ->
                    textView.text = "Response: %s".format("Success!")
                    // Never goes here for unknown reason
                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                    textView.text = "Response: %s".format(error.message)
                    // atm error message gives response
                }
            )
            queue.add(jsonObjectRequest)

        }
    }
}
