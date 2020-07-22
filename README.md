# PermissionUtils 权限管理库

最近更新：添加“不再提醒”选项的处理逻辑；重构。

支持：
 - **权限不足时，可使用默认提示框；**
 - **权限不足时，也可自定义提示框**
 
业务流程图：
![流程图](https://github.com/jjjSilence/PermissionUtils/blob/master/%E6%9D%83%E9%99%90%E8%AF%B7%E6%B1%82%E6%B5%81%E7%A8%8B%E5%9B%BE.png)


宗旨：减少Ctrl+Ctrl+V的重复劳动。

注意：用户选择“不再提示”选项，requestPermissions会调用失败，但是会走onRequestPermissionsResult回调。所以可以在onRequestPermissionsResult方法中判断shouldShowRequestPermissionRationale方法。

shouldShowRequestPermissionRationale返回值:

 - true：再次申请权限，会出现一个“权限被拒后，不再提示”的选项；
 - false: 如果用户选择“不再提示”选项，会返回false。表示用户不想再看。
 具体可以参考：[这篇文章](https://www.jianshu.com/p/d8df0dac6eb6)或者官网
 
 
该库的使用方式：
1.在project的build.gradle文件中添加
maven { url 'https://jitpack.io' }

2.在app的build.gradle文件中添加
implementation 'com.github.jjjSilence:PermissionUtils:1.0.5'

 - 使用默认提示框：[示例](https://github.com/jjjSilence/PermissionUtils/blob/master/app/src/main/java/com/jjj/permission/utils/DefaultDialogActivity.java)
 - 使用自定义提示框：[示例](https://github.com/jjjSilence/PermissionUtils/blob/master/app/src/main/java/com/jjj/permission/utils/CustomDialogActivity.java)
