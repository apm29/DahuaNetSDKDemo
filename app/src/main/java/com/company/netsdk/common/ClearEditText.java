package com.company.netsdk.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.company.netsdk.R;

/**
 * 带清除功能的文本框
 */
public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {
    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable;

    /**
     * 控件是否有焦点
     */
    private boolean bHaveFocus;

    public ClearEditText(Context context) {
        this(context, null);
        init();
    }

    public ClearEditText(Context context, AttributeSet attributeSet) {
        // 这里构造方法也很重要，不加这个很多属性不能在XML里面定义
        this(context, attributeSet, android.R.attr.editTextStyle);
        init();
    }

    public ClearEditText(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init();
    }

    private void init() {
        // 获取EditText的DrawableRight，假如没有设置，我们就使用默认的图片
        mClearDrawable = getCompoundDrawables()[2];
        if(mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.drawable.ic_clear);
        }

        if(mClearDrawable != null) {
            mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        }

        // 默认设置隐藏图标
        setClearIconVisible(false);

        // 设置焦点改变的监听
        setOnFocusChangeListener(this);

        // 设置输入框里内容发生改变的监听
        addTextChangedListener(this);
    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(getCompoundDrawables()[2] != null) {
                boolean bTouchable = ( event.getX() > (getWidth() - getTotalPaddingRight()) )
                                       && ( event.getX() < (getWidth() - getPaddingRight()));
                if(bTouchable) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生改变的时候，判断里面字符串的长度 设置清除图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View v, boolean bHaveFocus) {
        this.bHaveFocus = bHaveFocus;
        if(bHaveFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     */
    public void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    /**
     * 当输入框内容发生改变时，回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if(bHaveFocus) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
