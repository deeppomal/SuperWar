package com.deeppomal.superherob.ui.home;

import android.net.Uri;

import com.deeppomal.superherob.MainActivity;
import com.deeppomal.superherob.SharedPrefManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public HomeViewModel() {
        mText = new MutableLiveData<>();

    }
    public LiveData<String> getText() {
        return mText;
    }


}