package com.example.newsmanagement.Service;

import com.example.newsmanagement.Model.News;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsService {
    ArrayList<News> news = new ArrayList<>();

    public ArrayList<News> getNews() {
        if (news.isEmpty())
            return null;

        return news;
    }

    public void addNews(News news1) {
        news.add(news1);
    }

    public boolean updateNews(String id, News news1) {
        for (int i = 0; i < news.size(); i++)
            if (news.get(i).getId().equals(id)) {
                news.set(i, news1);
                return true;
            }
        return false;
    }

    public boolean deleteNews(String id) {
        for (int i = 0; i < news.size(); i++)
            if (news.get(i).getId().equals(id)) {
                news.remove(i);
                return true;
            }
        return false;
    }

    public boolean publishNews(String id) {
        for (News n : news)
            if (n.getId().equals(id)) {
                n.setPublished(true);
                return true;
            }
        return false;
    }

    public ArrayList<News> getPublishedNews() {
        ArrayList<News> publishedNews = new ArrayList<>();

        for (News n : news)
            if (n.isPublished())
                publishedNews.add(n);

        if (publishedNews.isEmpty())
            return null;

        return publishedNews;
    }

    public ArrayList<News> getByCategory(String category) {
        ArrayList<News> categorizedNews = new ArrayList<>();

        for (News n : news)
            if (n.getCategory().equalsIgnoreCase(category))
                categorizedNews.add(n);

        if (categorizedNews.isEmpty())
            return null;

        return categorizedNews;
    }
}
