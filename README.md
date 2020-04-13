# PermissionUtils

权限封装类

1.在project的build.gradle文件中添加
maven { url 'https://jitpack.io' }
2.在项目中引用
implementation 'com.github.jjjSilence:PermissionUtils:1.0.0'

3.Demo:
public class MainActivity extends AppCompatActivity {
    private static String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (PermissionUtils.checkPermissions(this, NEEDED_PERMISSIONS)) {
            PermissionUtils.requestPermissions(this, NEEDED_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, new PermissionCallback() {
            @Override
            public void grantedPermissions() {

            }

            @Override
            public boolean deniedPermissions() {
                return false;
            }
        });
    }
}
