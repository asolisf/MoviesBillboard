package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.PopularContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PopularPresenterTest {

    @Mock
    PopularContract.View view;

    @Mock
    PopularContract.Interactor interactor;

    PopularPresenter presenter;

    @Before
    public void SetUp(){
        MockitoAnnotations.initMocks(this);
        this.presenter = new PopularPresenter(this.view,this.interactor);
    }

    @Test
    public void testSubscription(){
        this.presenter = new PopularPresenter(this.view,this.interactor);
        verify(this.interactor).Subscribe(this.presenter);
    }

    @Test
    public void loadData() {
        this.presenter.LoadData();
        verify(this.interactor).GetData();
    }

    @Test
    public void onDestroy() {
        this.presenter.OnDestroy();
        verify(this.interactor).Dispose();
    }

    @Test
    public void onGetDataSuccessIfResponseIsNull() {
        this.presenter.OnGetDataSuccess(null);
        verify(this.view).GetData(null);
    }

    @Test
    public void onGetDataErrorIfMessageIsNull() {
        this.presenter.OnGetDataError(null);
        verify(this.view).ShowError(null);
    }
}