package com.example.com.qqmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.qqmenu.view.TwoTabView;
import com.example.com.qqmenu.view.draglayout.DragLayout;
import com.example.com.qqmenu.view.scrollview.ElasticScrollView;
import com.nineoldandroids.view.ViewHelper;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *  主页
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] mPlanetTitles;
    @Bind(R.id.left_drawer)
    ListView mDrawerList;
    @Bind(R.id.dl)
    DragLayout mDragLayout;
    @Bind(R.id.tv_set)
    TextView tv_set;
    @Bind(R.id.iv_icon)
    ImageView iv_icon;
    @Bind(R.id.systemBar_view)
    View systemBar_view;
    @Bind(R.id.elastic_scrollview)
    ElasticScrollView elastic_scrollview;
    @Bind(R.id.home_tab_view)
    TwoTabView home_tab_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDragLayout();
        initView();
        addListener();
    }




    /**
     * 监听事件
     */
    private void addListener() {
        iv_icon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                mDragLayout.open();
            }
        });
        home_tab_view.setOnTabClickListener(new TwoTabView.OnTabClickListener() {
            @Override
            public void click(int position) {
                Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_LONG).show();
            }
        });
    }



    /**
     * 初始化view
     */
    private void initView() {
        ViewGroup.LayoutParams linearParams =(ViewGroup.LayoutParams) systemBar_view.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height = getStatusBarHeight();
        systemBar_view.setLayoutParams(linearParams);
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(this);
        elastic_scrollview.setDamk(5);
    }



    /**
     * 设置菜单
     */
    private void initDragLayout() {
        mDragLayout.setmDragListener(new DragLayout.DragListener() {
            public void onOpen() {
                 mDrawerList.smoothScrollToPosition(new Random().nextInt(30));
            }
            public void onClose() {
                shake();
            }

            public void onDrag(float percent) {
                ViewHelper.setAlpha(iv_icon, 1 - percent);
            }
        });
    }



    /**
     * 菜单关闭设置头像动画
     */
    private void shake() {
        iv_icon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
    }



    /**
     * 获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_LONG).show();
    }
}
