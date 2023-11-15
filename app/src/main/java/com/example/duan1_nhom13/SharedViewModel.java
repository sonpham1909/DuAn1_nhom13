package com.example.duan1_nhom13;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> searchText = new MutableLiveData<>();

    public MutableLiveData<String> getSearchText() {
        return searchText;
    }

    private MutableLiveData<Boolean> isSearchViewOpen = new MutableLiveData<>();

    // Getter và setter cho searchQuery và isSearchViewOpen

    // ...

    public void setSearchQuery(String query) {
        searchText.setValue(query);
    }

    public void setIsSearchViewOpen(boolean isOpen) {
        isSearchViewOpen.setValue(isOpen);
    }
}
