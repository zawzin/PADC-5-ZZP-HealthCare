package xyz.zzp.hckotlin.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import xyz.zzp.hckotlin.data.VO.HealthNewsVO
import xyz.zzp.hckotlin.data.models.HealthModel
import xyz.zzp.hckotlin.delegates.ForumItemDelegate
import xyz.zzp.hckotlin.mvp.views.ForumScreenView

class ForumPresenter : BasePresenter<ForumScreenView>(), ForumItemDelegate{
    override fun onTapForum(forum: HealthNewsVO?) {
        mView!!.lunchDetail(forum!!.completeUrl)
    }

    private lateinit var mHealthNewsListLD : MutableLiveData<List<HealthNewsVO>>

    override fun initPresenter(mView: ForumScreenView) {
        super.initPresenter(mView)
        mHealthNewsListLD = MutableLiveData()
        HealthModel.getInstance().load(mHealthNewsListLD,mErrorLD)
    }

    fun getHealthNewsList() : MutableLiveData<List<HealthNewsVO>>{
        return mHealthNewsListLD
    }

    fun refreshNews(){
        HealthModel.getInstance().load(mHealthNewsListLD,mErrorLD)
    }
}