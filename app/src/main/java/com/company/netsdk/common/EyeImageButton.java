package com.company.netsdk.common;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.company.netsdk.R;

/**
 * 密码显示与隐藏 ImageButton
 */
public class EyeImageButton extends ImageButton implements View.OnClickListener{
    private boolean bHideFirst = true;  // 输入框密码是否隐藏，默认为true
    private EditText editText;

    public EyeImageButton(Context context) {
        this(context, null);
    }

    public EyeImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, android.R.attr.imageButtonStyle);
    }

    public EyeImageButton(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        setOnClickListener(this);
    }

    /**
     * 设置需要隐藏文字的文本框, 设置前，外部要先findViewById文本
     */
    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void onClick(View v) {
        if(bHideFirst) {    // 明文
            setImageResource(R.drawable.mobile_openeye);
            HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
            editText.setTransformationMethod(method1);
            bHideFirst = false;
        } else {  // 密文
            setImageResource(R.drawable.mobile_closeeye);
            TransformationMethod method2 = PasswordTransformationMethod.getInstance();
            editText.setTransformationMethod(method2);
            bHideFirst = true;
        }
    }
}
