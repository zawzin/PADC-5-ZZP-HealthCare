package xyz.zzp.hckotlin.viewholders

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_forum.view.*
import xyz.zzp.hckotlin.data.VO.HealthNewsVO
import xyz.zzp.hckotlin.delegates.ForumItemDelegate

class ItemForumViewHolder(itemView : View,
                          private val delegate: ForumItemDelegate,
                          private val itemCount: Int) : BaseViewHolder<HealthNewsVO>(itemView) {


    override fun setData(data: HealthNewsVO) {
        mData = data

        itemView.vDivider.visibility = View.VISIBLE
        if (layoutPosition > (itemCount-2)){
            itemView.vDivider.visibility = View.GONE
        }

        itemView.tvTitle.setText(data.title)
        itemView.tvUsername.setText(data.author!!.autorName)

        if (data.image != null){
            Glide.with(itemView.ivImage)
                    .load(data.image)
                    .into(itemView.ivImage)
        }
        if (data.infoType != null){
            itemView.tvInfoType.setText(data.infoType)
        }
    }

    override fun onClick(p0: View?) {
        delegate.onTapForum(mData)
    }
}