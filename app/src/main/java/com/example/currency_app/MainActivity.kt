package com.example.currency_app

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun get(view:View){
        val download =Download()
        try {
            val url = "http://data.fixer.io/api/latest?access_key=f22d7d7fb4302ec16e7e9649a28d3e99&symbols="
            val baser=editText.text.toString()

            download.execute(url+baser)


        }

        catch (e: Exception){
            e.printStackTrace()
        }

    }
    inner class Download: AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg p0: String?): String {

            var result=" "
            var url: URL
            var httpURLConnection: HttpURLConnection
            try {
                url = URL(p0[0])
                httpURLConnection = url.openConnection() as HttpURLConnection
                val inputStream = httpURLConnection.inputStream
                val inputStreamReader = InputStreamReader(inputStream)
                var data=inputStreamReader.read()
                while(data>0){
                    var charecter=data.toChar()
                    data=inputStreamReader.read()


                }
                return result
            }
            catch (e:Exception){
                e.printStackTrace()
                return result
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonobject = JSONObject(result)
                println(jsonobject)
                val base=jsonobject.getString("base")
                println(base)
                val rates=jsonobject.getString("rates")

                val newjson=JSONObject(rates)

                val chf=newjson.getString("CHF")
                val cad=newjson.getString("CAD")
                val aud=newjson.getString("AUD")

                println(chf)

                chftext.text="CHF:"+chf
                cadview.text="CAD:"+cad
                audview.text="AUD:"+aud





            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
    }


}
