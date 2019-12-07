package com.architecture.business.core.repository

interface BaseRepository<Param, Result> {
    fun setParam(param: Param)
    fun getParam(): Param
}