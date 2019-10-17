package com.alansolisflores.movies.interactors;

import com.alansolisflores.movies.contracts.PopularContract;
import com.alansolisflores.movies.helpers.Config;
import com.alansolisflores.movies.mocks.repositories.entities.objects.Movie;
import com.alansolisflores.movies.mocks.repositories.entities.requests.MoviesRequest;
import com.alansolisflores.movies.mocks.repositories.entities.responses.MoviesResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PopularInteractorTest {

    @Mock
    PopularContract.Repository repository;

    @Mock
    MoviesRequest apiService;

    @Mock
    PopularContract.InteractorOutput interactorOutput;

    @Mock
    Response<MoviesResponse> response;

    @Mock
    Call<MoviesResponse> call;

    PopularInteractor interactor;

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

    @Test
    public void OnGetDataSuccessWhenResponseIsSuccessful(){
        when(response.isSuccessful()).thenReturn(true);
        verify(interactorOutput).OnGetDataSuccess(new ArrayList<Movie>());
    }
}