import org.junit.jupiter.api.Test;
import org.kainovk.PalindromeGame;
import org.kainovk.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeGameTest {

    @Test
    void testIsPalindrome() {
        PalindromeGame game = new PalindromeGame();

        assertTrue(game.isPalindrome("топот"));
        assertTrue(game.isPalindrome("а роза упала на лапу Азора"));
        assertTrue(game.isPalindrome("A man, a plan, a canal, Panama!"));
        assertFalse(game.isPalindrome("Java is fun"));
        assertTrue(game.isPalindrome("radar"));
        assertFalse(game.isPalindrome("hello"));
    }

    @Test
    void testProcessPhrase() {
        PalindromeGame game = new PalindromeGame();
        User user = new User("Alice");

        game.processPhrase("A man, a plan, a canal, Panama!", user);
        assertEquals(31, user.getPoints());

        game.processPhrase("Java is fun", user);
        assertEquals(31, user.getPoints());

        game.processPhrase("Was it a car or a cat I saw?", user);
        assertEquals(59, user.getPoints());
    }

    @Test
    void testLeaderboard() {
        PalindromeGame game = new PalindromeGame();
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        User user3 = new User("Charlie");

        game.processPhrase("A man, a plan, a canal, Panama!", user1);
        game.processPhrase("radar", user2);
        game.processPhrase("Java is fun", user3);

        assertEquals(2, game.getLeaderboard().size());
        assertEquals("Alice", game.getLeaderboard().get(0).getName());
        assertEquals("Bob", game.getLeaderboard().get(1).getName());
    }

    @Test
    void testMultipleUsers() {
        PalindromeGame game = new PalindromeGame();
        User user1 = new User("Alice");
        User user2 = new User("Bob");

        game.processPhrase("A man, a plan, a canal, Panama!", user1);
        game.processPhrase("radar", user2);

        assertEquals(31, user1.getPoints());
        assertEquals(5, user2.getPoints());

        game.processPhrase("No lemon, no melon", user1);
        game.processPhrase("Was it a car asdfg or a cat I saw?", user2);

        assertEquals(49, user1.getPoints());
        assertEquals(5, user2.getPoints());
    }
}
