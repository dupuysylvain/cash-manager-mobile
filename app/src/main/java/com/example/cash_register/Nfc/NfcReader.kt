package com.example.cash_register.Nfc

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.example.cash_register.R
import kotlinx.android.synthetic.main.activity_nfc_reader.*

class NfcReader : AppCompatActivity(), NfcAdapter.ReaderCallback  {
    private var nfcAdapter: NfcAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc_reader)
        val manager = this.getSystemService(Context.NFC_SERVICE) as NfcManager
        val adapter = manager.defaultAdapter

        if (adapter != null && adapter.isEnabled) {

            nfcAdapter = NfcAdapter.getDefaultAdapter(this)

            av_from_code.setAnimation("nfc_payment2.json")
            av_from_code.playAnimation()
            av_from_code.loop(true)

        }else {
            startNfcSettingsActivity()
        }
    }

    public override fun onResume() {
        super.onResume()
        nfcAdapter?.enableReaderMode(this, this,
            NfcAdapter.FLAG_READER_NFC_A or
                    NfcAdapter.FLAG_READER_NFC_B or
                    NfcAdapter.FLAG_READER_NFC_F or
                    NfcAdapter.FLAG_READER_NFC_V or
                    NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null)
    }

    public override fun onPause() {
        super.onPause()
        nfcAdapter?.disableReaderMode(this)
    }

    override fun onTagDiscovered(tag: Tag?) {
        av_from_code.visibility = View.GONE;
        val isoDep = IsoDep.get(tag)

        isoDep.connect()
        //Utils.hexStringToByteArray("00A4040007A0000002471001"))
        runOnUiThread {
            textView.append("\nCard Response: " + Utils.toHex(isoDep.tag.id))
            isoDep.close()
        }
    }


    private fun startNfcSettingsActivity() {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
        } else {
            startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
        }
    }
}
