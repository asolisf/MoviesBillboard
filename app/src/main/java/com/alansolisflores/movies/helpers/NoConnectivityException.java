package com.alansolisflores.movies.helpers;

class NoConnectivityException extends Throwable {
    @Override
    public String getMessage() {
        return "No Internet Connection";
    }
}
