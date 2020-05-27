package com.example.practicepizza_20200526;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.practicepizza_20200526.databinding.ActivityDetailPizzaStoreBinding;
import com.example.practicepizza_20200526.datas.PizzaStore;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.Serializable;
import java.util.List;

public class PizzaStoreDetailActivity extends BaseActivity {
    ActivityDetailPizzaStoreBinding binding;
    PizzaStore mStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_pizza_store);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        binding.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionListener permissionListener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Uri myUri = Uri.parse(String.format("tel:%s",mStore.getPhoneNum()));
                        Intent myIntent = new Intent(Intent.ACTION_CALL,myUri);
                        startActivity(myIntent);
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        Toast.makeText(mContext, "통화 권한을 허가해줘야 통화가 가능합니다.", Toast.LENGTH_SHORT).show();
                    }
                };
                TedPermission.with(mContext)
                        .setPermissionListener(permissionListener)
                        .setDeniedMessage("설정에서 권한 승인이 필요합니다.")
                        .setPermissions(Manifest.permission.CALL_PHONE)
                        .check();
            }
        });
    }

    @Override
    public void setValues() {
        mStore = (PizzaStore) getIntent().getSerializableExtra("store");

        Log.d("가게이름", mStore.getName());
        binding.nameTxt.setText(mStore.getName());
        binding.phoneNumTxt.setText(mStore.getPhoneNum());
        Glide.with(mContext).load(mStore.getLogoImgUrl()).into(binding.logoImg);
    }
}
