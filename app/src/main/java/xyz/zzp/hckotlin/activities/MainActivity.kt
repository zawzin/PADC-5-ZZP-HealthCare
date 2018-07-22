package xyz.zzp.hckotlin.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.LENGTH_LONG
import android.support.design.widget.Snackbar.LENGTH_SHORT
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.padcmyanmar.mmnews.kotlin.components.SmartScrollListener

import kotlinx.android.synthetic.main.activity_main.*
import xyz.zzp.hckotlin.R
import xyz.zzp.hckotlin.adapters.MainScreenAdapter
import xyz.zzp.hckotlin.data.VO.HealthNewsVO
import xyz.zzp.hckotlin.mvp.presenters.ForumPresenter
import xyz.zzp.hckotlin.mvp.views.ForumScreenView

class MainActivity : BaseActivity(),ForumScreenView {

    private lateinit var mMainAdapter: MainScreenAdapter
    private lateinit var mPresenter: ForumPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mPresenter = ViewModelProviders.of(this).get(ForumPresenter::class.java)
        mPresenter.initPresenter(this)

        swipeRefreshLayout.isRefreshing = true

        val mSmartScrollListener = SmartScrollListener(object : SmartScrollListener.OnSmartScrollListener {
            override fun onListEndReach() {
                Snackbar.make(rvMain, "List reached end.", LENGTH_SHORT).show()
            }
        })
        rvMain.addOnScrollListener(mSmartScrollListener)

        rvMain.setEmptyView(vpEmptyNews)
        rvMain.layoutManager = LinearLayoutManager(applicationContext)
        mMainAdapter = MainScreenAdapter(applicationContext,mPresenter)
        rvMain.adapter = mMainAdapter

        mPresenter.getHealthNewsList().observe(this,object : Observer<List<HealthNewsVO>>{
            override fun onChanged(t: List<HealthNewsVO>?) {
                swipeRefreshLayout.isRefreshing = false
                mMainAdapter.setNewData(t as MutableList<HealthNewsVO>)
            }
        })

        mPresenter.getmErrorLD().observe(this,object : Observer<String>{
            override fun onChanged(t: String?) {
                displayErrorBV(t!!)
                swipeRefreshLayout.isRefreshing = false
            }

        })

        swipeRefreshLayout.setOnRefreshListener {
            mMainAdapter.clearData()
            mPresenter.refreshNews()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun displayErrorBV(errorMsg: String){
        Snackbar.make(rvMain,errorMsg, LENGTH_LONG).show()
    }
    override fun lunchDetail(url: String?) {
        Log.i("Health Care!","Clicked")

        val customTabsIntent : CustomTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}
