package com.jjj.permission.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jjj.permission.PermissionUtils;
import com.jjj.permissionutils.R;
import com.jjj.smartpopupwindow.CommonPopupWindow;

/**
 * xx
 *
 * @author jiangjiaojiao
 * @since 2020-07-22
 */
public class CustomPopupwindow {
    private CommonPopupWindow mCommonPopupWindow;

    public void showCustomTipsPopupWindow(final Activity activity, final String[] needPermissions) {
        if (mCommonPopupWindow == null) {
            mCommonPopupWindow = new CommonPopupWindow(activity)
                    .setContentView(R.layout.permission_popupwindow)
                    .setLayoutMatchParent()
                    .createPopupWindow()
                    .initPopupWindow(new CommonPopupWindow.InitPoputWindowCallback() {
                        @Override
                        public void initPopupWindow(@NonNull View view, @NonNull final CommonPopupWindow popupWindow) {
                            mCommonPopupWindow = popupWindow;
                            TextView leftItemView = view.findViewById(R.id.left_item_view);
                            leftItemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    activity.finish();
                                    popupWindow.onDismiss();
                                }
                            });

                            TextView rightItemView = view.findViewById(R.id.right_item_view);
                            rightItemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    PermissionUtils.checkAndRequestPermissions(activity, needPermissions);
                                }
                            });
                        }
                    })
                    .showAtLocation(activity.findViewById(android.R.id.content),
                            Gravity.CENTER, 0, 0);
        }
        mCommonPopupWindow.showPopupWindow();
    }

    public void hideCustomTipsPopupWindow() {
        if (mCommonPopupWindow != null) {
            mCommonPopupWindow.onDismiss();
        }
    }
}
