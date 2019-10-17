package com.alansolisflores.movies.interactors;

import com.alansolisflores.movies.contracts.PopularContract;
import com.alansolisflores.movies.mocks.repositories.entities.requests.MoviesRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PopularInteractorTest {

    @Mock
    PopularContract.Repository repository;

    @Mock
    MoviesRequest apiService;

    @Mock
    PopularContract.InteractorOutput interactorOutput;

    PopularContract.Interactor interactor;

    @Before
    public void SetUp(){
        MockitoAnnotations.initMocks(this);
        interactor = new PopularInteractor(repository,apiService);
    }

    @Test
    public void successDisposeIfRepositoryIsCorrect() {
        interactor.Dispose();
        verify(repository).Dispose();
    }

    public void successSubscribeIfInteractorOuputIsNull(){
        interactor.Subscribe(null);
    }
}