package dev.syorito_hatsuki.pcsim.init

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.niksaen.pcsim.R


object InitFont {
    fun getPixelFont(context: Context) : Typeface? {
        return ResourcesCompat.getFont(context, R.font.pixel)
    }
}
