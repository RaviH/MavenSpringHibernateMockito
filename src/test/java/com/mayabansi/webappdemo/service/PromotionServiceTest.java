package com.mayabansi.webappdemo.service;

import com.mayabansi.webappdemo.dao.BookDao;
import com.mayabansi.webappdemo.dao.CustomerDao;
import com.mayabansi.webappdemo.domain.Book;
import com.mayabansi.webappdemo.domain.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 19, 2010
 * Time: 1:07:42 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class PromotionServiceTest {

    @Mock
    BookDao mockedBookDao;

    @Mock
    CustomerDao customerDao;

    PromotionsService promotionsService;
    User user;

    CustomerSpecialsService realCustomerSpecialsService = new CustomerSpecialsService();
    WeeklySpecialsService realWeeklySpecialsService = new WeeklySpecialsService();

    static List<Book> top5BooksOnSaleList;
    static List<Book> booksOnSpecialPromotionList;

    /**
     * Test to show if we do not stub methods in a mocked object,
     * we will get the defaults:
     * <p/>
     * From mockito website:
     * <p/>
     * By default for all methods that return value, mock returns null, an empty collection or
     * appropriate primitive/primitive wrapper value (e.g: 0, false, ... for int/Integer, boolean/Boolean, ...).
     */
    @Test
    public void simple_Non_Stubbed_Mocks_Will_Cause_Default_Values_To_Be_Returned() {

        // Inject the mocked DAO
        promotionsService.setBookDao(mockedBookDao);

        // Business logic under test - Test passing of null user.
        final List<Book> promotionList = promotionsService.getSimplePromotions(null);

        // Regular JUnit Asserts
        assertNotNull(promotionList);
        assertTrue(promotionList.size() == 0); // <= NOTE: The size of the list is 0 instead of 1.
    }

    @Test
    public void show_How_Stubbing_Works() {

        /**
         * Set up phase.
         *
         * 1. Create Mocks,
         * 2. Stub only required methods
         * 3. Inject the mock objects.
         */

        // Stubbing
        when(mockedBookDao.getTop5BooksOnSale()).thenReturn(top5BooksOnSaleList);
        when(mockedBookDao.getSpecialPromotionsBasedOnUser(Matchers.<User>anyObject())).thenReturn(booksOnSpecialPromotionList);

        // Injecting mocked DAO
        promotionsService.setBookDao(mockedBookDao);

        // Calling business method
        final List<Book> promotionList = promotionsService.getSimplePromotions(user);

        // Verification of behavior
        verify(mockedBookDao, never()).getTop5BooksOnSale();
        verify(mockedBookDao, times(1)).getSpecialPromotionsBasedOnUser(user);

        assertNotNull(promotionList);
        assertTrue(promotionList.size() == 3); // Stubbed method being called because the size is 3. The real method returns 1 element list.
    }

    @Test
    public void show_Multiple_Stubbing_1() {
        Book book1 = new Book().setTitle("Book #1");
        Book book2 = new Book().setTitle("Book #2");

        when(mockedBookDao.get(1L)).thenReturn(book1);
        assertEquals("Book #1", mockedBookDao.get(1L).getTitle());

        when(mockedBookDao.get(1L)).thenReturn(book2);
        assertEquals("Book #2", mockedBookDao.get(1L).getTitle());
    }

    @Test
    public void show_Multiple_Stubbing_2() {
        Book book1 = new Book().setTitle("Book #1");
        Book book2 = new Book().setTitle("Book #2");

        when(mockedBookDao.get(1L))
                .thenReturn(book1)
                .thenReturn(book2)
                .thenReturn(book1);

        assertEquals("Book #1", mockedBookDao.get(1L).getTitle());
        assertEquals("Book #2", mockedBookDao.get(1L).getTitle());
        assertEquals("Book #1", mockedBookDao.get(1L).getTitle());
    }

    @Test
    public void show_Multiple_Stubbing_3() {
        Book book1 = new Book().setTitle("Book #1");
        Book book2 = new Book().setTitle("Book #2");

        when(mockedBookDao.get(1L))
                .thenReturn(book1, book2, book1);

        assertEquals("Book #1", mockedBookDao.get(1L).getTitle());
        assertEquals("Book #2", mockedBookDao.get(1L).getTitle());
        assertEquals("Book #1", mockedBookDao.get(1L).getTitle());
    }

    @Test
    public void last_Stubbing_Rules() {
        Book book1 = new Book().setTitle("Book #1");
        Book book2 = new Book().setTitle("Book #2");

        when(mockedBookDao.get(1L)).thenReturn(book1);

        when(mockedBookDao.get(1L)).thenReturn(book2);

        assertEquals("Book #2", mockedBookDao.get(1L).getTitle());
    }

    @Test(expected = RuntimeException.class)
    public void stub_Exceptions_1() {

        when(mockedBookDao.get(1L)).thenThrow(new RuntimeException());
        mockedBookDao.get(1L);
    }

    @Test(expected = IllegalStateException.class)
    public void stub_Exceptions_2() {

        doThrow(new IllegalStateException("Illegal")).when(mockedBookDao).remove(1L);
        mockedBookDao.remove(1L);
    }

    @Test
    public void show_Verification() {

        // Inject the mocked DAO
        promotionsService.setBookDao(mockedBookDao);

        // Business logic under test - Test passing of null user.
        final List<Book> promotionList = promotionsService.getSimplePromotions(null);

        // Notice the different types of verify below
        verify(mockedBookDao).getTop5BooksOnSale();
        verify(mockedBookDao, times(1)).getTop5BooksOnSale();

        verify(mockedBookDao, never()).getSpecialPromotionsBasedOnUser(null);

        verify(mockedBookDao, atLeastOnce()).getTop5BooksOnSale();
        verify(mockedBookDao, atLeast(1)).getTop5BooksOnSale();

        verify(mockedBookDao, atMost(1)).getTop5BooksOnSale();
    }


    @Test
    public void services_Are_Being_Mocked_Here() {
        //Mock
        CustomerSpecialsService customerSpecialsService = mock(CustomerSpecialsService.class);
        WeeklySpecialsService weeklySpecialsService = mock(WeeklySpecialsService.class);

        // Stubbing
        when(mockedBookDao.getTop5BooksOnSale()).thenReturn(top5BooksOnSaleList);
        when(mockedBookDao.getSpecialPromotionsBasedOnUser(null)).thenReturn(booksOnSpecialPromotionList);
        when(customerSpecialsService.getSpecials()).thenReturn(booksOnSpecialPromotionList);

        final PromotionsService promotionsService = new PromotionsService();
        promotionsService.setBookDao(mockedBookDao); // Look mocked DAO
        promotionsService.setCustomerSpecialsService(customerSpecialsService); // This is mocked too!
        promotionsService.setWeeklySpecialsService(weeklySpecialsService); // And this one too is mocked!

        // Passing in null user
        final List<Book> promotionList = promotionsService.getPromotions(null);

        verify(mockedBookDao).getTop5BooksOnSale();
        verify(mockedBookDao, never()).getSpecialPromotionsBasedOnUser(null);
        verify(customerSpecialsService, never()).applySpecials(anyList(), Matchers.<User>anyObject());
        verify(customerSpecialsService).getSpecials();

        assertNotNull(promotionList);
        assertTrue(promotionList.size() == 3);
    }


    @Test
    public void non_Null_User() {

        //Mock
        CustomerSpecialsService customerSpecialsService = mock(CustomerSpecialsService.class);
        WeeklySpecialsService weeklySpecialsService = new WeeklySpecialsService();

        // Stubbing
        when(mockedBookDao.getTop5BooksOnSale()).thenReturn(top5BooksOnSaleList);
        when(mockedBookDao.getSpecialPromotionsBasedOnUser(user)).thenReturn(booksOnSpecialPromotionList);
        //Look below is
        //when(customerSpecialsService.applySpecials(anyList(), Matchers.<User>anyObject())).thenReturn(booksOnSpecialPromotionList);


        final PromotionsService promotionsService = new PromotionsService();
        promotionsService.setBookDao(mockedBookDao); // Look mocked DAO
        promotionsService.setCustomerSpecialsService(customerSpecialsService); // This is mocked too!
        promotionsService.setWeeklySpecialsService(weeklySpecialsService); // And this one too is mocked!

        // Passing in null user
        final List<Book> promotionList = promotionsService.getPromotions(user);

        verify(mockedBookDao, never()).getTop5BooksOnSale();
        verify(mockedBookDao, times(1)).getSpecialPromotionsBasedOnUser(user);

        assertNotNull(promotionList);
        System.out.println("Size" + promotionList.size());
        assertTrue(promotionList.size() == 3);
    }

    @BeforeClass
    public static void beforeClass() {
        final Book[] bookArr = new Book[]{
                new Book("Beautiful life", 25.00D, 2005),
                new Book("Sarasota rocks", 15.00D, 2010),
                new Book("Music gives soul", 125.00D, 2006),
                new Book("Mocking using Mockito", 20.00D, 2004),
                new Book("Why we should?", 99.00D, 2009)
        };

        top5BooksOnSaleList = Arrays.asList(bookArr);

        final Book[] booksOnSpecialPromotionArr = new Book[]{
                new Book("Beautiful life", 20.00D, 2005),
                new Book("Sarasota rocks", 10.00D, 2010),
                new Book("Music gives soul", 100.00D, 2006)
        };

        booksOnSpecialPromotionList = Arrays.asList(booksOnSpecialPromotionArr);

    }

    @Before
    public void beforeEachMethod() {
        promotionsService = new PromotionsService();

        user = new User("ravi.hasija@gmail.com");
        user.setId(1L);
    }

}
