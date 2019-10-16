package com.alansolisflores.movies.interactors;

import com.alansolisflores.movies.components.DaggerInteractorComponent;
import com.alansolisflores.movies.components.InteractorComponent;
import com.alansolisflores.movies.contracts.PopularContract;
import com.alansolisflores.movies.modules.ApiServiceModuleMock;
import com.alansolisflores.movies.modules.MoviesRepositoryModuleMock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PopularInteractorTest {

    @Mock
    public PopularContract.InteractorOutput interactorOutput;

    @Mock
    public PopularContract.Repository repository;


    public PopularContract.Interactor popularInteractor;

    @Before
    public void SetUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        InteractorComponent interactorComponent =
                DaggerInteractorComponent
                        .builder()
                        .apiServiceModule(new ApiServiceModuleMock())
                        .moviesRepositoryModule(new MoviesRepositoryModuleMock())
                        .build();
        DaggerInteractorComponent mock = org.mockito.Mockito.mock(DaggerInteractorComponent.class);
        when(mock.create()).thenReturn(interactorComponent);
        this.popularInteractor = new PopularInteractor(interactorOutput);
    }

    @Test
    public void dispose() {
        this.popularInteractor.Dispose();
        verify(repository).Dispose();
    }
}