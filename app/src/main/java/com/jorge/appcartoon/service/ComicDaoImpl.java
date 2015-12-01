package com.jorge.appcartoon.service;

import com.jorge.appcartoon.CartoonApplication;
import com.jorge.dao.Comic;
import com.jorge.dao.ComicDao;
import java.util.List;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Comic 的增删改查
 * @author：Jorge on 2015/11/27 16:36
 */
public class ComicDaoImpl {
   private ComicDao comicDao;
    public ComicDaoImpl() {
        comicDao = CartoonApplication.getApplication().getDaoSession().getComicDao();
    }
    private static class SingletonHolder {
        public final static ComicDaoImpl instance = new ComicDaoImpl();
    }
    public static ComicDaoImpl getInstance() {
        return SingletonHolder.instance;
    }
    /**
     * 插入一条漫画记录， 如果已经存在  则 修改当前观看章节
     * @param comic_id  漫画id
     * @param chapter_id 章节 id
     */
    public void insert(int comic_id, int chapter_id) {
//        Comic comic = isExist(comic_id, comicDao);
        Comic comic =  query(comic_id);
        if (comic == null) {
            comic = new Comic(null, comic_id, chapter_id, "", 0, 0, 0);
            comicDao.insert(comic);
        } else {
            comic.setChapter_id(chapter_id);
            comicDao.insertOrReplace(comic);
        }
    }
    /**
     * 根据 Comic_id 去查询数据库  看是否有记录，
     * 返回值不为空  看过该漫画。
     * 返回值为空 没有看过该漫画。
     *
     * @param Comic_id： 漫画的 comic_id
     * @param comicDao  ： dao
     * @return Comic: 漫画数据
     */
    public Comic isExist(int Comic_id, ComicDao comicDao) {
        Comic comic = null;
        QueryBuilder qb = comicDao.queryBuilder();
        List list = qb.where(ComicDao.Properties.Comic_id.eq(Comic_id)).list();
        if (list.size() > 0) {
            comic = (Comic) list.get(0);
            return comic;
        }
        return comic;
    }
    /**
     *  根据 漫画 id 返回 漫画数据
     * @param comic_id ： 漫画id
     * @return
     */
    public Comic query(int comic_id) {
        Comic comic = comicDao.queryBuilder().where(ComicDao.Properties.Comic_id.eq(comic_id)).unique();
        return  comic;
    }
    /**
     * 清空表中的数据
     */
    public void deleteAll(){
        comicDao.deleteAll();;
    }


    /** 查询 */
    public List<Comic> getAllData()
    {
        QueryBuilder<Comic> qb = comicDao.queryBuilder();
        return qb.list();
    }
}
