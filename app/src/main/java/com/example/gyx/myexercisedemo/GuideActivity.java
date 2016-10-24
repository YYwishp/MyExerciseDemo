package com.example.gyx.myexercisedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导界面，viewpager
 */
public class GuideActivity extends FragmentActivity {
    private ViewPager viewpager;
    private Button button;
    private List<ImageView> list;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);


        viewpager = (ViewPager) findViewById(R.id.viewpager);
        button = (Button) findViewById(R.id.button);

        //1，获取显示数据
        initData();
        //给viewpager设置Adapter
        viewpager.setAdapter(new MyAdapter());
        //viewpager切换监听
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动的时候
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //选择完成的时候
            @Override
            public void onPageSelected(int position) {
                //判断是否是第三个界面，是，显示button；不是，隐藏button
                if (position == list.size() - 1) {
                    //显示button
                    button.setVisibility(View.VISIBLE);
                    //点击button跳转到主界面
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转到主界面
                            Intent intent = new Intent(GuideActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    });
                } else {
                    //隐藏button
                    button.setVisibility(View.GONE);
                }
            }
            //滑动状态改变的时候
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initData() {
        list = new ArrayList<ImageView>();
        //创建3个ImageView
        ImageView imageView0 = new ImageView(this);
        imageView0.setBackgroundResource(R.drawable.a);
        ImageView imageView1 = new ImageView(this);
        imageView1.setBackgroundResource(R.drawable.c);
        ImageView imageView2 = new ImageView(this);
        imageView2.setBackgroundResource(R.drawable.d);
        //添加数据
        list.add(imageView0);
        list.add(imageView1);
        list.add(imageView2);

    }

    private class MyAdapter extends PagerAdapter {
        //设置viewpager个数
        @Override
        public int getCount() {
            return list.size();
        }

        //判断Object是否和isinstantiateItem返回的View对象一致
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //添加条目的操作
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //添加条目
            container.addView(list.get(position));
            return list.get(position);
        }

        //销毁条目操作
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }


}
