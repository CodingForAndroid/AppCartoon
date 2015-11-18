package com.jorge.appcartoon.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jorge.appcartoon.Constant;
import com.jorge.appcartoon.R;
import com.jorge.appcartoon.bean.CartRecClassify;
import com.jorge.appcartoon.bean.CartWork;
import com.jorge.appcartoon.http.ApiUtil;
import com.jorge.appcartoon.ui.activity.CartInsActivity;
import com.jorge.appcartoon.ui.activity.SwipeFinishActivity;
import com.jorge.appcartoon.util.ImageLoaderHelper;
import com.jorge.appcartoon.util.UIUtils;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
/**
 *  首页推荐的Holder
 * @author：Jorge on 2015/9/16 12:25
 */
public class CartRecHolder extends BaseHolder<CartRecClassify> {
    @Bind(R.id.iv_1)
    ImageView iv1;
    @Bind(R.id.tv_1_title)
    TextView tv1Title;
    @Bind(R.id.tv_1_sub_title)
    TextView tv1SubTitle;
    @Bind(R.id.rl_1)
    RelativeLayout rl1;
    @Bind(R.id.iv_2)
    ImageView iv2;
    @Bind(R.id.tv_2_title)
    TextView tv2Title;
    @Bind(R.id.tv_2_sub_title)
    TextView tv2SubTitle;
    @Bind(R.id.rl_2)
    RelativeLayout rl2;
    @Bind(R.id.iv_3)
    ImageView iv3;
    @Bind(R.id.tv_3_title)
    TextView tv3Title;
    @Bind(R.id.tv_3_sub_title)
    TextView tv3SubTitle;
    @Bind(R.id.rl_3)
    RelativeLayout rl3;
    @Bind(R.id.iv_4)
    ImageView iv4;
    @Bind(R.id.tv_4_title)
    TextView tv4Title;
    @Bind(R.id.tv_4_sub_title)
    TextView tv4SubTitle;
    @Bind(R.id.rl_4)
    RelativeLayout rl4;
    @Bind(R.id.iv_5)
    ImageView iv5;
    @Bind(R.id.tv_5_title)
    TextView tv5Title;
    @Bind(R.id.tv_5_sub_title)
    TextView tv5SubTitle;
    @Bind(R.id.rl_5)
    RelativeLayout rl5;
    @Bind(R.id.iv_6)
    ImageView iv6;
    @Bind(R.id.tv_6_title)
    TextView tv6Title;
    @Bind(R.id.tv_6_sub_title)
    TextView tv6SubTitle;
    @Bind(R.id.rl_6)
    RelativeLayout rl6;
    @Bind(R.id.iv_7)
    ImageView iv7;
    @Bind(R.id.tv_7_title)
    TextView tv7Title;
    @Bind(R.id.tv_7_sub_title)
    TextView tv7SubTitle;
    @Bind(R.id.rl_7)
    RelativeLayout rl7;
    @Bind(R.id.iv_8)
    ImageView iv8;
    @Bind(R.id.tv_8_title)
    TextView tv8Title;
    @Bind(R.id.tv_8_sub_title)
    TextView tv8SubTitle;
    @Bind(R.id.rl_8)
    RelativeLayout rl8;
    @Bind(R.id.iv_9)
    ImageView iv9;
    @Bind(R.id.tv_9_title)
    TextView tv9Title;
    @Bind(R.id.tv_9_sub_title)
    TextView tv9SubTitle;
    @Bind(R.id.rl_9)
    RelativeLayout rl9;
    //标题
    @Bind(R.id.tv_strongly_title)
    TextView title;
    //图标
    @Bind(R.id.tv_strongly_right_ic)
    TextView rightIcon;
    @Bind(R.id.rl_channel_title)
    RelativeLayout channelTitleLayout;
    @Override
    protected View initView() {
        View view = UIUtils.inflate(R.layout.item_recommended_channel);
        ButterKnife.bind(this, view);
        return view;
    }

