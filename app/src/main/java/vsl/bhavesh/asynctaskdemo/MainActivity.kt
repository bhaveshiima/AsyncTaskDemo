package vsl.bhavesh.asynctaskdemo

import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.net.URI
import java.net.URL
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Runtime Permission [ START ]
        var status = ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.INTERNET)

        // Permission not granted
        if (status==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.INTERNET),0)
        }
        // Runtime Permission [ END ]



        b1.setOnClickListener {

            var task = MyTask(iview1) //call the MyTask Funtion with input paramenter
            task.execute() //execute AsyncTask

        }



        // AsyncTasK Policy - to provide Information to Activity Thread [ START ]
        var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        // AsyncTasK Policy - to provide Information to Activity Thread [ END ]

    } //onCreate


    // Create Network class for AsyncTask [ START ]
    // image view as a constuctor
    class MyTask(var iview1:ImageView):AsyncTask<Unit,Unit,Unit>(){

        var isr:InputStream? = null //define as a global

        // InBackground Method [ START ]
        override fun doInBackground(vararg p0: Unit?) {

            var url = URL("http://i1.wp.com/corporateethos.com/wp-content/uploads/2016/09/Vikram-Sarabhai-Library.jpg?fit=700%2C409")
            var isr = url.openStream() // OpenStream

        }
        // InBackground Method [ END ]

        // Post Execution [ START ]
        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)

            var bmp = BitmapFactory.decodeStream(isr) // convert input stream into bitmap object
            iview1.setImageBitmap(bmp) //set bitmap image as a input parameter of this.

        }
        // Post Execution [ END ]

    }
    // Create Network class for AsyncTask [ START ]


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults[0] == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this,"You cannot access..OOPS..!Permission Deny",Toast.LENGTH_LONG).show()
          }

    }


}// Main Activity
