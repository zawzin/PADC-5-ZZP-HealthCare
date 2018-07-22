package xyz.zzp.hckotlin.delegates

import xyz.zzp.hckotlin.data.VO.HealthNewsVO

interface ForumItemDelegate{
    fun onTapForum(forum : HealthNewsVO?)
}