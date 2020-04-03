package br.com.havebreak.activeChat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.havebreak.model.Message

abstract class AbstractViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(message: Message)
}