package com.aaks.news.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aaks.news.dal.ArticleDbConstants

@Entity
data class Article (@PrimaryKey(autoGenerate = true) var id: Long,
               @ColumnInfo(name = ArticleDbConstants.URL) var url: String,
               @ColumnInfo(name = ArticleDbConstants.TITLE) var title: String,
               @ColumnInfo(name = ArticleDbConstants.DESCRIPTION) var description: String){
}