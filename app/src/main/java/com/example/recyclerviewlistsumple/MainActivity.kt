package com.example.recyclerviewlistsumple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), RecyclerViewHolder.ItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val posts: MutableList<Post> = createPostData(0, 50).toMutableList()


        viewAdapter = RecyclerAdapter(this, this, posts)
        viewManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView = findViewById<RecyclerView>(R.id.mainRecyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
            addOnScrollListener(InfiniteScrollListener())
        }
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(applicationContext, "position $position was tapped", Toast.LENGTH_SHORT).show()
    }

    private fun createPostData(offset: Int, limit: Int): List<Post> {
        val names = listOf("Alice", "Jhon", "Smith", "Taro", "Jun", "Debit", "Mike", "Kebin", "Dain", "Kein")
        return (offset + 1..offset + limit).toList().map {
            val random = (0..9).random()
            Post(nickname = names[random], caption = "これは${it}番目のキャプションです。") // フェッチしたpostDataを直接返してもよい。
        }
    }

    /**
     * API でリストデータを取得して画面に反映することを想定したメソッド。
     */
    private fun updateItemList(index: Int) {
        val list = createPostData(index, 20)
        viewAdapter.add(list)
    }

    /**
     * リストの下端までスクロールしたタイミングで発火するリスナー。
     */
    inner class InfiniteScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            // アダプターが保持しているアイテムの合計
            val itemCount = viewAdapter.itemCount
            // 画面に表示されているアイテム数
            val childCount = recyclerView.childCount
            val manager = recyclerView.layoutManager as LinearLayoutManager
            // 画面に表示されている一番上のアイテムの位置
            val firstPosition = manager.findFirstVisibleItemPosition()

            // 以下の条件に当てはまれば一番下までスクロールされたと判断できる。
            if (itemCount == childCount + firstPosition) {
                updateItemList(viewAdapter.itemCount)
            }
        }
    }

}