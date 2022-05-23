package bu.ac.kr.anyfeeling

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val toast = Toast.makeText(context, "NotificationReceiver Call....", Toast.LENGTH_SHORT)
        toast.show()
    }
}