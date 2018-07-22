package xyz.zzp.hckotlin.adapters

import android.content.Context
import android.view.ViewGroup
import xyz.zzp.hckotlin.R
import xyz.zzp.hckotlin.data.VO.HealthNewsVO
import xyz.zzp.hckotlin.delegates.ForumItemDelegate
import xyz.zzp.hckotlin.viewholders.ItemForumViewHolder

class MainScreenAdapter(context: Context,
                        private val mDelegate: ForumItemDelegate) : BaseRecyclerAdapter<ItemForumViewHolder,HealthNewsVO>(context) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemForumViewHolder {

            val topItem = mLayoutInflator.inflate(R.layout.item_forum,parent,false)
            return ItemForumViewHolder(topItem,mDelegate,itemCount)
    }

}