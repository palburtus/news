package com.aaks.news.dal

import android.content.ContentValues
import android.provider.BaseColumns
import com.aaks.news.model.Article

fun Article.buildContentValues() : ContentValues{
    var contentValues = ContentValues()

    if(this.id != null) {
        contentValues.put(BaseColumns._ID, this.id)
    }

    contentValues.put(ArticleDbConstants.URL, this.url)
    contentValues.put(ArticleDbConstants.TITLE, this.title)
    contentValues.put(ArticleDbConstants.DESCRIPTION, this.description)

    return contentValues

}