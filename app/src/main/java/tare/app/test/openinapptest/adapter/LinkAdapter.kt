package tare.app.test.openinapptest.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import tare.app.test.api.models.Link
import tare.app.test.openinapptest.databinding.ItemLinkBinding
import tare.app.test.openinapptest.utils.convertToStandardDate


class LinkAdapter : RecyclerView.Adapter<LinkAdapter.LinkViewHolder>() {
    private var itemList = listOf<Link>()

    inner class LinkViewHolder(private val binding: ItemLinkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(link: Link) {
            binding.clicks.text = link.totalClicks.toString()
            binding.linkName.text = link.title ?: "No title"
            binding.link.text = link.webLink ?: "N/A"
            binding.linkDate.text = link.createdAt?.convertToStandardDate() ?: link.createdAt
            binding.linkLayout.setOnClickListener {
                val clipboard =
                    binding.root.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Smart Link", link.webLink)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(binding.root.context, "Copied to clipboard", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        return LinkViewHolder(
            ItemLinkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun updateData(list: List<Link>) {
        itemList = list
        notifyDataSetChanged()
    }
}