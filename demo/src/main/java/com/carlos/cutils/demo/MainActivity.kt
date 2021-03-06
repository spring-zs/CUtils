package com.carlos.cutils.demo

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.carlos.cutils.base.adapter.CBaseMyPagerAdapter
import com.carlos.cutils.demo.fragment.*
import com.carlos.cutils.listener.PermissionListener
import com.carlos.cutils.util.LogUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {


    private val WECHAT_SERVICE_NAME = "com.carlos.grabredenvelope/.services.WechatService"

    var fragments = mutableListOf<Fragment>( RewardFragment(), WebviewFragment(), ClipFragment(), ViewFragment(), ViewPagerFragment())
    var titles = mutableListOf("打赏", "网页", "剪贴", "View", "ViewPager")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = CBaseMyPagerAdapter(supportFragmentManager, fragments, titles)
        viewpager.adapter = adapter
        sliding_tabs.setupWithViewPager(viewpager)
        viewpager.currentItem = fragments.size-1

        getPermissions()

        val rect = intent.sourceBounds
        LogUtils.d("rect:$rect")
    }



    private fun getPermissions() {
        requestPermission(100, object : PermissionListener {
            override fun permissionSuccess() {}
            override fun permissionFail() {}
        }, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    fun checkItem(item: Int) {
        viewpager.currentItem = item
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Thread.sleep(12000)
//        setContentView(R.layout.activity_main)
//
//        val list = listOf<String>(
//            "WebView",
//            "捐赠"
//        )
//
//        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
//        listview.adapter = adapter
//        listview.setOnItemClickListener { parent, view, position, id ->
//            when(position) {
//                0 -> startActivity(WebviewFragment::class.java)
//                1 -> {
////                    throw NullPointerException("exception hh")
////                    startActivity(RewardActivity::class.java)
//                }
//            }
//        }
//    }


}
