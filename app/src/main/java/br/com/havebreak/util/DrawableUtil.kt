package br.com.havebreak.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import br.com.havebreak.R
import kotlinx.android.synthetic.main.my_message.view.*

class DrawableUtil {
    companion object {
        fun getDrawableMessageCheck(context: Context, messageWasRead: Boolean): Drawable? {
            if(messageWasRead) {
                return ContextCompat.getDrawable(context, R.drawable.ic_message_read_check)
            } else {
                return ContextCompat.getDrawable(context, R.drawable.ic_message_sent_check)
            }
        }
    }
}