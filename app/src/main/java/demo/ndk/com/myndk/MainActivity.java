package demo.ndk.com.myndk;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gds.activity.RecycleviewActivity;
import com.gds.jniutils.JniUtils;

public class MainActivity extends Activity {

    //http://img5.imgtn.bdimg.com/it/u=3586233367,3171193232&fm=11&gp=0.jpg



    private ImageView img;
    public Context context;

    JniUtils jniutils=new JniUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this,jniutils.getStringFromNative(),Toast.LENGTH_SHORT).show();
        context=this;
        img= (ImageView) findViewById(R.id.img);
        Glide.with(this)
                .load("http://img5.imgtn.bdimg.com/it/u=3586233367,3171193232&fm=11&gp=0.jpg")
                .error(R.drawable.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(img);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void play(View view){
        startActivity(new Intent(MainActivity.this, RecycleviewActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
        清除所有缓存
         */
        Glide.get(context).clearMemory();
        Glide.get(context).clearDiskCache();
    }
}
