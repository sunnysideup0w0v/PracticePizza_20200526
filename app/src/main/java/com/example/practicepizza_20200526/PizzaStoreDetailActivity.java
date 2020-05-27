package com.example.practicepizza_20200526;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.practicepizza_20200526.databinding.ActivityDetailPizzaStoreBinding;
import com.example.practicepizza_20200526.datas.PizzaStore;

import java.io.Serializable;

public class PizzaStoreDetailActivity extends BaseActivity {
    ActivityDetailPizzaStoreBinding binding;
    PizzaStore mStore;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_pizza_store);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

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
