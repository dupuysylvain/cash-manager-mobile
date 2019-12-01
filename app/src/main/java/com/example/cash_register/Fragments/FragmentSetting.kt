package com.example.cash_register.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.cash_register.Constants
import com.example.cash_register.Prefs
import com.example.cash_register.R
import com.example.cash_register.view.SignUpActivity

class FragmentSetting : Fragment() {

    private var apiUrl: String = ""
    private lateinit var textEdit: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fragment_setting, container, false)

        textEdit = view.findViewById(R.id.serverUrl)

        textEdit.setText(Prefs.getApiUrl(this.requireContext()))

        val buttonSave = view.findViewById<Button>(R.id.saveUrl)

        buttonSave.setOnClickListener {
            saveUrl()
        }

        return view
    }

    private fun saveUrl() {
        Prefs.setString(this.requireContext(), Constants.SHARED_PREFS, Constants.API_URL, textEdit.text.toString())
        Toast.makeText(
            this.requireContext(),
            "Sauvegarde effectuée avec succès.",
            Toast.LENGTH_SHORT
        ).show()
    }
}
