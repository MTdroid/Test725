package exercise.article;

import exercise.worker.Worker;
import exercise.worker.WorkerImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class WorkerImplTest {

    private Worker worker;
    @Mock
    private Library library;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        worker = new WorkerImpl(library);
    }
    @Test

    public void addNewArticleWithAllFields_Test1() {
        List<Article> testList = new ArrayList<>();
        testList.add(new Article("aaa", "aaa", "aaa", LocalDate.of(2, 2, 2)));
        worker.addNewArticles(testList);
        assertEquals(1, testList.size());
        assertEquals("aaa", testList.get(0).getTitle());

    }
    @Test
    public void addNewArticleWithoutAllFields_Test2() {
        List<Article> testList = new ArrayList<>();
        testList.add(new Article(null, null, null, null));
        List<Article> test = worker.prepareArticles(testList);
        assertEquals(0, test.size());
    }

    @Test
    public void addNewArticleWithoutTitle_Test3() {
        List<Article> testList = new ArrayList<>();
        testList.add(new Article(null, "aaa", "aaa", LocalDate.of(2, 2, 2)));
        List<Article> test = worker.prepareArticles(testList);
        assertEquals(0, test.size());
    }

    @Test
    public void addNewArticleWithoutContent_Test4() {
        List<Article> testList = new ArrayList<>();
        testList.add(new Article("aaa", null, "aaa", LocalDate.of(2, 2, 2)));
        List<Article> test = worker.prepareArticles(testList);
        assertEquals(0, test.size());
    }

    @Test
    public void addNewArticleWithoutAuthor_Test5() {
        List<Article> testList = new ArrayList<>();
        testList.add(new Article("aaa", "aaa", null, LocalDate.of(2, 2, 2)));
        List<Article> test = worker.prepareArticles(testList);
        assertEquals(0, test.size());
    }

    @Test
    public void addNewArticleWithoutDate_Test6() {
        List<Article> testList = new ArrayList<>();
        testList.add(new Article("aaa", "aaa", "aaa", null));
        List<Article> test = worker.prepareArticles(testList);
        assertEquals(test.get(0).getCreationDate(), LocalDate.now());
    }

    @Test
    public void DuplicateArticle_Test7() throws Exception {
        List<Article> testList = new ArrayList<>();
        testList.add(new Article("aaa", "aaa", "aaa", LocalDate.of(2, 2, 2)));
        testList.add(new Article("aaa", "aaa", "aaa", LocalDate.of(2, 2, 2)));
        List<Article> test = worker.prepareArticles(testList);
        assertEquals(1, test.size());
    }
}