        @Override
    public void refreshView() {
        //对于一行有三个item
        ImageView[] covers3 = {iv1,iv2, iv3, iv4, iv5, iv6,iv7,iv8,iv9};
        TextView[] titles3 = {tv1Title,tv2Title, tv3Title, tv4Title, tv5Title, tv6Title,tv7Title,tv8Title,tv9Title};
        TextView[] subTitles3 = {tv1SubTitle,tv2SubTitle, tv3SubTitle, tv4SubTitle, tv5SubTitle, tv6SubTitle,tv7SubTitle,tv8SubTitle,tv9SubTitle};
        RelativeLayout[] layouts3={rl1,rl2,rl3,rl4,rl5,rl6,rl7,rl8,rl9};
        //对于是 一行有两个item
        ImageView[] covers2 = {iv1,iv2, iv4, iv5,iv7,iv8};
        TextView[] titles2 = {tv1Title,tv2Title,tv4Title, tv5Title,tv7Title,tv8Title};
        TextView[] subTitles2 = {tv1SubTitle,tv2SubTitle, tv4SubTitle, tv5SubTitle,tv7SubTitle,tv8SubTitle};
        RelativeLayout[] layouts2={rl1,rl2,rl4,rl5,rl7,rl8};
        //隐藏所有的 图片
        for(int i=0;i<layouts3.length;i++){
            layouts3[i].setVisibility(View.GONE);
        }
        //隐藏标题布局
        channelTitleLayout.setVisibility(View.GONE);
        CartRecClassify cartRecClassify=getData();
        List<CartWork> list= cartRecClassify.data;

        //重新设置右边的图标
        switch (cartRecClassify.category_id){
            case Constant.CATEGORY_ID0:
                setTextDrawable(title, R.mipmap.icon1_rec_2x);
                rightIcon.setBackgroundResource(R.mipmap.icon_right_arrow);
                break;
            case Constant.CATEGORY_ID1:
                setTextDrawable(title, R.mipmap.icon2_rec_2x);
                rightIcon.setBackgroundResource(R.mipmap.icon_right_arrow);;
                break;
            case Constant.CATEGORY_ID2:
                setTextDrawable(title, R.mipmap.icon3_rec_2x);
              rightIcon.setBackgroundResource(R.mipmap.icon_right_arrow);
                break;
            case Constant.CATEGORY_ID3:
                setTextDrawable(title, R.mipmap.icon4_rec_2x);
                rightIcon.setBackgroundResource(R.mipmap.icon_right_arrow);
                break;
            case Constant.CATEGORY_ID4:
                setTextDrawable(title, R.mipmap.icon5_rec_2x);
                rightIcon.setBackgroundResource(R.mipmap.icon_right_arrow);
                break;
            case Constant.CATEGORY_ID5:
                setTextDrawable(title, R.mipmap.icon6_rec_2x);
                rightIcon.setBackgroundResource(R.mipmap.icon_right_arrow);
                break;
            case Constant.CATEGORY_ID6:
                setTextDrawable(title, R.mipmap.icon7_rec_2x);
                rightIcon.setBackgroundResource(R.mipmap.icon_right_arrow);
                break;
            case Constant.CATEGORY_ID7:
                setTextDrawable(title, R.mipmap.icon8_rec_2x);
                rightIcon.setBackgroundResource(R.mipmap.icon_right_arrow);
                break;
            case Constant.CATEGORY_ID8:
                setTextDrawable(title, R.mipmap.icon9_rec_2x);
                rightIcon.setBackgroundResource(R.mipmap.icon_right_arrow);
                break;
            case Constant.CATEGORY_ID9:
                setTextDrawable(title, R.mipmap.icon10_rec_2x);
               rightIcon.setBackgroundResource(R.mipmap.icon_right_arrow);
                break;

        }
        //888888888888888888888
        if(list.size()==3||list.size()==6||list.size()==9){
            for (int i = 0; i < list.size(); i++) {
                CartWork item = list.get(i);
                ImageLoaderHelper.getInstance().loadImage(item.cover.split("\\?")[0], covers3[i]);
                titles3[i].setText(item.title);
                if(item.authors!=null&&!("".equals(item.authors))){
                    subTitles3[i].setText(item.authors);
                }else if(item.sub_title!=null&&!("".equals(item.sub_title))){
                    subTitles3[i].setText(item.sub_title);
                }else{
                    subTitles3[i].setText(item.title);
                }
                layouts3[i].setVisibility(View.VISIBLE);

                layouts3[i].setOnClickListener(new ItemClickListener(item.obj_id,item.id));
            }
            title.setText("  "+cartRecClassify.title);
            // 显示标题
            channelTitleLayout.setVisibility(View.VISIBLE);
        }else if(list.size()==2||list.size()==4){
            for (int i = 0; i < list.size(); i++) {
                CartWork item = list.get(i);
                ImageLoaderHelper.getInstance().loadImage(item.cover.split("\\?")[0], covers2[i]);
                titles2[i].setText(item.title);
                if(item.authors!=null&&!("".equals(item.authors))){
                    subTitles2[i].setText(item.authors);
                }else if(item.sub_title!=null&&!("".equals(item.sub_title))){
                    subTitles2[i].setText(item.sub_title);
                }else{
                    subTitles2[i].setText(item.title);
                }
                layouts2[i].setVisibility(View.VISIBLE);

                layouts2[i].setOnClickListener(new ItemClickListener(item.obj_id, item.id));
            }
            title.setText("  "+cartRecClassify.title);
            // 显示标题
            channelTitleLayout.setVisibility(View.VISIBLE);
        }else{


        }
        //8888888888888888888888
    }

public void setTextDrawable(TextView textView,int resId){
    Drawable drawable = UIUtils.getDrawable(resId);
    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
    textView.setCompoundDrawables(drawable, null, null, null);
}

    private  class ItemClickListener implements   View.OnClickListener{

        int  comic_id=0;
        public ItemClickListener(int obj_id ,int id) {
        if(0!=obj_id){
            comic_id=obj_id;
        }else if(0!=id){
            comic_id=id;
         }
        }
        @Override
        public void onClick(View v) {
        if(comic_id!=0){
            Intent intent= new Intent(UIUtils.getContext(),CartInsActivity.class);
//            Intent intent= new Intent(UIUtils.getContext(),SwipeFinishActivity.class);
            intent.putExtra(ApiUtil.COMIC_ID,comic_id);
            UIUtils.showToastSafe(""+comic_id);
            UIUtils.startActivity(intent);
        }
        }
    }
}
