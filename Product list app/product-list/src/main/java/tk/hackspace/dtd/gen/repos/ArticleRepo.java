package tk.hackspace.dtd.gen.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tk.hackspace.dtd.gen.Article;
import tk.hackspace.dtd.gen.BMEcat;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by terron on 28.12.15.
 */
public interface ArticleRepo extends JpaRepository<Article, Long> {

}
