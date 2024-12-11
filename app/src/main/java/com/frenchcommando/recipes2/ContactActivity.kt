package com.frenchcommando.recipes2

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ContactActivity : AppCompatActivity() {
        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_contact)

            val toolbar: Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
            collapsingToolbar.title = "Contact Me"

            val textInputLayout: TextInputLayout = findViewById(R.id.contact_message_layout)
            textInputLayout.setEndIconOnClickListener { submit() }

            val textInput: TextInputEditText = findViewById(R.id.contact_message)
            textInput.setOnEditorActionListener{ _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEND -> {
                        submit()
                        true
                    }
                    else -> false
                }
            }
            textInput.requestFocus()
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }

    private fun submit(){
        submitHandler()
        Toast.makeText(applicationContext, "MessageSent", Toast.LENGTH_SHORT).show()
        val textInput: TextInputEditText = findViewById(R.id.contact_message)
        textInput.onEditorAction(EditorInfo.IME_ACTION_DONE)
    }

    private fun submitHandler() {
        val messageView: EditText = findViewById(R.id.contact_message)
        val message = messageView.text

        val queue = Volley.newRequestQueue(this)

        val urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s"
        val text = "recipesAndroidMessage:%0A$message"

        val url = String.format(
            urlString, getString(R.string.telegram_token), getString(R.string.telegram_chat), text
        )

        val stringRequest = StringRequest(Request.Method.GET, url, {}, {})
        queue.add(stringRequest)
    }
}