
package com.erby.casestudy // name ng library package ay erby.casestudy 
import android.app.Service //android.app-naglalaman ng high-level classes na maaaring matawag para sa pagbuo ng Android Application model
import android.content.Context // ito ay library package na kapag dineclare or tinawag, lahat ng attributes at classes ay pwede na din matawag at gamitin
import android.net.ConnectivityManager // ito ay library package na kapag dineclare or tinawag, lahat ng attributes at classes ay pwede na din matawag at gamitin
import android.net.NetworkInfo // ito ay library package na kapag dineclare or tinawag, lahat ng attributes at classes ay pwede na din matawag at gamitin

class Connection_Detection { // the name of this class is Connection_Detection 
    var context: Context? = null // in this class, nagdeclare tayo ng variable na ang pangalan ay 'context' 
    fun connect_detector(context:Context) { // katumbas ng public void ang 'fun',kaya fun ang gamit dito dahil ang gamit na programming language ay Kotlin
    // Kotlin is a programming language use in Java Virtual Machine na maaaring gamitin sa paggawa ng android applications. 'Public' means that anyone can have an access. 'Void' means no returning value                                       
        this.context = context // ang 'this' parameter ay ginagamit upang ma-clarify natin kung anong object ang pinag uusapan
    }
    fun isConnected() { 
        val connectivityManager = context?.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
       
    } // under the isConnected() method is to get if the  

}
